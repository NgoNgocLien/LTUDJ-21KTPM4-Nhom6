package org.example.views;

import org.example.utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserPanel extends JPanel {
    private String fullname;
    private String subtitle;

    private JLabel fullnameLabel;
    private JLabel subLabel;

    public UserPanel(String fullname, String subtitle, boolean seen) {
        setPreferredSize(new Dimension(Constants.USER_PANEL_WIDTH, Constants.USER_PANEL_HEIGHT));
        setBackground(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, -2));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        fullnameLabel = new JLabel(fullname);
        fullnameLabel.setPreferredSize(new Dimension(Constants.USER_PANEL_WIDTH - 40, Constants.USER_PANEL_FULLNAME_HEIGHT));
        fullnameLabel.setBackground(null);
        fullnameLabel.setForeground(Color.BLACK);
        fullnameLabel.setFont(Constants.NORMAL_FONT);

        subLabel = new JLabel(subtitle);
        subLabel.setPreferredSize(new Dimension(Constants.USER_PANEL_WIDTH - 40, Constants.USER_PANEL_SUBLABEL_HEIGHT));
        subLabel.setBackground(null);
        subLabel.setForeground(Color.GRAY);
        subLabel.setFont(Constants.SMALL_FONT);
        // subLabel.setAlignmentY(Component.TOP_ALIGNMENT);

        if (!seen) {
            fullnameLabel.setFont(Constants.NORMAL_BOLD_FONT);
            subLabel.setFont(Constants.SMALL_BOLD_FONT);
            subLabel.setForeground(Color.BLACK);
        }

        add(fullnameLabel);
        add(subLabel);
        addMouseListener(new UserPanelListener(this));
    }

    public String getFullname() { return this.fullname; }

    private class UserPanelListener extends MouseAdapter {
        private JPanel panel;
        public UserPanelListener(JPanel panel) {
            this.panel = panel;
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            panel.setBackground(Color.LIGHT_GRAY);
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            // Your action when the panel is clicked
            System.out.println("Panel clicked!");
        }
        @Override
        public void mouseExited(MouseEvent e) {
            panel.setBackground(null);
        }
    }
}
