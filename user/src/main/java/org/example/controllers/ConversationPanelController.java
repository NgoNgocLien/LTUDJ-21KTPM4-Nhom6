package org.example.controllers;

import org.example.models.ChatInfo;
import org.example.models.Message;
import org.example.utilities.Constants;
import org.example.utilities.DatabaseHandler;
import org.example.utilities.GetFriendMessageWorker;
import org.example.utilities.GetGroupMessageWorker;
import org.example.views.ConversationPanel;
import org.example.views.MainFrame;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ConversationPanelController {
    private Socket socket;
    private MainFrameController MFC;
    private String myUsername;
    private DatabaseHandler DB;
    private MainFrame MF;
    private ConversationPanel conversationPanel;
    private JTextField inputField;
    private JButton sendButton;
    private JButton moreButton;
    private JScrollBar messagesScrollBar;
    private ArrayList<JMenuItem> moreMenuItems;
    private JMenuItem searchMessage;

    private class ListenToNewMessage implements Runnable {
        Thread t;
        public ListenToNewMessage() {
            t = new Thread(this, "ListenToNewMessage");
            t.start();
        }
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);

                    if (conversationPanel.isSearching()) {
                        continue;
                    }
                    ChatInfo chatInfo = conversationPanel.getChatInfo();
                    if (chatInfo == null) {
                        continue;
                    } else if (chatInfo.isGroup()) {
                        ArrayList<Message> messages = GetGroupMessageWorker.request(socket, myUsername, conversationPanel.getChatInfo().getGroupId(), conversationPanel.getLastMessage());
                        if (messages == null) {
                            continue;
                        }
                        else if (messages.size() > 0) {
                            for (Message message : messages) {
                                conversationPanel.addMessage(message);
                            }
                            conversationPanel.scrollToBottom();
                        }
                    } else if (chatInfo.isFriend()) {
                        ArrayList<Message> messages = GetFriendMessageWorker.request(socket, myUsername, conversationPanel.getChatInfo().getUsername(), conversationPanel.getLastMessage());
                        if (messages == null) {
                            continue;
                        }
                        else if (messages.size() > 0) {
                            for (Message message : messages) {
                                conversationPanel.addMessage(message);
                            }
                            conversationPanel.scrollToBottom();
                        }
                    }
                } catch (InterruptedException | IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class ListenToOnlineStatus implements Runnable {
        Thread t;
        public ListenToOnlineStatus() {
            t = new Thread(this, "ListenToOnlineStatus");
            t.start();
        }
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    ChatInfo chatInfo = conversationPanel.getChatInfo();
                    if (chatInfo == null) {
                        continue;
                    }
                    boolean isOnline = DB.getIsOnline(chatInfo.getUsername());
                    if (isOnline != chatInfo.isOnline()) {
                        conversationPanel.setChatName(chatInfo.getChatName(), isOnline);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public ConversationPanelController(Socket socket, MainFrameController mfc) {
        this.socket = socket;
        this.MFC = mfc;
        this.myUsername = mfc.getMyUsername();
        this.DB = mfc.getDB();
        this.MF = mfc.getMainFrame();
        this.conversationPanel = MF.getConversationPanel();

        this.inputField = conversationPanel.getInputField();
        this.sendButton = conversationPanel.getSendButton();
        this.moreButton = conversationPanel.getMoreButton();
        this.messagesScrollBar = conversationPanel.getMessagesScrollBar();

        this.moreMenuItems = conversationPanel.getMoreOptions();
        this.searchMessage = conversationPanel.getSearchMessage();

        inputField.addFocusListener(new InputFieldListener());
        inputField.addKeyListener(new InputFieldListener());
        sendButton.addActionListener(new SendButtonActionListener());

        new ListenToNewMessage();
        new ListenToOnlineStatus();
    }

    private class InputFieldListener implements FocusListener, KeyListener {
        @Override
        public void focusGained(FocusEvent e) {
            if (inputField.getText().equals("Type your message here...")) {
                inputField.setText("");
                inputField.setForeground(Constants.COLOR_TEXT_PRIMARY);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (inputField.getText().isEmpty()) {
                inputField.setForeground(Constants.COLOR_TEXT_SECONDARY);
                inputField.setText("Type your message here...");
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                sendButton.doClick();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    private class SendButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = inputField.getText();
            if (message.equals("Type your message here...")) {
                message = "";
            }
            if (!message.isEmpty()) {
                Message msg = new Message(-1, myUsername, conversationPanel.getChatInfo().getUsername(), conversationPanel.getChatInfo().getGroupId(), message, LocalDateTime.now(), true);
//                String receiver = conversationPanel.getReceiver();
//                DB.sendMessage(myUsername, receiver, message);
                DB.addMyMessage(msg);
//                conversationPanel.addMessage(msg);
                inputField.setText("");
                conversationPanel.scrollToBottom();
            }
        }
    }
}
