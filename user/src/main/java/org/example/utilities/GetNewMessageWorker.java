package org.example.utilities;

import org.example.models.Message;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GetNewMessageWorker extends SwingWorker<Void, Void> {
    private Socket socket;
    private String myUsername;
    private String friendUsername;
    private LocalDateTime lastMessage;
    public GetNewMessageWorker(String myUsername, String friendUsername, LocalDateTime lastMessage) {
        this.myUsername = myUsername;
        this.friendUsername = friendUsername;
        this.lastMessage = lastMessage;
    }
    public static ArrayList<Message> request(Socket socket, String myUsername, String friendUsername, LocalDateTime lastMessage) throws IOException, ClassNotFoundException {
        OutputStream outputStream = socket.getOutputStream();
        String msg = "getNewFriendMessage\n" + myUsername + "\n" + friendUsername + "\n" + lastMessage.toString() + "\n";
        outputStream.write(msg.getBytes());

        // receive object
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object[][] data = (Object[][]) objectInputStream.readObject();

        ArrayList<Message> messages = new ArrayList<>();
        if (data != null) {
            for (int i = 0; i < data.length; i++) {
                Message message = new Message((Integer) data[i][0], (String) data[i][1], (String) data[i][2], (Integer) data[i][3], (String) data[i][4], LocalDateTime.parse((String) data[i][5]), (Boolean) data[i][6]);
                messages.add(message);
                System.out.println(data[i][0] + " " + data[i][1] + " " + data[i][2] + " " + data[i][3] + " " + data[i][4] + " " + data[i][5] + " " + data[i][6]);
            }
        }
        return messages;
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            request(socket, myUsername, friendUsername, lastMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
