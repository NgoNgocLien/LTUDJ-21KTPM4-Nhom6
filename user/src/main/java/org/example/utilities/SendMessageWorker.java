//package org.example.utilities;
//
//import org.example.models.Message;
//
//import javax.swing.*;
//import java.util.ArrayList;
//
//public class SendMessageWorker extends SwingWorker<Void, Void> {
//    private String username;
//    private String message;
//
//    public SendMessageWorker(String username, String message, ArrayList<Message> messages) {
//        this.username = username;
//        this.message = message;
//        this.messages = messages;
//    }
//
//    @Override
//    protected Void doInBackground() throws Exception {
//        try {
//            AdminSocket.sendMessage(username, message, messages);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
