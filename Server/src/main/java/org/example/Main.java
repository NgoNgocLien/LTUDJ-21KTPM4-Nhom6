package org.example;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Server is running");
        UserServer userServer = new UserServer();
        AdminServer adminServer = new AdminServer();

        Thread userServerThread = new Thread(userServer::startServer);
        Thread adminServerThread = new Thread(adminServer::startServer);

        userServerThread.start();
        adminServerThread.start();

//        DBconnection dBconnection = new DBconnection();
//        dBconnection.connect();
//        System.out.println("Server is running");
//        chatServer server = new chatServer();
//        server.startServer();
    }
}