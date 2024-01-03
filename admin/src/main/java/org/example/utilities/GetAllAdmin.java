package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.example.utilities.AdminSocket.socket;

public class GetAllAdmin extends SwingWorker<Void, Void> {
    private String selected_id;

    public GetAllAdmin(String selected_id) {
        this.selected_id = selected_id;
    }
    public static Object[][] request(String selected_id , Socket socket) throws IOException, ClassNotFoundException {

        OutputStream outputStream = socket.getOutputStream();
        String msg = "getAllAdmin" + "\n" + selected_id;
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
            request(selected_id, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}