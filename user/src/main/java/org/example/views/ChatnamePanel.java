package org.example.views;

import org.example.utilities.Constants;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ChatnamePanel extends JPanel {
    private String chatName;
    private String status;

    private JLabel chatNameLabel;
    private JLabel statusLabel;
    private JButton moreButton;
    private ImageIcon moreIcon;

    public ChatnamePanel(String chatName, String status) {
        this.chatName = chatName;
        this.status = status;

        setPreferredSize(new Dimension(Constants.WINDOW_WIDTH - Constants.SIDE_PANEL_WIDTH, Constants.SEARCH_PANEL_HEIGHT));
        setLayout(new BorderLayout());
        setBackground(null);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(null);
        titlePanel.setLayout(new GridLayout(2, 1, 0, -20));
        titlePanel.setBackground(null);
        titlePanel.setBorder(new EmptyBorder(0, 20, 0, 0));

        chatNameLabel = new JLabel(chatName);
        chatNameLabel.setFont(Constants.BIG_FONT);
        chatNameLabel.setForeground(Color.BLACK);
        chatNameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        statusLabel = new JLabel(status);
        statusLabel.setFont(Constants.SMALL_FONT);
        if (status.equals("Online"))
            statusLabel.setForeground(Color.GREEN);
        else if (status.equals("Offline"))
            statusLabel.setForeground(Color.GRAY);

        titlePanel.add(chatNameLabel, BorderLayout.NORTH);
        titlePanel.add(statusLabel, BorderLayout.SOUTH);

        moreIcon = new ImageIcon("src/main/resources/more.png");
        moreButton = new JButton(moreIcon);
        moreButton.setBackground(null);
        moreButton.setBorderPainted(false);
        moreButton.setFocusPainted(false);
        moreButton.setContentAreaFilled(false);
        moreButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        add(titlePanel, BorderLayout.WEST);
        add(moreButton, BorderLayout.EAST);
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
        chatNameLabel.setText(chatName);
    }

    public void setStatus(String status) {
        this.status = status;
        statusLabel.setText(status);
    }
}
