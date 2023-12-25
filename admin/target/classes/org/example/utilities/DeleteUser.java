package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.example.utilities.AdminSocket.socket;

public class DeleteUser extends SwingWorker<Void, Void> {
    private String username;

    public DeleteUser(String username) {
        this.username = username;
    }
    public static Boolean request(String username, Socket socket) throws IOException, ClassNotFoundException {

        OutputStream outputStream = socket.getOutputStream();
        String method = "deleteUser";
        System.out.println(method);
        outputStream.write(method.getBytes());
        outputStream.write(username.getBytes());

        // receive object
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Boolean success = (Boolean) objectInputStream.readObject();

        return success;
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            request(username, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}