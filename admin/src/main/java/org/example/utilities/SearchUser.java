package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.example.utilities.AdminSocket.socket;

public class SearchUser extends SwingWorker<Void, Void> {
    private String username;
    private String fullname;
    private String selectedString;

    public SearchUser(String username, String fullname, String selectedString) {
        this.username = username;
        this.fullname = fullname;
        this.selectedString = selectedString;
    }
    public static Object[][] request(String username, String fullname , String selectedString, Socket socket) throws IOException, ClassNotFoundException {

        OutputStream outputStream = socket.getOutputStream();
        String msg = "searchUser" + "\n" + username + "\n" + fullname + "\n" + selectedString;
        outputStream.write(msg.getBytes());
        System.out.println(msg);

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object[][] data = (Object[][]) objectInputStream.readObject();

        return data;
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            request(username, fullname, selectedString, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
