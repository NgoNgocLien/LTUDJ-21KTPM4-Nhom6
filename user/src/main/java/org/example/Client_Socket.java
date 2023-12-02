package org.example;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client_Socket {
    static Socket socket;

    public static void start() {
//        try {
//            Scanner in = new Scanner(socket.getInputStream());
//            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//            Scanner scanner = new Scanner(System.in);
//
//            // Create a new thread for receiving messages from the server
//            new Thread(() -> {
//                while (true) {
//                    if (in.hasNextLine()) {
//                        String serverMessage = in.nextLine();
//                        System.out.println("Server: " + serverMessage);
//                    }
//                }
//            }).start();
//
//            // Main thread for sending messages to the server
//            while (true) {
//                String message = scanner.nextLine();
//                out.println(message);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            socket = new Socket("localhost", 145); // Connect to the server on localhost:12345
            System.out.println("Connected to the server.");

            // Close the socket
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loginRequest(String username, String password) throws IOException {
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        String userName_Pass = username + ":" + password;
        outputStream.write(userName_Pass.getBytes());

        byte[] buffer = new byte[1024];
        int bytesRead = inputStream.read(buffer);
        String response = new String(buffer, 0, bytesRead);
        System.out.println("SERVER: " + response);
    }

    void closeSocket() throws IOException {
        socket.close();
    }

    public static class LoginWorker extends SwingWorker<Void, Void> {
        private String username;
        private String password;

        public LoginWorker(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected Void doInBackground() throws Exception {
            try {
                loginRequest(username, password);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
