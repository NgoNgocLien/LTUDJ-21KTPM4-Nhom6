package org.example.utilities;

import static org.example.utilities.AdminSocket.socket;
import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class AddNewUser extends SwingWorker<Void, Void> {
    private String username;
    private String fullname;
    private String password;
    private String birthdate;
    private String gender;
    private String address;
    private String email;

    public AddNewUser(String username, String password, String fullname, String birthdate, String gender, String address, String email ) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.email = email;
    }
    public static Boolean request(String username, String password, String fullname , String address, String birthdate, String gender, String email, Socket socket) throws IOException, ClassNotFoundException {

        OutputStream outputStream = socket.getOutputStream();
        String msg = "addNewUser" + "\n" + username + "\n" +  password + "\n" + fullname + "\n" + address + "\n" + birthdate + "\n" + gender + "\n" + email ;
        outputStream.write(msg.getBytes());
        System.out.println(msg);

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Boolean success = (Boolean) objectInputStream.readObject();

        return success;
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            request(username, password, fullname, address, birthdate, gender, email, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
