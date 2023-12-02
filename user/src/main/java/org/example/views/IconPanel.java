package org.example.views;

import org.example.utilities.Constants;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class IconPanel extends JPanel {

    private JButton chatButton;
    private JButton friendButton;
    private JButton requestButton;
    private JButton blockButton;
    private JButton homeButton;

    private ImageIcon chatIcon;
    private ImageIcon friendIcon;
    private ImageIcon requestIcon;
    private ImageIcon blockIcon;
    private ImageIcon homeIcon;

    public IconPanel() {
        setBackground(null);
        setPreferredSize(new Dimension(Constants.ICON_PANEL_WIDTH, Constants.ICON_PANEL_HEIGHT));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        homeIcon = new ImageIcon("../../../../resources/home.png");
        homeButton = new JButton(homeIcon);

        chatIcon = new ImageIcon("../../../../resources/home.png");
        chatButton = new JButton(chatIcon);

        friendIcon = new ImageIcon("../../../../resources/home.png");
        friendButton = new JButton(friendIcon);

        requestIcon = new ImageIcon("../../../../resources/home.png");
        requestButton = new JButton(requestIcon);

        blockIcon = new ImageIcon("../../../../resources/home.png");
        blockButton = new JButton(blockIcon);

        homeButton.setBackground(null);
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        chatButton.setBackground(null);
        chatButton.setBorderPainted(false);
        chatButton.setFocusPainted(false);
        chatButton.setContentAreaFilled(false);
        chatButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        friendButton.setBackground(null);
        friendButton.setBorderPainted(false);
        friendButton.setFocusPainted(false);
        friendButton.setContentAreaFilled(false);
        friendButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        requestButton.setBackground(null);
        requestButton.setBorderPainted(false);
        requestButton.setFocusPainted(false);
        requestButton.setContentAreaFilled(false);
        requestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        blockButton.setBackground(null);
        blockButton.setBorderPainted(false);
        blockButton.setFocusPainted(false);
        blockButton.setContentAreaFilled(false);
        blockButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        add(chatButton);
        add(friendButton);
        add(requestButton);
        add(blockButton);
        add(homeButton);
    }
}

