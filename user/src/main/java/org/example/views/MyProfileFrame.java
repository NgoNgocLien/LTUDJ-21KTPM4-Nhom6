package org.example.views;

import org.example.models.Profile;
import org.example.utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyProfileFrame extends JFrame {
    private Profile myProfile;
    private JLabel fullnameTitleLabel;
    private JLabel usernameTitleLabel;
    private JTextField dateJoinedField;
    private JTextField fullnameField;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;
    private JTextField emailField;
    private JTextField addressField;
    private JPasswordField currentPasswordField;
    private JPasswordField newPasswordField;
    private JButton editProfileButton;

    public MyProfileFrame(Profile myProfile) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println("Error setting Windows look and feel: " + e);
        }

        this.myProfile = myProfile;
        setTitle(Constants.APP_NAME);
        setBackground(Constants.COLOR_BACKGROUND);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(480, 800));
        setResizable(true);
        // center the frame on screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - 240, dim.height / 2 - 400);

        // title
        fullnameTitleLabel = new JLabel(myProfile.getFullname());
        fullnameTitleLabel.setFont(Constants.FONT_TITLE);
        fullnameTitleLabel.setForeground(Constants.COLOR_PRIMARY);
        fullnameTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fullnameTitleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        // username
        usernameTitleLabel = new JLabel(myProfile.getUsername());
        usernameTitleLabel.setFont(Constants.FONT_NORMAL);
        usernameTitleLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);
        usernameTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameTitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JLabel dateJoinedLabel = new JLabel("Date joined");
        dateJoinedLabel.setFont(Constants.FONT_SMALL);
        dateJoinedLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);
        dateJoinedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateJoinedLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        dateJoinedLabel.setMaximumSize(new Dimension(300, 30));

        dateJoinedField = new JTextField();
        dateJoinedField.setFont(Constants.FONT_NORMAL);
        dateJoinedField.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateJoinedField.setMaximumSize(new Dimension(300, 40));
        dateJoinedField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        dateJoinedField.setEditable(false);
        // to format "dd-mm-yyyy"
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        dateJoinedField.setText(myProfile.getDateJoined().format(formatters));

        JLabel fullnameLabel = new JLabel("Full name");
        fullnameLabel.setFont(Constants.FONT_SMALL);
        fullnameLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);
        fullnameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fullnameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        fullnameLabel.setMaximumSize(new Dimension(300, 30));

        fullnameField = new JTextField();
        fullnameField.setFont(Constants.FONT_NORMAL);
        fullnameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        fullnameField.setMaximumSize(new Dimension(300, 40));
        fullnameField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        fullnameField.setText(myProfile.getFullname());

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(Constants.FONT_SMALL);
        genderLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);
        genderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        genderLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        genderLabel.setMaximumSize(new Dimension(300, 30));

        String[] genders = {"Gender", "Male", "Female"};

        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setFont(Constants.FONT_NORMAL);
        genderComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        genderComboBox.setMaximumSize(new Dimension(298, 38));
        if (myProfile.getGender() == 0) {
            genderComboBox.setSelectedItem("Female");
        } else if (myProfile.getGender() == 1) {
            genderComboBox.setSelectedItem("Male");
        }

        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new BoxLayout(genderPanel, BoxLayout.X_AXIS));
        genderPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        genderPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        genderPanel.setPreferredSize(new Dimension(300, 40));
        genderPanel.setMaximumSize(new Dimension(300, 40));
        genderPanel.add(genderComboBox);

        String[] days = new String[32];
        days[0] = "Day";
        for (int i = 1; i < 32; i++) {
            days[i] = Integer.toString(i);
        }

        String[] months = new String[13];
        months[0] = "Month";
        for (int i = 1; i < 13; i++) {
            months[i] = Integer.toString(i);
        }

        String[] years = new String[100];
        years[0] = "Year";
        for (int i = 1; i < 100; i++) {
            years[i] = Integer.toString(LocalDate.now().getYear() - i);
        }

        JLabel birthdayLabel = new JLabel("Date of Birth");
        birthdayLabel.setFont(Constants.FONT_SMALL);
        birthdayLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);
        birthdayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        birthdayLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        birthdayLabel.setMaximumSize(new Dimension(300, 30));

        dayComboBox = new JComboBox<>(days);
        dayComboBox.setMaximumSize(new Dimension(98, 38));
        dayComboBox.setFont(Constants.FONT_NORMAL);
        dayComboBox.setSelectedItem(Integer.toString(myProfile.getBirthdate().getDayOfMonth()));

        monthComboBox = new JComboBox<>(months);
        monthComboBox.setMaximumSize(new Dimension(98, 38));
        monthComboBox.setFont(Constants.FONT_NORMAL);
        monthComboBox.setSelectedItem(Integer.toString(myProfile.getBirthdate().getMonthValue()));

        yearComboBox = new JComboBox<>(years);
        yearComboBox.setMaximumSize(new Dimension(98, 38));
        yearComboBox.setFont(Constants.FONT_NORMAL);
        yearComboBox.setSelectedItem(Integer.toString(myProfile.getBirthdate().getYear()));

        JPanel birthdayPanel = new JPanel();
        birthdayPanel.setLayout(new BoxLayout(birthdayPanel, BoxLayout.X_AXIS));
        birthdayPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        birthdayPanel.setPreferredSize(new Dimension(300, 40));
        birthdayPanel.setMaximumSize(new Dimension(300, 40));
        birthdayPanel.add(dayComboBox);
        birthdayPanel.add(monthComboBox);
        birthdayPanel.add(yearComboBox);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(Constants.FONT_SMALL);
        emailLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        emailLabel.setMaximumSize(new Dimension(300, 30));

        emailField = new JTextField();
        emailField.setFont(Constants.FONT_NORMAL);
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField.setMaximumSize(new Dimension(300, 40));
        emailField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        emailField.setText(myProfile.getEmail());
        emailField.setEditable(false);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setFont(Constants.FONT_SMALL);
        addressLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);
        addressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addressLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        addressLabel.setMaximumSize(new Dimension(300, 30));

        addressField = new JTextField();
        addressField.setFont(Constants.FONT_NORMAL);
        addressField.setAlignmentX(Component.CENTER_ALIGNMENT);
        addressField.setMaximumSize(new Dimension(300, 40));
        addressField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        addressField.setText(myProfile.getAddress());

        // password
        JLabel currentPasswordLabel = new JLabel("Current password");
        currentPasswordLabel.setFont(Constants.FONT_SMALL);
        currentPasswordLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);
        currentPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentPasswordLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        currentPasswordLabel.setMaximumSize(new Dimension(300, 30));

        currentPasswordField = new JPasswordField();
        currentPasswordField.setFont(Constants.FONT_NORMAL);
        currentPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentPasswordField.setMaximumSize(new Dimension(300, 40));
        currentPasswordField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JLabel newPasswordLabel = new JLabel("New password");
        newPasswordLabel.setFont(Constants.FONT_SMALL);
        newPasswordLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);
        newPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newPasswordLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        newPasswordLabel.setMaximumSize(new Dimension(300, 30));

        newPasswordField = new JPasswordField();
        newPasswordField.setFont(Constants.FONT_NORMAL);
        newPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        newPasswordField.setMaximumSize(new Dimension(300, 40));
        newPasswordField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // edit profile button
        editProfileButton = new JButton("Edit profile");
        editProfileButton.setFont(Constants.FONT_NORMAL);
        editProfileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        editProfileButton.setMaximumSize(new Dimension(150, 40));
        editProfileButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        add(fullnameTitleLabel);
        add(usernameTitleLabel);

        add(dateJoinedLabel);
        add(dateJoinedField);
        // empty space
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(fullnameLabel);
        add(fullnameField);
        // empty space
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(genderLabel);
        add(genderPanel);
        // empty space
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(birthdayLabel);
        add(birthdayPanel);
        // empty space
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(emailLabel);
        add(emailField);
        // empty space
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(addressLabel);
        add(addressField);
        // empty space
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(currentPasswordLabel);
        add(currentPasswordField);
        // empty space
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(newPasswordLabel);
        add(newPasswordField);
        // empty space
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(editProfileButton);
        add(Box.createRigidArea(new Dimension(0, 10)));

        pack();
        setVisible(true);
    }

    public Profile getMyProfile() {
        return myProfile;
    }

    public JTextField getFullnameField() {
        return fullnameField;
    }

    public JComboBox<String> getGenderComboBox() {
        return genderComboBox;
    }

    public JComboBox<String> getDayComboBox() {
        return dayComboBox;
    }

    public JComboBox<String> getMonthComboBox() {
        return monthComboBox;
    }

    public JComboBox<String> getYearComboBox() {
        return yearComboBox;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getAddressField() {
        return addressField;
    }

    public JPasswordField getCurrentPasswordField() {
        return currentPasswordField;
    }

    public JPasswordField getNewPasswordField() {
        return newPasswordField;
    }

    public JButton getEditProfileButton() {
        return editProfileButton;
    }
}
