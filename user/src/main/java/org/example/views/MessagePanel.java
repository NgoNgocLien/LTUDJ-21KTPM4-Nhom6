package org.example.views;

import javax.swing.*;

public class MessagePanel extends JPanel {
    private JPanel messageListPanel;
    private JPanel startPanel;
    private JPanel myMessagePanel;
    private JPanel otherMessagePanel;

    public MessagePanel(String fullname, String username, boolean isFriend) {
        messageListPanel = new JPanel();
        startPanel = new StartChatPanel(fullname, username, isFriend);
        myMessagePanel = new JPanel();
        otherMessagePanel = new JPanel();

        add(startPanel);
    }
}
