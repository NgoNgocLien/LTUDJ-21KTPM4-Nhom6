package org.example.controllers;

import org.example.utilities.Constants;
import org.example.utilities.DatabaseHandler;
import org.example.views.ErrorMessage;
import org.example.views.LoginFrame;
import org.example.views.RegisterFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrameController {
    private LoginFrame LF;
    private DatabaseHandler DB;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel registerLine;
    private JLabel forgotPasswordLine;

    public LoginFrameController(LoginFrame LF, DatabaseHandler DB) {
        this.LF = LF;
        this.DB = DB;

        this.usernameField = LF.getUsernameField();
        this.passwordField = LF.getPasswordField();
        this.loginButton = LF.getLoginButton();
        this.registerLine = LF.getRegisterLine();
        this.forgotPasswordLine = LF.getForgotPasswordLine();

        this.usernameField.addKeyListener(new InputFieldListener(usernameField));
        this.passwordField.addKeyListener(new InputFieldListener(passwordField));

        this.loginButton.addActionListener(new LoginButtonListener());
        this.registerLine.addMouseListener(new LineListener(this.registerLine));
        this.forgotPasswordLine.addMouseListener(new LineListener(this.forgotPasswordLine));
    }

    private boolean checkUsername(String username) {
        String regex = "^[a-z0-9._-]{1,}$";
        return username.matches(regex);
    }

    private boolean checkPassword(String password) {
        // password can contain any special character
        String regex = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{8,}$";
        return password.matches(regex);
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
                String password = new String(passwordField.getPassword());
                if (inputField == usernameField && !username.isEmpty()) {
                    passwordField.requestFocus();
                }
                else if (inputField == passwordField && !password.isEmpty()) {
                    loginButton.doClick();
                }
            }
        }

        // Không để làm gì nhưng không xóa vì KeyListener bắt buộc phải override
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {}
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: login
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // check input fields
            if (username.isEmpty()) {
                usernameField.requestFocus();
                new ErrorMessage(LF, "Please enter your username");
                return;
            }
            else if (password.isEmpty()) {
                passwordField.requestFocus();
                new ErrorMessage(LF, "Please enter your password");
                return;
            }
            else if (!checkUsername(username) || !checkPassword(password)) {
                new ErrorMessage(LF, "Invalid username or password");
                return;
            }

            // open jdialog to show data
            JOptionPane.showMessageDialog(LF, "Username: " + username + "\nPassword: " + password);
        }
    }

    private class LineListener implements MouseListener {
        private JLabel line;

        public LineListener(JLabel line) {
            this.line = line;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == registerLine) {
                // close login frame
                LF.dispose();
                // open register frame
                RegisterFrame RF = new RegisterFrame();
                RegisterFrameController RFC = new RegisterFrameController(RF, DB);
            } else if (e.getSource() == forgotPasswordLine) {
                // TODO: open forgot password frame


            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == registerLine) {
                registerLine.setForeground(Constants.COLOR_PRIMARY);
                registerLine.setFont(Constants.FONT_SMALL_BOLD);
                registerLine.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            } else if (e.getSource() == forgotPasswordLine) {
                forgotPasswordLine.setForeground(Constants.COLOR_PRIMARY);
                forgotPasswordLine.setFont(Constants.FONT_SMALL_BOLD);
                forgotPasswordLine.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == registerLine) {
                registerLine.setForeground(Constants.COLOR_TEXT_PRIMARY);
                registerLine.setFont(Constants.FONT_SMALL);
                registerLine.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            } else if (e.getSource() == forgotPasswordLine) {
                forgotPasswordLine.setForeground(Constants.COLOR_TEXT_PRIMARY);
                forgotPasswordLine.setFont(Constants.FONT_SMALL);
                forgotPasswordLine.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }

        // Không để làm gì nhưng không xóa vì MouseListener bắt buộc phải override
        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}
    }
}
