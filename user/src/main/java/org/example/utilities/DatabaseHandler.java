package org.example.utilities;

import org.example.models.Friends;
import org.example.models.RecentChat;
import org.example.models.User;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DatabaseHandler {
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    // Database URL
    private final String DB_URL = "jdbc:mysql://localhost:3306/db_chat";
    // Database credentials
    private final String USER = "root";
    private final String PASS = "1234";
    private Connection conn = null;

    public DatabaseHandler() {
        try {
            Class.forName(JDBC_DRIVER); // Register JDBC driver
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS); // Open a connection
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void clearEnvironment() throws SQLException {
        System.out.println("Database closed.");
        if (conn != null) conn.close();
    }

    public User getUser(String user) throws SQLException {
        String sql = "SELECT * FROM USER WHERE username = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user);
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        } else {
//            String username, String password, String fullname, String address, LocalDate birthday,
//            int gender, String email, boolean isActive, LocalDate creationTime, boolean isLocked
            String username, password, fullname, address, email;
            int gender;
            LocalDate birthday, creationTime;
            boolean isActive, isLocked;

            username = rs.getString("username");
            password = rs.getString("password");
            fullname = rs.getString("fullname");
            address = rs.getString("address");
            email = rs.getString("email");

            birthday = rs.getDate("birthdate").toLocalDate();
            creationTime = rs.getDate("creation_time").toLocalDate();

            isActive = true;
            isLocked = rs.getBoolean("is_locked");

            gender = rs.getInt("gender");

            return new User(username, password, fullname, address, birthday, gender, email, isActive, creationTime, isLocked);
        }
    }

    public Friends getAllFriend(String username) throws SQLException {
        String sql = "SELECT * FROM FRIEND WHERE username = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();
        Friends friendList = new Friends(username);
        if (rs.next()) {
            rs = stmt.executeQuery();
            String friendUsername;
            LocalDate deleteChat;
            while (rs.next()) {
                friendUsername = rs.getString("username2");
                deleteChat = rs.getDate("user1_deleteChat").toLocalDate();
                friendList.addFriend(friendUsername, deleteChat);
            }
        }
        return friendList;
    }

    public RecentChat getRecentChat(String username) throws SQLException {
        String sql = "WITH RankedMessages AS ( " +
                "    SELECT " +
                "        M.sender, " +
                "        M.to_user, " +
                "        M.content, " +
                "        M.sent_time, " +
                "        M.seen_time, " +
                "        ROW_NUMBER() OVER (PARTITION BY CASE WHEN M.sender = ? THEN M.to_user ELSE M.sender END " +
                "                          ORDER BY M.sent_time DESC) AS row_num " +
                "    FROM MESSAGE M " +
                "    INNER JOIN FRIEND F ON ((M.sender = F.username1 AND M.to_user = F.username2) OR (M.sender = F.username2 AND M.to_user = F.username1)) " +
                "    WHERE (F.username1 = ? AND M.sent_time > F.user1_deleteChat) " +
                "       OR (F.username2 = ? AND M.sent_time > F.user2_deleteChat) " +
                ") " +
                "SELECT " +
                "    sender, " +
                "    to_user, " +
                "    content, " +
                "    seen_time " +
                "FROM RankedMessages " +
                "WHERE row_num = 1 " +
                "ORDER BY sent_time DESC";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, username);
        stmt.setString(3, username);

        ResultSet rs = stmt.executeQuery();
        RecentChat chats = new RecentChat(username);
        while (rs.next()) {
            String sender, to_user, content;
            boolean seen;
            sender = rs.getString("sender");
            to_user = rs.getString("to_user");
            content = rs.getString("content");
            seen = rs.getTimestamp("seen_time") != null;
            if (sender.equals(username))
                chats.addChat(to_user, "You: " + content, seen);
            else
                chats.addChat(sender, content, seen);
        }
        return chats;
    }
}
