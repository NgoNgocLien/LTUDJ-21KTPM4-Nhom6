/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

/**
 *
 * @author ACER
 */
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

public class AdminController {
    private Connection conn;
    static final String DB_URL = "jdbc:mysql://localhost:3306/db_chat?user=root&password=admin";

    AdminController(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connect to database successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Error: unable to load driver class.");
            System.exit(1);
        }
    }

    Object[][] getAllGroup(){
        try{
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT id_group, group_name, create_time FROM GROUP_CHAT";
            ResultSet rs = stmt.executeQuery(sql);
            List<Object[]> rows = new ArrayList<>();

            while(rs.next()){
                //Retrieve by column name
                int id_group = rs.getInt("id_group");
                String group_name = rs.getString("group_name");
                Date create_time = rs.getDate("create_time");
                Timestamp timestamp = new Timestamp(create_time.getTime());

                LocalDateTime localDateTime = timestamp.toLocalDateTime();
//                LocalDate localDate = ((java.sql.Date) create_time).toLocalDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H:m:s");
                String format_time = localDateTime.format(formatter);

                Object[] row = {id_group, group_name, format_time};
                // Add the row to the list
                rows.add(row);
            }

            rs.close();
            stmt.close();

            Object[][] data = new Object[rows.size()][];
            rows.toArray(data);

            return data;
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){ //Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }

    Object[][] getAllAdmin(int selected_id){
        try{
            String sql;
            sql = "SELECT gm.username, u.fullname "
                    + "FROM GROUP_MEMBER gm "
                    + "INNER JOIN GROUP_CHAT gc ON gc.id_group = gm.id_group "
                    + "INNER JOIN USER u ON u.username = gm.username "
                    + "WHERE gm.id_group = ? AND gm.is_admin = 1";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, selected_id);
            ResultSet rs = stmt.executeQuery();

            List<Object[]> rows = new ArrayList<>();

            while(rs.next()){
                //Retrieve by column name
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");

                Object[] row = {username, fullname};
                rows.add(row);
            }

            rs.close();
            stmt.close();

            Object[][] data = new Object[rows.size()][];
            rows.toArray(data);

            return data;
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){ //Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }

    Object[][] getAllMember(int selected_id){
        try{
            String sql;
            sql = "SELECT gm.username, u.fullname "
                    + "FROM GROUP_MEMBER gm "
                    + "INNER JOIN GROUP_CHAT gc ON gc.id_group = gm.id_group "
                    + "INNER JOIN USER u ON u.username = gm.username "
                    + "WHERE gm.id_group = ? AND gm.is_admin = 0";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, selected_id);
            ResultSet rs = stmt.executeQuery();

            List<Object[]> rows = new ArrayList<>();

            while(rs.next()){
                //Retrieve by column name
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");

                Object[] row = {username, fullname};
                rows.add(row);
            }

            rs.close();
            stmt.close();

            Object[][] data = new Object[rows.size()][];
            rows.toArray(data);

            return data;
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){ //Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }

    Object[][] searchGroupName(String text){
        try{
            String sql;
            sql = "SELECT id_group, group_name, create_time "
                    + "FROM GROUP_CHAT "
                    + "WHERE group_name LIKE ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + text + "%");
            ResultSet rs = stmt.executeQuery();

            List<Object[]> rows = new ArrayList<>();

            while(rs.next()){
                int id_group = rs.getInt("id_group");
                String group_name = rs.getString("group_name");
                Date create_time = rs.getDate("create_time");
                Timestamp timestamp = new Timestamp(create_time.getTime());

                LocalDateTime localDateTime = timestamp.toLocalDateTime();
//                LocalDate localDate = ((java.sql.Date) create_time).toLocalDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H:m:s");
                String format_time = localDateTime.format(formatter);

                Object[] row = {id_group, group_name, format_time};
                rows.add(row);
            }

            rs.close();
            stmt.close();

            Object[][] data = new Object[rows.size()][];
            rows.toArray(data);

            return data;
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){ //Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }

    Object[][] getAllReport(){
        try{
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT u.username, s.report_time, u.is_locked "
                    + "FROM SPAM s "
                    + "INNER JOIN MESSAGE m ON m.id_message = s.id_message "
                    + "INNER JOIN USER u ON u.username = m.sender ";
            ResultSet rs = stmt.executeQuery(sql);
            List<Object[]> rows = new ArrayList<>();
            int i = 1;
            while(rs.next()){
                //Retrieve by column name
                String username = rs.getString("username");
                Date report_time = rs.getDate("report_time");
                Timestamp timestamp = new Timestamp(report_time.getTime());

                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String format_time = localDateTime.format(formatter);

                int is_lock = rs.getInt("is_locked");
                String lock_text = (is_lock == 1) ? "Disabled" : "Enabled";

                Object[] row = {i, username, format_time, lock_text};
                // Add the row to the list
                rows.add(row);
                i++;
            }

            rs.close();
            stmt.close();

            Object[][] data = new Object[rows.size()][];
            rows.toArray(data);

            return data;
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){ //Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }

    Object[][] searchReport(String text, String[] date){
        try{
            String sql = "SELECT u.username, s.report_time, u.is_locked "
                    + "FROM SPAM s "
                    + "INNER JOIN MESSAGE m ON m.id_message = s.id_message "
                    + "INNER JOIN USER u ON u.username = m.sender ";
            PreparedStatement stmt;
            if (text.isEmpty()){
                sql += "WHERE DAY(s.report_time) = ? "
                        + "AND MONTH(s.report_time) = ? "
                        + "AND YEAR(s.report_time) = ?;";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, date[0]);
                stmt.setString(2, date[1]);
                stmt.setString(3, date[2]);
            }
            else if (date.length != 3){
                sql += "WHERE u.username LIKE ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, "%" + text + "%");
            }
            else {
                sql += "WHERE DAY(s.report_time) = ? "
                        + "AND MONTH(s.report_time) = ? "
                        + "AND YEAR(s.report_time) = ? "
                        + "AND u.username LIKE ? ;";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, date[0]);
                stmt.setString(2, date[1]);
                stmt.setString(3, date[2]);
                stmt.setString(4, "%" + text + "%");
            }

            ResultSet rs = stmt.executeQuery();

            List<Object[]> rows = new ArrayList<>();
            int i = 1;
            while(rs.next()){
                //Retrieve by column name
                String username = rs.getString("username");
                Date report_time = rs.getDate("report_time");
                Timestamp timestamp = new Timestamp(report_time.getTime());

                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String format_time = localDateTime.format(formatter);

                int is_lock = rs.getInt("is_locked");
                String lock_text = (is_lock == 1) ? "Disabled" : "Enabled";

                Object[] row = {i, username, format_time, lock_text};
                // Add the row to the list
                rows.add(row);
                i++;
            }
            rs.close();
            stmt.close();

            Object[][] data = new Object[rows.size()][];
            rows.toArray(data);

            return data;
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){ //Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }

    void disableUser(String username){
        try{
            String sql;
            sql = "UPDATE USER SET is_locked = 1 WHERE username = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.executeUpdate();
            stmt.close();

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){ //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    Object[][] getAllActiveUser(){
        try{
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT username, fullname, creation_time " +
                    ", COUNT(DISTINCT login_time) as session_count " +
                    ", COUNT(DISTINCT to_user) as partner_count " +
                    ", COUNT(DISTINCT to_group) as group_count " +
                    "FROM( " +
                    "	SELECT u.username, u.fullname, u.creation_time, h.login_time, h.logout_time,  " +
                    "	CASE  " +
                    "       WHEN m.to_group > 0 THEN null " +
                    "       ELSE m.to_user " +
                    "    END as to_user, m.to_group " +
                    "	FROM HISTORY_LOGIN h  " +
                    "	INNER JOIN USER u ON h.username = u.username " +
                    "	LEFT JOIN MESSAGE m ON m.sender = u.username AND m.sent_time BETWEEN h.login_time AND h.logout_time " +
                    "	GROUP BY u.username, u.fullname, u.creation_time, h.login_time, h.logout_time, m.to_user, m.to_group) AS res " +
                    "GROUP BY username, fullname, creation_time ";
            ResultSet rs = stmt.executeQuery(sql);

            List<Object[]> rows = new ArrayList<>();
            int i  = 1;
            while(rs.next()){
                //Retrieve by column name
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");
                Date create_time = rs.getDate("creation_time");
                Timestamp timestamp = new Timestamp(create_time.getTime());

                LocalDateTime localDateTime = timestamp.toLocalDateTime();
//                LocalDate localDate = ((java.sql.Date) create_time).toLocalDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H:m:s");
                String format_time = localDateTime.format(formatter);

                int session_count = rs.getInt("session_count");
                int partner_count = rs.getInt("partner_count");
                int group_count = rs.getInt("group_count");
                Object[] row = {i,username , fullname, format_time, session_count, partner_count, group_count};
                // Add the row to the list
                rows.add(row);
                i++;
            }

            rs.close();
            stmt.close();

            Object[][] data = new Object[rows.size()][];
            rows.toArray(data);

            return data;
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){ //Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }

    Object[][] searchActiveUser(String start_date, String end_date){
        try{

            String sql = "SELECT username, fullname, creation_time, " +
                    " COUNT(DISTINCT login_time) as session_count, " +
                    " COUNT(DISTINCT to_user) as partner_count, " +
                    " COUNT(DISTINCT to_group) as group_count " +
                    "FROM( " +
                    "	SELECT u.username, u.fullname, u.creation_time, h.login_time, h.logout_time,  " +
                    "	CASE  " +
                    "       WHEN m.to_group > 0 THEN null " +
                    "       ELSE m.to_user " +
                    "    END as to_user, m.to_group " +
                    "	FROM HISTORY_LOGIN h  " +
                    "	INNER JOIN USER u ON h.username = u.username " +
                    "	LEFT JOIN MESSAGE m ON m.sender = u.username AND m.sent_time BETWEEN h.login_time AND h.logout_time " +
                    "	WHERE h.login_time > ? AND h.logout_time < ?  " +
                    "	GROUP BY u.username, u.fullname, u.creation_time, h.login_time, h.logout_time, m.to_user, m.to_group) AS res " +
                    "GROUP BY username, fullname, creation_time ";

//            if (!session.isEmpty()){
//                switch (option){
//                    case "Equal to":
//                        sql += "HAVING session_count = ? ";
//                        break;
//                    case "Greater than":
//                        sql += "HAVING session_count ? ? ";
//                        break;
//                    case "Less than":
//                        sql += "HAVING session_count < ? ";
//                        break;
//                }
//            }

            PreparedStatement stmt;
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, start_date);
            stmt.setString(2, end_date);
//            stmt.setString(3, "%" + name + "%");
//            if (!session.isEmpty()){
//                 stmt.setString(4, session);
//            }

            ResultSet rs = stmt.executeQuery();

            List<Object[]> rows = new ArrayList<>();
            int i  = 1;
            while(rs.next()){
                //Retrieve by column name
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");
                Date create_time = rs.getDate("creation_time");
                Timestamp timestamp = new Timestamp(create_time.getTime());

                LocalDateTime localDateTime = timestamp.toLocalDateTime();
//                LocalDate localDate = ((java.sql.Date) create_time).toLocalDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H:m:s");
                String format_time = localDateTime.format(formatter);

                int session_count = rs.getInt("session_count");
                int partner_count = rs.getInt("partner_count");
                int group_count = rs.getInt("group_count");
                Object[] row = {i,username , fullname, format_time, session_count, partner_count, group_count};
                // Add the row to the list
                rows.add(row);
                i++;
            }

            rs.close();
            stmt.close();

            Object[][] data = new Object[rows.size()][];
            rows.toArray(data);

            return data;
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){ //Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }

    int[] getMonthlyActiveUser(String year){
        try{
            String sql = "SELECT MONTH(h.login_time) as month, COUNT(DISTINCT h.username) as user_count " +
                    "FROM HISTORY_LOGIN h " +
                    "WHERE YEAR(h.login_time) = ? " +
                    "GROUP BY MONTH(h.login_time) ORDER BY month ;";
            PreparedStatement stmt;
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, year);

            ResultSet rs = stmt.executeQuery();

            int[] user_count_month = new int[12];
            for (int i = 0; i < 12; i++)
                user_count_month[i] = 0;
            while(rs.next()){
                //Retrieve by column name
                int month = rs.getInt("month");
                int user_count = rs.getInt("user_count");
                user_count_month[month-1] = user_count;

            }
            rs.close();
            stmt.close();

            return user_count_month;
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){ //Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }

    Object[][] getAllUser(){
        try{
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT username, fullname, address, birthdate, gender, email, creation_time FROM USER";
            ResultSet rs = stmt.executeQuery(sql);
            List<Object[]> rows = new ArrayList<>();
            int serialNum = 1;

            while(rs.next()){
                //Retrieve by column name
                //int id_group = rs.getInt("id_group");
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");
                String address = rs.getString("address");
                Date birthdate = rs.getDate("birthdate");
                Boolean gender = rs.getBoolean("gender");
                String email = rs.getString("email");
                Date creation_time = rs.getDate("creation_time");
                Timestamp timestamp = new Timestamp(birthdate.getTime());
                Timestamp timestamp1 = new Timestamp(creation_time.getTime());

                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H:m:s");
                String format_time = localDateTime.format(formatter);

                LocalDateTime localDateTime1 = timestamp1.toLocalDateTime();
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy H:m:s");
                String format_time1 = localDateTime1.format(formatter1);

                String genderString = gender ? "Female" : "Male";

                Object[] row = {serialNum, username, fullname, address, format_time, genderString, email, format_time1};
                serialNum++;
                // Add the row to the list
                rows.add(row);
            }

            rs.close();
            stmt.close();

            Object[][] data = new Object[rows.size()][];
            rows.toArray(data);

            return data;
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){ //Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }

}
