package org.example.views;

import org.example.models.SideChatInfo;
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
    private ArrayList<UserPanel> userPanels;
    private ArrayList<SideChatInfo> userList;

    public UserListPanel(ArrayList<SideChatInfo> userList) {
        setPreferredSize(new Dimension(Constants.USER_LIST_PANEL_WIDTH, Constants.USER_LIST_PANEL_HEIGHT));
        setBackground(null);
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));
//        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setLayout(new BorderLayout());

        searchPanel = new SearchPanel();
        this.userList = userList;

        userListPanel = new JPanel();
        userListPanel.setPreferredSize(new Dimension(Constants.USER_PANEL_WIDTH, Constants.USER_PANEL_HEIGHT * userList.size()));
        userListPanel.setBackground(null);
        userListPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        userListScrollPane = new JScrollPane(userListPanel);
        userListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        userListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        buildUserListPanel(userList);

        add(searchPanel, BorderLayout.NORTH);
        add(userListScrollPane, BorderLayout.CENTER);
    }

    public void buildUserListPanel(ArrayList<SideChatInfo> userList) {
        if (userListPanel != null)
            userListPanel.removeAll();

        userPanels = new ArrayList<>();
        for (SideChatInfo chatInfo : userList) {
            UserPanel userPanel = new UserPanel(chatInfo);
            userPanels.add(userPanel);
            userListPanel.add(userPanel);
        }
        SwingUtilities.invokeLater(() -> {
            revalidate();  // Revalidate the panel
            repaint();     // Repaint the panel
        });
    }

    public ArrayList<UserPanel> getUserPanels() { return this.userPanels; }
}
