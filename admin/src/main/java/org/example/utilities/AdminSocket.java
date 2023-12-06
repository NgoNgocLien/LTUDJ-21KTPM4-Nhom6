package org.example.utilities;

import java.io.IOException;
import java.net.Socket;

public class AdminSocket {
    static Socket socket;

    public static Socket getSocket() {
        return socket;
    }

    public static void start() {
        try {
            socket = new Socket("localhost", 1235); // Connect to the server on localhost:1235
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
