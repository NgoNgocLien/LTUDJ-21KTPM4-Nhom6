package org.example.utilities;

import static org.example.utilities.AdminSocket.socket;
import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class GetUserHistoryLogin extends SwingWorker<Void, Void> {
    private String username;

    public GetUserHistoryLogin(String username) {
        this.username = username;
    }
    public static Object[][] request(String username, Socket socket) throws IOException, ClassNotFoundException {
        OutputStream outputStream = socket.getOutputStream();
        String msg = "getUserHistoryLogin" + "\n" + username;
        outputStream.write(msg.getBytes());
        System.out.println(msg);

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object[][] data = (Object[][]) objectInputStream.readObject();

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
