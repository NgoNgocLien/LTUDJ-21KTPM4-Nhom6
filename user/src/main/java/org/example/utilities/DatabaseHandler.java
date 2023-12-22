package org.example.utilities;

import org.example.models.ChatInfo;
import org.example.models.Message;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/db_chat";
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
    public void closeConnection() throws SQLException {
        System.out.println("Database closed.");
        if (conn != null) conn.close();
    }

    public ArrayList<ChatInfo> getAllChats(String myUsername) throws SQLException {
        String sql =
                "(WITH RankedMessages AS (   " +
                        "    SELECT M.sender, M.to_user, M.to_group, M.content, M.sent_time, M.seen_time, " +
                        "        ROW_NUMBER() OVER (PARTITION BY CASE WHEN M.sender = ? THEN M.to_user ELSE M.sender END " +
                        "                           ORDER BY M.sent_time DESC) AS row_num " +
                        "    FROM MESSAGE M " +
                        "    INNER JOIN FRIEND F ON ((M.sender = F.username1 AND M.to_user = F.username2) OR (M.sender = F.username2 AND M.to_user = F.username1)) " +
                        "    WHERE M.to_group IS NULL AND ((F.username1 = ? AND M.sent_time > F.user1_deleteChat) " +
                        "    OR (F.username2 = ? AND M.sent_time > F.user2_deleteChat)) " +
                        ") " +
                        "SELECT R.sender, R.to_user, R.to_group, R.content, R.seen_time, U.fullname AS chat_name " +
                        "FROM RankedMessages R " +
                        "JOIN USER U ON (U.username != ? AND (R.to_user = U.username OR R.sender = U.username)) " +
                        "WHERE R.row_num = 1 " +
                        "ORDER BY R.sent_time DESC) " +
                        "UNION " +
                        "(WITH RankedMessages AS (   " +
                        "    SELECT M.sender, M.to_user, M.to_group, M.content, M.sent_time, M.seen_time, " +
                        "        ROW_NUMBER() OVER (PARTITION BY M.to_group " +
                        "                          ORDER BY M.sent_time DESC) AS row_num " +
                        "    FROM MESSAGE M " +
                        "    INNER JOIN GROUP_MEMBER GM ON (GM.username = ? AND GM.id_group = M.to_group) " +
                        "    WHERE M.sent_time > GM.delete_history " +
                        ") " +
                        "SELECT R.sender, R.to_user, R.to_group, R.content, R.seen_time, G.group_name AS chat_name " +
                        "FROM RankedMessages R " +
                        "JOIN GROUP_CHAT G ON (G.id_group = R.to_group)  " +
                        "WHERE R.row_num = 1 " +
                        "ORDER BY R.sent_time DESC)";

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
            if(isMyMessage) msg = "You: " + message;
            else msg = sender + ": " + message;
            String toUser = rs.getString("to_user");
            int toGroup = rs.getObject("to_group") != null ? rs.getInt("to_group") : -1;
            boolean seen = rs.getTimestamp("seen_time") != null;
            if (toGroup != -1) {
                // get group quantity
                int quantity = getGroupMemberQuantity(toGroup);
                chats.add(new ChatInfo(chatName, toGroup, quantity, msg, !seen));
            }
            else {
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
        }
        else {
            rs.close();
            stmt.close();
            return -1;
        }

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
        }
        else {
            rs.close();
            stmt.close();
            return false;
        }
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
            }
            else {
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
            blocks.add(new ChatInfo(blockFullname, blockUsername, blockUsername, false, false));
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
            requests.add(new ChatInfo(requestFullname, requestUsername, requestUsername, false, false));
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
    public ArrayList<Message> getFriendMessages(String myUsername, String friendUsername) {
        String sql ="SELECT M.id_message, M.sender, M.to_user, M.content, M.sent_time " +
                    "FROM MESSAGE M " +
                    "WHERE ((M.sender = ? AND M.to_user = ?) OR (M.sender = ? AND M.to_user = ?)) AND M.to_group IS NULL " +
                    "ORDER BY M.sent_time";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, myUsername);
            stmt.setString(2, friendUsername);
            stmt.setString(3, friendUsername);
            stmt.setString(4, myUsername);
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

                messages.add(new Message(id, sender, to_user, content, sent_time.toLocalDateTime(), sender.equals(myUsername)));
            }
            rs.close();
            stmt.close();
            return messages;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ArrayList<Message> getGroupMessages(String myUsername, int idGroup) {
        String sql = "WITH GroupMessage AS ( " +
                    "SELECT M.sender, M.sent_time, M.content, M.id_message, " +
                    "ROW_NUMBER() OVER (PARTITION BY M.sent_time) AS rnk " +
                    "FROM MESSAGE M " +
                    "WHERE M.to_group = ?) " +
                    "SELECT sender, sent_time, content, id_message " +
                    "FROM GroupMessage " +
                    "WHERE rnk = 1";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idGroup);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Message> messages = new ArrayList<>();
            while (rs.next()) {
                int id;
                String sender, content;
                Timestamp sent_time;

                id = rs.getInt("id_message");
                sender = rs.getString("sender");
                content = rs.getString("content");
                sent_time = rs.getTimestamp("sent_time");

                messages.add(new Message(id, sender, "", content, sent_time.toLocalDateTime(), sender.equals(myUsername)));
            }
            rs.close();
            stmt.close();
            return messages;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


