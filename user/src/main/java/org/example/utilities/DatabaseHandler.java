package org.example.utilities;

import org.example.models.User;

import java.sql.Connection;
import java.util.ArrayList;

public class DatabaseHandler {
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    // Database URL
    private final String DB_URL = "jdbc:mysql://localhost:3306/db_chat";
    // Database credentials
    private final String USER = "root";
    private final String PASS = "1234";
    private Connection conn = null;

    // public ArrayList<User> getChatList(String username) {

    // }

}
