package org.example.views;
import org.example.models.Message;
import org.example.models.SideChatInfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.*;

public class MessagePanel extends JPanel {
    private JPanel messageListPanel;
    private JPanel startPanel;
    private JPanel oneMessagePanel;
//    private JPanel timestampPanel;
    private JPanel seenPanel;
    private JScrollPane messageScrollPane;

    public MessagePanel(SideChatInfo chatInfo, ArrayList<Message> messages) {
        setLayout(new BorderLayout());
        messageListPanel = new JPanel();
        messageListPanel.setLayout(new BoxLayout(messageListPanel, BoxLayout.Y_AXIS));

        buildMessagePanel(chatInfo, messages);

//        add(messageScrollPane, BorderLayout.SOUTH);
    }

    public void buildMessagePanel(SideChatInfo chatInfo, ArrayList<Message> messages) {
        messageListPanel.removeAll();
        if (chatInfo.getIsGroup()) {
            startPanel = new StartChatPanel(chatInfo.getChatName(), "", false, true);
        } else {
            startPanel = new StartChatPanel(chatInfo.getChatName(), chatInfo.getChatId(), true, false);
        }
        messageListPanel.add(startPanel);

        if (messages.size() == 0) {
            return;
        }

        LocalDateTime lastTimestamp = null;
        String lastSender = null;
        for (Message message : messages) {
            if (lastTimestamp == null || message.getSentTime().getDayOfYear() != lastTimestamp.getDayOfYear()) {
                lastTimestamp = message.getSentTime();
                MessageTimePanel timestampPanel = new MessageTimePanel(lastTimestamp);
                messageListPanel.add(timestampPanel);
            }
            if (message.getSender().equals(chatInfo.getMyUsername())) {
                oneMessagePanel = new OneMessagePanel(message.getContent(), true);
                messageListPanel.add(oneMessagePanel);
            } else {
                if (chatInfo.getIsGroup() && !message.getSender().equals(lastSender)) {
                    lastSender = message.getSender();
                    MemberNamePanel memberNamePanel = new MemberNamePanel(message.getSender());
                    messageListPanel.add(memberNamePanel);
                }
                oneMessagePanel = new OneMessagePanel(message.getContent(), false);
                messageListPanel.add(oneMessagePanel);
            }
        }

        if (messageScrollPane != null)
            remove(messageScrollPane);
        messageScrollPane = new JScrollPane(messageListPanel);
        messageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        messageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        messageScrollPane.setBorder(BorderFactory.createEmptyBorder());
        messageScrollPane.getVerticalScrollBar().setValue(messageScrollPane.getVerticalScrollBar().getMaximum());
        add(messageScrollPane, BorderLayout.SOUTH);

        SwingUtilities.invokeLater(() -> {
            revalidate();  // Revalidate the panel
            repaint();     // Repaint the panel
        });
//        seenPanel = new SeenPanel();
//        messageListPanel.add(seenPanel);
    }
}