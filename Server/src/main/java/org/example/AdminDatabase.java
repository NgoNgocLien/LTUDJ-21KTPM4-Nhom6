package org.example;

import java.sql.*;
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


}
