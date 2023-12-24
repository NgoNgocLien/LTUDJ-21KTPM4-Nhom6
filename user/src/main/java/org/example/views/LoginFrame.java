package org.example.views;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.example.controllers.LoginFrameController;
import org.example.utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel registerLine;
    private JLabel forgotPasswordLine;

    public LoginFrame() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println("Error setting Windows look and feel: " + e);
        }

        setBackground(Constants.COLOR_BACKGROUND);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(480, 530));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // register jiconfont
        IconFontSwing.register(FontAwesome.getIconFont());
        Icon icon = IconFontSwing.buildIcon(FontAwesome.FACEBOOK_SQUARE, 80, Constants.COLOR_PRIMARY);
        RotatedIcon rotatedIcon = new RotatedIcon(icon, RotatedIcon.Rotate.UPSIDE_DOWN);

        JLabel titleLabel = new JLabel();
        titleLabel.setIcon(rotatedIcon);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

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

        // login button
        loginButton = new JButton("Log in");
        loginButton.setFont(Constants.FONT_NORMAL);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setMaximumSize(new Dimension(100, 40));
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // register line
        registerLine = new JLabel("Don't have an account? Register");
        registerLine.setFont(Constants.FONT_SMALL);
        registerLine.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerLine.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // forgot password line
        forgotPasswordLine = new JLabel("Forgot password?");
        forgotPasswordLine.setFont(Constants.FONT_SMALL);
        forgotPasswordLine.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgotPasswordLine.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


        add(titleLabel);

        add(usernameLabel);
        add(usernameField);
        // empty space
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(passwordLabel);
        add(passwordField);
        // empty space
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(loginButton);
        // empty space
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(registerLine);
        // empty space
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(forgotPasswordLine);

        pack();
        setVisible(true);
    }
    public JTextField getUsernameField() { return usernameField; }
    public JPasswordField getPasswordField() { return passwordField; }
    public JButton getLoginButton() { return loginButton; }
    public JLabel getRegisterLine() { return registerLine; }
    public JLabel getForgotPasswordLine() { return forgotPasswordLine; }

    public static void main(String[] args) {
        LoginFrame lf = new LoginFrame();
        LoginFrameController lfc = new LoginFrameController(lf, null);
    }
}
