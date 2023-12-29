package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.example.utilities.AdminSocket.socket;

public class SearchActiveUser extends SwingWorker<Void, Void> {
    private String start_date;
    private String end_date;

    public SearchActiveUser(String start_date, String end_date) {
        this.start_date = start_date;
        this.end_date = end_date;
    }
    public static Object[][] request(String start_date, String end_date , Socket socket) throws IOException, ClassNotFoundException {

        OutputStream outputStream = socket.getOutputStream();
        String msg = "searchActiveUser" + "\n" + start_date + " \n" + end_date;

        outputStream.write(msg.getBytes());
        System.out.println(msg);


        // receive object
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object[][] data = (Object[][]) objectInputStream.readObject();

        return data;
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            request(start_date, end_date, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}