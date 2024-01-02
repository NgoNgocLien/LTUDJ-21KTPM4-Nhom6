package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.example.utilities.AdminSocket.socket;

public class GetMonthlyNewUser extends SwingWorker<Void, Void> {
    private String year;
    public GetMonthlyNewUser(String year) {
        this.year = year;
    }
    public static int[] request(String year, Socket socket) throws IOException, ClassNotFoundException {

        OutputStream outputStream = socket.getOutputStream();
        String msg = "getMonthlyNewUser" + "\n" + year;

        outputStream.write(msg.getBytes());
        System.out.println(msg);

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        int[] data = (int[]) objectInputStream.readObject();

        if (socket.isClosed())
            System.out.println("1");

        return data;
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            request(year, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
