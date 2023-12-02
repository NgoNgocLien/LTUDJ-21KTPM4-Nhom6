package org.example.views;

import org.example.models.User;
import org.example.utilities.Constants;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.ArrayList;

public class SidePanel extends JPanel {
    private JPanel iconPanel;
    private JPanel userListPanel;
    public SidePanel(ArrayList<User> users) {
        setPreferredSize(new Dimension(Constants.SIDE_PANEL_WIDTH, Constants.SIDE_PANEL_HEIGHT));
        setLayout(new BorderLayout());
        setBackground(null);

        // iconPanel = new JPanel();
        // iconPanel.setBackground(Color.BLUE);
        // iconPanel.setPreferredSize(new Dimension(Constants.ICON_PANEL_WIDTH, Constants.ICON_PANEL_HEIGHT));

        iconPanel = new IconPanel();

        userListPanel = new UserListPanel(users);

        add(iconPanel, BorderLayout.WEST);
        add(userListPanel, BorderLayout.EAST);
    }
}

