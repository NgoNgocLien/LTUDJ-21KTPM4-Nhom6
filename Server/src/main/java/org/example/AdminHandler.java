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
                System.out.println("method: " + method);

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

                    case "getAllReport":{
                        System.out.println("Admin get all report");

                        Object[][] data = dbcon.getAllReport();
//                        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(clientSocket.getOutputStream());
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(data);
                        objectOutputStream.flush();

                        for (Object[] row : data) {
                            for (Object element : row) {
                                System.out.print(element + " ");
                            }
                            System.out.println();
                        }

                        System.out.println("Data sent to client.");

                        break;
                    }

                    case "searchReport":{
                        System.out.println("Admin search report");

                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        String message = new String(buffer, 0, bytesRead);

                        String[] messageSplit = message.split("\n");
                        String username = messageSplit[0];
                        System.out.println(username);
                        String date = messageSplit[1];
                        if (date.equals("(dd-mm-yyyy)"))
                            date = "";

                        Object[][] data1 = dbcon.searchReport(username, date.split("-"));
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

                    case "disableUser":{
                        System.out.println("Admin disable user");

                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        String username = new String(buffer, 0, bytesRead);

                        dbcon.disableUser(username);
                        Object[][] data = dbcon.getAllReport();
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(data);
                        objectOutputStream.flush();

                        System.out.println("Data sent to client.");

                        break;
                    }

                    case "getAllActiveUser":{
                        System.out.println("Admin get all active user");

                        Object[][] data1 = dbcon.getAllActiveUser();
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

                    case "searchActiveUser":{
                        System.out.println("Admin search active user");

                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        String message = new String(buffer, 0, bytesRead);

                        String[] messageSplit = message.split("\n");
                        String start_date = messageSplit[0];
                        String end_date = messageSplit[1];

                        Object[][] data1 = dbcon.searchActiveUser(start_date, end_date);
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

                    case "getMonthlyActiveUser":{
                        System.out.println("Admin get monthly active user");

                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        String year = new String(buffer, 0, bytesRead);

                        int[] data1 = dbcon.getMonthlyActiveUser(year);
//                        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(clientSocket.getOutputStream());
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(data1);
                        objectOutputStream.flush();

                        for (int row : data1) {
                            System.out.println(row);
                        }

                        System.out.println("Data sent to client.");

                        break;
                    }

                    case "getAllUser":{
                        System.out.println("Admin get all user");

                        Object[][] data1 = dbcon.getAllUser();
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

                    case "searchUser":{
                        System.out.println("Admin search user");

                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        String message = new String(buffer, 0, bytesRead);

                        String[] messageSplit = message.split("\n");

                        String username = messageSplit[0];

                        String fullname = "";
                        String selectedString = "";
                        if (messageSplit.length > 2) {
                            fullname = messageSplit[1];
                            selectedString = messageSplit[2];
                        }

                        Object[][] data1 = dbcon.searchUser(username, fullname, selectedString);
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

                    case "addNewUser":{
                        System.out.println("Admin add new user");

                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        String message = new String(buffer, 0, bytesRead);

                        String[] messageSplit = message.split("\n");

                        String username = messageSplit[0];

                        String fullname = "";
                        String password = "";
                        String birthdate = "";
                        String gender = "";
                        String address = "";
                        String email = "";
                        if (messageSplit.length > 6) {
                            password = messageSplit[1];
                            fullname = messageSplit[2];
                            address = messageSplit[3];
                            birthdate = messageSplit[4];
                            gender = messageSplit[5];
                            email = messageSplit[6];
                        }

                        Boolean success = dbcon.addNewUser(username, password, fullname, address, birthdate, gender, email);
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(success);
                        objectOutputStream.flush();

                        System.out.println("Data sent to client.");

                        break;
                    }

                    case "getUserByUsername":{
                        System.out.println("Admin get user by username");

                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        String username = new String(buffer, 0, bytesRead);

                        Object[][] data1 = dbcon.getUserByUsername(username);
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

                    case "getUserHistoryLogin":{
                        System.out.println("Admin get user history login");

                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        String username = new String(buffer, 0, bytesRead);

                        Object[][] data1 = dbcon.getUserHistoryLogin(username);
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

                    case "getUserFriends":{
                        System.out.println("Admin get user friends");

                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        String username = new String(buffer, 0, bytesRead);
                        System.out.println("username: " + username);
                        Object[][] data1 = dbcon.getUserFriends(username);
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

                    case "updateUser":{
                        System.out.println("Admin update user");

                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        String message = new String(buffer, 0, bytesRead);

                        String[] messageSplit = message.split("\n");

                        String username = messageSplit[0];

                        String fullname = "";
                        String birthdate = "";
                        String gender = "";
                        String address = "";
                        String email = "";
                        if (messageSplit.length > 5) {
                            fullname = messageSplit[1];
                            address = messageSplit[2];
                            birthdate = messageSplit[3];
                            gender = messageSplit[4];
                            email = messageSplit[5];
                        }
                        System.out.println("birthdate handler: " + birthdate);
                        Boolean success = dbcon.updateUser(username, fullname, address, birthdate, gender, email);
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(success);
                        objectOutputStream.flush();

                        System.out.println("Data sent to client.");

                        break;
                    }

                    case "getAllEmail":{
                        System.out.println("Admin get all email");
                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        String username = new String(buffer, 0, bytesRead);

                        Object[][] data1 = dbcon.getAllEmail(username);
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

                    case "deleteUser":{
                        System.out.println("Admin delete user");

                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        String username = new String(buffer, 0, bytesRead);

                        Boolean success = dbcon.deleteUser(username);
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(success);
                        objectOutputStream.flush();

                        System.out.println("Data sent to client.");

                        break;
                    }

                    case "enableUser":{
                        System.out.println("Admin enable user");

                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        String username = new String(buffer, 0, bytesRead);

                        Boolean success = dbcon.enableUser(username);
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(success);
                        objectOutputStream.flush();

                        System.out.println("Data sent to client.");

                        break;
                    }

                    case "disableUserManage":{
                        System.out.println("Admin disable user manage");

                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        String username = new String(buffer, 0, bytesRead);

                        Boolean success = dbcon.disableUserManage(username);
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(success);
                        objectOutputStream.flush();

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
