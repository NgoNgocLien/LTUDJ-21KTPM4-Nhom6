package org.example.views;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.example.controllers.LoginFrameController;
import org.example.controllers.RegisterFrameController;
import org.example.utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class RegisterFrame extends JFrame {
    private JTextField fullnameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;
    private JTextField addressField;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;
    private JCheckBox termCheckBox;
    private JButton registerButton;
    private JLabel loginLine;

    public RegisterFrame() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println("Error setting Windows look and feel: " + e);
        }

        setTitle(Constants.APP_NAME);
        setBackground(Constants.COLOR_BACKGROUND);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(820, 720));
        setResizable(true);
        // center the frame on screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2 - 410, dim.height/2 - 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // register jiconfont
        IconFontSwing.register(FontAwesome.getIconFont());
        Icon icon = IconFontSwing.buildIcon(FontAwesome.FACEBOOK_SQUARE, 80, Constants.COLOR_PRIMARY);
        RotatedIcon rotatedIcon = new RotatedIcon(icon, RotatedIcon.Rotate.UPSIDE_DOWN);

        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(rotatedIcon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel titleLabel = new JLabel("Sign Up");
        titleLabel.setFont(Constants.FONT_TITLE);
        titleLabel.setForeground(Constants.COLOR_PRIMARY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(1, 2, 40, 0));
        container.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        container.setMaximumSize(new Dimension(640, 540));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // fullname
        JLabel fullnameLabel = new JLabel("Fullname");
        fullnameLabel.setFont(Constants.FONT_NORMAL);
        fullnameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fullnameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        fullnameLabel.setMaximumSize(new Dimension(300, 40));

        fullnameField = new JTextField();
        fullnameField.setFont(Constants.FONT_NORMAL);
        fullnameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        fullnameField.setMaximumSize(new Dimension(300, 40));
        fullnameField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(Constants.FONT_NORMAL);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        usernameLabel.setMaximumSize(new Dimension(300, 40));

        usernameField = new JTextField();
        usernameField.setFont(Constants.FONT_NORMAL);
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setMaximumSize(new Dimension(300, 40));
        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(Constants.FONT_NORMAL);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        passwordLabel.setMaximumSize(new Dimension(300, 40));

        passwordField = new JPasswordField();
        passwordField.setFont(Constants.FONT_NORMAL);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setMaximumSize(new Dimension(300, 40));
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // confirm password
        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setFont(Constants.FONT_NORMAL);
        confirmPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmPasswordLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        confirmPasswordLabel.setMaximumSize(new Dimension(300, 40));

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(Constants.FONT_NORMAL);
        confirmPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmPasswordField.setMaximumSize(new Dimension(300, 40));
        confirmPasswordField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        leftPanel.add(fullnameLabel);
        leftPanel.add(fullnameField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(usernameLabel);
        leftPanel.add(usernameField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(passwordLabel);
        leftPanel.add(passwordField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(confirmPasswordLabel);
        leftPanel.add(confirmPasswordField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(Constants.FONT_NORMAL);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        emailLabel.setMaximumSize(new Dimension(300, 40));

        emailField = new JTextField();
        emailField.setFont(Constants.FONT_NORMAL);
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField.setMaximumSize(new Dimension(300, 40));
        emailField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // address
        JLabel addressLabel = new JLabel("Address");
        addressLabel.setFont(Constants.FONT_NORMAL);
        addressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addressLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        addressLabel.setMaximumSize(new Dimension(300, 40));

        addressField = new JTextField();
        addressField.setFont(Constants.FONT_NORMAL);
        addressField.setAlignmentX(Component.CENTER_ALIGNMENT);
        addressField.setMaximumSize(new Dimension(300, 40));
        addressField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // gender
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(Constants.FONT_NORMAL);
        genderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        genderLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        genderLabel.setMaximumSize(new Dimension(300, 40));

        String[] genders = {"Gender", "Male", "Female"};

        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setFont(Constants.FONT_NORMAL);
        genderComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
//        genderComboBox.setPreferredSize(new Dimension(298, 38));
        genderComboBox.setMaximumSize(new Dimension(298, 38));

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
        birthdayLabel.setFont(Constants.FONT_NORMAL);
        birthdayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        birthdayLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        birthdayLabel.setMaximumSize(new Dimension(300, 40));

        dayComboBox = new JComboBox<>(days);
        monthComboBox = new JComboBox<>(months);
        yearComboBox = new JComboBox<>(years);

//        dayComboBox.setPreferredSize(new Dimension(98, 38));
        dayComboBox.setMaximumSize(new Dimension(98, 38));
        dayComboBox.setFont(Constants.FONT_NORMAL);
//        monthComboBox.setPreferredSize(new Dimension(98, 38));
        monthComboBox.setMaximumSize(new Dimension(98, 38));
        monthComboBox.setFont(Constants.FONT_NORMAL);
//        yearComboBox.setPreferredSize(new Dimension(98, 38));
        yearComboBox.setMaximumSize(new Dimension(98, 38));
        yearComboBox.setFont(Constants.FONT_NORMAL);

        JPanel birthdayPanel = new JPanel();
        birthdayPanel.setLayout(new BoxLayout(birthdayPanel, BoxLayout.X_AXIS));
        birthdayPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        birthdayPanel.setPreferredSize(new Dimension(300, 40));
        birthdayPanel.setMaximumSize(new Dimension(300, 40));
        birthdayPanel.add(dayComboBox);
        birthdayPanel.add(monthComboBox);
        birthdayPanel.add(yearComboBox);

        rightPanel.add(emailLabel);
        rightPanel.add(emailField);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(addressLabel);
        rightPanel.add(addressField);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(genderLabel);
        rightPanel.add(genderPanel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(birthdayLabel);
        rightPanel.add(birthdayPanel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // term
        termCheckBox = new JCheckBox("I agree to the Terms of Use");
        termCheckBox.setFont(Constants.FONT_SMALL);
        termCheckBox.setForeground(Constants.COLOR_PRIMARY);
        termCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        termCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // register button
        registerButton = new JButton("Sign up");
        registerButton.setFont(Constants.FONT_NORMAL);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setMaximumSize(new Dimension(100, 40));
        registerButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // login line
        loginLine = new JLabel("Already have an account? Log in");
        loginLine.setFont(Constants.FONT_SMALL);
        loginLine.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginLine.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        container.add(leftPanel);
        container.add(rightPanel);

        add(iconLabel);
        add(titleLabel);

        add(container);
        add(Box.createRigidArea(new Dimension(0, 0)));

        add(termCheckBox);
        add(Box.createRigidArea(new Dimension(0, 10)));

        add(registerButton);
        add(Box.createRigidArea(new Dimension(0, 40)));

        add(loginLine);
        add(Box.createRigidArea(new Dimension(0, 40)));

        pack();
        setVisible(true);
    }

    public JTextField getFullnameField() { return fullnameField; }
    public JTextField getUsernameField() { return usernameField; }
    public JPasswordField getPasswordField() { return passwordField; }
    public JPasswordField getConfirmPasswordField() { return confirmPasswordField; }
    public JTextField getEmailField() { return emailField; }
    public JTextField getAddressField() { return addressField; }
    public JComboBox<String> getGenderComboBox() { return genderComboBox; }
    public JComboBox<String> getDayComboBox() { return dayComboBox; }
    public JComboBox<String> getMonthComboBox() { return monthComboBox; }
    public JComboBox<String> getYearComboBox() { return yearComboBox; }
    public JCheckBox getTermCheckBox() { return termCheckBox; }
    public JButton getRegisterButton() { return registerButton; }
    public JLabel getLoginLine() { return loginLine; }
}
