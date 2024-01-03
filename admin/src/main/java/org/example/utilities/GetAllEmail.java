package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.example.utilities.AdminSocket.socket;

public class GetAllEmail extends SwingWorker<Void, Void> {
    private String username;
    public GetAllEmail(String username){
        this.username = username;
    }
    public static Object[][] request(String username, Socket socket) throws IOException, ClassNotFoundException {

        OutputStream outputStream = socket.getOutputStream();
        String msg = "getAllEmail" + "\n" + username;
        outputStream.write(msg.getBytes());
        System.out.println(msg);

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object[][] data = (Object[][]) objectInputStream.readObject();

        if (socket.isClosed())
            System.out.println("1");
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
