package org.example;

import javax.swing.*;
import java.awt.*;

public class LoginSignUpScreen extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public LoginSignUpScreen() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Messenger");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");
        JButton forgotButton = new JButton("Forgot password?");

        // Set layout manager to GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Add some padding

        // Add components to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
//        gbc.gridwidth = 2;
        add(loginButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(signUpButton, gbc);


        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(100, 0, 0, 0);
        add(forgotButton, gbc);

        // Add action listeners
        loginButton.addActionListener(e -> login());
        signUpButton.addActionListener(e -> signUp());
        forgotButton.addActionListener(e -> forgot());
    }

    private void login() {
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        // Perform login asynchronously
        Client_Socket.LoginWorker loginWorker = new Client_Socket.LoginWorker(username, password);
        loginWorker.execute();
        System.out.println("login");
    }

    private void signUp() {
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        // Perform sign up logic here (e.g., create a new user)
        JOptionPane.showMessageDialog(this, "Sign Up not implemented yet!");
    }

    private void forgot() {
        JOptionPane.showMessageDialog(this, "Forgot pass not implemented");
    }
}
