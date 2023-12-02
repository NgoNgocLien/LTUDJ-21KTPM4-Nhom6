package org.example.views;

import java.awt.BorderLayout;

import javax.swing.*;

public class ChatPanel extends JPanel {
    private JPanel messagePanel;
    private JPanel inputPanel;
    private JPanel chatnamePanel;

    public ChatPanel(String fullname, String username, String status, boolean isFriend) {
        setLayout(new BorderLayout());
        setBackground(null);

        chatnamePanel = new ChatnamePanel(fullname, status);
//        messagePanel = new MessagePanel(fullname, username, isFriend);
        inputPanel = new InputPanel();
        // messagePanel = MessagePanel();
        // inputPanel = InputPanel();

        add(chatnamePanel, BorderLayout.NORTH);
        add(messagePanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }

    public void updateStatus(String status) {

    }
}
