package org.example.views;

import org.example.models.ChatInfo;
import org.example.utilities.Constants;
import org.example.utilities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddMemberFrame extends JFrame {
    private ArrayList<JCheckBox> memberCheckBoxes;
    private JButton addMemberButton;

    public AddMemberFrame(ArrayList<ChatInfo> allFriendsNotMember) throws SQLException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println("Error setting Windows look and feel: " + e);
        }

        setTitle("Add Member");
        setSize(480, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - 240, dim.height / 2 - 250);

        JLabel titleLabel = new JLabel("Add Member");
        titleLabel.setFont(Constants.FONT_TITLE);
        titleLabel.setForeground(Constants.COLOR_PRIMARY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel chooseMemberPanel = new JPanel();
        chooseMemberPanel.setLayout(new BoxLayout(chooseMemberPanel, BoxLayout.Y_AXIS));

        DatabaseHandler DB = new DatabaseHandler();
        memberCheckBoxes = new ArrayList<>();
        for (ChatInfo chatInfo : allFriendsNotMember) {
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

        addMemberButton = new JButton("Add");
        addMemberButton.setFont(Constants.FONT_NORMAL);
        addMemberButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addMemberButton.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        addMemberButton.setMaximumSize(new Dimension(100, 40));

        JScrollPane scrollPane = new JScrollPane(chooseMemberPanel);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(300, 40 * allFriendsNotMember.size()));
        scrollPane.setMaximumSize(new Dimension(300, 300));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        add(titleLabel);

//        add(Box.createRigidArea(new Dimension(0, 20)));
        add(scrollPane);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(addMemberButton);
        add(Box.createRigidArea(new Dimension(0, 20)));

        setVisible(true);
    }

    public ArrayList<JCheckBox> getMemberCheckBoxes() {
        return memberCheckBoxes;
    }

    public JButton getAddMemberButton() {
        return addMemberButton;
    }
}
