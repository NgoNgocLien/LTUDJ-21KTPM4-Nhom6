package org.example.views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SignUpScreen extends JFrame {

    private JTextField usernameField, fullNameField, addressField, genderField, birthdateField, emailField;
    private JPasswordField passwordField;
    private JButton signUpButton;

    public SignUpScreen() {
        setTitle("SignUp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Create components
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        fullNameField = new JTextField(20);
        addressField = new JTextField(20);
        genderField = new JTextField(20);
        birthdateField = new JTextField(20);
        emailField = new JTextField(20);

        signUpButton = new JButton("Sign Up");

        // Set layout manager to GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

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

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Full Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(fullNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Address:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Gender:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(genderField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Birthdate:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        add(birthdateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        add(emailField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        add(signUpButton, gbc);

        // Add action listener to SignUp button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                signUp();
            }
        });
    }
}