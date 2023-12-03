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
    public SidePanel(ArrayList<ArrayList<Object>> userList) {
        setPreferredSize(new Dimension(Constants.SIDE_PANEL_WIDTH, Constants.SIDE_PANEL_HEIGHT));
        setLayout(new BorderLayout());
        setBackground(null);

        iconPanel = new IconPanel();

        userListPanel = new UserListPanel(userList);

        add(iconPanel, BorderLayout.WEST);
        add(userListPanel, BorderLayout.EAST);
    }
}

