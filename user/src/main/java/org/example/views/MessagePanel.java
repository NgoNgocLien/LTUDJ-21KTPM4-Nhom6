package org.example.views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDateTime;

import javax.swing.*;

public class MessagePanel extends JPanel {
    private JPanel messageListPanel;
    private JPanel startPanel;
    private JPanel oneMessagePanel;
    private JPanel timestampPanel;
    private JPanel seenPanel;

    public MessagePanel(String fullname, String username, boolean isFriend) {
        setLayout(new BorderLayout());

        startPanel = new StartChatPanel(fullname, username, isFriend);

        messageListPanel = new JPanel();
        messageListPanel.setLayout(new BoxLayout(messageListPanel, BoxLayout.Y_AXIS));

        messageListPanel.add(startPanel);

        timestampPanel = new MessageTimePanel(LocalDateTime.of(2021, 5, 1, 10, 10, 10));
        messageListPanel.add(timestampPanel);

        oneMessagePanel = new OneMessagePanel("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", true);
        messageListPanel.add(oneMessagePanel);

        oneMessagePanel = new OneMessagePanel("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", true);
        messageListPanel.add(oneMessagePanel);

        oneMessagePanel = new OneMessagePanel("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Hihi", false);
        messageListPanel.add(oneMessagePanel);

        oneMessagePanel = new OneMessagePanel("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Hihi", false);
        messageListPanel.add(oneMessagePanel);

        oneMessagePanel = new OneMessagePanel("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Hihi", true);
        messageListPanel.add(oneMessagePanel);

        oneMessagePanel = new OneMessagePanel("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Hihi", false);
        messageListPanel.add(oneMessagePanel);

        oneMessagePanel = new OneMessagePanel("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Hihi", false);
        messageListPanel.add(oneMessagePanel);

        oneMessagePanel = new OneMessagePanel("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet.", true);
        messageListPanel.add(oneMessagePanel);

        oneMessagePanel = new OneMessagePanel("Lorem ipsum dolor sit amet, consecte.", true);
        messageListPanel.add(oneMessagePanel);

        oneMessagePanel = new OneMessagePanel("Lorem ipsum dolor sit amet, consecte.", false);
        messageListPanel.add(oneMessagePanel);

        oneMessagePanel = new OneMessagePanel("Lorem ipsum dolor sit amet.", false);
        messageListPanel.add(oneMessagePanel);

        seenPanel = new SeenPanel(true);
        messageListPanel.add(seenPanel);

        // add(startPanel, BorderLayout.NORTH);
        add(messageListPanel, BorderLayout.SOUTH);
    }
}