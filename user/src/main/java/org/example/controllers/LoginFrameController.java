package org.example.controllers;

import org.example.utilities.Constants;
import org.example.utilities.DatabaseHandler;
import org.example.views.LoginFrame;

import javax.sound.sampled.LineListener;
import javax.swing.*;
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

    private class InputFieldListener implements KeyListener {
        private JTextField inputField;

        public InputFieldListener(JTextField inputField) {
            this.inputField = inputField;
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (inputField == usernameField) {
                    passwordField.requestFocus();
                } else if (inputField == passwordField) {
                    loginButton.doClick();
                }
            }
        }

        // Không để làm gì nhưng không xóa vì KeyListener bắt buộc phải override
        @Override
        public void keyReleased(KeyEvent e) {}
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: login
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

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
                // TODO: open register frame
            } else if (e.getSource() == forgotPasswordLine) {
                // TODO: open forgot password frame
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == registerLine) {
                registerLine.setForeground(Constants.COLOR_PRIMARY);
                registerLine.setFont(Constants.FONT_SMALL_BOLD);
            } else if (e.getSource() == forgotPasswordLine) {
                forgotPasswordLine.setForeground(Constants.COLOR_PRIMARY);
                forgotPasswordLine.setFont(Constants.FONT_SMALL_BOLD);

            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == registerLine) {
                registerLine.setForeground(Constants.COLOR_TEXT_PRIMARY);
                registerLine.setFont(Constants.FONT_SMALL);
            } else if (e.getSource() == forgotPasswordLine) {
                forgotPasswordLine.setForeground(Constants.COLOR_TEXT_PRIMARY);
                forgotPasswordLine.setFont(Constants.FONT_SMALL);
            }
        }

        // Không để làm gì nhưng không xóa vì MouseListener bắt buộc phải override
        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}
    }
}
