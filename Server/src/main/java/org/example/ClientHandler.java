package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private final chatServer server;
    private final Socket clientSocket;
    private final Scanner input;
    private final PrintWriter output;

    public ClientHandler(chatServer server, Socket clientSocket) throws IOException {
        this.server = server;
        this.clientSocket = clientSocket;
        this.input = new Scanner(clientSocket.getInputStream());
        this.output = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            DBconnection dbcon = new DBconnection();
            dbcon.connect();
            while (true) {
//                 Get the input and output streams of the client socket
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();

                // Read data from the client
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                String clientMessage = new String(buffer, 0, bytesRead);
                String[] parts = clientMessage.split(":");
                String response_login;
                if (dbcon.login(parts[0], parts[1])) {
                    response_login = "true";
                } else
                    response_login = "false";
                outputStream.write(response_login.getBytes());


                // Send a response back to the client
//                String message = input.nextLine();
//                if (message.equalsIgnoreCase("/quit")) {
//                    server.removeClient(this);
//                    break;
//                }

//                server.broadcastMessage("Client " + clientSocket + ": " + message, this);
            }
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                input.close();
                output.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        output.println(message);
    }
}
