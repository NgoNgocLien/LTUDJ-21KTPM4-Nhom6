package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.example.utilities.Client_Socket.socket;

public class LoginWorker extends SwingWorker<Void, Void> {
    private String username;
    private String password;

    public LoginWorker(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static boolean loginRequest(String username, String password, Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        String userName_Pass = username + ":" + password;
        outputStream.write(userName_Pass.getBytes());

        byte[] buffer = new byte[1024];
        int bytesRead = inputStream.read(buffer);
        String response = new String(buffer, 0, bytesRead);
        System.out.println(response);
        return response.equals("true");
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            loginRequest(username, password, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}