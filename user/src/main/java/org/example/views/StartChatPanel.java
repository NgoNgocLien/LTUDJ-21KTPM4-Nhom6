//package org.example.views;
//
//import org.example.utilities.Constants;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class StartChatPanel extends JPanel {
//    private JLabel fullnameLabel;
//    private JLabel usernameLabel;
//    private JLabel statementLabel;
//
//    private JPanel fullnamePanel;
//    private JPanel usernamePanel;
//    private JPanel statementPanel;
//
//    public StartChatPanel(String fullname, String username, boolean isFriend, boolean isGroup) {
//        setLayout(new GridLayout(5, 1));
//        setBackground(null);
//
//        if (!isGroup && !isFriend && fullname.isEmpty() && username.isEmpty()) {
//            fullname = "Welcome back!";
//            username = "";
//            statementLabel = new JLabel("Please select a user to start chatting.");
//        }
//        else if (isGroup)
//            statementLabel = new JLabel("You are a member of this group. Let start chatting!");
//        else if (isFriend)
//            statementLabel = new JLabel("You are friend with this user. You can now chat with your friend.");
//        else
//            statementLabel = new JLabel("You are not friend with this user. You cannot send messages now.");
//
//        fullnameLabel = new JLabel(fullname);
//        fullnameLabel.setFont(Constants.BIGGER_FONT);
//        fullnameLabel.setForeground(Color.BLACK);
//
//        usernameLabel = new JLabel(username);
//        usernameLabel.setFont(Constants.NORMAL_FONT);
//        usernameLabel.setForeground(Color.GRAY);
//
//        statementLabel.setFont(Constants.NORMAL_FONT);
//        statementLabel.setForeground(Color.GRAY);
//
//        fullnamePanel = new JPanel();
//        fullnamePanel.setBackground(null);
//        fullnamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
//        fullnamePanel.add(fullnameLabel);
//
//        usernamePanel = new JPanel();
//        usernamePanel.setBackground(null);
//        usernamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
//        usernamePanel.add(usernameLabel);
//
//        statementPanel = new JPanel();
//        statementPanel.setBackground(null);
//        statementPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
//        statementPanel.add(statementLabel);
//
//        add(new JPanel());
//        add(fullnamePanel);
//        add(usernamePanel);
//        add(statementPanel);
//        add(new JPanel());
//    }
//}
