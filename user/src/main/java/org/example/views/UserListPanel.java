package org.example.views;

import org.example.models.User;
import org.example.utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserListPanel extends JPanel {
    private JPanel searchPanel;
    private JPanel userListPanel;
    private JScrollPane userListScrollPane;
    private ArrayList<User> users;

    public UserListPanel(ArrayList<User> users) {
        setPreferredSize(new Dimension(Constants.USER_LIST_PANEL_WIDTH, Constants.USER_LIST_PANEL_HEIGHT));
        setBackground(null);
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        searchPanel = new SearchPanel();

        buildUserListPanel(users);

        add(searchPanel);
        add(userListScrollPane);
    }

    private void buildUserListPanel(ArrayList<User> users) {
        userListPanel = new JPanel();
        userListPanel.setPreferredSize(new Dimension(Constants.USER_PANEL_WIDTH, Constants.USER_PANEL_HEIGHT * users.size() * 3));
        userListPanel.setBackground(null);
        userListPanel.setLayout(new GridLayout(users.size() * 3, 1));

        for (int i = 0; i < users.size(); i++) {
            userListPanel.add(new UserPanel(users.get(i).getFullname(), users.get(i).getUsername()));
        }
        for (int i = 0; i < users.size(); i++) {
            userListPanel.add(new UserPanel(users.get(i).getFullname(), users.get(i).getUsername()));
        }
        for (int i = 0; i < users.size(); i++) {
            userListPanel.add(new UserPanel(users.get(i).getFullname(), users.get(i).getUsername()));
        }

        userListScrollPane = new JScrollPane(userListPanel);
        userListScrollPane.setPreferredSize(new Dimension(Constants.USER_PANEL_WIDTH, Constants.USER_LIST_PANEL_HEIGHT - Constants.SEARCH_PANEL_HEIGHT));
        userListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        userListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
}
