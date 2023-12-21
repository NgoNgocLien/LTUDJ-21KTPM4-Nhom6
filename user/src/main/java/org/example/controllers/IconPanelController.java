package org.example.controllers;

import org.example.models.ChatInfo;
import org.example.utilities.DatabaseHandler;
import org.example.views.IconPanel;
import org.example.views.MainFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class IconPanelController {
    String myUsername;
    DatabaseHandler DB;
    MainFrame MF;
    IconPanel iconPanel;
    JLabel homeIconLabel;
    JLabel chatIconLabel;
    JLabel friendIconLabel;
    JLabel groupIconLabel;
    JLabel requestIconLabel;
    JLabel blockIconLabel;
    JLabel logoutIconLabel;

    public IconPanelController(MainFrame MF, DatabaseHandler DB, String myUsername) {
        this.myUsername = myUsername;
        this.DB = DB;
        this.MF = MF;
        this.iconPanel = MF.getIconPanel();
        this.homeIconLabel = iconPanel.getHomeIconLabel();
        this.chatIconLabel = iconPanel.getChatIconLabel();
        this.friendIconLabel = iconPanel.getFriendIconLabel();
        this.groupIconLabel = iconPanel.getGroupIconLabel();
        this.requestIconLabel = iconPanel.getRequestIconLabel();
        this.blockIconLabel = iconPanel.getBlockIconLabel();
        this.logoutIconLabel = iconPanel.getLogoutIconLabel();

        homeIconLabel.addMouseListener(new IconMouseListener(homeIconLabel));
        chatIconLabel.addMouseListener(new IconMouseListener(chatIconLabel));
        friendIconLabel.addMouseListener(new IconMouseListener(friendIconLabel));
        groupIconLabel.addMouseListener(new IconMouseListener(groupIconLabel));
        requestIconLabel.addMouseListener(new IconMouseListener(requestIconLabel));
        blockIconLabel.addMouseListener(new IconMouseListener(blockIconLabel));
        logoutIconLabel.addMouseListener(new IconMouseListener(logoutIconLabel));
    }

    private class IconMouseListener extends MouseAdapter {
        JLabel iconLabel;
        public IconMouseListener(JLabel iconLabel) {
            this.iconLabel = iconLabel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == homeIconLabel) {
                iconPanel.setFocusLabel(homeIconLabel);
            } else if (e.getSource() == chatIconLabel) {
                iconPanel.setFocusLabel(chatIconLabel);
                try {
                    ArrayList<ChatInfo> chats = DB.getAllChats(myUsername);
                    MF.getChatListPanel().rebuildChatPanelsScrollPane(chats);
                }
                catch (Exception ex) {
                    System.out.println("Error getting all chats: " + ex);
                }
            } else if (e.getSource() == friendIconLabel) {
                iconPanel.setFocusLabel(friendIconLabel);
                try {
                    ArrayList<ChatInfo> friends = DB.getAllFriends(myUsername);
                    MF.getChatListPanel().rebuildChatPanelsScrollPane(friends);
                }
                catch (Exception ex) {
                    System.out.println("Error getting all friends: " + ex);
                }
            } else if (e.getSource() == groupIconLabel) {
                iconPanel.setFocusLabel(groupIconLabel);
                try {
                    ArrayList<ChatInfo> groups = DB.getAllGroups(myUsername);
                    MF.getChatListPanel().rebuildChatPanelsScrollPane(groups);
                }
                catch (Exception ex) {
                    System.out.println("Error getting all groups: " + ex);
                }
            } else if (e.getSource() == requestIconLabel) {
                iconPanel.setFocusLabel(requestIconLabel);
                try {
                    ArrayList<ChatInfo> requests = DB.getAllRequests(myUsername);
                    MF.getChatListPanel().rebuildChatPanelsScrollPane(requests);
                }
                catch (Exception ex) {
                    System.out.println("Error getting all requests: " + ex);
                }
            } else if (e.getSource() == blockIconLabel) {
                iconPanel.setFocusLabel(blockIconLabel);
                try {
                    ArrayList<ChatInfo> blocks = DB.getAllBlocks(myUsername);
                    MF.getChatListPanel().rebuildChatPanelsScrollPane(blocks);
                }
                catch (Exception ex) {
                    System.out.println("Error getting all blocks: " + ex);
                }
            } else if (e.getSource() == logoutIconLabel) {
                iconPanel.setFocusLabel(logoutIconLabel);
            }
        }
    }
}

