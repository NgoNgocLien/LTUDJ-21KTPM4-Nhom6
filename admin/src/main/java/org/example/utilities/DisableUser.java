package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static org.example.utilities.AdminSocket.socket;

public class DisableUser extends SwingWorker<Void, Void> {
    private String username;

    public DisableUser(String username) {
        this.username = username;
    }
    public static Object[][] request(String username, Socket socket) throws IOException, ClassNotFoundException {

        OutputStream outputStream = socket.getOutputStream();
        String msg = "disableUser" + "\n" + username;

        outputStream.write(msg.getBytes());
        System.out.println(msg);

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object[][] data = (Object[][]) objectInputStream.readObject();

        for (Object[] row : data) {
            for (Object element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

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