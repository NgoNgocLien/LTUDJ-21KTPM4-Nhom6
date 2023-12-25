package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.example.utilities.AdminSocket.socket;

public class EnableUser extends SwingWorker<Void, Void> {
    private String username;

    public EnableUser(String username) {
        this.username = username;
    }
    public static Boolean request(String username, Socket socket) throws IOException, ClassNotFoundException {

        OutputStream outputStream = socket.getOutputStream();
        String method = "enableUser";
        String message = username;
        System.out.println(method);
        outputStream.write(method.getBytes());
        outputStream.write(message.getBytes());

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Boolean data = (Boolean) objectInputStream.readObject();

        return data;
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
