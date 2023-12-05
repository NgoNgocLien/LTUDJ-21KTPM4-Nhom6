package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AdminServer {
    private final List<AdminHandler> admins = new ArrayList<>();

    public void startServer() {
        int  adminPort = 1235;

        try {
            ServerSocket serverSocketAdmin = new ServerSocket(1235);

            System.out.println("Server started. Listening on port 1235...");

            while (true) {

                Socket adminSocket = serverSocketAdmin.accept();
                System.out.println("New admin connected: " + adminSocket);

                AdminHandler adminHandler = new AdminHandler(this, adminSocket);
                admins.add(adminHandler);

                new Thread(adminHandler).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(String message, ClientHandler sender) {
//        for (ClientHandler client : clients) {
//            if (client != sender) {
//                client.sendMessage(message);
//            }
//        }
    }

    public void removeAdmin(AdminHandler client) {
        admins.remove(client);
    }
}
