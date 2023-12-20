package org.example.controllers;

import org.example.models.Message;
import org.example.models.SideChatInfo;
import org.example.models.User;
import org.example.utilities.DatabaseHandler;
import org.example.views.MainChatView;
import org.example.views.MessagePanel;
import org.example.views.UserPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainControllers {
    public MainControllers(MainChatView mainChatView, DatabaseHandler DB, User currentUser) {
        // set action listener for user panels
        ArrayList<UserPanel> usersPanels = mainChatView.getUserListPanel().getUserPanels();
        for (UserPanel userPanel : usersPanels) {
            userPanel.addMouseListener(new UserPanelController(mainChatView, DB, userPanel));
        }
        mainChatView.getIconPanel().getChatButton().addActionListener(new IconButtonController(mainChatView, DB, currentUser));
        mainChatView.getIconPanel().getGroupButton().addActionListener(new IconButtonController(mainChatView, DB, currentUser));
        mainChatView.getIconPanel().getFriendButton().addActionListener(new IconButtonController(mainChatView, DB, currentUser));
        mainChatView.getIconPanel().getRequestButton().addActionListener(new IconButtonController(mainChatView, DB, currentUser));
        mainChatView.getIconPanel().getBlockButton().addActionListener(new IconButtonController(mainChatView, DB, currentUser));
        mainChatView.getIconPanel().getHomeButton().addActionListener(new IconButtonController(mainChatView, DB, currentUser));
    }

    public static void main(String[] args) {
        try {
            String username = "tdung";
            DatabaseHandler DB = new DatabaseHandler();
            User currentUser = DB.getUser(username);

            ArrayList<SideChatInfo> recentChats = DB.getRecentChat(username);
            ArrayList<Message> messages = DB.getMessages(recentChats.get(0));
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MainChatView mainChatView = new MainChatView(recentChats, messages);
                    MainControllers mainControllers = new MainControllers(mainChatView, DB, currentUser);

                    mainChatView.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            // Perform actions before closing the main frame
                            try {
                                // Close the database connection or perform other cleanup tasks
                                DB.clearEnvironment();
                            } catch (SQLException se) {
                                se.printStackTrace();
                            }
                        }
                    });
                }
            });
            // check if window is closed

//            DatabaseHandler DB = new DatabaseHandler();
//            ArrayList<SideChatInfo> recentChats = DB.getRecentChat("bphuong");
//            ArrayList<Message> messages = DB.getMessages(recentChats.get(0));
//            System.out.println(recentChats.get(0).getIsGroup());
//            for (Message message : messages) {
//                System.out.println(message.getContent());
//            }


        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
