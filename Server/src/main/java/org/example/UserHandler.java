package org.example;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class UserHandler implements Runnable {
    private final UserServer server;
    private final Socket clientSocket;
    private final UserDatabase DB;
    private final Scanner input;
    private final PrintWriter output;
    private String username;

    public UserHandler(UserServer server, Socket clientSocket, UserDatabase DB) throws IOException {
        this.server = server;
        this.clientSocket = clientSocket;
        this.DB = DB;
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

                // Read data from the client
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                String msg = new String(buffer, 0, bytesRead);

                System.out.println("msg: " + msg);

                String msgArr[] = msg.split("\n");
                String method = msgArr[0];

                System.out.println("check");
                System.out.println(method);
                System.out.println("check");

                if (method.equals("login")) {
                    outputStream.write(("ok").getBytes(StandardCharsets.UTF_8));
                    System.out.println("user wnt to login");
                    bytesRead = inputStream.read(buffer);
                    String clientMessage = new String(buffer, 0, bytesRead);
                    System.out.println(clientMessage);
                    String[] parts = clientMessage.split(":");
                    String response_login;
                    if (DB.login(parts[0], parts[1]))
                        response_login = "true";
                    else
                        response_login = "false";
                    outputStream.write(response_login.getBytes());

                }
                else if (method.equals("signup")) {
                    outputStream.write(("ok").getBytes(StandardCharsets.UTF_8));
                    System.out.println("user wnt to signup");
                    bytesRead = inputStream.read(buffer);
                    String clientMessage = new String(buffer, 0, bytesRead);
                    String[] parts = clientMessage.split("\n");

                    System.out.println(Arrays.toString(parts));
                    if (parts.length < 7) {
                        outputStream.write("false".getBytes());
                    } else {
                        String response_signup;
                        if (DB.signup(parts))
                            response_signup = "true";
                        else response_signup = "false";
                        System.out.println("response");
                        System.out.println(response_signup);
                        outputStream.write(response_signup.getBytes());
                    }
                }
                else if (method.equals("forgetpassword")) {
                    System.out.println("user forget pwd");
                    String username = msgArr[1], email = msgArr[2];
                    System.out.println(email + " " + username);

                    // random mật khẩu
                    String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                    Random random = new Random();
                    int length = 15;
                    StringBuilder password = new StringBuilder(length);

                    for (int i = 0; i < length; i++) {
                        int randomIndex = random.nextInt(allowedChars.length());
                        char randomChar = allowedChars.charAt(randomIndex);
                        password.append(randomChar);
                    }

                    String newPwd = password.toString();

                    String response = "false";
                    if (DB.updatePwd(username, email, newPwd) && sendMail(email, newPwd)){
                        response = "true";
                    }

                    System.out.println("response");
                    System.out.println(response);

                    outputStream.write(response.getBytes(StandardCharsets.UTF_8));
                } else if (method.equals("getNewFriendMessage")) {
                    System.out.println("user get new friend's message");
                    String fromUsername = msgArr[1];
                    String toUsername = msgArr[2];
                    LocalDate lastMessage = LocalDate.parse(msgArr[3]);



//
//                    System.out.println("response");
//                    System.out.println(response);
//
//                    outputStream.write(response.getBytes(StandardCharsets.UTF_8));
                }


                // Send a response back to the client
//                String message = input.nextLine();
//                if (message.equalsIgnoreCase("/quit")) {
//                    server.removeClient(this);
//                    break;
//                }

//                server.broadcastMessage("Client " + clientSocket + ": " + message, this);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
//                input.close();
//                output.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        output.println(message);
    }

    public boolean sendMail(String to, String newPwd){
        final String from = "adsmap102@gmail.com";
        final String password = "bqdfbiawmwgsnesh";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        javax.mail.Session session = javax.mail.Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);

        try {
            // Kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            // Người gửi
            msg.setFrom(from);

            // Người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

            msg.setSentDate(new Date());

            if (newPwd.isEmpty()){
                msg.setSubject("Change your password");
                msg.setContent("You changed password successfully", "text/HTML; charset=UTF-8");
            } else{
                msg.setSubject("Reset your password");
                msg.setContent("<p>Your new password is <strong>" + newPwd + "</strong></p>", "text/HTML; charset=UTF-8");
            }

            Transport.send(msg);
            System.out.println("Gửi email thành công");
            return true;
        } catch (Exception e) {
            System.out.println("Gặp lỗi trong quá trình gửi email");
            e.printStackTrace();
            return false;
        }
    }
}
