package org.example.controllers;

import org.example.Main;
import org.example.models.Message;
import org.example.utilities.Constants;
import org.example.utilities.DatabaseHandler;
import org.example.views.ConversationPanel;
import org.example.views.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;

public class ConversationPanelController {
    private MainFrameController MFC;
    private String myUsername;
    private DatabaseHandler DB;
    private MainFrame MF;
    private ConversationPanel conversationPanel;
    private JTextField inputField;
    private JButton sendButton;
    private JButton moreButton;
    private JScrollBar messagesScrollBar;

    public ConversationPanelController(MainFrameController mfc) {
        this.MFC = mfc;
        this.myUsername = mfc.getMyUsername();
        this.DB = mfc.getDB();
        this.MF = mfc.getMainFrame();
        this.conversationPanel = MF.getConversationPanel();

        this.inputField = conversationPanel.getInputField();
        this.sendButton = conversationPanel.getSendButton();
        this.moreButton = conversationPanel.getMoreButton();
        this.messagesScrollBar = conversationPanel.getMessagesScrollBar();

        inputField.addFocusListener(new InputFieldListener());
        inputField.addKeyListener(new InputFieldListener());
        sendButton.addActionListener(new SendButtonActionListener());
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
                conversationPanel.addMessage(msg);
                inputField.setText("");
                conversationPanel.srollToBottom();
            }
        }
    }
}
