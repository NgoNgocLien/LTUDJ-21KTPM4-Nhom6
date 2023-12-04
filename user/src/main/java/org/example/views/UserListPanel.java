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

    public UserListPanel(ArrayList<ArrayList<Object>> userList) {
        setPreferredSize(new Dimension(Constants.USER_LIST_PANEL_WIDTH, Constants.USER_LIST_PANEL_HEIGHT));
        setBackground(null);
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));
//        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setLayout(new BorderLayout());

        searchPanel = new SearchPanel();

        buildUserListPanel(userList);

        add(searchPanel, BorderLayout.NORTH);
        add(userListScrollPane, BorderLayout.CENTER);
    }

    private void buildUserListPanel(ArrayList<ArrayList<Object>> userList) {
        userListPanel = new JPanel();
        userListPanel.setPreferredSize(new Dimension(Constants.USER_PANEL_WIDTH, Constants.USER_PANEL_HEIGHT * userList.size()));
        userListPanel.setBackground(null);
        userListPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        for (ArrayList<Object> objects : userList) {
            userListPanel.add(new UserPanel((String) objects.get(0), (String) objects.get(1), (Boolean) objects.get(2)));
        }

        userListScrollPane = new JScrollPane(userListPanel);
        userListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        userListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
}
