package org.example.views;

import org.example.utilities.Constants;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class IconPanel extends JPanel {

    private JButton chatButton;
    private JButton groupButton;
    private JButton friendButton;
    private JButton requestButton;
    private JButton blockButton;
    private JButton homeButton;

    private ImageIcon chatIcon;
    private ImageIcon groupIcon;
    private ImageIcon friendIcon;
    private ImageIcon requestIcon;
    private ImageIcon blockIcon;
    private ImageIcon homeIcon;

    public IconPanel() {
        setBackground(null);
        setPreferredSize(new Dimension(Constants.ICON_PANEL_WIDTH, Constants.ICON_PANEL_HEIGHT));
//        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        setLayout(new BorderLayout());

        homeIcon = new ImageIcon("src/main/resources/home.png");
        homeButton = new JButton(homeIcon);

        chatIcon = new ImageIcon("src/main/resources/chat.png");
        chatButton = new JButton(chatIcon);

        groupIcon = new ImageIcon("src/main/resources/group.png");
        groupButton = new JButton(groupIcon);

        friendIcon = new ImageIcon("src/main/resources/friends.png");
        friendButton = new JButton(friendIcon);

        requestIcon = new ImageIcon("src/main/resources/request.png");
        requestButton = new JButton(requestIcon);

        blockIcon = new ImageIcon("src/main/resources/block.png");
        blockButton = new JButton(blockIcon);

        homeButton.setBackground(Constants.BACKGROUND_COLOR);
        homeButton.setBorderPainted(false);
//        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        homeButton.setPreferredSize(new Dimension(80, 80));

        chatButton.setBackground(Constants.BACKGROUND_COLOR);
        chatButton.setBorderPainted(false);
        chatButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        chatButton.setPreferredSize(new Dimension(80, 80));

        groupButton.setBackground(Constants.BACKGROUND_COLOR);
        groupButton.setBorderPainted(false);
        groupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        groupButton.setPreferredSize(new Dimension(80, 80));

        friendButton.setBackground(Constants.BACKGROUND_COLOR);
        friendButton.setBorderPainted(false);
        friendButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        friendButton.setPreferredSize(new Dimension(80, 80));

        requestButton.setBackground(Constants.BACKGROUND_COLOR);
        requestButton.setBorderPainted(false);
        requestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        requestButton.setPreferredSize(new Dimension(80, 80));

        blockButton.setBackground(Constants.BACKGROUND_COLOR);
        blockButton.setBorderPainted(false);
        blockButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        blockButton.setPreferredSize(new Dimension(80, 80));

        JPanel topIcons = new JPanel();
        topIcons.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        topIcons.setPreferredSize(new Dimension(Constants.ICON_PANEL_WIDTH, 100 * 5));
        topIcons.add(chatButton);
        topIcons.add(groupButton);
        topIcons.add(friendButton);
        topIcons.add(requestButton);
        topIcons.add(blockButton);

        add(topIcons, BorderLayout.NORTH);
        add(homeButton, BorderLayout.SOUTH);
    }

    public JButton getChatButton() { return this.chatButton; }
    public JButton getGroupButton() { return this.groupButton; }
    public JButton getFriendButton() { return this.friendButton; }
    public JButton getRequestButton() { return this.requestButton; }
    public JButton getBlockButton() { return this.blockButton; }
    public JButton getHomeButton() { return this.homeButton; }
}

