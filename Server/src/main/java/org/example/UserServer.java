package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class UserServer {
    private final List<ClientHandler> clients = new ArrayList<>();

    public void startServer() {
        try {
            ServerSocket serverSocketUser = new ServerSocket(1435);
            System.out.println("Server started. Listening on port 1435...");

            while (true) {

                Socket clientSocket = serverSocketUser.accept();
                System.out.println("New user connected: " + clientSocket);

                ClientHandler clientHandler = new ClientHandler(this, clientSocket);
                clients.add(clientHandler);

                new Thread(clientHandler).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

}