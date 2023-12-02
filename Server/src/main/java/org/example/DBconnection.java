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

}
