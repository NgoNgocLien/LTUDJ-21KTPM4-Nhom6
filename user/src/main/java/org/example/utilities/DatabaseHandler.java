package org.example.utilities;

import org.example.models.*;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public ArrayList<SideChatInfo> getRecentChat(String username) throws SQLException {
        String sql = "(WITH RankedMessages AS (" +
                "    SELECT " +
                "        M.sender, " +
                "        M.to_user, " +
                "        M.to_group, " +
                "        M.content, " +
                "        M.sent_time, " +
                "        M.seen_time, " +
                "        ROW_NUMBER() OVER (PARTITION BY CASE WHEN M.sender = ? THEN M.to_user ELSE M.sender END " +
                "                          ORDER BY M.sent_time DESC) AS row_num " +
                "    FROM MESSAGE M " +
                "    INNER JOIN FRIEND F ON ((M.sender = F.username1 AND M.to_user = F.username2) OR (M.sender = F.username2 AND M.to_user = F.username1)) " +
                "    WHERE M.to_group IS NULL AND ((F.username1 = ? AND M.sent_time > F.user1_deleteChat) OR (F.username2 = ? AND M.sent_time > F.user2_deleteChat)) " +
                ") " +
                "SELECT " +
                "    R.sender, " +
                "    R.to_user, " +
                "    R.to_group, " +
                "    R.content, " +
                "    R.seen_time, " +
                "    U.fullname AS chat_name " +
                "FROM RankedMessages R " +
                "JOIN USER U ON (U.username != ? AND (R.to_user = U.username OR R.sender = U.username)) " +
                "WHERE R.row_num = 1 " +
                "ORDER BY R.sent_time DESC) " +
                "UNION " +
                "(WITH RankedMessages AS (" +
                "    SELECT " +
                "        M.sender, " +
                "        M.to_user, " +
                "        M.to_group, " +
                "        M.content, " +
                "        M.sent_time, " +
                "        M.seen_time, " +
                "        ROW_NUMBER() OVER (PARTITION BY M.to_group " +
                "                          ORDER BY M.sent_time DESC) AS row_num " +
                "    FROM MESSAGE M " +
                "    INNER JOIN GROUP_MEMBER GM ON (GM.username = ? AND GM.id_group = M.to_group) " +
                "    WHERE M.sent_time > GM.delete_history " +
                ")" +
                "SELECT " +
                "    R.sender, " +
                "    R.to_user, " +
                "    R.to_group, " +
                "    R.content, " +
                "    R.seen_time, " +
                "    G.group_name AS chat_name " +
                "FROM RankedMessages R " +
                "JOIN GROUP_CHAT G ON (G.id_group = R.to_group) " +
                "WHERE R.row_num = 1 " +
                "ORDER BY R.sent_time DESC);";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, username);
        stmt.setString(3, username);
        stmt.setString(4, username);
        stmt.setString(5, username);

        ResultSet rs = stmt.executeQuery();
        ArrayList<SideChatInfo> chats = new ArrayList<>();
        while (rs.next()) {
            String sender, to_user, to_group, content, chat_name;
            boolean seen;

            sender = rs.getString("sender");
            to_user = rs.getString("to_user");
            to_group = rs.getObject("to_group") != null ? String.valueOf(rs.getInt("to_group")) : "";
            content = rs.getString("content");

            String chatId;
            if (sender.equals(username)) {
                content = "You: " + content;
                chatId = to_user;
            }
            else {
                content = sender + ": " + content;
                chatId = sender;
            }

            chat_name = rs.getString("chat_name");

            if (to_user.equals(username)) // whether the user saw the latest message from sender
                seen = rs.getTimestamp("seen_time") != null;
            else
                seen = true;

            if (to_group.isEmpty()) {
                chats.add(new SideChatInfo(username, chatId, chat_name, content, seen, false));
            } else {
                chats.add(new SideChatInfo(username, to_group, chat_name, content, seen, true));
            }
        }
        return chats;
    }

    public ArrayList<SideChatInfo> getGroupChat(String username) throws SQLException {
        String sql = "WITH RankedMessages AS (  " +
                "    SELECT " +
                "        M.sender, " +
                "        M.to_user, " +
                "        M.to_group, " +
                "        M.content, " +
                "        M.sent_time, " +
                "        M.seen_time, " +
                "        ROW_NUMBER() OVER (PARTITION BY M.to_group " +
                "                          ORDER BY M.sent_time DESC) AS row_num " +
                "    FROM MESSAGE M " +
                "    INNER JOIN GROUP_MEMBER GM ON (GM.username = ? AND GM.id_group = M.to_group) " +
                "    WHERE M.sent_time > GM.delete_history " +
                ") " +
                "SELECT " +
                "    R.sender, " +
                "    R.to_user, " +
                "    R.to_group, " +
                "    R.content, " +
                "    R.seen_time, " +
                "    G.group_name AS chat_name " +
                "FROM RankedMessages R " +
                "JOIN GROUP_CHAT G ON (G.id_group = R.to_group) " +
                "WHERE R.row_num = 1 " +
                "ORDER BY R.sent_time DESC";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();
        ArrayList<SideChatInfo> chats = new ArrayList<>();
        while (rs.next()) {
            String sender, to_user, to_group, content, chat_name;
            boolean seen;

            sender = rs.getString("sender");
            to_user = rs.getString("to_user");
            to_group = rs.getObject("to_group") != null ? String.valueOf(rs.getInt("to_group")) : "";
            content = rs.getString("content");

            String chatId;
            if (sender.equals(username)) {
                content = "You: " + content;
                chatId = to_user;
            }
            else {
                content = sender + ": " + content;
                chatId = sender;
            }

            chat_name = rs.getString("chat_name");

            if (to_user.equals(username)) // whether the user saw the latest message from sender
                seen = rs.getTimestamp("seen_time") != null;
            else
                seen = true;

            if (to_group.isEmpty()) {
                chats.add(new SideChatInfo(username, chatId, chat_name, content, seen, false));
            } else {
                chats.add(new SideChatInfo(username, to_group, chat_name, content, seen, true));
            }
        }
        return chats;
    }

    public ArrayList<SideChatInfo> getBlockUsers(String username) throws SQLException {
        String sql = "";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();
        ArrayList<SideChatInfo> chats = new ArrayList<>();
        while (rs.next()) {
            String sender, to_user, to_group, content, chat_name;
            boolean seen;

            sender = rs.getString("sender");
            to_user = rs.getString("to_user");
            to_group = rs.getObject("to_group") != null ? String.valueOf(rs.getInt("to_group")) : "";
            content = rs.getString("content");

            String chatId;
            if (sender.equals(username)) {
                content = "You: " + content;
                chatId = to_user;
            }
            else {
                content = sender + ": " + content;
                chatId = sender;
            }

            chat_name = rs.getString("chat_name");

            if (to_user.equals(username)) // whether the user saw the latest message from sender
                seen = rs.getTimestamp("seen_time") != null;
            else
                seen = true;

            if (to_group.isEmpty()) {
                chats.add(new SideChatInfo(username, chatId, chat_name, content, seen, false));
            } else {
                chats.add(new SideChatInfo(username, to_group, chat_name, content, seen, true));
            }
        }
        return chats;
    }

    public ArrayList<Message> getMessages(SideChatInfo chatInfo) throws SQLException {
        if (chatInfo.getIsGroup()) {
            String sql = "(WITH SimilarMessage AS " +
                    "(SELECT M.*, ROW_NUMBER() OVER (PARTITION BY sent_time) AS row_num " +
                    "FROM MESSAGE M " +
                    "WHERE M.sender = ? AND M.to_group = ?)" +
                    "SELECT id_message, sender, to_user, to_group, content, sent_time, seen_time " +
                    "FROM SimilarMessage " +
                    "WHERE row_num = 1) " +
                    "UNION " +
                    "(SELECT * " +
                    "FROM MESSAGE " +
                    "WHERE to_user = ? AND to_group IS NOT NULL)" +
                    "ORDER BY sent_time";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, chatInfo.getMyUsername());
            stmt.setInt(2, Integer.parseInt(chatInfo.getChatId()));
            stmt.setString(3, chatInfo.getMyUsername());
            ArrayList<Message> messages = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id;
                String sender, to_user, content;
                int to_group;
                LocalDateTime sent_time, seen_time;

                id = rs.getInt("id_message");
                sender = rs.getString("sender");
                to_user = rs.getString("to_user"); // nonsense
                to_group = rs.getInt("to_group");
                content = rs.getString("content");
                sent_time = rs.getTimestamp("sent_time") != null ? rs.getTimestamp("sent_time").toLocalDateTime() : null;
                seen_time = rs.getTimestamp("seen_time") != null ? rs.getTimestamp("seen_time").toLocalDateTime() : null;

                messages.add(new Message(chatInfo.getMyUsername(), id, sender, to_user, to_group, content, sent_time, seen_time));
            }
            return messages;
        } else {
            String sql = "SELECT * " +
                    "FROM MESSAGE M " +
                    "WHERE ((M.sender = ? AND M.to_user = ?) OR (M.sender = ? AND M.to_user = ?)) AND M.to_group IS NULL " +
                    "ORDER BY sent_time";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, chatInfo.getMyUsername());
            stmt.setString(2, chatInfo.getChatId());
            stmt.setString(3, chatInfo.getChatId());
            stmt.setString(4, chatInfo.getMyUsername());

            ResultSet rs = stmt.executeQuery();
            ArrayList<Message> messages = new ArrayList<>();
            while (rs.next()) {
                int id;
                String sender, to_user, content;
                int to_group;
                LocalDateTime sent_time, seen_time;

                id = rs.getInt("id_message");
                sender = rs.getString("sender");
                to_user = rs.getString("to_user");
                to_group = rs.getObject("to_group") != null ? rs.getInt("to_group") : -1; // nonsense
                content = rs.getString("content");
                sent_time = rs.getTimestamp("sent_time") != null ? rs.getTimestamp("sent_time").toLocalDateTime() : null;
                seen_time = rs.getTimestamp("seen_time") != null ? rs.getTimestamp("seen_time").toLocalDateTime() : null;

                messages.add(new Message(chatInfo.getMyUsername(), id, sender, to_user, to_group, content, sent_time, seen_time));
            }
            return messages;
        }
    }
}
