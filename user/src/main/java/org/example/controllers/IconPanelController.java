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
    private MainFrameController MFC;
    private String myUsername;
    private DatabaseHandler DB;
    private MainFrame MF;
    private IconPanel iconPanel;
    private JLabel homeIconLabel;
    private JLabel chatIconLabel;
    private JLabel friendIconLabel;
    private JLabel groupIconLabel;
    private JLabel addIconLabel;
    private JLabel requestIconLabel;
    private JLabel blockIconLabel;
    private JLabel logoutIconLabel;

    public IconPanelController(MainFrameController mfc) {
        this.MFC = mfc;
        this.myUsername = mfc.getMyUsername();
        this.DB = mfc.getDB();
        this.MF = mfc.getMainFrame();
        this.iconPanel = MF.getIconPanel();
        this.homeIconLabel = iconPanel.getHomeIconLabel();
        this.chatIconLabel = iconPanel.getChatIconLabel();
        this.friendIconLabel = iconPanel.getFriendIconLabel();
        this.groupIconLabel = iconPanel.getGroupIconLabel();
        this.addIconLabel = iconPanel.getAddIconLabel();
        this.requestIconLabel = iconPanel.getRequestIconLabel();
        this.blockIconLabel = iconPanel.getBlockIconLabel();
        this.logoutIconLabel = iconPanel.getLogoutIconLabel();

        homeIconLabel.addMouseListener(new IconMouseListener(homeIconLabel));
        chatIconLabel.addMouseListener(new IconMouseListener(chatIconLabel));
        friendIconLabel.addMouseListener(new IconMouseListener(friendIconLabel));
        groupIconLabel.addMouseListener(new IconMouseListener(groupIconLabel));
        addIconLabel.addMouseListener(new IconMouseListener(addIconLabel));
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
                    MF.getChatListPanel().rebuildChatPanelsScrollPane(chats, true);
                    MF.getChatListPanel().setTitleLabel("Chats");
                    MF.getChatListPanel().setInputFieldPlaceholder("Search for a message");
                    MFC.getChatListPanelController().renewListener();
                }
                catch (Exception ex) {
                    System.out.println("Error getting all chats: " + ex);
                }
            } else if (e.getSource() == friendIconLabel) {
                iconPanel.setFocusLabel(friendIconLabel);
                try {
                    ArrayList<ChatInfo> friends = DB.getAllFriends(myUsername);
                    MF.getChatListPanel().rebuildChatPanelsScrollPane(friends, true);
                    MF.getChatListPanel().setTitleLabel("Friends");
                    MF.getChatListPanel().setInputFieldPlaceholder("Search for a user");
                    MFC.getChatListPanelController().renewListener();
                }
                catch (Exception ex) {
                    System.out.println("Error getting all friends: " + ex);
                }
            } else if (e.getSource() == groupIconLabel) {
                iconPanel.setFocusLabel(groupIconLabel);
                try {
                    ArrayList<ChatInfo> groups = DB.getAllGroups(myUsername);
                    MF.getChatListPanel().rebuildChatPanelsScrollPane(groups, true);
                    MF.getChatListPanel().setTitleLabel("Groups");
                    MF.getChatListPanel().setInputFieldPlaceholder("Search for a group");
                    MFC.getChatListPanelController().renewListener();
                }
                catch (Exception ex) {
                    System.out.println("Error getting all groups: " + ex);
                }
            } else if (e.getSource() == addIconLabel) {
                iconPanel.setFocusLabel(addIconLabel);
                try {
                    ArrayList<ChatInfo> groups = DB.getAllSuggests(myUsername);
                    MF.getChatListPanel().rebuildChatPanelsScrollPane(groups, false);
                    MF.getChatListPanel().setTitleLabel("Add a Friend");
                    MF.getChatListPanel().setInputFieldPlaceholder("Search for a user");
                    MFC.getChatListPanelController().renewListener();
                }
                catch (Exception ex) {
                    System.out.println("Error getting all suggested users: " + ex);
                }
            } else if (e.getSource() == requestIconLabel) {
                iconPanel.setFocusLabel(requestIconLabel);
                try {
                    ArrayList<ChatInfo> requests = DB.getAllRequests(myUsername);
                    MF.getChatListPanel().rebuildChatPanelsScrollPane(requests, false);
                    MF.getChatListPanel().setTitleLabel("Friend Requests");
                    MF.getChatListPanel().setInputFieldPlaceholder("Search for a friend request");
                    MFC.getChatListPanelController().renewListener();
                }
                catch (Exception ex) {
                    System.out.println("Error getting all requests: " + ex);
                }
            } else if (e.getSource() == blockIconLabel) {
                iconPanel.setFocusLabel(blockIconLabel);
                try {
                    ArrayList<ChatInfo> blocks = DB.getAllBlocks(myUsername);
                    MF.getChatListPanel().rebuildChatPanelsScrollPane(blocks, false);
                    MF.getChatListPanel().setTitleLabel("Blocked Users");
                    MF.getChatListPanel().setInputFieldPlaceholder("Search for a blocked user");
                    MFC.getChatListPanelController().renewListener();
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

