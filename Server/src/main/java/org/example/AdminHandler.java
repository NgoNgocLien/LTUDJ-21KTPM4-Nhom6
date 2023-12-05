package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class AdminHandler implements Runnable {
    private final AdminServer server;
    private final Socket clientSocket;
    private final Scanner input;
    private final PrintWriter output;

    public AdminHandler(AdminServer server, Socket clientSocket) throws IOException {
        this.server = server;
        this.clientSocket = clientSocket;
        this.input = new Scanner(clientSocket.getInputStream());
        this.output = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            System.out.println("check*");
            AdminDatabase dbcon = new AdminDatabase();
            dbcon.connect();
            System.out.println("check0");
            while (true) {
//                 Get the input and output streams of the client socket
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();

                System.out.println("check1");
                // Read data from the client
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                String method = new String(buffer, 0, bytesRead);
                System.out.println("check2");
                System.out.println(method);
                System.out.println("check3");

//                outputStream.write("ok".getBytes());

                switch (method){
                    case "getAllGroup":
                        System.out.println("Admin get all group");

                        Object[][] data = dbcon.getAllGroup();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                        objectOutputStream.writeObject(data);
                        objectOutputStream.flush();

                        for (Object[] row : data) {
                            for (Object element : row) {
                                System.out.print(element + " ");
                            }
                            System.out.println();
                        }

                        System.out.println("Data sent to client.");

                        objectOutputStream.close();
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            input.close();
            output.close();
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendMessage(String message) {
        output.println(message);
    }
}
