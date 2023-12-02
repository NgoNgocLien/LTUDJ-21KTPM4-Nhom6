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

    public UserPanel(String fullname, String subtitle) {
        setPreferredSize(new Dimension(Constants.USER_PANEL_WIDTH, Constants.USER_PANEL_HEIGHT));
        setBackground(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, -4));
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

        add(fullnameLabel);
        add(subLabel);
    }
}