package org.example.controllers;

import org.example.models.SideChatInfo;
import org.example.models.User;
import org.example.utilities.Constants;
import org.example.utilities.DatabaseHandler;
import org.example.views.MainChatView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

public class IconButtonController implements ActionListener {
    private MainChatView mainChatView;
    private DatabaseHandler DB;
    private User currentUser;
    private JButton chatButton;
    private JButton groupButton;
    private JButton friendButton;
    private JButton requestButton;
    private JButton blockButton;
    private JButton homeButton;
    public IconButtonController(MainChatView mainChatView, DatabaseHandler DB, User currentUser) {
        this.mainChatView = mainChatView;
        this.DB = DB;
        this.currentUser = currentUser;
        this.chatButton = mainChatView.getIconPanel().getChatButton();
        this.groupButton = mainChatView.getIconPanel().getGroupButton();
        this.friendButton = mainChatView.getIconPanel().getFriendButton();
        this.requestButton = mainChatView.getIconPanel().getRequestButton();
        this.blockButton = mainChatView.getIconPanel().getBlockButton();
        this.homeButton = mainChatView.getIconPanel().getHomeButton();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.chatButton) {
            this.chatButton.setBackground(Constants.HOVER_COLOR);
            this.groupButton.setBackground(Constants.BACKGROUND_COLOR);
            this.friendButton.setBackground(Constants.BACKGROUND_COLOR);
            this.requestButton.setBackground(Constants.BACKGROUND_COLOR);
            this.blockButton.setBackground(Constants.BACKGROUND_COLOR);
            this.homeButton.setBackground(Constants.BACKGROUND_COLOR);

            try {
                ArrayList<SideChatInfo> recentChats = DB.getRecentChat(currentUser.getUsername());
                mainChatView.getUserListPanel().buildUserListPanel(recentChats);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (e.getSource() == this.groupButton) {
            this.groupButton.setBackground(Constants.HOVER_COLOR);
            this.chatButton.setBackground(Constants.BACKGROUND_COLOR);
            this.friendButton.setBackground(Constants.BACKGROUND_COLOR);
            this.requestButton.setBackground(Constants.BACKGROUND_COLOR);
            this.blockButton.setBackground(Constants.BACKGROUND_COLOR);
            this.homeButton.setBackground(Constants.BACKGROUND_COLOR);

            try {
                ArrayList<SideChatInfo> recentChats = DB.getGroupChat(currentUser.getUsername());
                mainChatView.getUserListPanel().buildUserListPanel(recentChats);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (e.getSource() == this.friendButton) {
            this.friendButton.setBackground(Constants.HOVER_COLOR);
            this.chatButton.setBackground(Constants.BACKGROUND_COLOR);
            this.groupButton.setBackground(Constants.BACKGROUND_COLOR);
            this.requestButton.setBackground(Constants.BACKGROUND_COLOR);
            this.blockButton.setBackground(Constants.BACKGROUND_COLOR);
            this.homeButton.setBackground(Constants.BACKGROUND_COLOR);



        } else if (e.getSource() == mainChatView.getIconPanel().getRequestButton()) {
            System.out.println("Request button clicked");
        } else if (e.getSource() == mainChatView.getIconPanel().getBlockButton()) {
            System.out.println("Block button clicked");
        } else if (e.getSource() == mainChatView.getIconPanel().getHomeButton()) {
            System.out.println("Home button clicked");
        }
    }
}
