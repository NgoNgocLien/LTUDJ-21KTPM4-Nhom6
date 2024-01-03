package org.example.controllers;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.example.models.ChatInfo;
import org.example.models.Message;
import org.example.models.Profile;
import org.example.utilities.Constants;
import org.example.utilities.DatabaseHandler;
import org.example.views.ChatListPanel;
import org.example.views.MainFrame;
import org.example.views.MyProfileFrame;
import org.example.views.ProfileFrame;

import javax.swing.*;
import java.awt.event.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class ChatListPanelController {
    private Socket socket;
    private MainFrameController MFC;
    private MainFrame MF;
    private DatabaseHandler DB;
    String myUsername;
    private ChatListPanel chatListPanel;
    private ArrayList<ChatListPanel.AChatPanel> chatPanels;
    private JTextField inputField;
    private JButton searchButton;
    private JLabel titleLabel;

    public ChatListPanelController(Socket socket, MainFrameController mfc) {
        this.socket = socket;
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
        searchButton.addActionListener(new SearchButtonListener());

        titleLabel = chatListPanel.getTitleLabel();
    }

    public void setPlusIconListener() {
        titleLabel.addMouseListener(new PlusIconMouseListener());
    }

    private class PlusIconMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            // open add group frame
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // replace plus icon with plus icon with background
            if (!titleLabel.getText().trim().equals("Groups")) {
                return;
            }
            IconFontSwing.register(FontAwesome.getIconFont());
            Icon plusIcon = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 35, Constants.COLOR_TEXT_LIGHT_SECONDARY);
            titleLabel.setIcon(plusIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!titleLabel.getText().trim().equals("Groups")) {
                return;
            }
            // replace plus icon with normal plus icon
            IconFontSwing.register(FontAwesome.getIconFont());
            Icon plusIcon = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 35, Constants.COLOR_ICON_PRIMARY);
            titleLabel.setIcon(plusIcon);
        }
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

    private class  SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputField.getText();
            if (input.equals(chatListPanel.getInputFieldPlaceholder())) {
                input = "";
            }
            System.out.println("Search for: " + input);

            if (Objects.equals(chatListPanel.getInputFieldPlaceholder(), "Search for a message")) {
//                ArrayList<ChatInfo> infos = DB.searchMessages(myUsername, input);
//                chatListPanel.rebuildChatPanelsScrollPane(infos, true);
                return;
            } else if (Objects.equals(chatListPanel.getInputFieldPlaceholder(), "Search for a friend")) {
//                ArrayList<ChatInfo> infos = DB.searchFriends(myUsername, input);
//                chatListPanel.rebuildChatPanelsScrollPane(infos, true);
                return;
            } else if (Objects.equals(chatListPanel.getInputFieldPlaceholder(), "Search for a group")) {
//                ArrayList<ChatInfo> infos = DB.searchGroups(myUsername, input);
//                chatListPanel.rebuildChatPanelsScrollPane(infos, true);
                return;
            } else if (Objects.equals(chatListPanel.getInputFieldPlaceholder(), "Search for a user")) {
//                ArrayList<ChatInfo> infos = DB.searchUsers(myUsername, input);
//                chatListPanel.rebuildChatPanelsScrollPane(infos, true);
                return;
            } else if (Objects.equals(chatListPanel.getInputFieldPlaceholder(), "Search for a friend request")) {
//                ArrayList<ChatInfo> infos = DB.searchRequests(myUsername, input);
//                chatListPanel.rebuildChatPanelsScrollPane(infos, true);
                return;
            } else if (Objects.equals(chatListPanel.getInputFieldPlaceholder(), "Search for a blocked user")) {
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
                MF.getConversationPanel().scrollToBottom();
            } else {
                String username = chatInfo.getUsername();
                ArrayList<Message> messages = null;
                try {
                    messages = DB.getFriendMessages(myUsername, username, LocalDate.of(1990, 1, 1));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                MF.getConversationPanel().rebuildConversationPanel(chatInfo, messages);
                MF.getConversationPanel().scrollToBottom();
            }
            if (!chatPanel.getMode()) {
                // open profile
                Profile profile = null;
                try {
                    profile = DB.getProfilebyUsername(chatInfo.getUsername());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                ProfileFrame PF = new ProfileFrame(profile);
            }
        }
    }
}
