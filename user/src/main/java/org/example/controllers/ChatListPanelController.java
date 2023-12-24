package org.example.controllers;

import org.example.models.ChatInfo;
import org.example.models.Message;
import org.example.utilities.Constants;
import org.example.utilities.DatabaseHandler;
import org.example.views.ChatListPanel;
import org.example.views.MainFrame;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChatListPanelController {
    private MainFrameController MFC;
    private MainFrame MF;
    private DatabaseHandler DB;
    String myUsername;
    private ChatListPanel chatListPanel;
    private ArrayList<ChatListPanel.AChatPanel> chatPanels;
    private JTextField inputField;
    private JButton searchButton;

    public ChatListPanelController(MainFrameController mfc) {
        this.MFC = mfc;
        this.MF = mfc.getMainFrame();
        this.DB = mfc.getDB();
        this.myUsername = mfc.getMyUsername();

        chatListPanel = MF.getChatListPanel();
        chatPanels = chatListPanel.getChatPanels();
        chatPanels.forEach(chatPanel -> chatPanel.addMouseListener(new ChatPanelMouseListener(chatPanel)));

        inputField = chatListPanel.getInputField();
        inputField.addKeyListener(new InputFieldListener());
        inputField.addFocusListener(new InputFieldListener());

        searchButton = chatListPanel.getSearchButton();
        searchButton.addActionListener(new SearchButtonActionListener());
    }

    private class InputFieldListener implements FocusListener, KeyListener {
        @Override
        public void focusGained(FocusEvent e) {
            if (inputField.getText().equals(chatListPanel.getInputFieldPlaceholder())) {
                inputField.setText("");
                inputField.setForeground(Constants.COLOR_TEXT_PRIMARY);
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            if (inputField.getText().isEmpty()) {
                inputField.setForeground(Constants.COLOR_TEXT_SECONDARY);
                inputField.setText(chatListPanel.getInputFieldPlaceholder());
            }
        }
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                searchButton.doClick();
            }
        }
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyReleased(KeyEvent e) {}
    }

    private class  SearchButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputField.getText();
            if (input.equals(chatListPanel.getInputFieldPlaceholder())) {
                input = "";
            }
            System.out.println("Search for: " + input);

            if (chatListPanel.getInputFieldPlaceholder() == "Search for a message") {
//                ArrayList<ChatInfo> infos = DB.searchMessages(myUsername, input);
//                chatListPanel.rebuildChatPanelsScrollPane(infos, true);
                return;
            } else if (chatListPanel.getInputFieldPlaceholder() == "Search for a friend") {
//                ArrayList<ChatInfo> infos = DB.searchFriends(myUsername, input);
//                chatListPanel.rebuildChatPanelsScrollPane(infos, true);
                return;
            } else if (chatListPanel.getInputFieldPlaceholder() == "Search for a group") {
//                ArrayList<ChatInfo> infos = DB.searchGroups(myUsername, input);
//                chatListPanel.rebuildChatPanelsScrollPane(infos, true);
                return;
            } else if (chatListPanel.getInputFieldPlaceholder() == "Search for a user") {
//                ArrayList<ChatInfo> infos = DB.searchUsers(myUsername, input);
//                chatListPanel.rebuildChatPanelsScrollPane(infos, true);
                return;
            } else if (chatListPanel.getInputFieldPlaceholder() == "Search for a friend request") {
//                ArrayList<ChatInfo> infos = DB.searchRequests(myUsername, input);
//                chatListPanel.rebuildChatPanelsScrollPane(infos, true);
                return;
            } else if (chatListPanel.getInputFieldPlaceholder() == "Search for a blocked user") {
//                ArrayList<ChatInfo> infos = DB.searchBlockedUsers(myUsername, input);
//                chatListPanel.rebuildChatPanelsScrollPane(infos, true);
                return;
            }
        }
    }

    public void renewListener() {
        chatPanels = chatListPanel.getChatPanels();
        chatPanels.forEach(chatPanel -> chatPanel.addMouseListener(new ChatPanelMouseListener(chatPanel)));
    }

    private class ChatPanelMouseListener extends MouseAdapter {
        ChatListPanel.AChatPanel chatPanel;
        public ChatPanelMouseListener(ChatListPanel.AChatPanel chatPanel) {
            this.chatPanel = chatPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            chatListPanel.setFocusChatPanel(chatPanel);
            ChatInfo chatInfo = chatPanel.getInfo();

            String chatName = chatInfo.getChatName();
            boolean isOnline = chatInfo.isOnline();
            MF.getConversationPanel().setChatName(chatName, isOnline);
            boolean isGroup = chatInfo.isGroup();
            if (isGroup) {
                int groupId = chatInfo.getGroupId();
                ArrayList<Message> messages = null;
                try {
                    messages = DB.getGroupMessages(myUsername, groupId);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                MF.getConversationPanel().rebuildConversationPanel(chatInfo, messages);
            } else {
                String username = chatInfo.getUsername();
                ArrayList<Message> messages = null;
                try {
                    messages = DB.getFriendMessages(myUsername, username);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                MF.getConversationPanel().rebuildConversationPanel(chatInfo, messages);
            }
            if (!chatPanel.getMode()) {
                // open profile

            }
        }
    }
}
