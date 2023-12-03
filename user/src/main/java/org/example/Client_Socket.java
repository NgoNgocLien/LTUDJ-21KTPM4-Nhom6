package org.example;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client_Socket {
    static Socket socket;

    public static Socket getSocket() {
        return socket;
    }

    public static void start() {
        try {
            socket = new Socket("localhost", 1435); // Connect to the server on localhost:12345
            System.out.println(socket);
            System.out.println("Server connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void closeSocket() throws IOException {
        socket.close();
    }


}
