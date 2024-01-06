package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class UserDatabase {
    Connection connection;

    UserDatabase(MainDatabase mainDB) {
        this.connection = mainDB.getConnection();
    }

    boolean login(String username, String password) throws SQLException {
        Statement stm = connection.createStatement();
        String sql = "USE messenger_db";
        stm.executeUpdate(sql);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT password FROM user WHERE username = ?");
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String passwordCheck = resultSet.getString("password");
                System.out.println(passwordCheck);
                if (Objects.equals(passwordCheck, password)) return true;
            } else {
                return false;
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean signup(String[] parts) throws SQLException {
        Statement stm = connection.createStatement();
        String sql = "USE messenger_db";
        stm.executeUpdate(sql);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (username, password, fullname, address, gender, birthdate, email, creation_time, is_locked) VALUES (?, ?, ?, ?, ?, STR_TO_DATE(?, '%Y-%m-%d'), ?, now(), 0)");
//            username
            preparedStatement.setString(1, parts[0]);
            //password
            preparedStatement.setString(2, parts[1]);
            //fullname
            preparedStatement.setString(3, parts[2]);
            //address
            preparedStatement.setString(4, parts[3]);
            //gender
            if (Objects.equals(parts[4], "male")) preparedStatement.setInt(5, 1);
            else preparedStatement.setInt(5, 0);
            //birthdate
            preparedStatement.setString(6, parts[5]);
            //email
            preparedStatement.setString(7, parts[6]);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException se) {
            System.out.println(se.getErrorCode());
        }
        return false;
    }

    boolean updatePwd(String username, String email, String pwd) throws SQLException {
        Statement stm = connection.createStatement();
        String sql = "USE db_chat";
        stm.executeUpdate(sql);

        try{
            String sql1;
            sql1 = "UPDATE USER SET password = ? WHERE email = ? AND username = ?";

            PreparedStatement stmt = connection.prepareStatement(sql1);
            stmt.setString(1, pwd);
            stmt.setString(2, email);
            stmt.setString(3, username);
            int row = stmt.executeUpdate();
            stmt.close();

            if (row > 0)
                return true;

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){ //Handle errors for Class.forName
            e.printStackTrace();
        }
        return false;
    }

    public Object[][] getFriendMessages(String myUsername, String friendUsername, String lastMessage) {
//        SELECT M.id_message, M.sender, M.to_user, M.content, M.sent_time
//        FROM MESSAGE M
//        INNER JOIN FRIEND F ON (F.username1 = M.sender AND F.username2 = M.to_user) OR (F.username2 = M.sender AND F.username1 = M.to_user)
//        WHERE ((M.sender = 'hlong' AND M.to_user = 'hlap') OR (M.sender = 'hlap' AND M.to_user = 'hlong')) AND M.to_group IS NULL AND '1990-02-02' < M.sent_time AND ((F.username1 = 'hlong' AND M.sent_time > F.user1_deleteChat) OR (F.username2 = 'hlong' AND M.sent_time > F.user2_deleteChat))
//        ORDER BY M.sent_time;
        String sql = "SELECT M.id_message, M.sender, M.to_user, M.content, M.sent_time\n" +
                "FROM MESSAGE M\n" +
                "INNER JOIN FRIEND F ON (F.username1 = M.sender AND F.username2 = M.to_user) OR (F.username2 = M.sender AND F.username1 = M.to_user)\n" +
                "WHERE ((M.sender = ? AND M.to_user = ?) OR (M.sender = ? AND M.to_user = ?)) AND M.to_group IS NULL AND ? < M.sent_time AND ((F.username1 = ? AND M.sent_time > F.user1_deleteChat) OR (F.username2 = ? AND M.sent_time > F.user2_deleteChat))\n" +
                "ORDER BY M.sent_time";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, myUsername);
            stmt.setString(2, friendUsername);
            stmt.setString(3, friendUsername);
            stmt.setString(4, myUsername);
            stmt.setString(5, lastMessage);
            stmt.setString(6, myUsername);
            stmt.setString(7, myUsername);
            ResultSet rs = stmt.executeQuery();

            List<Object[]> rows = new ArrayList<>();
            while (rs.next()) {
                int id;
                String sender, to_user, content;
                Timestamp sent_time;

                id = rs.getInt("id_message");
                sender = rs.getString("sender");
                to_user = rs.getString("to_user");
                content = rs.getString("content");
                sent_time = rs.getTimestamp("sent_time");

//                messages.add(new Message(id, sender, to_user, -1, content, sent_time.toLocalDateTime(), sender.equals(myUsername)));
                Object[] row = {id, sender, to_user, -1, content, sent_time.toLocalDateTime().toString(), sender.equals(myUsername)};
                rows.add(row);
            }
            rs.close();
            stmt.close();

            if (rows.isEmpty()) return null;
            else {
                Object[][] messages = new Object[rows.size()][];
                rows.toArray(messages);
                return messages;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    Object[][] getGroupMessages(String myUsername, int idGroup, String lastMessage) {
//        SELECT M.id_message, M.sender, M.to_user, M.content, M.sent_time
//        FROM MESSAGE M
//        INNER JOIN GROUP_MEMBER GM ON M.to_group = GM.id_group
//        WHERE GM.username = 'hlong' AND M.to_group = 5 AND '1990-02-02' < M.sent_time AND M.sent_time > GM.delete_history

        String tempSql = "SELECT delete_history " +
                "FROM GROUP_MEMBER " +
                "WHERE username = ? and id_group = ?";
        PreparedStatement tempStmt = null;
        try {
            tempStmt = connection.prepareStatement(tempSql);
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
                            "WHERE rnk = 1 AND (sent_time > ?) AND (sent_time > ?)";
                    PreparedStatement stmt = null;
                    System.out.println(deleteHistory.toString() + " " + lastMessage);
                    try {
                        stmt = connection.prepareStatement(sql);
                        stmt.setInt(1, idGroup);
                        stmt.setString(2, deleteHistory.toString());
                        stmt.setString(3, lastMessage);

                        ResultSet rs = stmt.executeQuery();

                        List<Object[]> rows = new ArrayList<>();
                        while (rs.next()) {
                            int id;
                            String sender, to_user, content;
                            Timestamp sent_time;

                            id = rs.getInt("id_message");
                            sender = rs.getString("sender");
                            content = rs.getString("content");
                            sent_time = rs.getTimestamp("sent_time");

                            Object[] row = {id, sender, "", idGroup, content, sent_time.toLocalDateTime().toString(), sender.equals(myUsername)};
                            rows.add(row);
                        }
                        rs.close();
                        stmt.close();

                        if (rows.isEmpty()) return null;
                        else {
                            Object[][] messages = new Object[rows.size()][];
                            rows.toArray(messages);
                            return messages;
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}