package org.example.views;

import org.example.models.SideChatInfo;
import org.example.models.User;
import org.example.utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserPanel extends JPanel {
    private JLabel chatNameLabel;
    private JLabel subLabel;
    private SideChatInfo chatInfo;

    public UserPanel(SideChatInfo chatInfo) {
        this.chatInfo = chatInfo;

        setPreferredSize(new Dimension(Constants.USER_PANEL_WIDTH, Constants.USER_PANEL_HEIGHT));
        setBackground(Color.WHITE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, -2));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//        setFocusPainted(false);
//        setContentAreaFilled(true);

        chatNameLabel = new JLabel(chatInfo.getChatName());
        chatNameLabel.setPreferredSize(new Dimension(Constants.USER_PANEL_WIDTH - 40, Constants.USER_PANEL_FULLNAME_HEIGHT));
        chatNameLabel.setBackground(null);
        chatNameLabel.setForeground(Color.BLACK);
        chatNameLabel.setFont(Constants.NORMAL_FONT);

        subLabel = new JLabel(chatInfo.getSubtitle());
        subLabel.setPreferredSize(new Dimension(Constants.USER_PANEL_WIDTH - 40, Constants.USER_PANEL_SUBLABEL_HEIGHT));
        subLabel.setBackground(null);
        subLabel.setForeground(Color.GRAY);
        subLabel.setFont(Constants.SMALL_FONT);

        if (!chatInfo.getSeen()) {
            chatNameLabel.setFont(Constants.NORMAL_BOLD_FONT);
            subLabel.setFont(Constants.SMALL_BOLD_FONT);
            subLabel.setForeground(Color.BLACK);
        }

        add(chatNameLabel);
        add(subLabel);
    }

     public SideChatInfo getChatInfo() { return this.chatInfo; }
}
