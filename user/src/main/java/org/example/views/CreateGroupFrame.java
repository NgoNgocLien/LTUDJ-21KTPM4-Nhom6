package org.example.views;

import org.example.models.ChatInfo;
import org.example.utilities.Constants;
import org.example.utilities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreateGroupFrame extends JFrame {
    private JTextField groupNameTextField;
    private ArrayList<JCheckBox> memberCheckBoxes;
    private JButton createGroupButton;
    public CreateGroupFrame(ArrayList<ChatInfo> allFriends) throws SQLException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println("Error setting Windows look and feel: " + e);
        }

        setTitle("Create Group");
        setSize(480, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - 240, dim.height / 2 - 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Create Group");
        titleLabel.setFont(Constants.FONT_TITLE);
        titleLabel.setForeground(Constants.COLOR_PRIMARY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // group name
        JPanel groupNamePanel = new JPanel();
        groupNamePanel.setLayout(new BoxLayout(groupNamePanel, BoxLayout.Y_AXIS));

        JLabel groupNameLabel = new JLabel("Group Name");
        groupNameLabel.setFont(Constants.FONT_NORMAL);
        groupNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        groupNameLabel.setMaximumSize(new Dimension(300, 40));

        groupNameTextField = new JTextField();
        groupNameTextField.setFont(Constants.FONT_NORMAL);
        groupNameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupNameTextField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        groupNameTextField.setMaximumSize(new Dimension(300, 40));

        groupNamePanel.add(groupNameLabel);
        groupNamePanel.add(groupNameTextField);

        JPanel chooseMemberPanel = new JPanel();
        chooseMemberPanel.setLayout(new BoxLayout(chooseMemberPanel, BoxLayout.Y_AXIS));

        DatabaseHandler DB = new DatabaseHandler();
        memberCheckBoxes = new ArrayList<>();
        for (ChatInfo chatInfo : allFriends) {
            JCheckBox checkBox = new JCheckBox(chatInfo.getChatName());
            checkBox.setActionCommand(chatInfo.getUsername());
            checkBox.setFont(Constants.FONT_NORMAL);
            checkBox.setAlignmentX(Component.CENTER_ALIGNMENT);
            checkBox.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            checkBox.setMaximumSize(new Dimension(300, 40));
            chooseMemberPanel.add(checkBox);
            memberCheckBoxes.add(checkBox);
            System.out.println(checkBox.getActionCommand());
        }

        createGroupButton = new JButton("Create");
        createGroupButton.setFont(Constants.FONT_NORMAL);
        createGroupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createGroupButton.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        createGroupButton.setMaximumSize(new Dimension(100, 40));

        JScrollPane scrollPane = new JScrollPane(chooseMemberPanel);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(300, 40 * allFriends.size()));
        scrollPane.setMaximumSize(new Dimension(300, 300));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        add(titleLabel);
        add(groupNamePanel);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(scrollPane);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(createGroupButton);
        add(Box.createRigidArea(new Dimension(0, 20)));

        setVisible(true);
    }

    public JTextField getGroupNameTextField() {
        return groupNameTextField;
    }

    public ArrayList<JCheckBox> getMemberCheckBoxes() {
        return memberCheckBoxes;
    }

    public JButton getCreateGroupButton() {
        return createGroupButton;
    }
}