//    public static void main(String[] args) {
//        DatabaseHandler DB = new DatabaseHandler();
//        try {
//            ArrayList<ChatInfo> friends = DB.getAllFriends(? );
//            for (ChatInfo friend : friends) {
//                System.out.println(friend.getChatName() + " " + friend.getSubTitle());
//            }
//        }
//        catch (Exception ex) {
//            System.out.println("Error getting all friends: " + ex);
//        }
//    }

}



//package utilities;
//
////import .models.*;
//
//import java.sql.*;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//
//public class DatabaseHandler {
//    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    // Database URL
//    private final String DB_URL = "jdbc:mysql://localhost:3306/db_chat";
//    // Database credentials
//    private final String USER = "root";
//    private final String PASS = "1234";
//    private Connection conn = null;
//
//    public DatabaseHandler() {
//        try {
//            Class.forName(JDBC_DRIVER); // Register JDBC driver
//            System.out.println("Connecting to database...");
//            conn = DriverManager.getConnection(DB_URL,USER,PASS); // Open a connection
//        } catch (SQLException se) {
//            se.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public void clearEnvironment() throws SQLException {
//        System.out.println("Database closed.");
//        if (conn != null) conn.close();
//    }
//
//    public User getUser(String user) throws SQLException {
//        String sql = "SELECT * FROM USER WHERE username = ?";
//        PreparedStatement stmt = conn.prepareStatement(sql);
//        stmt.setString(1, user);
//        ResultSet rs = stmt.executeQuery();
//
//        if (!rs.next()) {
//            return null;
//        } else {
////            String username, String password, String fullname, String address, LocalDate birthday,
////            int gender, String email, boolean isActive, LocalDate creationTime, boolean isLocked
//            String username, password, fullname, address, email;
//            int gender;
//            LocalDate birthday, creationTime;
//            boolean isActive, isLocked;
//
//            username = rs.getString("username");
//            password = rs.getString("password");
//            fullname = rs.getString("fullname");
//            address = rs.getString("address");
//            email = rs.getString("email");
//
//            birthday = rs.getDate("birthdate").toLocalDate();
//            creationTime = rs.getDate("creation_time").toLocalDate();
//
//            isActive = true;
//            isLocked = rs.getBoolean("is_locked");
//
//            gender = rs.getInt("gender");
//
//            return new User(username, password, fullname, address, birthday, gender, email, isActive, creationTime, isLocked);
//        }
//    }
//
//    public Friends getAllFriend(String username) throws SQLException {
//        String sql = "SELECT * FROM FRIEND WHERE username = ?";
//        PreparedStatement stmt = conn.prepareStatement(sql);
//        stmt.setString(1, username);
//
//        ResultSet rs = stmt.executeQuery();
//        Friends friendList = new Friends(username);
//        if (rs.next()) {
//            rs = stmt.executeQuery();
//            String friendUsername;
//            LocalDate deleteChat;
//            while (rs.next()) {
//                friendUsername = rs.getString("username2");
//                deleteChat = rs.getDate("user1_deleteChat").toLocalDate();
//                friendList.addFriend(friendUsername, deleteChat);
//            }
//        }
//        return friendList;
//    }
//
//    public ArrayList<SideChatInfo> getRecentChat(String username) throws SQLException {
//        String sql = "(WITH RankedMessages AS (" +
//                "    SELECT " +
//                "        M.sender, " +
//                "        M.to_user, " +
//                "        M.to_group, " +
//                "        M.content, " +
//                "        M.sent_time, " +
//                "        M.seen_time, " +
//                "        ROW_NUMBER() OVER (PARTITION BY CASE WHEN M.sender = ? THEN M.to_user ELSE M.sender END " +
//                "                          ORDER BY M.sent_time DESC) AS row_num " +
//                "    FROM MESSAGE M " +
//                "    INNER JOIN FRIEND F ON ((M.sender = F.username1 AND M.to_user = F.username2) OR (M.sender = F.username2 AND M.to_user = F.username1)) " +
//                "    WHERE M.to_group IS NULL AND ((F.username1 = ? AND M.sent_time > F.user1_deleteChat) OR (F.username2 = ? AND M.sent_time > F.user2_deleteChat)) " +
//                ") " +
//                "SELECT " +
//                "    R.sender, " +
//                "    R.to_user, " +
//                "    R.to_group, " +
//                "    R.content, " +
//                "    R.seen_time, " +
//                "    U.fullname AS chat_name " +
//                "FROM RankedMessages R " +
//                "JOIN USER U ON (U.username != ? AND (R.to_user = U.username OR R.sender = U.username)) " +
//                "WHERE R.row_num = 1 " +
//                "ORDER BY R.sent_time DESC) " +
//                "UNION " +
//                "(WITH RankedMessages AS (" +
//                "    SELECT " +
//                "        M.sender, " +
//                "        M.to_user, " +
//                "        M.to_group, " +
//                "        M.content, " +
//                "        M.sent_time, " +
//                "        M.seen_time, " +
//                "        ROW_NUMBER() OVER (PARTITION BY M.to_group " +
//                "                          ORDER BY M.sent_time DESC) AS row_num " +
//                "    FROM MESSAGE M " +
//                "    INNER JOIN GROUP_MEMBER GM ON (GM.username = ? AND GM.id_group = M.to_group) " +
//                "    WHERE M.sent_time > GM.delete_history " +
//                ")" +
//                "SELECT " +
//                "    R.sender, " +
//                "    R.to_user, " +
//                "    R.to_group, " +
//                "    R.content, " +
//                "    R.seen_time, " +
//                "    G.group_name AS chat_name " +
//                "FROM RankedMessages R " +
//                "JOIN GROUP_CHAT G ON (G.id_group = R.to_group) " +
//                "WHERE R.row_num = 1 " +
//                "ORDER BY R.sent_time DESC);";
//        PreparedStatement stmt = conn.prepareStatement(sql);
//        stmt.setString(1, username);
//        stmt.setString(2, username);
//        stmt.setString(3, username);
//        stmt.setString(4, username);
//        stmt.setString(5, username);
//
//        ResultSet rs = stmt.executeQuery();
//        ArrayList<SideChatInfo> chats = new ArrayList<>();
//        while (rs.next()) {
//            String sender, to_user, to_group, content, chat_name;
//            boolean seen;
//
//            sender = rs.getString("sender");
//            to_user = rs.getString("to_user");
//            to_group = rs.getObject("to_group") != null ? String.valueOf(rs.getInt("to_group")) : "";
//            content = rs.getString("content");
//
//            String chatId;
//            if (sender.equals(username)) {
//                content = "You: " + content;
//                chatId = to_user;
//            }
//            else {
//                content = sender + ": " + content;
//                chatId = sender;
//            }
//
//            chat_name = rs.getString("chat_name");
//
//            if (to_user.equals(username)) // whether the user saw the latest message from sender
//                seen = rs.getTimestamp("seen_time") != null;
//            else
//                seen = true;
//
//            if (to_group.isEmpty()) {
//                chats.add(new SideChatInfo(username, chatId, chat_name, content, seen, false));
//            } else {
//                chats.add(new SideChatInfo(username, to_group, chat_name, content, seen, true));
//            }
//        }
//        return chats;
//    }
//
//    public ArrayList<SideChatInfo> getGroupChat(String username) throws SQLException {
//        String sql = "WITH RankedMessages AS (  " +
//                "    SELECT " +
//                "        M.sender, " +
//                "        M.to_user, " +
//                "        M.to_group, " +
//                "        M.content, " +
//                "        M.sent_time, " +
//                "        M.seen_time, " +
//                "        ROW_NUMBER() OVER (PARTITION BY M.to_group " +
//                "                          ORDER BY M.sent_time DESC) AS row_num " +
//                "    FROM MESSAGE M " +
//                "    INNER JOIN GROUP_MEMBER GM ON (GM.username = ? AND GM.id_group = M.to_group) " +
//                "    WHERE M.sent_time > GM.delete_history " +
//                ") " +
//                "SELECT " +
//                "    R.sender, " +
//                "    R.to_user, " +
//                "    R.to_group, " +
//                "    R.content, " +
//                "    R.seen_time, " +
//                "    G.group_name AS chat_name " +
//                "FROM RankedMessages R " +
//                "JOIN GROUP_CHAT G ON (G.id_group = R.to_group) " +
//                "WHERE R.row_num = 1 " +
//                "ORDER BY R.sent_time DESC";
//        PreparedStatement stmt = conn.prepareStatement(sql);
//        stmt.setString(1, username);
//
//        ResultSet rs = stmt.executeQuery();
//        ArrayList<SideChatInfo> chats = new ArrayList<>();
//        while (rs.next()) {
//            String sender, to_user, to_group, content, chat_name;
//            boolean seen;
//
//            sender = rs.getString("sender");
//            to_user = rs.getString("to_user");
//            to_group = rs.getObject("to_group") != null ? String.valueOf(rs.getInt("to_group")) : "";
//            content = rs.getString("content");
//
//            String chatId;
//            if (sender.equals(username)) {
//                content = "You: " + content;
//                chatId = to_user;
//            }
//            else {
//                content = sender + ": " + content;
//                chatId = sender;
//            }
//
//            chat_name = rs.getString("chat_name");
//
//            if (to_user.equals(username)) // whether the user saw the latest message from sender
//                seen = rs.getTimestamp("seen_time") != null;
//            else
//                seen = true;
//
//            if (to_group.isEmpty()) {
//                chats.add(new SideChatInfo(username, chatId, chat_name, content, seen, false));
//            } else {
//                chats.add(new SideChatInfo(username, to_group, chat_name, content, seen, true));
//            }
//        }
//        return chats;
//    }
//
//    public ArrayList<SideChatInfo> getBlockUsers(String username) throws SQLException {
//        String sql = "";
//        PreparedStatement stmt = conn.prepareStatement(sql);
//        stmt.setString(1, username);
//
//        ResultSet rs = stmt.executeQuery();
//        ArrayList<SideChatInfo> chats = new ArrayList<>();
//        while (rs.next()) {
//            String sender, to_user, to_group, content, chat_name;
//            boolean seen;
//
//            sender = rs.getString("sender");
//            to_user = rs.getString("to_user");
//            to_group = rs.getObject("to_group") != null ? String.valueOf(rs.getInt("to_group")) : "";
//            content = rs.getString("content");
//
//            String chatId;
//            if (sender.equals(username)) {
//                content = "You: " + content;
//                chatId = to_user;
//            }
//            else {
//                content = sender + ": " + content;
//                chatId = sender;
//            }
//
//            chat_name = rs.getString("chat_name");
//
//            if (to_user.equals(username)) // whether the user saw the latest message from sender
//                seen = rs.getTimestamp("seen_time") != null;
//            else
//                seen = true;
//
//            if (to_group.isEmpty()) {
//                chats.add(new SideChatInfo(username, chatId, chat_name, content, seen, false));
//            } else {
//                chats.add(new SideChatInfo(username, to_group, chat_name, content, seen, true));
//            }
//        }
//        return chats;
//    }
//
//    public ArrayList<Message> getMessages(SideChatInfo chatInfo) throws SQLException {
//        if (chatInfo.getIsGroup()) {
//            String sql = "(WITH SimilarMessage AS " +
//                    "(SELECT M.*, ROW_NUMBER() OVER (PARTITION BY sent_time) AS row_num " +
//                    "FROM MESSAGE M " +
//                    "WHERE M.sender = ? AND M.to_group = ?)" +
//                    "SELECT id_message, sender, to_user, to_group, content, sent_time, seen_time " +
//                    "FROM SimilarMessage " +
//                    "WHERE row_num = 1) " +
//                    "UNION " +
//                    "(SELECT * " +
//                    "FROM MESSAGE " +
//                    "WHERE to_user = ? AND to_group IS NOT NULL)" +
//                    "ORDER BY sent_time";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setString(1, chatInfo.getMyUsername());
//            stmt.setInt(2, Integer.parseInt(chatInfo.getChatId()));
//            stmt.setString(3, chatInfo.getMyUsername());
//            ArrayList<Message> messages = new ArrayList<>();
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                int id;
//                String sender, to_user, content;
//                int to_group;
//                LocalDateTime sent_time, seen_time;
//
//                id = rs.getInt("id_message");
//                sender = rs.getString("sender");
//                to_user = rs.getString("to_user"); // nonsense
//                to_group = rs.getInt("to_group");
//                content = rs.getString("content");
//                sent_time = rs.getTimestamp("sent_time") != null ? rs.getTimestamp("sent_time").toLocalDateTime() : null;
//                seen_time = rs.getTimestamp("seen_time") != null ? rs.getTimestamp("seen_time").toLocalDateTime() : null;
//
//                messages.add(new Message(chatInfo.getMyUsername(), id, sender, to_user, to_group, content, sent_time, seen_time));
//            }
//            return messages;
//        } else {
//            String sql = "SELECT * " +
//                    "FROM MESSAGE M " +
//                    "WHERE ((M.sender = ? AND M.to_user = ?) OR (M.sender = ? AND M.to_user = ?)) AND M.to_group IS NULL " +
//                    "ORDER BY sent_time";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setString(1, chatInfo.getMyUsername());
//            stmt.setString(2, chatInfo.getChatId());
//            stmt.setString(3, chatInfo.getChatId());
//            stmt.setString(4, chatInfo.getMyUsername());
//
//            ResultSet rs = stmt.executeQuery();
//            ArrayList<Message> messages = new ArrayList<>();
//            while (rs.next()) {
//                int id;
//                String sender, to_user, content;
//                int to_group;
//                LocalDateTime sent_time, seen_time;
//
//                id = rs.getInt("id_message");
//                sender = rs.getString("sender");
//                to_user = rs.getString("to_user");
//                to_group = rs.getObject("to_group") != null ? rs.getInt("to_group") : -1; // nonsense
//                content = rs.getString("content");
//                sent_time = rs.getTimestamp("sent_time") != null ? rs.getTimestamp("sent_time").toLocalDateTime() : null;
//                seen_time = rs.getTimestamp("seen_time") != null ? rs.getTimestamp("seen_time").toLocalDateTime() : null;
//
//                messages.add(new Message(chatInfo.getMyUsername(), id, sender, to_user, to_group, content, sent_time, seen_time));
//            }
//            return messages;
//        }
//    }
//}
