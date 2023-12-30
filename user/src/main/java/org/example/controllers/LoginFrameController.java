package org.example.controllers;

import org.example.models.ChatInfo;
import org.example.models.Profile;
import org.example.utilities.Constants;
import org.example.utilities.DatabaseHandler;
import org.example.views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginFrameController {
    private LoginFrame LF;
    private DatabaseHandler DB;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel registerLine;
    private JLabel forgotPasswordLine;

    private Socket socket;

    public LoginFrameController(Socket socket, LoginFrame LF, DatabaseHandler DB) {
        this.LF = LF;
        this.DB = DB;

        this.LF.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    DB.closeConnection();
                } catch (Exception e) {
                    System.out.println("Error closing window: " + e);
                }
            }
        });

        this.socket = socket;

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
//            else if (!checkUsername(username) || !checkPassword(password)) { // Kiểm tra format trước khi kiểm tra qua database
//                new ErrorMessage(LF, "Invalid username or password");
//                return;
//            }

            // TEST & DELETE AFTER: show username and password
//            JOptionPane.showMessageDialog(LF, "Username: " + username + "\nPassword: " + password);

            // TODO: verify login info with database
            try {
                Profile profile = DB.getProfilebyUsername(username);

                if(profile.getUsername() == null){
                    new ErrorMessage(LF, "Wrong username or password");
                }
                else if(!profile.getPassword().equals(password)){
                    new ErrorMessage(LF, "Wrong username or password");
                }
                else{
                    JOptionPane.showMessageDialog(LF, "Login success");
                    ArrayList<ChatInfo> allChats = null;
                    try {
                        allChats= DB.getAllChats(username);
                    } catch (SQLException se) {
                        se.printStackTrace();
                    }
                    MainFrame mainFrame = new MainFrame(allChats);
                    MainFrameController mainFrameController = new MainFrameController(socket, mainFrame, DB, username);
                }
                LF.dispose();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

//            ArrayList<ChatInfo> allChats = null;
//            try {
//                allChats= DB.getAllChats("hlong");
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//            MainFrame mainFrame = new MainFrame( allChats);
//            MainFrameController mainFrameController = new MainFrameController(socket, mainFrame, DB, "hlong");
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
                RegisterFrameController RFC = new RegisterFrameController(socket, RF, DB);
            } else if (e.getSource() == forgotPasswordLine) {
                // close login frame
                LF.dispose();
                // open forgot password frame
                ForgotPasswordFrame FPF = new ForgotPasswordFrame();
                ForgotPasswordFrameController FPFC = new ForgotPasswordFrameController(socket, FPF, DB);
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
