package org.example.views;

import java.awt.BorderLayout;

import javax.swing.*;

public class ChatPanel extends JPanel {
    private JPanel messagePanel;
    private JPanel inputPanel;
    private JPanel chatnamePanel;
    private JScrollPane messageScrollPane;

    public ChatPanel(String fullname, String username, String status, boolean isFriend) {
        setLayout(new BorderLayout());
        setBackground(null);

        chatnamePanel = new ChatnamePanel(fullname, status);
        messagePanel = new MessagePanel(fullname, username, isFriend);
        inputPanel = new InputPanel();

        messageScrollPane = new JScrollPane(messagePanel);
        messageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JScrollBar verticalScrollBar = messageScrollPane.getVerticalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMaximum());

        add(chatnamePanel, BorderLayout.NORTH);
        add(messageScrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }
}
