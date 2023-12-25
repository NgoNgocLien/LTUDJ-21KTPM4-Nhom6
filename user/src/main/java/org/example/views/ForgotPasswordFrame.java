package org.example.views;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.example.utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPasswordFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField emailField;
    private JButton forgotPasswordButton;
    private JLabel rememberLine;

    public ForgotPasswordFrame() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println("Error setting Windows look and feel: " + e);
        }

        setTitle(Constants.APP_NAME);
        setBackground(Constants.COLOR_BACKGROUND);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(480, 460));
        setResizable(true);
        // center the frame on screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2 - 240, dim.height/2 - 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // register jiconfont
//        IconFontSwing.register(FontAwesome.getIconFont());
//        Icon icon = IconFontSwing.buildIcon(FontAwesome.FACEBOOK_SQUARE, 80, Constants.COLOR_PRIMARY);
//        RotatedIcon rotatedIcon = new RotatedIcon(icon, RotatedIcon.Rotate.UPSIDE_DOWN);
//
//        JLabel iconLabel = new JLabel();
//        iconLabel.setIcon(rotatedIcon);
//        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        iconLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // title
        JLabel titleLabel = new JLabel("Forgot Password");
        titleLabel.setFont(Constants.FONT_TITLE);
        titleLabel.setForeground(Constants.COLOR_PRIMARY);
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

        // email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(Constants.FONT_NORMAL);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        emailLabel.setMaximumSize(new Dimension(300, 40));

        emailField = new JPasswordField();
        emailField.setFont(Constants.FONT_NORMAL);
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField.setMaximumSize(new Dimension(300, 40));
        emailField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // statement
        JLabel statementLabel = new JLabel("New password will be sent to your email");
        statementLabel.setFont(Constants.FONT_SMALL_ITALIC);
        statementLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);
        statementLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statementLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        statementLabel.setMaximumSize(new Dimension(300, 40));

        // login button
        forgotPasswordButton = new JButton("Reset password");
        forgotPasswordButton.setFont(Constants.FONT_NORMAL);
        forgotPasswordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgotPasswordButton.setMaximumSize(new Dimension(200, 40));
        forgotPasswordButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // register line
        rememberLine = new JLabel("Remember password? Log in");
        rememberLine.setFont(Constants.FONT_SMALL);
        rememberLine.setAlignmentX(Component.CENTER_ALIGNMENT);
        rememberLine.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        add(titleLabel);

        add(usernameLabel);
        add(usernameField);
        // empty space
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(emailLabel);
        add(emailField);

        add(statementLabel);

        // empty space
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(forgotPasswordButton);

        // empty space
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(rememberLine);


        pack();
        setVisible(true);
    }
    public JTextField getUsernameField() { return usernameField; }
    public JPasswordField getEmailField() { return emailField; }
    public JButton getForgotPasswordButton() { return forgotPasswordButton; }
    public JLabel getRememberLine() { return rememberLine; }
}
