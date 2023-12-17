package org.example.views;

import org.example.controllers.MainControllers;
import org.example.models.Message;
import org.example.models.RecentChat;
import org.example.models.SideChatInfo;
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
    private IconPanel iconPanel;
    private UserListPanel userListPanel;
    private ChatPanel chatPanel;

    public MainChatView(ArrayList<SideChatInfo> recentChats, ArrayList<Message> messages) {
//         try {
//             // set windows look and feel
//             UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//         } catch (Exception e) {
//             System.out.println("Error setting Windows look and feel: " + e);
//         }

        setTitle(Constants.APP_TITLE);
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(Constants.SIDE_PANEL_WIDTH, Constants.SIDE_PANEL_HEIGHT));
        sidePanel.setLayout(new BorderLayout());
        sidePanel.setBackground(null);
        iconPanel = new IconPanel();
        userListPanel = new UserListPanel(recentChats);
        sidePanel.add(iconPanel, BorderLayout.WEST);
        sidePanel.add(userListPanel, BorderLayout.EAST);

        chatPanel = new ChatPanel(recentChats.get(0), messages);

        add(sidePanel, BorderLayout.WEST);
        add(chatPanel, BorderLayout.CENTER);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public IconPanel getIconPanel() {
        return iconPanel;
    }

    public UserListPanel getUserListPanel() {
        return userListPanel;
    }

    public ChatPanel getChatPanel() {
        return chatPanel;
    }
}
