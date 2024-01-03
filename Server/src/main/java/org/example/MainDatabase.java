package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainDatabase {
    private Connection connection;
    public MainDatabase() {
        connection = null;
    }
    void connect() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String DB_URL = "jdbc:mysql://localhost/db_chat?allowPublicKeyRetrieval=true&useSSL=false";
            final String USER = "root";
            final String PASSWORD = "1234";
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected to DBMS!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection() {
        return connection;
    }
}
