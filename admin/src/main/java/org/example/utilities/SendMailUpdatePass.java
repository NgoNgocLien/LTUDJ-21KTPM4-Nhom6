package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.example.utilities.AdminSocket.socket;

public class SendMailUpdatePass extends SwingWorker<Void, Void> {
    private String email;

    public SendMailUpdatePass(String email) {
        this.email = email;
    }
    public static Boolean request(String email, Socket socket) throws IOException, ClassNotFoundException {

        OutputStream outputStream = socket.getOutputStream();
        String msg = "sendMailUpdatePass" + "\n" + email;
        outputStream.write(msg.getBytes());
        System.out.println(msg);

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Boolean success = (Boolean) objectInputStream.readObject();

        if (socket.isClosed())
            System.out.println("1");
        return success;
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            request(email, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
