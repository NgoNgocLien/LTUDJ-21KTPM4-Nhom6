package org.example.views;

import org.example.models.Message;
import org.example.models.SideChatInfo;

import java.awt.BorderLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.*;

public class ChatPanel extends JPanel {
    private SideChatInfo chatInfo;
    private ArrayList<Message> messages;
    private MessagePanel messagePanel;
    private InputPanel inputPanel;
    private ChatnamePanel chatnamePanel;
    private JScrollPane messageScrollPane;

    public ChatPanel(SideChatInfo chatInfo, ArrayList<Message> messages) {
        setLayout(new BorderLayout());
        setBackground(null);

        chatnamePanel = new ChatnamePanel(chatInfo.getChatName(), "Unknown");
        messagePanel = new MessagePanel(chatInfo, messages);
        inputPanel = new InputPanel();

        messageScrollPane = new JScrollPane(messagePanel);
        messageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JScrollBar verticalScrollBar = messageScrollPane.getVerticalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMaximum());

        add(chatnamePanel, BorderLayout.NORTH);
        add(messageScrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }

    public MessagePanel getMessagePanel() { return messagePanel; }
    public InputPanel getInputPanel() { return inputPanel; }
    public ChatnamePanel getChatnamePanel() { return chatnamePanel; }

}
