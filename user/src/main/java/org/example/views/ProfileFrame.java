package org.example.views;

import org.example.models.Profile;
import org.example.utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class ProfileFrame extends JFrame {
    int mode; //1: not a friend //2: is a friend //3: request //4: blocked
    private Profile profile;
    private JButton leftButton;
    private JButton rightButton;


    public ProfileFrame(Profile profile, int mode) {
        System.out.println("ProfileFrame" + mode);
        this.mode = mode;
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println("Error setting Windows look and feel: " + e);
        }

        this.profile = profile;
        setTitle(Constants.APP_NAME);
        setBackground(Constants.COLOR_BACKGROUND);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(480, 640));
        setResizable(true);
        // center the frame on screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - 240, dim.height / 2 - 320);

        // title
        JLabel fullnameTitleLabel = new JLabel(profile.getFullname());
        fullnameTitleLabel.setFont(Constants.FONT_TITLE);
        fullnameTitleLabel.setForeground(Constants.COLOR_PRIMARY);
        fullnameTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fullnameTitleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // username
        JLabel usernameTitleLabel = new JLabel(profile.getUsername());
        usernameTitleLabel.setFont(Constants.FONT_NORMAL);
        usernameTitleLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);
        usernameTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameTitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JLabel dateJoinedLabel = new JLabel("Date joined");
        dateJoinedLabel.setFont(Constants.FONT_SMALL);
        dateJoinedLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);
        dateJoinedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        dateJoinedLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dateJoinedLabel.setMaximumSize(new Dimension(300, 30));

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        JLabel dateJoinedField = new JLabel(profile.getDateJoined().format(formatters));
        dateJoinedField.setFont(Constants.FONT_NORMAL);
        dateJoinedField.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateJoinedField.setMaximumSize(new Dimension(300, 40));
        dateJoinedField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(Constants.FONT_SMALL);
        genderLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);
        genderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        genderLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        genderLabel.setMaximumSize(new Dimension(300, 30));

        JLabel genderField = new JLabel(profile.getGender() == 1 ? "Male" : "Female");
        genderField.setFont(Constants.FONT_NORMAL);
        genderField.setAlignmentX(Component.CENTER_ALIGNMENT);
        genderField.setMaximumSize(new Dimension(300, 40));
        genderField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JLabel birthdayLabel = new JLabel("Date of Birth");
        birthdayLabel.setFont(Constants.FONT_SMALL);
        birthdayLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);
        birthdayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        birthdayLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        birthdayLabel.setMaximumSize(new Dimension(300, 30));

        JLabel birthdateField = new JLabel(profile.getBirthdate().format(formatters));
        birthdateField.setFont(Constants.FONT_NORMAL);
        birthdateField.setAlignmentX(Component.CENTER_ALIGNMENT);
        birthdateField.setMaximumSize(new Dimension(300, 40));
        birthdateField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(Constants.FONT_SMALL);
        emailLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        emailLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        emailLabel.setMaximumSize(new Dimension(300, 30));

        JLabel emailField = new JLabel();
        emailField.setFont(Constants.FONT_NORMAL);
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField.setMaximumSize(new Dimension(300, 40));
        emailField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        emailField.setText(profile.getEmail());

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setFont(Constants.FONT_SMALL);
        addressLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);
        addressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        addressLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        addressLabel.setMaximumSize(new Dimension(300, 30));

        JLabel addressField = new JLabel();
        addressField.setFont(Constants.FONT_NORMAL);
        addressField.setAlignmentX(Component.CENTER_ALIGNMENT);
        addressField.setMaximumSize(new Dimension(300, 40));
        addressField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        addressField.setText(profile.getAddress());

        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.X_AXIS));
        buttonContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonContainer.setMaximumSize(new Dimension(300, 40));

        if (mode == 1) {
            // edit profile button
            leftButton = new JButton("Add friend"); // if friend, show "Unfriend"
            leftButton.setFont(Constants.FONT_NORMAL);
            leftButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            leftButton.setMaximumSize(new Dimension(140, 40));
            leftButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

            // block button
            rightButton = new JButton("Block"); // if blocked, show "Unblock"
            rightButton.setFont(Constants.FONT_NORMAL);
            rightButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            rightButton.setMaximumSize(new Dimension(140, 40));
            rightButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        } else if (mode == 2) {
            // edit profile button
            leftButton = new JButton("Unfriend"); // if friend, show "Unfriend"
            leftButton.setFont(Constants.FONT_NORMAL);
            leftButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            leftButton.setMaximumSize(new Dimension(140, 40));
            leftButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

            // block button
            rightButton = new JButton("Block"); // if blocked, show "Unblock"
            rightButton.setFont(Constants.FONT_NORMAL);
            rightButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            rightButton.setMaximumSize(new Dimension(140, 40));
            rightButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        } else if (mode == 3) {
            // edit profile button
            leftButton = new JButton("Accept"); // if friend, show "Unfriend"
            leftButton.setFont(Constants.FONT_NORMAL);
            leftButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            leftButton.setMaximumSize(new Dimension(140, 40));
            leftButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

            // block button
            rightButton = new JButton("Remove"); // if blocked, show "Unblock"
            rightButton.setFont(Constants.FONT_NORMAL);
            rightButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            rightButton.setMaximumSize(new Dimension(140, 40));
            rightButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        } else if (mode == 4) {
            leftButton = new JButton("Unblock");
            leftButton.setFont(Constants.FONT_NORMAL);
            leftButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            leftButton.setMaximumSize(new Dimension(140, 40));
            leftButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        }


        buttonContainer.add(leftButton);
        buttonContainer.add(Box.createRigidArea(new Dimension(20, 0)));
        if (rightButton != null) {
            buttonContainer.add(rightButton);

        }
        add(fullnameTitleLabel);
        add(usernameTitleLabel);

        // empty space
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(buttonContainer);
        // empty space
        add(Box.createRigidArea(new Dimension(0, 20)));

        add(dateJoinedLabel);
        add(dateJoinedField);

        // empty space
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(genderLabel);
        add(genderField);
        // empty space
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(birthdayLabel);
        add(birthdateField);
        // empty space
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(emailLabel);
        add(emailField);
        // empty space
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(addressLabel);
        add(addressField);
        // empty space
        add(Box.createRigidArea(new Dimension(0, 20)));


        pack();
        setVisible(true);
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public JButton getLeftButton() {
        return leftButton;
    }

    public void setLeftButton(JButton leftButton) {
        this.leftButton = leftButton;
    }

    public JButton getRightButton() {
        return rightButton;
    }

    public void setRightButton(JButton rightButton) {
        this.rightButton = rightButton;
    }
    //    public static void main(String[] args) {
//        Profile profile = new Profile(LocalDate.now(), "fullname", "username", 1, LocalDate.now(), "email", "address");
//        SwingUtilities.invokeLater(() -> new ProfileFrame(profile));
//    }
}
