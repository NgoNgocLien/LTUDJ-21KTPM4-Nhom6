package org.example.utilities;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

import static org.example.utilities.AdminSocket.socket;

public class GetAllGroup extends SwingWorker<Void, Void> {

    public static Object[][] request(Socket socket) throws IOException, ClassNotFoundException {

        OutputStream outputStream = socket.getOutputStream();
        String method = "getAllGroup";
        System.out.println(method);
        outputStream.write(method.getBytes());
        System.out.println(method);

//        InputStream inputStream = socket.getInputStream();
//        inputStream.readNBytes(2);

        // receive object
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object[][] data = (Object[][]) objectInputStream.readObject();

//        objectInputStream.close();
        if (socket.isClosed())
            System.out.println("1");
        return data;
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            request(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}