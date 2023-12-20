package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.example.utilities.Client_Socket.socket;

public class ForgetPwd extends SwingWorker<Void, Void> {
    private String email;

    public ForgetPwd(String email) {
        this.email = email;
    }

    public static boolean request(String email, Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(email.getBytes());

        byte[] buffer = new byte[1024];
        int bytesRead = inputStream.read(buffer);
        String response = new String(buffer, 0, bytesRead);
        System.out.println(response);
        return response.equals("true");
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            request(email, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}