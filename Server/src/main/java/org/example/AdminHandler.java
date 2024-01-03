package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class AdminHandler implements Runnable {
    private final AdminServer server;
    private final Socket clientSocket;
    private final AdminDatabase dbcon;
    private final Scanner input;
    private final PrintWriter output;

    public AdminHandler(AdminServer server, Socket clientSocket, AdminDatabase dbcon) throws IOException {
        this.server = server;
        this.clientSocket = clientSocket;
        this.dbcon = dbcon;

        this.input = new Scanner(clientSocket.getInputStream());
        this.output = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (true) {
//                 Get the input and output streams of the client socket
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

                System.out.println("check1");

                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                String msg = new String(buffer, 0, bytesRead);

                System.out.println("msg: " + msg);

                String msgArr[] = msg.split("\n");
                System.out.println("check2");

                switch (msgArr[0]){
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

                        Object[][] data2 = dbcon.getAllAdmin(msgArr[1]);
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

                        Object[][] data2 = dbcon.getAllMember(msgArr[1]);
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

                        Object[][] data2 = dbcon.searchGroupName(msgArr[1]);

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

                        String username = msgArr[1];
                        System.out.println(username);
                        String date = msgArr[2];
                        if (date.equals("(dd-mm-yyyy)"))
                            date = "";
                        System.out.println(date);

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

                        dbcon.disableUser(msgArr[1]);
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

                        String start_date = msgArr[1];
                        String end_date = msgArr[2];

                        System.out.println(start_date + " & " + end_date);
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

                        int[] data1 = dbcon.getMonthlyActiveUser(msgArr[1]);
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

                        String username = msgArr[1];
                        String fullname = msgArr[2];
                        String selectedString = msgArr[3];
                        System.out.println(username + " & " + fullname + " & " + selectedString);
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

                        String username = msgArr[1];
                        String password = msgArr[2];
                        String fullname = msgArr[3];
                        String address = msgArr[4];
                        String birthdate = msgArr[5];
                        String gender = msgArr[6];
                        String email = msgArr[7];

                        Boolean success = dbcon.addNewUser(username, password, fullname, address, birthdate, gender, email);
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(success);
                        objectOutputStream.flush();

                        System.out.println("Data sent to client.");

                        break;
                    }

                    case "getUserByUsername":{
                        System.out.println("Admin get user by username");

                        String username = msgArr[1];
                        System.out.println(username);
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

                        String username = msgArr[1];

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

                        String username = msgArr[1];

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

                        String username = msgArr[1];
                        String fullname = msgArr[2];
                        String address = msgArr[3];
                        String birthdate = msgArr[4];
                        String gender = msgArr[5];
                        String email = msgArr[6];

                        Boolean success = dbcon.updateUser(username, fullname, address, birthdate, gender, email);
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(success);
                        objectOutputStream.flush();

                        System.out.println("Data sent to client.");

                        break;
                    }

                    case "getAllEmail":{
                        System.out.println("Admin get all email");

                        String username = msgArr[1];

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

                        String username = msgArr[1];

                        Boolean success = dbcon.deleteUser(username);
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(success);
                        objectOutputStream.flush();

                        System.out.println("Data sent to client.");

                        break;
                    }

                    case "enableUser":{
                        System.out.println("Admin enable user");

                        String username = msgArr[1];

                        Boolean success = dbcon.enableUser(username);
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(success);
                        objectOutputStream.flush();

                        System.out.println("Data sent to client.");

                        break;
                    }

                    case "disableUserManage":{
                        System.out.println("Admin disable user manage");

                        String username = msgArr[1];

                        Boolean success = dbcon.disableUserManage(username);
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(success);
                        objectOutputStream.flush();

                        System.out.println("Data sent to client.");

                        break;
                    }

                    case "getAllPassRequest":{
                        System.out.println("Admin get all pass request");

                        Object[][] data1 = dbcon.getAllPassRequest();
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

                    case "updatePassUser":{
                        System.out.println("Admin update user pass");

                        String username = msgArr[1];
                        String new_pwd = msgArr[2];

                        Boolean success = dbcon.updatePassUser(username, new_pwd);
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(success);
                        objectOutputStream.flush();

                        System.out.println("Data sent to client.");

                        break;
                    }

                    case "getAllLoginHistory":{
                        System.out.println("Admin get all login history");

                        Object[][] data1 = dbcon.getAllLoginHistory();
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

                    case "getAllUserFriend":{
                        System.out.println("Admin get all user friend");

                        Object[][] data1 = dbcon.getAllUserFriend();
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

                    case "searchUserFriend":{
                        System.out.println("Admin search user friend");

                        String directFriendCount = msgArr[1];
                        String fullname = msgArr[2];
                        String selectedString = msgArr[3];
                        System.out.println(directFriendCount + " & " + fullname + " & " + selectedString);
                        Object[][] data1 = dbcon.searchUserFriend(directFriendCount, fullname, selectedString);
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

                    case "getMonthlyNewUser":{
                        System.out.println("Admin get monthly new user");

                        int[] data1 = dbcon.getMonthlyNewUser(msgArr[1]);
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(data1);
                        objectOutputStream.flush();

                        for (int row : data1) {
                            System.out.println(row);
                        }

                        System.out.println("Data sent to client.");

                        break;
                    }

                }
            }
        } catch (IOException e) {
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
