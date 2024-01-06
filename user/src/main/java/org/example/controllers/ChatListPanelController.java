package org.example.controllers;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.example.models.ChatInfo;
import org.example.models.Message;
import org.example.models.Profile;
import org.example.utilities.Constants;
import org.example.utilities.DatabaseHandler;
import org.example.utilities.GetFriendMessageWorker;
import org.example.utilities.GetGroupMessageWorker;
import org.example.views.ChatListPanel;
import org.example.views.MainFrame;
import org.example.views.ProfileFrame;

import javax.swing.*;
import java.awt.event.*;
import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class ChatListPanelController {
    private String myUsername;
    private ChatInfo currentConversation;
    private Socket socket;
    private MainFrameController MFC;
    private MainFrame MF;
    private DatabaseHandler DB;
    private ChatListPanel chatListPanel;
    private ArrayList<ChatListPanel.AChatPanel> chatPanels;
    private JTextField inputField;
    private JButton searchButton;
    private JLabel titleLabel;
    private boolean searching = false;

    private class ReloadChatList implements Runnable {
        Thread t;
        public ReloadChatList() {
            t = new Thread(this, "ReloadChatList");
            t.start();
        }
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(2000);
                    if (searching) {
                        continue;
                    }
                    if (chatListPanel.getTitleLabel().getText().equals("Chats")) {
                        ArrayList<ChatInfo> chats = DB.getAllChats(myUsername);
                        chatListPanel.rebuildChatPanelsScrollPane(chats, 2, false, currentConversation);
                        renewListener();
                    } else if (chatListPanel.getTitleLabel().getText().equals("Friends")) {
                        ArrayList<ChatInfo> friends = DB.getAllFriends(myUsername);
                        chatListPanel.rebuildChatPanelsScrollPane(friends, 1, false, currentConversation);
                        renewListener();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (SQLException e) {

                }
            }
        }
    }

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

        new ReloadChatList();
    }

    public void setPlusIconListener() {
        titleLabel.addMouseListener(new PlusIconMouseListener());
    }

    public void renewListener() {
        chatPanels = chatListPanel.getChatPanels();
        chatPanels.forEach(chatPanel -> chatPanel.addMouseListener(new ChatPanelMouseListener(chatPanel)));
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
                searching = false;
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                searchButton.doClick();
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputField.getText();
            if (input.equals(chatListPanel.getInputFieldPlaceholder())) {
                input = "";
            }

            if (Objects.equals(chatListPanel.getInputFieldPlaceholder(), "Search for a message")) {
                try{
                    System.out.println("searching for a message"+ input);
                    ArrayList<ChatInfo> infos = DB.searchChatFromAll(myUsername, input);
                    searching = true;
                    chatListPanel.rebuildChatPanelsScrollPane(infos, 2, false, null);
                    renewListener();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                return;
            } else if (Objects.equals(chatListPanel.getInputFieldPlaceholder(), "Search for a friend")) {
                try {
                    ArrayList<ChatInfo> infos = DB.searchFriends(myUsername, input);
                    chatListPanel.rebuildChatPanelsScrollPane(infos, 2, false, null);
                    renewListener();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (Objects.equals(chatListPanel.getInputFieldPlaceholder(), "Search for a group")) {
//                ArrayList<ChatInfo> infos = DB.searchGroups(myUsername, input);
//                chatListPanel.rebuildChatPanelsScrollPane(infos, true);
                return;
            } else if (Objects.equals(chatListPanel.getInputFieldPlaceholder(), "Search for a user")) {
                ArrayList<ChatInfo> infos = DB.getAllStrangers();
                for(int i = 0; i < infos.size(); i++) {
                    if(!infos.get(i).getUsername().contains(input)) {
                        infos.remove(i);
                        i--;
                    }
                }
                chatListPanel.rebuildChatPanelsScrollPane(infos, 1, true, null);
                renewListener();

            } else if (Objects.equals(chatListPanel.getInputFieldPlaceholder(), "Search for a friend request")) {
                try {
                    ArrayList<ChatInfo> infos = DB.getAllRequests(myUsername);
                    for (int i = 0; i < infos.size(); i++) {
                        if (!infos.get(i).getUsername().contains(input)) {
                            infos.remove(i);
                            i--;
                        }
                    }
                    chatListPanel.rebuildChatPanelsScrollPane(infos, 3, false, null);
                    renewListener();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            } else if (Objects.equals(chatListPanel.getInputFieldPlaceholder(), "Search for a blocked user")) {
                try {
                    ArrayList<ChatInfo> infos = DB.getAllBlocks(myUsername);
                    for (int i = 0; i < infos.size(); i++) {
                        if (!infos.get(i).getUsername().contains(input)) {
                            infos.remove(i);
                            i--;
                        }
                    }
                    chatListPanel.rebuildChatPanelsScrollPane(infos, 4, false, null);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
//                ArrayList<ChatInfo> infos = DB.searchBlockedUsers(myUsername, input);
//                chatListPanel.rebuildChatPanelsScrollPane(infos, true);
                return;
            }
        }
    }

    private class ChatPanelMouseListener extends MouseAdapter {
        ChatListPanel.AChatPanel chatPanel;

        public ChatPanelMouseListener(ChatListPanel.AChatPanel chatPanel) {
            this.chatPanel = chatPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            chatListPanel.setFocusChatPanel(chatPanel);
            currentConversation = chatPanel.getInfo();

            String chatName = currentConversation.getChatName();
            boolean isOnline = currentConversation.isOnline();
            MF.getConversationPanel().setChatName(chatName, isOnline);
            boolean isGroup = currentConversation.isGroup();
            if (isGroup) {
                int groupId = currentConversation.getGroupId();
                ArrayList<Message> messages = null;
                try {
//                    messages = DB.getGroupMessages(myUsername, groupId);
                    messages = GetGroupMessageWorker.request(socket, myUsername, groupId, LocalDate.of(1990, 1, 1).atStartOfDay());
                } catch (Exception exception) {
                    exception.printStackTrace(System.out);
                }
                MF.getConversationPanel().rebuildConversationPanel(currentConversation, messages);
                MF.getConversationPanel().scrollToBottom();
                MF.getConversationPanel().setSearching(false);
            } else {
                String username = currentConversation.getUsername();
                ArrayList<Message> messages = null;
                try {
//                    messages = DB.getFriendMessages(myUsername, username, LocalDate.of(1990, 1, 1));
                    messages = GetFriendMessageWorker.request(socket, myUsername, username, LocalDate.of(1990, 1, 1).atStartOfDay());
                } catch (Exception exception) {
                    exception.printStackTrace(System.out);
                }
                MF.getConversationPanel().rebuildConversationPanel(currentConversation, messages);
                MF.getConversationPanel().scrollToBottom();
                MF.getConversationPanel().setSearching(false);
            }
            if (chatPanel.getMode() == 1) {
                // open profile
                Profile profile = null;
                try {
                    profile = DB.getProfilebyUsername(currentConversation.getUsername());
                } catch (Exception exception) {
                    exception.printStackTrace(System.out);
                }
                ProfileFrame PF = new ProfileFrame(profile, chatPanel.getMode());
                ProfileFrameController PFC = new ProfileFrameController(socket, PF, DB);
            } else if (chatPanel.getMode() == 3) {
                // open profile
                Profile profile = null;
                try {
                    profile = DB.getProfilebyUsername(currentConversation.getUsername());
                } catch (Exception exception) {
                    exception.printStackTrace(System.out);
                }
                ProfileFrame PF = new ProfileFrame(profile, chatPanel.getMode());
                ProfileFrameController PFC = new ProfileFrameController(socket, PF, DB);
            } else if (chatPanel.getMode() == 4) {
                Profile profile = null;
                try {
                    profile = DB.getProfilebyUsername(currentConversation.getUsername());
                    ProfileFrame PF = new ProfileFrame(profile, chatPanel.getMode());
                    ProfileFrameController PFC = new ProfileFrameController(socket, PF, DB);
                } catch (Exception exception) {
                    exception.printStackTrace(System.out);
                }
            }
        }
    }
}
