package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.example.utilities.AdminSocket.socket;

public class UpdatePassUser extends SwingWorker<Void, Void> {
    private String username;
    private String new_pwd;

    public UpdatePassUser(String username, String new_pwd) {
        this.username = username;
        this.new_pwd = new_pwd;
    }
    public static Boolean request(String username, String new_pwd, Socket socket) throws IOException, ClassNotFoundException {
        OutputStream outputStream = socket.getOutputStream();
        String msg = "updatePassUser" + "\n" + username + "\n" + new_pwd;
        outputStream.write(msg.getBytes());
        System.out.println(msg);

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Boolean success = (Boolean) objectInputStream.readObject();

        return success;
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            request(username, new_pwd, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
