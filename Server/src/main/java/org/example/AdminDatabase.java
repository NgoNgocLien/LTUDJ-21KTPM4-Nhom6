package org.example;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class AdminDatabase {
    Connection connection;

    AdminDatabase() {
        this.connection = null;
    }

    void connect() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String DB_URL = "jdbc:mysql://localhost/db_chat?allowPublicKeyRetrieval=true&useSSL=false";
            final String USER = "root";
            final String PASSWORD = "admin";
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected to DBMS!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Object[][] getAllGroup(){
        try{
            Statement stmt = connection.createStatement();
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
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

    Object[][] getAllAdmin(String selected_id){
        try{
            String sql;
            sql = "SELECT gm.username, u.fullname "
                    + "FROM GROUP_MEMBER gm "
                    + "INNER JOIN GROUP_CHAT gc ON gc.id_group = gm.id_group "
                    + "INNER JOIN USER u ON u.username = gm.username "
                    + "WHERE gm.id_group = ? AND gm.is_admin = 1 AND u.is_locked != 2";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, selected_id);
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

    Object[][] getAllMember(String selected_id){
        try{
            String sql;
            sql = "SELECT gm.username, u.fullname "
                    + "FROM GROUP_MEMBER gm "
                    + "INNER JOIN GROUP_CHAT gc ON gc.id_group = gm.id_group "
                    + "INNER JOIN USER u ON u.username = gm.username "
                    + "WHERE gm.id_group = ? AND gm.is_admin = 0 AND u.is_locked != 2";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, selected_id);
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

            PreparedStatement stmt = connection.prepareStatement(sql);
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
            Statement stmt = connection.createStatement();
            String sql;
            sql = "SELECT u.username, s.report_time, u.is_locked "
                    + "FROM SPAM s "
                    + "INNER JOIN MESSAGE m ON m.id_message = s.id_message "
                    + "INNER JOIN USER u ON u.username = m.sender AND u.is_locked != 2";
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
                    + "INNER JOIN USER u ON u.username = m.sender AND u.is_locked != 2";
            PreparedStatement stmt;
            if (text.isEmpty()){
                sql += "WHERE DAY(s.report_time) = ? "
                        + "AND MONTH(s.report_time) = ? "
                        + "AND YEAR(s.report_time) = ?;";
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, date[0]);
                stmt.setString(2, date[1]);
                stmt.setString(3, date[2]);
            }
            else if (date.length != 3){
                sql += "WHERE u.username LIKE ?";
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, "%" + text + "%");
            }
            else {
                sql += "WHERE DAY(s.report_time) = ? "
                        + "AND MONTH(s.report_time) = ? "
                        + "AND YEAR(s.report_time) = ? "
                        + "AND u.username LIKE ? ;";
                stmt = connection.prepareStatement(sql);
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

    String disableUser(String username){
        try{
            String sql;
            sql = "UPDATE USER SET is_locked = 1 WHERE username = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.executeUpdate();
            stmt.close();

            sql = "SELECT u.username, s.report_time, u.is_locked "
                    + "FROM SPAM s "
                    + "INNER JOIN MESSAGE m ON m.id_message = s.id_message "
                    + "INNER JOIN USER u ON u.username = m.sender ";
            ResultSet rs = stmt.executeQuery(sql);
            List<Object[]> rows = new ArrayList<>();
            int i = 1;
            while(rs.next()){
                //Retrieve by column name
                String username1 = rs.getString("username");
                Date report_time = rs.getDate("report_time");
                Timestamp timestamp = new Timestamp(report_time.getTime());

                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String format_time = localDateTime.format(formatter);

                int is_lock = rs.getInt("is_locked");
                String lock_text = (is_lock == 1) ? "Disabled" : "Enabled";

                Object[] row = {i, username1, format_time, lock_text};
                // Add the row to the list
                rows.add(row);
                i++;
            }

            return "true";
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){ //Handle errors for Class.forName
            e.printStackTrace();
        }
        return "false";
    }

    Object[][] getAllActiveUser(){
        try{
            Statement stmt = connection.createStatement();
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
                    "	INNER JOIN USER u ON h.username = u.username AND u.is_locked != 2 " +
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
            stmt = connection.prepareStatement(sql);
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
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
            stmt = connection.prepareStatement(sql);
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
            Statement stmt = connection.createStatement();
            String sql;
            sql = "SELECT username, fullname, address, birthdate, gender, email, creation_time FROM USER WHERE is_locked != 2";
            ResultSet rs = stmt.executeQuery(sql);
            List<Object[]> rows = new ArrayList<>();
            int serialNum = 1;

            while(rs.next()){
                //Retrieve by column name
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String format_time = localDateTime.format(formatter);

                LocalDateTime localDateTime1 = timestamp1.toLocalDateTime();
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String format_time1 = localDateTime1.format(formatter1);

                String genderString = gender ? "Female" : "Male";

                Object[] row = { serialNum, username, fullname, address, format_time, genderString, email, format_time1 };
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

    Object[][] searchUser(String text1, String text2, String text3){
        try{
            PreparedStatement stmt = null;
            String sql = "SELECT DISTINCT s.username, s.fullname, s.address, s.birthdate, s.gender, s.email, s.creation_time FROM USER s " + "INNER JOIN HISTORY_LOGIN h ON s.username = h.username ";

            if (text1.isEmpty()){
                if (text2.isEmpty()){
                    System.out.println("search by active status");
                    // search by active status
                    if(Objects.equals(text3, "All")){
                        sql += "WHERE s.is_locked != 2 ";
                        stmt = connection.prepareStatement(sql);
                    }
                    else if(Objects.equals(text3, "Active")){
                        sql += "WHERE s.is_locked != 2 AND h.login_time IS NOT NULL AND h.logout_time IS NULL ";
                        stmt = connection.prepareStatement(sql);
                    }
                    else{
                        sql += "WHERE s.is_locked != 2 AND h.login_time IS NOT NULL AND h.logout_time IS NOT NULL ";
                        stmt = connection.prepareStatement(sql);
                    }
                }
                // search by fullname + active status
                else{
                    System.out.println("search by fullname + active status");
                    if(Objects.equals(text3, "All")){
                        sql += "WHERE s.fullname LIKE ? AND s.is_locked != 2 ";
                        stmt = connection.prepareStatement(sql);
                        stmt.setString(1, "%" + text2 + "%");
                    }
                    else if(Objects.equals(text3, "Active")){
                        sql += "WHERE s.fullname LIKE ? ";
                        sql += "AND s.is_locked != 2 AND h.login_time IS NOT NULL AND h.logout_time IS NULL ";

                        stmt = connection.prepareStatement(sql);
                        stmt.setString(1, "%" + text2 + "%");
                    }
                    else{
                        sql += "WHERE s.fullname LIKE ? ";
                        sql += "AND s.is_locked != 2 AND h.login_time IS NOT NULL AND h.logout_time IS NOT NULL ";

                        stmt = connection.prepareStatement(sql);
                        stmt.setString(1, "%" + text2 + "%");
                    }
                }
            }
            else{
                if (text2.isEmpty()){
                    // search by username + active status
                    System.out.println("search by username + active status");
                    if(Objects.equals(text3, "All")){
                        sql += "WHERE s.username LIKE ? AND s.is_locked != 2 ";
                        stmt = connection.prepareStatement(sql);
                        stmt.setString(1, "%" + text1 + "%");
                    }
                    else if(Objects.equals(text3, "Active")){
                        sql += "WHERE s.username LIKE ? ";
                        sql += "AND s.is_locked != 2 AND h.login_time IS NOT NULL AND h.logout_time IS NULL ";

                        stmt = connection.prepareStatement(sql);
                        stmt.setString(1, "%" + text1 + "%");
                    }
                    else{
                        sql += "WHERE s.username LIKE ? ";
                        sql += "AND s.is_locked != 2 AND h.login_time IS NOT NULL AND h.logout_time IS NOT NULL ";

                        stmt = connection.prepareStatement(sql);
                        stmt.setString(1, "%" + text1 + "%");
                    }
                }
                else {
                    System.out.println("search by username + fullname + active status");
                    // search by username + fullname + active status
                    sql += "WHERE s.fullname LIKE ? " + "AND s.username LIKE ? ;";
                    if(Objects.equals(text3, "All")){
                        sql += "WHERE s.is_locked != 2 ";
                        stmt = connection.prepareStatement(sql);
                        stmt.setString(1, "%" + text2 + "%");
                        stmt.setString(2, "%" + text1 + "%");
                    }
                    else if(Objects.equals(text3, "Active")){
                        sql += "WHERE s.is_locked != 2 AND h.login_time IS NOT NULL AND h.logout_time IS NULL ";

                        stmt = connection.prepareStatement(sql);
                        stmt.setString(1, "%" + text2 + "%");
                        stmt.setString(2, "%" + text1 + "%");
                    }
                    else{
                        sql += "WHERE s.is_locked != 2 AND h.login_time IS NOT NULL AND h.logout_time IS NOT NULL ";

                        stmt = connection.prepareStatement(sql);
                        stmt.setString(1, "%" + text2 + "%");
                        stmt.setString(2, "%" + text1 + "%");
                    }
                }
            }

            ResultSet rs = stmt.executeQuery();

            List<Object[]> rows = new ArrayList<>();
            int i = 1;
            while(rs.next()){

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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String format_time = localDateTime.format(formatter);

                LocalDateTime localDateTime1 = timestamp1.toLocalDateTime();
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String format_time1 = localDateTime1.format(formatter1);

                String genderString = gender ? "Female" : "Male";

                Object[] row = {i, username, fullname, address, format_time, genderString, email, format_time1};

                rows.add(row);
                i++;
            }
            rs.close();
            stmt.close();

            Object[][] data = new Object[rows.size()][];
            rows.toArray(data);

            return data;
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    Boolean addNewUser(String username, String password, String fullname , String address, String birthdate, String gender, String email){
        try{
            String sql = "INSERT INTO USER (username, password, fullname, address, birthdate, gender, email, creation_time, is_locked) VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), false)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            Boolean genderBoolean = "Female".equals(gender);
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date parsedDate = inputDateFormat.parse(birthdate);
            Date sqlDate = new Date(parsedDate.getTime());

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, fullname);
            pstmt.setString(4, address);
            pstmt.setDate(5, java.sql.Date.valueOf(outputDateFormat.format(sqlDate)));
            pstmt.setBoolean(6, genderBoolean);
            pstmt.setString(7, email);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                pstmt.close();
                return true;
            } else {
                pstmt.close();
                return false;
            }
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    Object[][] getUserByUsername(String userName){
        try{
            String sql = "SELECT * FROM USER " + "WHERE username LIKE ?";
            PreparedStatement stmt;
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + userName + "%");
            ResultSet rs = stmt.executeQuery();
            List<Object[]> rows = new ArrayList<>();

            while(rs.next()){
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");
                String address = rs.getString("address");
                Date birthdate = rs.getDate("birthdate");
                Boolean gender = rs.getBoolean("gender");
                String email = rs.getString("email");
                Date creation_time = rs.getDate("creation_time");
                int is_locked = rs.getInt("is_locked");
                Timestamp timestamp = new Timestamp(birthdate.getTime());
                Timestamp timestamp1 = new Timestamp(creation_time.getTime());

                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String format_time = localDateTime.format(formatter);

                LocalDateTime localDateTime1 = timestamp1.toLocalDateTime();
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String format_time1 = localDateTime1.format(formatter1);

                String genderString = gender ? "Female" : "Male";
                String statusString = (is_locked == 1) ? "Disabled" : "Enabled";
                Object[] row = {username, fullname, address, format_time, genderString, email, format_time1, statusString};

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

    Object[][] getUserHistoryLogin(String userName){
        try{
            String sql = "SELECT * FROM HISTORY_LOGIN " + "WHERE username LIKE ?";
            PreparedStatement stmt;
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + userName + "%");
            ResultSet rs = stmt.executeQuery();
            List<Object[]> rows = new ArrayList<>();

            int i = 1;
            while(rs.next()){
                Date login_time = rs.getDate("login_time");
                Date logout_time = rs.getDate("logout_time");

                Timestamp timestamp = new Timestamp(login_time.getTime());
                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String format_time = localDateTime.format(formatter);

                String format_time1;
                if (logout_time != null) {
                    Timestamp timestamp1 = new Timestamp(logout_time.getTime());
                    LocalDateTime localDateTime1 = timestamp1.toLocalDateTime();
                    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    format_time1 = localDateTime1.format(formatter1);
                } else {
                    format_time1 = "Active";
                }

                Object[] row = {i, format_time,  format_time1};

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

    Object[][] getUserFriends(String userName){
        try{
            String sql = "SELECT u.username AS username, u.fullname AS fullname " + "FROM FRIEND f " + "JOIN USER u ON f.username1 = u.username " + "WHERE f.username2 LIKE ? AND f.accepted = 1 AND u.is_locked != 2 "
                    + "UNION " + "SELECT u.username AS username, u.fullname AS fullname " + "FROM FRIEND f " + "JOIN USER u ON f.username2 = u.username " + "WHERE f.username1 LIKE ? AND f.accepted = 1 AND u.is_locked != 2";
            PreparedStatement stmt;
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + userName + "%");
            stmt.setString(2, "%" + userName + "%");
            ResultSet rs = stmt.executeQuery();
            List<Object[]> rows = new ArrayList<>();
            int i = 1;
            while(rs.next()){
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");

                Object[] row = {i, username,  fullname};
                rows.add(row);
                i++;
            }

            rs.close();
            stmt.close();
            if (rows.isEmpty()) {
                rows.add(new Object[]{"", "", "No friends found"});
            }
            Object[][] data = new Object[rows.size()][];
            rows.toArray(data);

            return data;
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    Boolean updateUser(String username, String fullname , String address, String birthdate, String gender, String email){
        try{
            System.out.println("birthdate: " + birthdate);
            String sql = "UPDATE USER SET fullname = ?, address = ?, birthdate = ?, gender = ?, email = ? WHERE username LIKE ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            Boolean genderBoolean = "Female".equals(gender);
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date parsedDate = inputDateFormat.parse(birthdate);

            pstmt.setString(1, fullname);
            pstmt.setString(2, address);
            pstmt.setDate(3, new java.sql.Date(parsedDate.getTime()));
            pstmt.setBoolean(4, genderBoolean);
            pstmt.setString(5, email);
            pstmt.setString(6, "%" + username + "%");

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                pstmt.close();
                return true;
            } else {
                pstmt.close();
                return false;
            }
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    Object[][] getAllEmail(String username){
        try{
            String sql = "SELECT email FROM USER WHERE username NOT LIKE ? AND is_locked != 2";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + username + "%");
            ResultSet rs = stmt.executeQuery();
            List<Object[]> rows = new ArrayList<>();

            while(rs.next()){
                String email = rs.getString("email");
                Object[] row = { email };

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

    Boolean deleteUser(String username){
        try{
            String sql = "UPDATE USER SET is_locked = 2 WHERE username LIKE ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "%" + username + "%");

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                pstmt.close();
                return true;
            } else {
                pstmt.close();
                return false;
            }
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    Boolean enableUser(String username){
        try{
            String sql;
            sql = "UPDATE USER SET is_locked = 0 WHERE username = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                stmt.close();
                return true;
            } else {
                stmt.close();
                return false;
            }
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    Boolean disableUserManage(String username){
        try{
            String sql;
            sql = "UPDATE USER SET is_locked = 1 WHERE username = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                stmt.close();
                return true;
            } else {
                stmt.close();
                return false;
            }
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    Object[][] getAllPassRequest(){
        try{
            Statement stmt = connection.createStatement();
            String sql;
            sql = "SELECT up.username, up.new_pwd FROM UPDATE_PWD up INNER JOIN USER u ON up.username = u.username WHERE u.is_locked != 2";
            ResultSet rs = stmt.executeQuery(sql);
            List<Object[]> rows = new ArrayList<>();
            int serialNum = 1;

            while(rs.next()){
                String username = rs.getString("up.username");
                String new_pwd = rs.getString("up.new_pwd");


                Object[] row = { serialNum, username, new_pwd };
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

    Boolean updatePassUser(String username, String new_pwd){
        try{
            String sql = "UPDATE USER SET password = ? WHERE username LIKE ? ";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, new_pwd);
            pstmt.setString(2, "%" + username + "%");

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                sql = "DELETE FROM UPDATE_PWD WHERE username LIKE ? ";
                PreparedStatement deleteStmt = connection.prepareStatement(sql);
                deleteStmt.setString(1, "%" + username + "%");

                int deleteAffectedRows = deleteStmt.executeUpdate();

                deleteStmt.close();
                pstmt.close();
                return deleteAffectedRows > 0;
            } else {
                pstmt.close();
                return false;
            }
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    Object[][] getAllLoginHistory(){
        try{
            Statement stmt = connection.createStatement();
            String sql;
            sql = "SELECT h.id, h.username, u.fullname, h.login_time FROM HISTORY_LOGIN h INNER JOIN USER u ON h.username = u.username WHERE u.is_locked != 2 ORDER BY h.login_time DESC";
            ResultSet rs = stmt.executeQuery(sql);
            List<Object[]> rows = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt("h.id");
                String username = rs.getString("h.username");
                String fullname = rs.getString("u.fullname");
                Date login_time = rs.getDate("h.login_time");
                Timestamp timestamp = new Timestamp(login_time.getTime());

                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String format_time = localDateTime.format(formatter);

                Object[] row = { id, username, fullname, format_time };
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

    Object[][] getAllUserFriend(){
        try{
            String sql = "WITH DirectFriends AS (SELECT U.username, CASE WHEN U.username = F1.username1 THEN F1.username2 ELSE F1.username1 END AS friend_username " +
                            "FROM USER U LEFT JOIN FRIEND F1 ON (U.username = F1.username1 OR U.username = F1.username2) AND F1.accepted = 1 WHERE U.is_locked != 2) " +
                            "SELECT U.username, U.fullname, U.creation_time, COUNT(DISTINCT DF.friend_username) AS direct_friends_count, " +
                            "COUNT(DISTINCT FF.friend_of_friend_username) AS friends_of_friends_count FROM USER U LEFT JOIN DirectFriends DF ON U.username = DF.username " +
                            "LEFT JOIN ((SELECT U.username AS user_username, F2.username2 AS friend_of_friend_username FROM USER U " +
                            "LEFT JOIN DirectFriends DF ON U.username = DF.username " +
                            "LEFT JOIN FRIEND F2 ON DF.friend_username = F2.username1 AND F2.accepted = 1 " +
                            "WHERE U.is_locked != 2 GROUP BY U.username, F2.username2) " +
                            "UNION " +
                            "(SELECT U.username AS user_username, F3.username1 AS friend_of_friend_username FROM USER U LEFT JOIN DirectFriends DF ON U.username = DF.username " +
                            "LEFT JOIN FRIEND F3 ON DF.friend_username = F3.username2 AND F3.accepted = 1 WHERE U.is_locked != 2 GROUP BY U.username, F3.username1) " +
                            ") FF ON U.username = FF.user_username WHERE U.is_locked != 2 GROUP BY U.username, U.fullname, U.creation_time";
            PreparedStatement stmt;
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Object[]> rows = new ArrayList<>();
            int i = 1;
            while(rs.next()){
                String username = rs.getString("U.username");
                String fullname = rs.getString("U.fullname");
                Date registration_time = rs.getDate("U.creation_time");
                int direct_friend_count = rs.getInt("direct_friends_count");
                int friends_of_friends_count = rs.getInt("friends_of_friends_count");
                Timestamp timestamp = new Timestamp(registration_time.getTime());

                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String format_time = localDateTime.format(formatter);

                Object[] row = {i, username,  fullname, format_time, direct_friend_count, friends_of_friends_count};
                rows.add(row);
                i++;
            }

            rs.close();
            stmt.close();

            Object[][] data = new Object[rows.size()][];
            rows.toArray(data);

            return data;
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    Object[][] searchUserFriend(String text1, String text2, String text3){
        try{
            PreparedStatement stmt = null;
            String sql = "WITH DirectFriends AS (SELECT U.username, CASE WHEN U.username = F1.username1 THEN F1.username2 ELSE F1.username1 END AS friend_username " +
                    "FROM USER U LEFT JOIN FRIEND F1 ON (U.username = F1.username1 OR U.username = F1.username2) AND F1.accepted = 1 WHERE U.is_locked != 2) " +
                    "SELECT U.username, U.fullname, U.creation_time, COUNT(DISTINCT DF.friend_username) AS direct_friends_count, " +
                    "COUNT(DISTINCT FF.friend_of_friend_username) AS friends_of_friends_count FROM USER U LEFT JOIN DirectFriends DF ON U.username = DF.username " +
                    "LEFT JOIN ((SELECT U.username AS user_username, F2.username2 AS friend_of_friend_username FROM USER U " +
                    "LEFT JOIN DirectFriends DF ON U.username = DF.username " +
                    "LEFT JOIN FRIEND F2 ON DF.friend_username = F2.username1 AND F2.accepted = 1 " +
                    "WHERE U.is_locked != 2 GROUP BY U.username, F2.username2) " +
                    "UNION " +
                    "(SELECT U.username AS user_username, F3.username1 AS friend_of_friend_username FROM USER U LEFT JOIN DirectFriends DF ON U.username = DF.username " +
                    "LEFT JOIN FRIEND F3 ON DF.friend_username = F3.username2 AND F3.accepted = 1 WHERE U.is_locked != 2 GROUP BY U.username, F3.username1) " +
                    ") FF ON U.username = FF.user_username WHERE U.is_locked != 2 ";

            if (text2.isEmpty()) {
                System.out.println("search by direct friend count");
                int friend_count = Integer.parseInt(text1);
                // search by direct friend count
                if (Objects.equals(text3, "Equal to")) {
                    sql += "GROUP BY U.username, U.fullname, U.creation_time HAVING direct_friends_count = ?";
                    stmt = connection.prepareStatement(sql);
                    stmt.setInt(1, friend_count);
                } else if (Objects.equals(text3, "Greater than")) {
                    sql += "GROUP BY U.username, U.fullname, U.creation_time HAVING direct_friends_count > ?";
                    stmt = connection.prepareStatement(sql);
                    stmt.setInt(1, friend_count);
                } else {
                    sql += "GROUP BY U.username, U.fullname, U.creation_time HAVING direct_friends_count < ?";
                    stmt = connection.prepareStatement(sql);
                    stmt.setInt(1, friend_count);
                }
            }
            else{
                if(text1.isEmpty()){
                    // search by fullname
                    System.out.println("search by fullname");
                    sql += "AND U.fullname LIKE ? GROUP BY U.username, U.fullname, U.creation_time";
                    stmt = connection.prepareStatement(sql);
                    stmt.setString(1, "%" + text2 + "%");
                }
                else {
                    // search by fullname + direct friend count
                    int friend_count = Integer.parseInt(text1);
                    System.out.println("search by username + direct friend count");
                    sql += "AND U.fullname LIKE ? ";
                    if (Objects.equals(text3, "Equal to")) {
                        sql += "GROUP BY U.username, U.fullname, U.creation_time HAVING direct_friends_count = ?";
                        stmt = connection.prepareStatement(sql);
                        stmt.setString(1, "%" + text2 + "%");
                        stmt.setInt(2, friend_count);
                    } else if (Objects.equals(text3, "Greater than")) {
                        sql += "GROUP BY U.username, U.fullname, U.creation_time HAVING direct_friends_count > ?";
                        stmt = connection.prepareStatement(sql);
                        stmt.setString(1, "%" + text2 + "%");
                        stmt.setInt(2, friend_count);
                    } else {
                        sql += "GROUP BY U.username, U.fullname, U.creation_time HAVING direct_friends_count < ?";
                        stmt = connection.prepareStatement(sql);
                        stmt.setString(1, "%" + text2 + "%");
                        stmt.setInt(2, friend_count);
                    }
                }
            }

            ResultSet rs = stmt.executeQuery();

            List<Object[]> rows = new ArrayList<>();
            int i = 1;
            while(rs.next()){
                String username = rs.getString("U.username");
                String fullname = rs.getString("U.fullname");
                Date registration_time = rs.getDate("U.creation_time");
                int direct_friend_count = rs.getInt("direct_friends_count");
                int friends_of_friends_count = rs.getInt("friends_of_friends_count");
                Timestamp timestamp = new Timestamp(registration_time.getTime());

                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String format_time = localDateTime.format(formatter);

                Object[] row = {i, username,  fullname, format_time, direct_friend_count, friends_of_friends_count};
                rows.add(row);
                i++;
            }
            rs.close();
            stmt.close();

            Object[][] data = new Object[rows.size()][];
            rows.toArray(data);

            return data;
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    int[] getMonthlyNewUser(String year){
        try{
            String sql = "SELECT MONTH(u.creation_time) as month, COUNT(DISTINCT u.username) as user_count " +
                    "FROM USER u " +
                    "WHERE YEAR(u.creation_time) = ? " +
                    "GROUP BY MONTH(u.creation_time) ORDER BY month ;";

            PreparedStatement stmt;
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, year);

            ResultSet rs = stmt.executeQuery();

            int[] user_count_month = new int[12];
            for (int i = 0; i < 12; i++)
                user_count_month[i] = 0;
            while(rs.next()){
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
}
