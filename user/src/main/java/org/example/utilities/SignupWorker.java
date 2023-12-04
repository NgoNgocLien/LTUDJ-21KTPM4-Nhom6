package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.example.utilities.Client_Socket.socket;

public class SignupWorker extends SwingWorker<Void, Void> {
    private String username, password, fullname, address, gender, birthdate, email;

//    public SignupWorker(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }

    public static boolean signupRequest(String username, String password, String fullname, String address, String gender, String birthdate, String email, Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();


        String signupData = username + "\n" + password + "\n" + fullname + "\n" + address + "\n" + gender + "\n" + birthdate + "\n" + email;
        outputStream.write(signupData.getBytes());
        System.out.println("send infor to server");

        byte[] buffer = new byte[1024];
        int bytesRead = inputStream.read(buffer);
        String response = new String(buffer, 0, bytesRead);

        return response.equals("true");
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            signupRequest(username, password, fullname, address, gender, birthdate, email, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}