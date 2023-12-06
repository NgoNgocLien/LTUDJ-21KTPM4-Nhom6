package org.example.views;

import org.example.utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserPanel extends JPanel {
    private String chatName;
    private String subtitle;
    private boolean seen;
    private String chatId;
    private boolean isGroup;

    private JLabel fullnameLabel;
    private JLabel subLabel;

    public UserPanel(String chatName, String subtitle, boolean seen) {
        this.chatName = chatName;
        this.subtitle = subtitle;
        this.seen = seen;
//        this.chatId = chatId;
//        this.isGroup = isGroup;

        setPreferredSize(new Dimension(Constants.USER_PANEL_WIDTH, Constants.USER_PANEL_HEIGHT));
        setBackground(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, -2));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        fullnameLabel = new JLabel(chatName);
        fullnameLabel.setPreferredSize(new Dimension(Constants.USER_PANEL_WIDTH - 40, Constants.USER_PANEL_FULLNAME_HEIGHT));
        fullnameLabel.setBackground(null);
        fullnameLabel.setForeground(Color.BLACK);
        fullnameLabel.setFont(Constants.NORMAL_FONT);

        subLabel = new JLabel(subtitle);
        subLabel.setPreferredSize(new Dimension(Constants.USER_PANEL_WIDTH - 40, Constants.USER_PANEL_SUBLABEL_HEIGHT));
        subLabel.setBackground(null);
        subLabel.setForeground(Color.GRAY);
        subLabel.setFont(Constants.SMALL_FONT);

        if (!seen) {
            fullnameLabel.setFont(Constants.NORMAL_BOLD_FONT);
            subLabel.setFont(Constants.SMALL_BOLD_FONT);
            subLabel.setForeground(Color.BLACK);
        }

        add(fullnameLabel);
        add(subLabel);
        addMouseListener(new UserPanelListener(this));
    }

    public String getChatName() { return this.chatName; }
    public String getSubtitle() { return this.subtitle; }
    public boolean getSeen() { return this.seen; }
    public String getChatId() { return this.chatId; }
    public boolean getIsGroup() { return this.isGroup; }

    private class UserPanelListener extends MouseAdapter {
        private UserPanel panel;
        public UserPanelListener(UserPanel panel) {
            this.panel = panel;
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            panel.setBackground(Color.LIGHT_GRAY);
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            // Your action when the panel is clicked
            System.out.println(panel.getChatId() + " " + panel.getIsGroup());
        }
        @Override
        public void mouseExited(MouseEvent e) {
            panel.setBackground(null);
        }
    }
}
