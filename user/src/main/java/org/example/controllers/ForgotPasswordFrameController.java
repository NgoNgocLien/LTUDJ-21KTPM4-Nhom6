package org.example.controllers;

import org.example.utilities.Constants;
import org.example.utilities.DatabaseHandler;
import org.example.utilities.ForgetPwd;
import org.example.views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;

public class ForgotPasswordFrameController {
    private ForgotPasswordFrame FPF;
    private DatabaseHandler DB;
    private JTextField usernameField;
    private JTextField emailField;
    private JButton forgotPasswordButton;
    private JLabel rememberLine;
    private Socket socket;

    public ForgotPasswordFrameController(Socket socket, ForgotPasswordFrame FPF, DatabaseHandler DB) {
        this.FPF = FPF;
        this.DB = DB;
        this.socket = socket;

        this.FPF.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    DB.closeConnection();
                } catch (Exception e) {
                    System.out.println("Error closing window: " + e);
                }
            }
        });

        this.usernameField = FPF.getUsernameField();
        this.emailField = FPF.getEmailField();
        this.forgotPasswordButton = FPF.getForgotPasswordButton();
        this.rememberLine = FPF.getRememberLine();

        this.usernameField.addKeyListener(new InputFieldListener(usernameField));
        this.emailField.addKeyListener(new InputFieldListener(emailField));

        this.forgotPasswordButton.addActionListener(new ForgotPasswordButtonListener());
        this.rememberLine.addMouseListener(new LineListener(this.rememberLine));
    }

    private boolean checkUsername(String username) {
        String regex = "^[a-z0-9._-]{1,}$";
        return username.matches(regex);
    }

    private boolean checkEmail(String email) {
        // aa@bb.cc
        String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        return email.matches(regex);
    }

    private class InputFieldListener implements KeyListener {
        private JTextField inputField;

        public InputFieldListener(JTextField inputField) {
            this.inputField = inputField;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String username = usernameField.getText();
                String email = emailField.getText();
                if (inputField == usernameField && !username.isEmpty()) {
                    emailField.requestFocus();
                }
                else if (inputField == emailField && !email.isEmpty()) {
                    forgotPasswordButton.doClick();
                }
            }
        }

        // Không để làm gì nhưng không xóa vì KeyListener bắt buộc phải override
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {}
    }

    private class ForgotPasswordButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String email = emailField.getText();

            // check input fields
            if (username.isEmpty()) {
                usernameField.requestFocus();
                new ErrorMessage(FPF, "Please enter your username");
                return;
            }
            else if (email.isEmpty()) {
                emailField.requestFocus();
                new ErrorMessage(FPF, "Please enter your email");
                return;
            }
            else if (!checkUsername(username) || !checkEmail(email)) { // Kiểm tra format trước khi kiểm tra qua database
                new ErrorMessage(FPF, "Invalid username or email");
                return;
            }

            // TEST & DELETE AFTER: show username and email
//            JOptionPane.showMessageDialog(FPF, "Username: " + username + "\nEmail: " + email);

            // TODO: verify user info with database
            try {
                if (ForgetPwd.request(username, email, socket)){
                    new SuccessMessage(FPF, "Please check your email to get new password");

                    // success: close forgot password frame, open login frame
                    FPF.dispose();
                    LoginFrame LF = new LoginFrame();
                    LoginFrameController LFC = new LoginFrameController(socket, LF, DB);
                }
                else{
                    new ErrorMessage(FPF, "Wrong username or email");
                }

            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }


        }
    }

    private class LineListener implements MouseListener {
        private JLabel line;

        public LineListener(JLabel line) {
            this.line = line;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == rememberLine) {
                // close forgot password frame
                FPF.dispose();
                // open login frame
                LoginFrame LF = new LoginFrame();
                LoginFrameController LFC = new LoginFrameController(socket, LF, DB);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == rememberLine) {
                rememberLine.setForeground(Constants.COLOR_PRIMARY);
                rememberLine.setFont(Constants.FONT_SMALL_BOLD);
                rememberLine.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == rememberLine) {
                rememberLine.setForeground(Constants.COLOR_TEXT_PRIMARY);
                rememberLine.setFont(Constants.FONT_SMALL);
                rememberLine.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }

        // Không để làm gì nhưng không xóa vì MouseListener bắt buộc phải override
        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}
    }
}
