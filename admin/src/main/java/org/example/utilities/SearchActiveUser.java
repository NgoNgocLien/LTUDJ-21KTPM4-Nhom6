package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.example.utilities.AdminSocket.socket;

public class SearchReport extends SwingWorker<Void, Void> {
    private String username;
    private String date;

    public SearchReport(String username, String date) {
        this.username = username;
        this.date = date;
    }
    public static Object[][] request(String username, String date , Socket socket) throws IOException, ClassNotFoundException {

        OutputStream outputStream = socket.getOutputStream();
        String method = "searchReport";
        String message = username + "\n" + date;
        System.out.println(method);
        outputStream.write(method.getBytes());
        outputStream.write(message.getBytes());


        // receive object
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object[][] data = (Object[][]) objectInputStream.readObject();

        return data;
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            request(username, date, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}