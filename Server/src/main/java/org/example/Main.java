package org.example;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        DBconnection dbcon = new DBconnection();
        dbcon.connect();
        System.out.println("Server is running");
        chatServer server = new chatServer();
        server.startServer();
//        try {
//            ServerSocket serverSocket = new ServerSocket(145); // Server socket on port 12345
//            System.out.println("Server is waiting for a connection...");
//
//            Socket clientSocket = serverSocket.accept(); // Wait for a client to connect
//            System.out.println("Client connected!");
//
//            // Get the input and output streams of the client socket
//            InputStream inputStream = clientSocket.getInputStream();
//            OutputStream outputStream = clientSocket.getOutputStream();
//
//            // Read data from the client
//            byte[] buffer = new byte[1024];
//            int bytesRead = inputStream.read(buffer);
//            String clientMessage = new String(buffer, 0, bytesRead);
////            System.out.println("Received from client: " + clientMessage);
//            String[] parts = clientMessage.split(":");
//            String response_login;
//            if(dbcon.login(parts[0], parts[1])){
//                response_login = "true";
//            }else
//                response_login = "false";
//            outputStream.write(response_login.getBytes());
//
//
//            // Send a response back to the client
////            String responseMessage = "Hello from the server!";
////            outputStream.write(responseMessage.getBytes());
//
//            // Close the sockets
//            clientSocket.close();
//            serverSocket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}