package org.example.controllers;

import org.example.models.ChatInfo;
import org.example.models.Profile;
import org.example.utilities.DatabaseHandler;
import org.example.views.IconPanel;
import org.example.views.LoginFrame;
import org.example.views.MainFrame;
import org.example.views.MyProfileFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;
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
    private Socket socket;

    public IconPanelController(Socket socket, MainFrameController mfc) {
        this.socket = socket;
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
//                iconPanel.setFocusLabel(homeIconLabel);
                // TODO: open my profile
                try {
                    Profile myProfile = DB.getProfilebyUsername(myUsername);
                    MyProfileFrame MPF = new MyProfileFrame(myProfile);
                    MyProfileFrameController MPFC = new MyProfileFrameController(MPF, DB);
                } catch (Exception ex) {
                    System.out.println("Error getting my profile: " + ex);
                }
            } else if (e.getSource() == chatIconLabel) {
                iconPanel.setFocusLabel(chatIconLabel);
                try {
                    ArrayList<ChatInfo> chats = DB.getAllChats(myUsername);
                    MF.getChatListPanel().rebuildChatPanelsScrollPane(chats, 0, false, null);
                    MF.getChatListPanel().setTitleLabel("Chats", false);
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
                    MF.getChatListPanel().rebuildChatPanelsScrollPane(friends, 2, false, null);
                    MF.getChatListPanel().setTitleLabel("Friends", false);
                    MF.getChatListPanel().setInputFieldPlaceholder("Search for a friend");
                    MFC.getChatListPanelController().renewListener();
                }
                catch (Exception ex) {
                    System.out.println("Error getting all friends: " + ex);
                }
            } else if (e.getSource() == groupIconLabel) {
                iconPanel.setFocusLabel(groupIconLabel);
                try {
                    ArrayList<ChatInfo> groups = DB.getAllGroups(myUsername);
                    MF.getChatListPanel().rebuildChatPanelsScrollPane(groups, 2, false, null);
                    MF.getChatListPanel().setTitleLabel("Groups                 ", true);
                    MF.getChatListPanel().setInputFieldPlaceholder("Search for a group");
                    MFC.getChatListPanelController().renewListener();
                    MFC.getChatListPanelController().setPlusIconListener();
                }
                catch (Exception ex) {
                    System.out.println("Error getting all groups: " + ex);
                }
            } else if (e.getSource() == addIconLabel) {
                iconPanel.setFocusLabel(addIconLabel);
                try {
                    ArrayList<ChatInfo> groups = DB.getAllSuggests(myUsername);
                    MF.getChatListPanel().rebuildChatPanelsScrollPane(groups, 1, true, null);
                    MF.getChatListPanel().setTitleLabel("Add a Friend", false);
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
                    MF.getChatListPanel().rebuildChatPanelsScrollPane(requests, 3, false, null);
                    MF.getChatListPanel().setTitleLabel("Friend Requests", false);
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
                    MF.getChatListPanel().rebuildChatPanelsScrollPane(blocks, 4, false, null);
                    MF.getChatListPanel().setTitleLabel("Blocked Users", false);
                    MF.getChatListPanel().setInputFieldPlaceholder("Search for a blocked user");
                    MFC.getChatListPanelController().renewListener();
                }
                catch (Exception ex) {
                    System.out.println("Error getting all blocks: " + ex);
                }
            } else if (e.getSource() == logoutIconLabel) {
                int confirm = JOptionPane.showConfirmDialog(MF, "Are you sure you want to log out?", "Log out", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        // TODO: set user status to offline
                        DB.setLogoutTime(myUsername);
                    } catch (Exception ex) {
                        System.out.println("Error closing window: " + ex);
                    }
                    MF.dispose();
                    LoginFrame LF = new LoginFrame();
                    LoginFrameController LFC = new LoginFrameController(socket, LF, DB);
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == logoutIconLabel) {
                iconPanel.setFocusTemporaryLabel(logoutIconLabel);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == logoutIconLabel) {
                iconPanel.setUnfocusLabel(logoutIconLabel);
            }
        }
    }
}

