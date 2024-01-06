package org.example.utilities;

import org.example.models.ChatInfo;
import org.example.models.Message;
import org.example.models.Profile;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DatabaseHandler {
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/db_chat?allowPublicKeyRetrieval=true&useSSL=false";
    private final String USER = "root";
    private final String PASS = "admin";
    private Connection conn = null;

    public static String loginedUsername;

    public DatabaseHandler() {
        try {
            Class.forName(JDBC_DRIVER); // Register JDBC driver
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS); // Open a connection
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }



    public void reportSpamMessage(int idMessage) throws SQLException {
        String sql = "SELECT * FROM SPAM WHERE id_message = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idMessage);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            System.out.println("Spam reported1.");
            String sql2 = "UPDATE SPAM SET report_time = ? WHERE id_message = ?";
            stmt = conn.prepareStatement(sql2);
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(2, idMessage);
            stmt.executeUpdate();
        } else {
            System.out.println("Spam reported2.");
            String sql2 = "INSERT INTO SPAM (id_message, report_time) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql2);
            stmt.setInt(1, idMessage);
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.executeUpdate();
        }
    }

    public void closeConnection() throws SQLException {
        System.out.println("Database closed.");
        if (conn != null) conn.close();
    }

    public String getLoginedUsername() {
        return loginedUsername;
    }

    public void setLoginedUsername(String username) {
        loginedUsername = username;
    }

    public boolean checkValidAccountLogin(String username) throws SQLException {
        String sql = "SELECT username FROM USER WHERE username = ? AND is_locked = 0";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            rs.close();
            stmt.close();
            return true;
        } else {
            rs.close();
            stmt.close();
            return false;
        }
    }
    public Profile getProfilebyUsername(String username) throws SQLException {
        String sql = "SELECT username, fullname, address, birthdate, gender, email, creation_time, password FROM USER WHERE username = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        Profile profile = null;
        if (rs.next()) {
            String fullname = rs.getString("fullname");
            String address = rs.getString("address");
            LocalDate birthdate = rs.getDate("birthdate").toLocalDate();
            int gender = rs.getInt("gender");
            String email = rs.getString("email");
            LocalDate dateJoined = rs.getDate("creation_time").toLocalDate();
            String password = rs.getString("password");
            profile = new Profile(dateJoined, fullname, username, gender, birthdate, email, address, password);
        }
        return profile;
    }



    public Profile getProfilebyEmail(String email) throws SQLException {
        String sql = "SELECT username, fullname, address, birthdate, gender, email, creation_time, password FROM USER WHERE email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        Profile profile = null;
        if (rs.next()) {
            String fullname = rs.getString("fullname");
            String address = rs.getString("address");
            LocalDate birthdate = rs.getDate("birthdate").toLocalDate();
            int gender = rs.getInt("gender");
            String username = rs.getString("username");
            LocalDate dateJoined = rs.getDate("creation_time").toLocalDate();
            String password = rs.getString("password");
            profile = new Profile(dateJoined, fullname, username, gender, birthdate, email, address, password);
        }
        return profile;
    }

    public void saveRegisteredAccount(String username, String password, String fullname, String address, LocalDate birthdate, boolean gender, String email) throws SQLException {
        String sql = "INSERT INTO USER (username, password, fullname, address, birthdate, gender, email, creation_time, is_locked) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);
        stmt.setString(3, fullname);
        stmt.setString(4, address);
        stmt.setDate(5, Date.valueOf(birthdate));
        stmt.setInt(6, gender ? 1 : 0);
        stmt.setString(7, email);
        stmt.setDate(8, Date.valueOf(LocalDate.now()));
        stmt.setBoolean(9, false);

        stmt.executeUpdate();
    }

    public ArrayList<ChatInfo> searchChatFromAll(String myUsername, String key) throws SQLException {
        String sql = "SELECT * FROM (" +
                "  SELECT MESSAGE.*, " +
                "         ROW_NUMBER() OVER (PARTITION BY content, sender ORDER BY id_message) AS row_num " +
                "  FROM MESSAGE " +
                "  WHERE (sender = ? OR to_user = ?) AND content LIKE ?" +
                ") AS subquery " +
                "WHERE row_num = 1";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, myUsername);
        stmt.setString(2, myUsername);
        stmt.setString(3, "%" + key + "%");

        ResultSet rs = stmt.executeQuery();
        ArrayList<ChatInfo> chats = new ArrayList<>();
        while (rs.next()) {
            if (rs.getObject("to_group") != null) {
                if(rs.getString("sender").equals(myUsername)){
                    sql = "SELECT * FROM GROUP_CHAT WHERE id_group = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, rs.getInt("to_group"));
                    ResultSet rs2 = stmt.executeQuery();
                    if (rs2.next())
                        chats.add(new ChatInfo(rs2.getString("group_name"), rs.getString("sender"), "You: " + rs.getString("content"), true));
                }
                else{
                    sql = "SELECT * FROM GROUP_CHAT WHERE id_group = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, rs.getInt("to_group"));
                    ResultSet rs2 = stmt.executeQuery();
                    if (rs2.next())
                        chats.add(new ChatInfo(rs2.getString("group_name"), rs.getString("sender"), rs.getString("sender") + ":" + rs.getString("content"), true));
                }
            } else {
                if(rs.getString("sender").equals(myUsername)){
                    sql = "SELECT * FROM USER WHERE username = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, rs.getString("to_user"));
                    ResultSet rs2 = stmt.executeQuery();
                    if (rs2.next())
                        chats.add(new ChatInfo(rs2.getString("fullname"), rs.getString("to_user"), "You: " + rs.getString("content"), true));
                }
                else{
                    sql = "SELECT * FROM USER WHERE username = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, rs.getString("sender"));
                    ResultSet rs2 = stmt.executeQuery();
                    if (rs2.next())
                        chats.add(new ChatInfo(rs2.getString("fullname"), rs.getString("sender"), rs.getString("sender") + ": " + rs.getString("content"), true));

                }
            }
        }
        rs.close();
        stmt.close();
        return chats;
    }

    public void changeGroupName(int idGroup, String newName){
        String sql = "UPDATE GROUP_CHAT SET group_name = ? WHERE id_group = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, newName);
            stmt.setInt(2, idGroup);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
        }
    }

    public ArrayList<ChatInfo> getAllChats(String myUsername) throws SQLException {
        String sql = "WITH Messages AS ( " +
                "(WITH RankedMessages AS (   " +
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
                "    WHERE M.to_group IS NULL AND ((F.username1 = ? AND M.sent_time > F.user1_deleteChat) " +
                "          OR (F.username2 = ? AND M.sent_time > F.user2_deleteChat)) " +
                ") " +
                " " +
                "SELECT " +
                "    R.sender, " +
                "    R.to_user, " +
                "    R.to_group, " +
                "    R.content, " +
                "    R.seen_time, " +
                "    R.sent_time, " +
                "    U.fullname AS chat_name " +
                "FROM RankedMessages R " +
                "JOIN USER U ON (U.username != ? AND (R.to_user = U.username OR R.sender = U.username)) " +
                "WHERE R.row_num = 1) " +
                " " +
                "UNION " +
                " " +
                "(WITH RankedMessages AS (   " +
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
                " " +
                "SELECT " +
                "    R.sender, " +
                "    R.to_user, " +
                "    R.to_group, " +
                "    R.content, " +
                "    R.seen_time, " +
                "    R.sent_time, " +
                "    G.group_name AS chat_name " +
                "FROM RankedMessages R " +
                "JOIN GROUP_CHAT G ON (G.id_group = R.to_group)  " +
                "WHERE R.row_num = 1) " +
                ") " +
                " " +
                "SELECT *  " +
                "FROM Messages " +
                "ORDER BY sent_time DESC";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, myUsername);
        stmt.setString(2, myUsername);
        stmt.setString(3, myUsername);
        stmt.setString(4, myUsername);
        stmt.setString(5, myUsername);

        ResultSet rs = stmt.executeQuery();
        ArrayList<ChatInfo> chats = new ArrayList<>();
        while (rs.next()) {
            String chatName = rs.getString("chat_name");
            String message = rs.getString("content");
            String sender = rs.getString("sender");
            String msg;
            boolean isMyMessage = sender.equals(myUsername);
            if (isMyMessage) msg = "You: " + message;
            else msg = sender + ": " + message;
            String toUser = rs.getString("to_user");
            int toGroup = rs.getObject("to_group") != null ? rs.getInt("to_group") : -1;
            boolean seen;
            if (isMyMessage) seen = true;
            else seen = rs.getObject("seen_time") != null;
            if (toGroup != -1) {
                // get group quantity
                int quantity = getGroupMemberQuantity(toGroup);
                chats.add(new ChatInfo(chatName, toGroup, quantity, msg, !seen));
            } else {
                boolean isOnline = getIsOnline(toUser);
                if (isMyMessage)
                    chats.add(new ChatInfo(chatName, toUser, msg, isOnline, !seen));
                else
                    chats.add(new ChatInfo(chatName, sender, msg, isOnline, !seen));
            }
        }
        rs.close();
        stmt.close();
        return chats;
    }

    public int getGroupMemberQuantity(int idGroup) throws SQLException {
        String sql = "SELECT COUNT(M.username) AS quantity " +
                "FROM GROUP_MEMBER M  " +
                "WHERE M.id_group = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idGroup);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int quantity = rs.getInt("quantity");
            rs.close();
            stmt.close();
            return quantity;
        } else {
            rs.close();
            stmt.close();
            return -1;
        }

    }

    public void addFriend(String myUsername, String friendUsername) throws SQLException {
        String sql = "INSERT INTO FRIEND (username1, username2, accepted, user1_deleteChat, user2_deleteChat) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, myUsername);
        stmt.setString(2, friendUsername);
        stmt.setInt(3, 0);
        stmt.setDate(4, Date.valueOf(LocalDate.now()));
        stmt.setDate(5, Date.valueOf(LocalDate.now()));
        stmt.executeUpdate();
        stmt.close();
    }

    public void blockFriend(String myUsername, String friendUsername) throws SQLException {
        String sql = "INSERT INTO BLOCK (username, block) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, myUsername);
        stmt.setString(2, friendUsername);
        stmt.executeUpdate();
        stmt.close();
    }

    public void unFriend(String myUsername, String friendUsername) throws SQLException {
        String sql = "DELETE FROM FRIEND WHERE (username1 = ? AND username2 = ?) OR (username1 = ? AND username2 = ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, myUsername);
        stmt.setString(2, friendUsername);
        stmt.setString(3, friendUsername);
        stmt.setString(4, myUsername);
        stmt.executeUpdate();
        stmt.close();
    }

    public void acceptFriend(String myUsername, String friendUsername) throws SQLException {
        String sql = "UPDATE FRIEND SET accepted = 1 WHERE username1 = ? AND username2 = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, friendUsername);
        stmt.setString(2, myUsername);
        stmt.executeUpdate();
        stmt.close();
    }

    public boolean getIsOnline(String username) throws SQLException {
        String sql = "SELECT H.logout_time " +
                "FROM HISTORY_LOGIN H " +
                "WHERE H.username = ? AND H.logout_time IS NULL";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            rs.close();
            stmt.close();
            return true;
        } else {
            rs.close();
            stmt.close();
            return false;
        }
    }

    public ArrayList<Message> searchMessage(ChatInfo info, String keyword) {
//        ArrayList<Message> messages = new ArrayList<>();
//        String sql = "SELECT M.id_message, M.sender, M.to_user, M.to_group, M.content, M.sent_time " +
//                "FROM MESSAGE M " +
//                "WHERE ((M.sender = ? AND M.to_user = ?) OR (M.sender = ? AND M.to_user = ?)) AND M.to_group IS NULL AND M.content LIKE ? " +
//                "ORDER BY M.sent_time";
//        PreparedStatement stmt = null;
//        try {
//            stmt = conn.prepareStatement(sql);
//            stmt.setString(1, info.getUsername());
//            stmt.setString(2, loginedUsername);
//            stmt.setString(3, loginedUsername);
//            stmt.setString(4, info.getUsername());
//            stmt.setString(5, "%" + keyword + "%");
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                int id;
//                String sender, to_user, content;
//                Timestamp sent_time;
//
//                id = rs.getInt("id_message");
//                sender = rs.getString("sender");
//                to_user = rs.getString("to_user");
//                content = rs.getString("content");
//                sent_time = rs.getTimestamp("sent_time");
//
//                messages.add(new Message(id, sender, to_user, -1, content, sent_time.toLocalDateTime(), sender.equals(loginedUsername)));
//            }
//            rs.close();
//            stmt.close();
//            return messages;
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        return null;
    }

    public ArrayList<ChatInfo> getAllFriends(String myUsername) throws SQLException {
        String sql = "WITH RankedLogin AS (" +
                "SELECT " +
                "   U.username, " +
                "   U.fullname, " +
                "   H.login_time, " +
                "   H.logout_time, " +
                "   ROW_NUMBER() OVER (PARTITION BY U.username ORDER BY H.login_time DESC) AS rnk " +
                "FROM FRIEND F " +
                "INNER JOIN USER U ON U.username = F.username2 " +
                "INNER JOIN HISTORY_LOGIN H ON H.username = U.username " +
                "WHERE F.username1 = ? AND F.accepted = 1 " +
                "UNION " +
                "SELECT " +
                "   U.username, " +
                "   U.fullname, " +
                "   H.login_time, " +
                "   H.logout_time, " +
                "   ROW_NUMBER() OVER (PARTITION BY U.username ORDER BY H.login_time DESC) AS rnk " +
                "FROM FRIEND F " +
                "INNER JOIN USER U ON U.username = F.username1 " +
                "INNER JOIN HISTORY_LOGIN H ON H.username = U.username " +
                "WHERE F.username2 = ? AND F.accepted = 1" +
                ") " +
                "SELECT username, fullname, login_time, logout_time " +
                "FROM RankedLogin " +
                "WHERE rnk = 1 " +
                "ORDER BY login_time DESC, logout_time ASC;";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, myUsername);
        stmt.setString(2, myUsername);

        ResultSet rs = stmt.executeQuery();
        ArrayList<ChatInfo> friends = new ArrayList<>();
        while (rs.next()) {
            String friendUsername = rs.getString("username");
            String friendFullname = rs.getString("fullname");
//            Timestamp loginTime = rs.getTimestamp("login_time");
            Timestamp logoutTime = rs.getTimestamp("logout_time");
            if (logoutTime == null) {
                friends.add(new ChatInfo(friendFullname, friendUsername, friendUsername, true, false));
            } else {
                friends.add(new ChatInfo(friendFullname, friendUsername, friendUsername, false, false));
            }
        }
        rs.close();
        stmt.close();
        return friends;
    }

    public ArrayList<ChatInfo> getAllBlocks(String myUsername) throws SQLException {
        String sql = "SELECT B.block, U.fullname " +
                "FROM BLOCK B " +
                "LEFT JOIN USER U ON U.username = B.block " +
                "WHERE B.username = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, myUsername);
        ResultSet rs = stmt.executeQuery();
        ArrayList<ChatInfo> blocks = new ArrayList<>();
        while (rs.next()) {
            String blockUsername = rs.getString("block");
            String blockFullname = rs.getString("fullname");
            blocks.add(new ChatInfo(blockFullname, blockUsername, blockUsername, false));
        }
        rs.close();
        stmt.close();
        return blocks;
    }

    public ArrayList<ChatInfo> getAllRequests(String myUsername) throws SQLException {
        String sql = "SELECT U.username, U.fullname " +
                "FROM FRIEND F " +
                "INNER JOIN USER U ON U.username = F.username1 " +
                "WHERE F.username2 = ? AND F.accepted = 0";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, myUsername);
        ResultSet rs = stmt.executeQuery();
        ArrayList<ChatInfo> requests = new ArrayList<>();
        while (rs.next()) {
            String requestUsername = rs.getString("username");
            String requestFullname = rs.getString("fullname");
            requests.add(new ChatInfo(requestFullname, requestUsername, requestUsername, false));
        }
        rs.close();
        stmt.close();
        return requests;
    }

    public ArrayList<ChatInfo> getAllGroups(String myUsername) throws SQLException {
        String sql = "SELECT G.group_name, G.id_group, COUNT(M.username) AS quantity " +
                "FROM GROUP_MEMBER M " +
                "LEFT JOIN GROUP_CHAT G ON G.id_group = M.id_group " +
                "WHERE G.id_group IN (SELECT M2.id_group FROM GROUP_MEMBER M2 WHERE M2.username = ?) " +
                "GROUP BY G.group_name, G.id_group";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, myUsername);
        ResultSet rs = stmt.executeQuery();
        ArrayList<ChatInfo> groups = new ArrayList<>();
        while (rs.next()) {
            int idGroup = rs.getInt("id_group");
            String groupName = rs.getString("group_name");
            int numberOfMembers = rs.getInt("quantity");
            String subTitle;
            if (numberOfMembers == 1) subTitle = numberOfMembers + " member";
            else subTitle = numberOfMembers + " members";

            groups.add(new ChatInfo(groupName, idGroup, numberOfMembers, subTitle, false));
        }
        rs.close();
        stmt.close();
        return groups;
    }

    public ArrayList<ChatInfo> getAllSuggests(String myUsername) {
        String sql = "WITH Suggested AS ( " +
                "SELECT U.username, U.fullname " +
                "FROM USER U " +
                "INNER JOIN FRIEND F ON F.username1 = U.username " +
                "WHERE F.username1 != ? AND F.username2 IN  (SELECT U1.username " +
                "FROM USER U1 " +
                "INNER JOIN FRIEND F1 ON F1.username1 = U1.username " +
                "WHERE F1.username2 = ?  " +
                "UNION " +
                "SELECT U2.username " +
                "FROM USER U2 " +
                "INNER JOIN FRIEND F2 ON F2.username2 = U2.username " +
                "WHERE F2.username1 = ?) " +
                "UNION ALL " +
                "SELECT U.username, U.fullname " +
                "FROM USER U " +
                "INNER JOIN FRIEND F ON F.username2 = U.username " +
                "WHERE F.username2 != ? AND F.username1 IN  (SELECT U1.username " +
                "FROM USER U1 " +
                "INNER JOIN FRIEND F1 ON F1.username1 = U1.username " +
                "WHERE F1.username2 = ?  " +
                "UNION " +
                "SELECT U2.username " +
                "FROM USER U2 " +
                "INNER JOIN FRIEND F2 ON F2.username2 = U2.username " +
                "WHERE F2.username1 = ?) " +
                "UNION ALL " +
                "SELECT U.username, U.fullname " +
                "FROM USER U " +
                "INNER JOIN GROUP_MEMBER M ON M.username = U.username " +
                "WHERE U.username != ? AND M.id_group IN (SELECT M1.id_group FROM GROUP_MEMBER M1 WHERE M1.username = ?) " +
                "AND U.username NOT IN  (SELECT U.username " +
                "FROM USER U " +
                "INNER JOIN FRIEND F ON F.username1 = U.username " +
                "WHERE F.username2 = ? " +
                "UNION " +
                "SELECT U.username " +
                "FROM USER U " +
                "INNER JOIN FRIEND F ON F.username2 = U.username " +
                "WHERE F.username1 = ?) " +
                ") " +
                "SELECT S.username, S.fullname, COUNT(*) AS priority " +
                "FROM Suggested S " +
                "WHERE S.username NOT IN (SELECT B.block FROM BLOCK B WHERE B.username = ? " +
                "UNION  " +
                "SELECT B.username FROM BLOCK B WHERE B.block = ?) " +
                "GROUP BY username, fullname " +
                "ORDER BY priority DESC ";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, myUsername);
            stmt.setString(2, myUsername);
            stmt.setString(3, myUsername);
            stmt.setString(4, myUsername);
            stmt.setString(5, myUsername);
            stmt.setString(6, myUsername);
            stmt.setString(7, myUsername);
            stmt.setString(8, myUsername);
            stmt.setString(9, myUsername);
            stmt.setString(10, myUsername);
            stmt.setString(11, myUsername);
            stmt.setString(12, myUsername);

            ResultSet rs = stmt.executeQuery();
            ArrayList<ChatInfo> suggests = new ArrayList<>();
            while (rs.next()) {
                String suggestUsername = rs.getString("username");
                String suggestFullname = rs.getString("fullname");
                suggests.add(new ChatInfo(suggestFullname, suggestUsername, suggestUsername, false));
            }
            rs.close();
            stmt.close();
            return suggests;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ArrayList<Message> getFriendMessages(String myUsername, String friendUsername, LocalDate lastMessage) {
        String sql = "SELECT M.id_message, M.sender, M.to_user, M.content, M.sent_time " +
                "FROM MESSAGE M " +
                "WHERE ((M.sender = ? AND M.to_user = ?) OR (M.sender = ? AND M.to_user = ?)) AND M.to_group IS NULL AND M.sent_time > ? " +
                "ORDER BY M.sent_time";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, myUsername);
            stmt.setString(2, friendUsername);
            stmt.setString(3, friendUsername);
            stmt.setString(4, myUsername);
            stmt.setDate(5, Date.valueOf(lastMessage));
            ResultSet rs = stmt.executeQuery();
            ArrayList<Message> messages = new ArrayList<>();
            while (rs.next()) {
                int id;
                String sender, to_user, content;
                Timestamp sent_time;

                id = rs.getInt("id_message");
                sender = rs.getString("sender");
                to_user = rs.getString("to_user");
                content = rs.getString("content");
                sent_time = rs.getTimestamp("sent_time");

                messages.add(new Message(id, sender, to_user, -1, content, sent_time.toLocalDateTime(), sender.equals(myUsername)));
            }
            rs.close();
            stmt.close();
            return messages;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ArrayList<Message> getFriendMessagesWithKeyWord(String myUsername, String friendUsername, String keyword) {
        String sql = "SELECT M.id_message, M.sender, M.to_user, M.content, M.sent_time " +
                "FROM MESSAGE M " +
                "INNER JOIN FRIEND F ON (F.username1 = M.sender AND F.username2 = M.to_user) OR (F.username2 = M.sender AND F.username1 = M.to_user) " +
                "WHERE ((M.sender = ? AND M.to_user = ?) OR (M.sender = ? AND M.to_user = ?)) " +
                "   AND M.to_group IS NULL " +
                "   AND ((F.username1 = ? AND M.sent_time > F.user1_deleteChat) OR (F.username2 = ? AND M.sent_time > F.user2_deleteChat)) " +
                "   AND M.content LIKE ? " +
                "ORDER BY M.sent_time DESC " +
                "LIMIT 1";

        PreparedStatement stmt = null;
        Timestamp sentTime = null;
        Message searchMessage = null;
        ArrayList<Message> messages = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, myUsername);
            stmt.setString(2, friendUsername);
            stmt.setString(3, friendUsername);
            stmt.setString(4, myUsername);
            stmt.setString(5, myUsername);
            stmt.setString(6, myUsername);
            stmt.setString(7, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id;
                String sender, to_user, content;
                Timestamp sent_time;

                id = rs.getInt("id_message");
                sender = rs.getString("sender");
                to_user = rs.getString("to_user");
                content = rs.getString("content");
                sent_time = rs.getTimestamp("sent_time");
                sentTime = sent_time;

                searchMessage = new Message(id, sender, to_user, -1, content, sent_time.toLocalDateTime(), sender.equals(myUsername));
                System.out.println(searchMessage.getContent());

                String sql2 = "(SELECT M.id_message, M.sender, M.to_user, M.content, M.sent_time " +
                        "FROM MESSAGE M " +
                        "INNER JOIN FRIEND F ON (F.username1 = M.sender AND F.username2 = M.to_user) OR (F.username2 = M.sender AND F.username1 = M.to_user) " +
                        "WHERE ((M.sender = ? AND M.to_user = ?) OR (M.sender = ? AND M.to_user = ?)) " +
                        "   AND M.to_group IS NULL " +
                        "   AND ((F.username1 = ? AND M.sent_time > F.user1_deleteChat) OR (F.username2 = ? AND M.sent_time > F.user2_deleteChat)) " +
                        "   AND sent_time < ? " +
                        "ORDER BY M.sent_time " +
                        "LIMIT 10) ";
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                stmt2.setString(1, myUsername);
                stmt2.setString(2, friendUsername);
                stmt2.setString(3, friendUsername);
                stmt2.setString(4, myUsername);
                stmt2.setString(5, myUsername);
                stmt2.setString(6, myUsername);
                stmt2.setTimestamp(7, sentTime);

                ResultSet rs2 = stmt2.executeQuery();
                while (rs2.next()) {
                    id = rs2.getInt("id_message");
                    sender = rs2.getString("sender");
                    to_user = rs2.getString("to_user");
                    content = rs2.getString("content");
                    sent_time = rs2.getTimestamp("sent_time");

                    messages.add(new Message(id, sender, to_user, -1, content, sent_time.toLocalDateTime(), sender.equals(myUsername)));
                }
                messages.add(searchMessage);
                rs2.close();
                stmt2.close();
            }
            rs.close();
            stmt.close();
            return messages;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return messages;
    }
    public ArrayList<Message> getGroupMessages(String myUsername, int idGroup) {
//        SELECT delete_history
//        FROM GROUP_MEMBER
//        WHERE username = 'hlong' and id_group = 5;
        ArrayList<Message> messages = new ArrayList<>();
        String tempSql = "SELECT delete_history " +
                "FROM GROUP_MEMBER " +
                "WHERE username = ? and id_group = ?";
        PreparedStatement tempStmt = null;
        try {
            tempStmt = conn.prepareStatement(tempSql);
            tempStmt.setString(1, myUsername);
            tempStmt.setInt(2, idGroup);
            ResultSet tempRs = tempStmt.executeQuery();
            if (tempRs.next()) {
                Timestamp deleteHistory = tempRs.getTimestamp("delete_history");
                tempRs.close();
                tempStmt.close();
                if (deleteHistory != null) {
                    String sql = "WITH GroupMessage AS ( " +
                            "SELECT M.sender, M.sent_time, M.content, M.id_message, " +
                            "ROW_NUMBER() OVER (PARTITION BY M.sent_time) AS rnk " +
                            "FROM MESSAGE M " +
                            "WHERE M.to_group = ?) " +
                            "SELECT sender, sent_time, content, id_message " +
                            "FROM GroupMessage " +
                            "WHERE rnk = 1 AND (sent_time > ?)";
                    PreparedStatement stmt = null;
                    try {
                        stmt = conn.prepareStatement(sql);
                        stmt.setInt(1, idGroup);
                        stmt.setTimestamp(2, deleteHistory);
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                            int id;
                            String sender, content;
                            Timestamp sent_time;

                            id = rs.getInt("id_message");
                            sender = rs.getString("sender");
                            content = rs.getString("content");
                            sent_time = rs.getTimestamp("sent_time");

                            messages.add(new Message(id, sender, "", idGroup, content, sent_time.toLocalDateTime(), sender.equals(myUsername)));
                        }
                        rs.close();
                        stmt.close();
                        return messages;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return messages;
    }

    public ArrayList<Message> getGroupMessagesWithKeyWord(String myUsername, int idGroup, String keyword) {
//        SELECT delete_history
//        FROM GROUP_MEMBER
//        WHERE username = 'hlong' and id_group = 5;
        ArrayList<Message> messages = new ArrayList<>();
        Message searchMessage = null;
        Timestamp sentTime = null;
        String tempSql = "SELECT delete_history " +
                "FROM GROUP_MEMBER " +
                "WHERE username = ? and id_group = ?";
        PreparedStatement tempStmt = null;
        try {
            tempStmt = conn.prepareStatement(tempSql);
            tempStmt.setString(1, myUsername);
            tempStmt.setInt(2, idGroup);
            ResultSet tempRs = tempStmt.executeQuery();
            if (tempRs.next()) {
                Timestamp deleteHistory = tempRs.getTimestamp("delete_history");
                tempRs.close();
                tempStmt.close();
                if (deleteHistory != null) {
                    String sql = "WITH GroupMessage AS ( " +
                            "SELECT M.sender, M.sent_time, M.content, M.id_message, " +
                            "ROW_NUMBER() OVER (PARTITION BY M.sent_time) AS rnk " +
                            "FROM MESSAGE M " +
                            "WHERE M.to_group = ?) " +
                            "SELECT sender, sent_time, content, id_message " +
                            "FROM GroupMessage " +
                            "WHERE rnk = 1 AND (sent_time > ?) AND content LIKE ?";
                    PreparedStatement stmt = null;
                    try {
                        stmt = conn.prepareStatement(sql);
                        stmt.setInt(1, idGroup);
                        stmt.setTimestamp(2, deleteHistory);
                        stmt.setString(3, "%" + keyword + "%");
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            int id;
                            String sender, content;
                            Timestamp sent_time;

                            id = rs.getInt("id_message");
                            sender = rs.getString("sender");
                            content = rs.getString("content");
                            sent_time = rs.getTimestamp("sent_time");
                            sentTime = sent_time;

                            searchMessage = new Message(id, sender, "", idGroup, content, sent_time.toLocalDateTime(), sender.equals(myUsername));

                            String sql2 = "WITH GroupMessage AS ( " +
                                    "SELECT M.sender, M.sent_time, M.content, M.id_message, " +
                                    "ROW_NUMBER() OVER (PARTITION BY M.sent_time) AS rnk " +
                                    "FROM MESSAGE M " +
                                    "WHERE M.to_group = ?) " +
                                    "SELECT sender, sent_time, content, id_message " +
                                    "FROM GroupMessage " +
                                    "WHERE rnk = 1 AND (sent_time > ?) AND sent_time < ? " +
                                    "ORDER BY sent_time " +
                                    "LIMIT 10";
                            PreparedStatement stmt2 = null;
                            try {
                                stmt2 = conn.prepareStatement(sql2);
                                stmt2.setInt(1, idGroup);
                                stmt2.setTimestamp(2, deleteHistory);
                                stmt2.setTimestamp(3, sentTime);
                                ResultSet rs2 = stmt2.executeQuery();
                                while (rs2.next()) {
                                    id = rs2.getInt("id_message");
                                    sender = rs2.getString("sender");
                                    content = rs2.getString("content");
                                    sent_time = rs2.getTimestamp("sent_time");

                                    messages.add(new Message(id, sender, "", idGroup, content, sent_time.toLocalDateTime(), sender.equals(myUsername)));
                                }
                                messages.add(searchMessage);
                                rs2.close();
                                stmt2.close();
                                return messages;
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                        rs.close();
                        stmt.close();
                        return messages;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return messages;
    }

    public ArrayList<String> getAllMembers(int idGroup) {
        String sql = "SELECT M.username " +
                "FROM GROUP_MEMBER M " +
                "WHERE M.id_group = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idGroup);
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> members = new ArrayList<>();
            while (rs.next()) {
                String member = rs.getString("username");
                members.add(member);
            }
            rs.close();
            stmt.close();
            return members;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void addMyMessage(Message message) {
        String sql = "INSERT INTO MESSAGE (sender, to_user, to_group, content, sent_time, seen_time) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        try {
            if (message.getToGroup() == -1) {
                // add message to friend
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, message.getSender());
                stmt.setString(2, message.getToUser());
                stmt.setNull(3, Types.INTEGER);
                stmt.setString(4, message.getContent());
                stmt.setTimestamp(5, Timestamp.valueOf(message.getSentTime()));
                stmt.setTimestamp(6, null);
                stmt.executeUpdate();
                stmt.close();
            } else {
                // get all member
                // for each member, add message to database
                ArrayList<String> members = getAllMembers(message.getToGroup());
                for (String member : members) {
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, message.getSender());
                    stmt.setString(2, member);
                    stmt.setInt(3, message.getToGroup());
                    stmt.setString(4, message.getContent());
                    stmt.setTimestamp(5, Timestamp.valueOf(message.getSentTime()));
                    stmt.setTimestamp(6, null);
                    stmt.executeUpdate();
                    stmt.close();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateMyProfile(Profile newProfile, String currentPass) throws SQLException {
        String sql = "UPDATE USER SET password = ?, fullname = ?, address = ?, birthdate = ?, gender = ?, email = ? WHERE username = ?";
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement(sql);
        if (!newProfile.getPassword().isEmpty()) {
            stmt.setString(1, newProfile.getPassword());
        } else {
            stmt.setString(1, currentPass);
        }
        stmt.setString(2, newProfile.getFullname());
        stmt.setString(3, newProfile.getAddress());
        stmt.setDate(4, Date.valueOf(newProfile.getBirthdate()));
        stmt.setInt(5, newProfile.getGender());
        stmt.setString(6, newProfile.getEmail());
        stmt.setString(7, newProfile.getUsername());
        stmt.executeUpdate();
        stmt.close();
    }

    public void unblockFriend(String myUsername, String friendUsername) throws SQLException {
        String sql = "DELETE FROM BLOCK WHERE username = ? AND block = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, myUsername);
        stmt.setString(2, friendUsername);
        stmt.executeUpdate();
        stmt.close();
    }

    public void setLogoutTime(String username) {
        String sql = "UPDATE HISTORY_LOGIN SET logout_time = ? WHERE username = ? ";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(2, username);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
        }
    }

    public void deleteChat(String deleteUsername, String username2) throws SQLException {
        String sql = "SELECT * FROM FRIEND WHERE username1 = ? AND username2 = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, deleteUsername);
        stmt.setString(2, username2);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String sql2 = "UPDATE FRIEND SET user1_deleteChat = ? WHERE username1 = ? AND username2 = ?";
            PreparedStatement stmt2 = conn.prepareStatement(sql2);
            stmt2.setDate(1, Date.valueOf(LocalDate.now()));
            stmt2.setString(2, deleteUsername);
            stmt2.setString(3, username2);
            stmt2.executeUpdate();
            stmt2.close();
        } else {
            String sql2 = "UPDATE FRIEND SET user2_deleteChat = ? WHERE username1 = ? AND username2 = ?";
            PreparedStatement stmt2 = conn.prepareStatement(sql2);
            stmt2.setDate(1, Date.valueOf(LocalDate.now()));
            stmt2.setString(2, username2);
            stmt2.setString(3, deleteUsername);
            stmt2.executeUpdate();
            stmt2.close();
        }
    }

    public void setLoginTime(String username) {
        String sql = "INSERT INTO HISTORY_LOGIN (username, login_time) VALUES (?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
        }
    }

    public ArrayList<ChatInfo> searchFriends(String myUsername, String input) throws SQLException {
        String sql = "SELECT friend.username2, USER.fullname FROM FRIEND " +
                "JOIN USER ON USER.username = friend.username2 " +
                "WHERE friend.accepted = 1 AND friend.username1 = ? AND friend.username2 LIKE ? " +
                "UNION " +
                "SELECT friend.username1, USER.fullname FROM FRIEND " +
                "JOIN USER ON USER.username = friend.username1 " +
                "WHERE friend.accepted = 1 AND friend.username2 = ? AND friend.username1 LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, myUsername);
        stmt.setString(2, "%" + input + "%");
        stmt.setString(3, myUsername);
        stmt.setString(4, "%" + input + "%");
        ResultSet rs = stmt.executeQuery();
        ArrayList<ChatInfo> friends = new ArrayList<>();
        while (rs.next()) {
            String friendUsername = "";
            if (rs.getString("username2").isEmpty()) {
                friendUsername = rs.getString("username1");
            } else {
                friendUsername = rs.getString("username2");
            }
            String friendFullname = rs.getString("fullname");
            friends.add(new ChatInfo(friendFullname, friendUsername, friendUsername, false));
        }
        rs.close();
        stmt.close();
        return friends;
    }

    public void deleteFriendChat(String myUsername, String friendUsername) throws SQLException {
        String sql = "UPDATE FRIEND SET user1_deleteChat = ? WHERE (username1 = ? AND username2 = ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
        stmt.setString(2, myUsername);
        stmt.setString(3, friendUsername);
        int row = stmt.executeUpdate();
        stmt.close();
        System.out.println("delete friend chat 1");

        if (row != 1) {
            String sql2 = "UPDATE FRIEND SET user2_deleteChat = ? WHERE (username1 = ? AND username2 = ?)";
            PreparedStatement stmt2 = conn.prepareStatement(sql2);
            stmt2.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt2.setString(2, friendUsername);
            stmt2.setString(3, myUsername);
            stmt2.executeUpdate();
            stmt2.close();
            System.out.println("delete friend chat 2");
        }
    }

    public void deleteGroupChat(String myUsername, int idGroup) throws SQLException {
        String sql = "UPDATE GROUP_MEMBER SET delete_history = ? WHERE (username = ? AND id_group = ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
        stmt.setString(2, myUsername);
        stmt.setInt(3, idGroup);
        stmt.executeUpdate();
        stmt.close();
    }

    public ArrayList<ChatInfo> getAllStrangers() {
        String sql = "SELECT username, fullname FROM USER WHERE is_locked = 0 AND username NOT IN (SELECT username1 FROM FRIEND WHERE username2 = ?" +
                "UNION " +
                "SELECT username2 FROM FRIEND WHERE username1 = ? " +
                "UNION " +
                "SELECT block FROM BLOCK WHERE username = ?" +
                "UNION " +
                "SELECT username FROM BLOCK WHERE block = ?" +
                ") AND username != ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, loginedUsername);
            stmt.setString(2, loginedUsername);
            stmt.setString(3, loginedUsername);
            stmt.setString(4, loginedUsername);
            stmt.setString(5, loginedUsername);
            ResultSet rs = stmt.executeQuery();
            ArrayList<ChatInfo> users = new ArrayList<>();
            while (rs.next()) {
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");
                users.add(new ChatInfo(fullname, username, username, false));
            }
            rs.close();
            stmt.close();
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
        }
        return null;
    }
}