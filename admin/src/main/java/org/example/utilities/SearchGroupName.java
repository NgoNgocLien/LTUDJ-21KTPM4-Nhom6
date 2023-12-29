package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.example.utilities.AdminSocket.socket;

public class SearchGroupName extends SwingWorker<Void, Void> {
    private String name;

    public SearchGroupName(String name) {
        this.name = name;
    }
    public static Object[][] request(String name , Socket socket) throws IOException, ClassNotFoundException {

        OutputStream outputStream = socket.getOutputStream();
        String msg = "searchGroupName" + "\n" + name;
        outputStream.write(msg.getBytes());
        System.out.println(msg);

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object[][] data = (Object[][]) objectInputStream.readObject();

        return data;
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            request(name, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}