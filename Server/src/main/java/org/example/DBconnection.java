package org.example;

import java.sql.*;
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
}
