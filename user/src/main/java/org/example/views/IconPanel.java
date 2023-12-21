package org.example.views;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.example.utilities.Constants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class IconPanel extends JPanel {
    private Icon homeIcon;
    private Icon chatIcon;
    private Icon friendIcon;
    private Icon groupIcon;
    private Icon requestIcon;
    private Icon blockIcon;
    private Icon logoutIcon;

    private JLabel homeIconLabel;
    private JLabel chatIconLabel;
    private JLabel friendIconLabel;
    private JLabel groupIconLabel;
    private JLabel requestIconLabel;
    private JLabel blockIconLabel;
    private JLabel logoutIconLabel;

    public IconPanel() {
        setBackground(Constants.COLOR_PRIMARY);
        setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();
        upperPanel.setBackground(Constants.COLOR_PRIMARY);
        upperPanel.setLayout(new GridLayout(6, 1, 0, 0));

        JPanel lowerPanel = new JPanel();
        lowerPanel.setBackground(Constants.COLOR_PRIMARY);
//        lowerPanel.setLayout(new GridLayout(1, 1, 0, 0));

        // Register the IconFont
        IconFontSwing.register(FontAwesome.getIconFont());

        // Initialize the Icons
        homeIcon = IconFontSwing.buildIcon(FontAwesome.HOME, 40, Constants.COLOR_ICON_PRIMARY);
        chatIcon = IconFontSwing.buildIcon(FontAwesome.COMMENT, 40, Constants.COLOR_ICON_PRIMARY);
        friendIcon = IconFontSwing.buildIcon(FontAwesome.USER, 40, Constants.COLOR_ICON_PRIMARY);
        groupIcon = IconFontSwing.buildIcon(FontAwesome.USERS, 40, Constants.COLOR_ICON_PRIMARY);
        requestIcon = IconFontSwing.buildIcon(FontAwesome.BELL, 40, Constants.COLOR_ICON_PRIMARY);
        blockIcon = IconFontSwing.buildIcon(FontAwesome.BAN, 40, Constants.COLOR_ICON_PRIMARY);
        logoutIcon = IconFontSwing.buildIcon(FontAwesome.SIGN_OUT, 40, Constants.COLOR_ICON_PRIMARY);

        // Initialize the Labels
        homeIconLabel = new JLabel(homeIcon);
        homeIconLabel.setPreferredSize(new Dimension(60, 70));
        homeIconLabel.setOpaque(true);
        homeIconLabel.setBackground(Constants.COLOR_PRIMARY);

        chatIconLabel = new JLabel(chatIcon);
        chatIconLabel.setPreferredSize(new Dimension(60, 70));
        chatIconLabel.setOpaque(true);
        chatIconLabel.setBackground(Constants.COLOR_SECONDARY);

        friendIconLabel = new JLabel(friendIcon);
        friendIconLabel.setPreferredSize(new Dimension(60, 70));
        friendIconLabel.setOpaque(true);
        friendIconLabel.setBackground(Constants.COLOR_PRIMARY);

        groupIconLabel = new JLabel(groupIcon);
        groupIconLabel.setPreferredSize(new Dimension(60, 70));
        groupIconLabel.setOpaque(true);
        groupIconLabel.setBackground(Constants.COLOR_PRIMARY);

        requestIconLabel = new JLabel(requestIcon);
        requestIconLabel.setPreferredSize(new Dimension(60, 70));
        requestIconLabel.setOpaque(true);
        requestIconLabel.setBackground(Constants.COLOR_PRIMARY);

        blockIconLabel = new JLabel(blockIcon);
        blockIconLabel.setPreferredSize(new Dimension(60, 70));
        blockIconLabel.setOpaque(true);
        blockIconLabel.setBackground(Constants.COLOR_PRIMARY);

        logoutIconLabel = new JLabel(logoutIcon);
        logoutIconLabel.setPreferredSize(new Dimension(60, 70));
        logoutIconLabel.setOpaque(true);
        logoutIconLabel.setBackground(Constants.COLOR_PRIMARY);

        // Add the Labels to the Panel
        upperPanel.add(homeIconLabel);
        upperPanel.add(chatIconLabel);
        upperPanel.add(friendIconLabel);
        upperPanel.add(groupIconLabel);
        upperPanel.add(requestIconLabel);
        upperPanel.add(blockIconLabel);

        lowerPanel.add(logoutIconLabel);

        // Add the Panels to the Main Panel
        add(upperPanel, BorderLayout.NORTH);
        add(lowerPanel, BorderLayout.SOUTH);
    }

    public JLabel getHomeIconLabel() { return homeIconLabel; }
    public JLabel getChatIconLabel() { return chatIconLabel; }
    public JLabel getFriendIconLabel() { return friendIconLabel; }
    public JLabel getGroupIconLabel() { return groupIconLabel; }
    public JLabel getRequestIconLabel() { return requestIconLabel; }
    public JLabel getBlockIconLabel() { return blockIconLabel; }
    public JLabel getLogoutIconLabel() { return logoutIconLabel; }

    public void setFocusLabel(JLabel label) {
        homeIconLabel.setBackground(Constants.COLOR_PRIMARY);
        chatIconLabel.setBackground(Constants.COLOR_PRIMARY);
        friendIconLabel.setBackground(Constants.COLOR_PRIMARY);
        groupIconLabel.setBackground(Constants.COLOR_PRIMARY);
        requestIconLabel.setBackground(Constants.COLOR_PRIMARY);
        blockIconLabel.setBackground(Constants.COLOR_PRIMARY);
        logoutIconLabel.setBackground(Constants.COLOR_PRIMARY);

        label.setBackground(Constants.COLOR_SECONDARY);
    }
}
