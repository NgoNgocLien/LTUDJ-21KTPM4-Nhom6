package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class UserServer {
    private final List<UserHandler> clients = new ArrayList<>();

    public void startServer() {
        try {
            ServerSocket serverSocketUser = new ServerSocket(1435);
            System.out.println("Server started. Listening on port 1435...");

            MainDatabase mainDB = new MainDatabase();
            mainDB.connect();
            UserDatabase userDB = new UserDatabase(mainDB);

            while (true) {
                Socket clientSocket = serverSocketUser.accept();
                System.out.println("New user connected: " + clientSocket);

                UserHandler clientHandler = new UserHandler(this, clientSocket, userDB);
                clients.add(clientHandler);

                new Thread(clientHandler).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void broadcastMessage(String message, UserHandler sender) {
        for (UserHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public void removeClient(UserHandler client) {
        clients.remove(client);
    }

}
