package org.example.views;

import org.example.models.RecentChat;
import org.example.models.User;
import org.example.utilities.Constants;
import org.example.utilities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Array;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainChatView extends JFrame {
    private JPanel iconPanel;
    private JPanel userListPanel;
    private JPanel sidePanel;
    private JPanel chatPanel;

    public MainChatView(ArrayList<ArrayList<Object>> recentChats) {
        // try {
        //     // set windows look and feel
        //     UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        // } catch (Exception e) {
        //     System.out.println("Error setting Windows look and feel: " + e);
        // }

        setTitle(Constants.APP_TITLE);
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        sidePanel = new SidePanel(recentChats);
        chatPanel = new ChatPanel("John Doe", "johndoe", "Online", true);

        add(sidePanel, BorderLayout.WEST);
        add(chatPanel, BorderLayout.CENTER);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            String username = "ncluan";
            DatabaseHandler DB = new DatabaseHandler();
            User currentUser = DB.getUser(username);

            ArrayList<ArrayList<Object>> recentChats = DB.getRecentChat(username).getChatList();
            for (ArrayList<Object> chats : recentChats) {
                System.out.println(chats.get(0) + " " + chats.get(1) + " " + chats.get(2));
            }
            new MainChatView(recentChats);

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        ArrayList<User> users = new ArrayList<User>();
//        users.add(new User("John Doe", "@johndoe"));
//        users.add(new User("Jane Doe", "@janedoe"));
//        users.add(new User("John Smith", "@johnsmith"));
//        users.add(new User("Jane Smith", "@janesmith"));
//        users.add(new User("John Doe", "@johndoe"));
//        users.add(new User("Jane Doe", "@janedoe"));
//        users.add(new User("John Smith", "@johnsmith"));
//        users.add(new User("Jane Smith", "@janesmith"));



    }
}
