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
            AdminDatabase dbcon = new AdminDatabase();
            dbcon.connect();
            while (true) {
//                 Get the input and output streams of the client socket
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

                System.out.println("check1");
                // Read data from the client
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                String method = new String(buffer, 0, bytesRead);
                System.out.println("check2");
                System.out.println(method);

                switch (method){
                    case "getAllGroup":{
                        System.out.println("Admin get all group");

                        Object[][] data1 = dbcon.getAllGroup();
//                        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(clientSocket.getOutputStream());
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(data1);
                        objectOutputStream.flush();

                        for (Object[] row : data1) {
                            for (Object element : row) {
                                System.out.print(element + " ");
                            }
                            System.out.println();
                        }

                        System.out.println("Data sent to client.");

                        break;
                    }

                    case "getAllAdmin":{
                        System.out.println("Admin get all admin");
                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        String selected_id = new String(buffer, 0, bytesRead);

//                        String selected_id = "1";
                        Object[][] data2 = dbcon.getAllAdmin(selected_id);
//                        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(clientSocket.getOutputStream());
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(data2);
                        objectOutputStream.flush();

                        for (Object[] row : data2) {
                            for (Object element : row) {
                                System.out.print(element + " ");
                            }
                            System.out.println();
                        }

                        System.out.println("Data sent to client.");

                        break;
                    }

                    case "getAllMember":{
                        System.out.println("Admin get all member");
                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        String selected_id = new String(buffer, 0, bytesRead);

//                        String selected_id = "1";
                        Object[][] data2 = dbcon.getAllMember(selected_id);
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(data2);
                        objectOutputStream.flush();

                        for (Object[] row : data2) {
                            for (Object element : row) {
                                System.out.print(element + " ");
                            }
                            System.out.println();
                        }

                        System.out.println("Data sent to client.");

                        break;
                    }

                    case "searchGroupName":{
                        System.out.println("Admin search group name");
                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        String name = new String(buffer, 0, bytesRead);

                        Object[][] data2 = dbcon.searchGroupName(name);

                        objectOutputStream.reset();
                        objectOutputStream.writeObject(data2);
                        objectOutputStream.flush();

                        for (Object[] row : data2) {
                            for (Object element : row) {
                                System.out.print(element + " ");
                            }
                            System.out.println();
                        }

                        System.out.println("Data sent to client.");

                        break;
                    }

                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("ok");
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
