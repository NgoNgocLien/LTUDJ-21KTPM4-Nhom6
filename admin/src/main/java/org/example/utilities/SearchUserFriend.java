package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.example.utilities.AdminSocket.socket;

public class SearchUserFriend extends SwingWorker<Void, Void> {
    private String directFriendCount;
    private String fullname;
    private String selectedString;

    public SearchUserFriend(String directFriendCount, String fullname, String selectedString) {
        this.directFriendCount = directFriendCount;
        this.fullname = fullname;
        this.selectedString = selectedString;
    }
    public static Object[][] request(String directFriendCount, String fullname , String selectedString, Socket socket) throws IOException, ClassNotFoundException {

        OutputStream outputStream = socket.getOutputStream();
        String msg = "searchUserFriend" + "\n" + directFriendCount + "\n" + fullname + "\n" + selectedString;
        outputStream.write(msg.getBytes());
        System.out.println(msg);

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object[][] data = (Object[][]) objectInputStream.readObject();

        return data;
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            request(directFriendCount, fullname, selectedString, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}