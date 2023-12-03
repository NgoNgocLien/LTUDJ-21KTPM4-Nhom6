package org.example.views;

import org.example.models.DemoUser;
import org.example.models.User;
import org.example.utilities.LoginWorker;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class LoginSignUpScreen extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public LoginSignUpScreen(Socket socket) {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Regnessem");
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
        add(loginButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(signUpButton, gbc);


        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(100, 0, 0, 0);
        add(forgotButton, gbc);

        // Add action listeners
        loginButton.addActionListener(e -> {
            try {
                login(socket);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        signUpButton.addActionListener(e -> signUp(socket));
        forgotButton.addActionListener(e -> forgot(socket));
    }

    private void login(Socket socket) throws IOException {
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        if (LoginWorker.loginRequest(username, password, socket)) {
            ArrayList<DemoUser> users = new ArrayList<DemoUser>();
            users.add(new DemoUser("John Doe", "@johndoe"));
            users.add(new DemoUser("Jane Doe", "@janedoe"));
            users.add(new DemoUser("John Smith", "@johnsmith"));
            users.add(new DemoUser("Jane Smith", "@janesmith"));

            SwingUtilities.invokeLater(() -> {
                this.setVisible(false);
                this.dispose(); // Release resources associated with the frame
            });
//            new MainChatView(users);
        } else JOptionPane.showMessageDialog(this, "Login failed!!!");
    }

    private void signUp(Socket socket) {

        // Perform sign up logic here (e.g., create a new user)
        JOptionPane.showMessageDialog(this, "Sign Up not implemented yet!");
    }

    private void forgot(Socket socket) {
        JOptionPane.showMessageDialog(this, "Forgot pass not implemented");
    }
}
