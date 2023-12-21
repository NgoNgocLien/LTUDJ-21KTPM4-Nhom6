package org.example.views;

import org.example.models.DemoUser;
import org.example.utilities.ForgetPwd;
import org.example.utilities.LoginWorker;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        signUpButton.addActionListener(e -> {
            try {
                signUp(socket);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        forgotButton.addActionListener(e -> {
            try {
                forgot(socket);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void login(Socket socket) throws IOException {
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        OutputStream outputStream = socket.getOutputStream();
        String method = "login";
        outputStream.write(method.getBytes());

        InputStream inputStream = socket.getInputStream();
        inputStream.readNBytes(2);

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
        } else JOptionPane.showMessageDialog(this, "Login failed!!!");
    }

    private void signUp(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        String method = "signup";
        outputStream.write(method.getBytes());
        inputStream.readNBytes(2);

        // Perform sign up logic here (e.g., create a new user)
        SwingUtilities.invokeLater(() -> new SignUpScreen(socket).setVisible(true));

    }

    private void forgot(Socket socket) throws IOException {
        String email = JOptionPane.showInputDialog(this, "Enter your email to reset the password:");
        if (email != null && !email.isEmpty()) {
            OutputStream outputStream = socket.getOutputStream();
            String method = "forgetpassword";
            outputStream.write(method.getBytes());

            InputStream inputStream = socket.getInputStream();
            inputStream.readNBytes(2);

            if (ForgetPwd.request(email, socket))
                JOptionPane.showMessageDialog(this, "We sent the new password to your email", "Reset password",JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(this, "Reset password unsuccessfully. Do again", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
