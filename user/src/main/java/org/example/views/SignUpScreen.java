package org.example.views;


import org.example.utilities.SignupWorker;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class SignUpScreen extends JFrame {

    private JTextField usernameField, fullNameField, addressField, birthdateField, emailField;
    private JPasswordField passwordField;
    private JButton signUpButton;

    private JRadioButton jMale, jFemale;

    public SignUpScreen(Socket socket) {
        setTitle("SignUp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        // Create components
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        fullNameField = new JTextField(20);
        addressField = new JTextField(20);
        jMale = new JRadioButton("Male");
        jFemale = new JRadioButton("Female");
        birthdateField = new JTextField(20);
        emailField = new JTextField(20);

        signUpButton = new JButton("Sign Up");
        ButtonGroup gender = new ButtonGroup();
        gender.add(jFemale);
        gender.add(jMale);

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
        add(jMale, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        add(jFemale, gbc);

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
        signUpButton.addActionListener(e -> {
            try {
                signUp(socket);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void signUp(Socket socket) throws IOException {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String fullname = fullNameField.getText();
        String address = addressField.getText();
        String gender = "";
        if (jMale.isSelected()) gender = "male";
        else if (jFemale.isSelected()) gender = "female";
        String birthdate = birthdateField.getText();
        String email = emailField.getText();
        SignupWorker.signupRequest(username, password, fullname, address, gender, birthdate, email, socket);
    }


}