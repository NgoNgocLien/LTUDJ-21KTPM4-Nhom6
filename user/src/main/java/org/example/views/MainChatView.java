//package org.example.views;
//
//import org.example.models.User;
//import org.example.utilities.Constants;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.ArrayList;
//
//public class MainChatView extends JFrame {
//    private JPanel iconPanel;
//    private JPanel userListPanel;
//    private JPanel sidePanel;
//    private JPanel chatPanel;
//
//    public MainChatView(ArrayList<User> users) {
//        // try {
//        //     // set windows look and feel
//        //     UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//        // } catch (Exception e) {
//        //     System.out.println("Error setting Windows look and feel: " + e);
//        // }
//
//        setTitle(Constants.APP_TITLE);
//        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//        setBackground(Color.WHITE);
//
//        sidePanel = new SidePanel(users);
//
//        chatPanel = new ChatPanel("John Doe", "johndoe", "Online", true);
//        // chatPanel.setBackground(Color.GREEN);
//
//        // chatPanel = new ChatPanel();
//
//        add(sidePanel, BorderLayout.WEST);
//        add(chatPanel, BorderLayout.CENTER);
//
//        setVisible(true);
//    }
//
////    public static void main(String[] args) {
////        ArrayList<User> users = new ArrayList<User>();
////        users.add(new User("John Doe", "@johndoe"));
////        users.add(new User("Jane Doe", "@janedoe"));
////        users.add(new User("John Smith", "@johnsmith"));
////        users.add(new User("Jane Smith", "@janesmith"));
////        users.add(new User("John Doe", "@johndoe"));
////        users.add(new User("Jane Doe", "@janedoe"));
////        users.add(new User("John Smith", "@johnsmith"));
////        users.add(new User("Jane Smith", "@janesmith"));
////
////        new MainChatView(users);
////    }
//}
