package org.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class DBconnection {
    Connection connection;

    DBconnection() {
        this.connection = null;
    }

    void connect() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String DB_URL = "jdbc:mysql://localhost/?allowPublicKeyRetrieval=true&useSSL=false";
            final String USER = "root";
            final String PASSWORD = "admin";
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected to DBMS!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
}
