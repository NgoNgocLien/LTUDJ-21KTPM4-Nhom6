package org.example.utilities;

import org.example.models.Message;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GetGroupMessageWorker extends SwingWorker<ArrayList<Message>, Void> {
    private Socket socket;
    private String myUsername;
    private int idGroup;
    private LocalDateTime lastMessage;
    public GetGroupMessageWorker(String myUsername, int idGroup, LocalDateTime lastMessage) {
        this.myUsername = myUsername;
        this.idGroup = idGroup;
        this.lastMessage = lastMessage;
    }
    public static ArrayList<Message> request(Socket socket, String myUsername, int idGroup, LocalDateTime lastMessage) throws IOException, ClassNotFoundException {
        OutputStream outputStream = socket.getOutputStream();
        String msg = "getGroupMessage\n" + myUsername + "\n" + idGroup + "\n" + lastMessage.toString() + "\n";
        outputStream.write(msg.getBytes());

        // receive object
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object[][] data = (Object[][]) objectInputStream.readObject();

        ArrayList<Message> messages = new ArrayList<>();
        if (data != null) {
            if (data.length == 0) {
                System.out.println("data.length == 0");
            }
            for (int i = 0; i < data.length; i++) {
                Message message = new Message((Integer) data[i][0], (String) data[i][1], (String) data[i][2], (Integer) data[i][3], (String) data[i][4], LocalDateTime.parse((String) data[i][5]), (Boolean) data[i][6]);
                messages.add(message);
                System.out.println(data[i][0] + " " + data[i][1] + " " + data[i][2] + " " + data[i][3] + " " + data[i][4] + " " + data[i][5] + " " + data[i][6]);
            }
        } else {
            System.out.println("null");
        }
        return messages;
    }

    @Override
    protected ArrayList<Message> doInBackground() throws Exception {
        try {
            return request(socket, myUsername, idGroup, lastMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
