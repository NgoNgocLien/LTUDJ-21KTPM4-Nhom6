package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.example.utilities.AdminSocket.socket;

public class GetAllMember extends SwingWorker<Void, Void> {
    private String selected_id;

    public GetAllMember(String selected_id) {
        this.selected_id = selected_id;
    }
    public static Object[][] request(String selected_id , Socket socket) throws IOException, ClassNotFoundException {

        OutputStream outputStream = socket.getOutputStream();
        String method = "getAllMember";
        System.out.println(socket);
        outputStream.write(method.getBytes());
        outputStream.write(selected_id.getBytes());
        System.out.println(socket);

        // receive object
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object[][] data = (Object[][]) objectInputStream.readObject();
        System.out.println(socket);
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