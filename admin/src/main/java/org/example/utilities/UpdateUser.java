package org.example.utilities;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.example.utilities.AdminSocket.socket;

public class UpdateUser extends SwingWorker<Void, Void> {
    private String username;
    private String fullname;
    private String address;
    private String birthdate;
    private String gender;
    private String email;

    public UpdateUser(String username, String fullname, String address, String birthdate, String gender, String email) {
        this.username = username;
        this.fullname = fullname;
        this.address = address;
        this.birthdate = birthdate;
        this.gender = gender;
        this.email = email;
    }
    public static Boolean request(String username, String fullname , String address, String birthdate, String gender, String email, Socket socket) throws IOException, ClassNotFoundException {
        System.out.println("birthdate utilities: " + birthdate);
        OutputStream outputStream = socket.getOutputStream();
        String method = "updateUser";
        String message = username + "\n" + fullname + "\n" + address + "\n" + birthdate + "\n" + gender + "\n" + email ;
        System.out.println(method);
        outputStream.write(method.getBytes());
        outputStream.write(message.getBytes());

        // receive object
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Boolean success = (Boolean) objectInputStream.readObject();

        return success;
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            request(username, fullname, address, birthdate, gender, email, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
