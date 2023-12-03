package org.example;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        DBconnection dBconnection = new DBconnection();
        dBconnection.connect();
        System.out.println("Server is running");
        chatServer server = new chatServer();
        server.startServer();
    }
}