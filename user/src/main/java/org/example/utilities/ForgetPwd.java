package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static org.example.utilities.Client_Socket.socket;

public class ForgetPwd extends SwingWorker<Void, Void> {
    private String email;
    private String username;

    public ForgetPwd(String username, String email) {
        this.email = email;
        this.username =username;
    }

    public static boolean request(String username, String email, Socket socket) throws IOException, ClassNotFoundException {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(("forgetpassword\n" + username + "\n" + email + "\n").getBytes());

        InputStream inputStream = socket.getInputStream();
        byte[] buffer = new byte[1024];
//        inputStream.read(buffer);
        int bytesRead = inputStream.read(buffer);
        String response = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
        System.out.println(response);
        return response.contains("true");
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            request(username, email, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}