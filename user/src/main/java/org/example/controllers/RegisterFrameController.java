package org.example.controllers;

import org.example.models.Profile;
import org.example.utilities.Constants;
import org.example.utilities.DatabaseHandler;
import org.example.views.ErrorMessage;
import org.example.views.LoginFrame;
import org.example.views.RegisterFrame;
import org.example.views.SuccessMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;

public class RegisterFrameController {
    private RegisterFrame RF;
    private DatabaseHandler DB;
    private JTextField fullnameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;
    private JTextField addressField;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;
    private JCheckBox termCheckBox;
    private JButton registerButton;
    private JLabel loginLine;
    private Socket socket;

    public RegisterFrameController(Socket socket, RegisterFrame RF, DatabaseHandler DB) {
        this.RF = RF;
        this.DB = DB;
        this.socket = socket;

        this.RF.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    DB.closeConnection();
                } catch (Exception e) {
                    System.out.println("Error closing window: " + e);
                }
            }
        });

        this.fullnameField = RF.getFullnameField();
        this.usernameField = RF.getUsernameField();
        this.passwordField = RF.getPasswordField();
        this.confirmPasswordField = RF.getConfirmPasswordField();
        this.emailField = RF.getEmailField();
        this.addressField = RF.getAddressField();
        this.genderComboBox = RF.getGenderComboBox();
        this.dayComboBox = RF.getDayComboBox();
        this.monthComboBox = RF.getMonthComboBox();
        this.yearComboBox = RF.getYearComboBox();
        this.termCheckBox = RF.getTermCheckBox();
        this.registerButton = RF.getRegisterButton();
        this.loginLine = RF.getLoginLine();

        this.fullnameField.addKeyListener(new InputFieldListener(fullnameField));
        this.usernameField.addKeyListener(new InputFieldListener(usernameField));
        this.passwordField.addKeyListener(new InputFieldListener(passwordField));
        this.confirmPasswordField.addKeyListener(new InputFieldListener(confirmPasswordField));
        this.emailField.addKeyListener(new InputFieldListener(emailField));
        this.addressField.addKeyListener(new InputFieldListener(addressField));

        this.registerButton.addActionListener(new RegisterButtonListener());
        this.loginLine.addMouseListener(new LineListener(this.loginLine));

    }

    private boolean checkFullName(String fullname) {
        String regex = "^[a-zA-Z\\s]+";
        return fullname.matches(regex);
    }

    private boolean checkUsername(String username) {
        String regex = "^[a-z0-9._-]+$";
        return username.matches(regex);
    }

    private boolean checkPassword(String password) {
        // password can contain any special character
        String regex = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{6,}$";
        return password.matches(regex);
    }

    private boolean checkEmail(String email) {
        // aa@bb.cc
        String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        return email.matches(regex);
    }

    private boolean checkAddress(String address) {
        String regex = "^[a-zA-Z0-9.,/()\\s]+";
        return address.matches(regex);
    }

    private boolean checkDOB(int day, int month, int year) {
        try {
            LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    private class InputFieldListener implements KeyListener {
        private JTextField inputField;

        public InputFieldListener(JTextField inputField) {
            this.inputField = inputField;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String fullname = fullnameField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String email = emailField.getText();

                if (inputField == fullnameField && !fullname.isEmpty()) {
                    usernameField.requestFocus();
                } else if (inputField == usernameField && !username.isEmpty()) {
                    passwordField.requestFocus();
                } else if (inputField == passwordField && !password.isEmpty()) {
                    confirmPasswordField.requestFocus();
                } else if (inputField == confirmPasswordField && !confirmPassword.isEmpty()) {
                    emailField.requestFocus();
                } else if (inputField == emailField && !email.isEmpty()) {
                    addressField.requestFocus();
                }
            }
        }

        // Không để làm gì nhưng không xóa vì KeyListener bắt buộc phải override
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String fullname = fullnameField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String email = emailField.getText();
            String address = addressField.getText();
            String gender = (String) genderComboBox.getSelectedItem();

            String day = (String) dayComboBox.getSelectedItem();
            String month = (String) monthComboBox.getSelectedItem();
            String year = (String) yearComboBox.getSelectedItem();

            if (fullname.isEmpty()) {
                new ErrorMessage(RF, "Fullname is required");
                fullnameField.requestFocus();
                return;
            } else if (username.isEmpty()) {
                new ErrorMessage(RF, "Username is required");
                usernameField.requestFocus();
                return;
            } else if (password.isEmpty()) {
                new ErrorMessage(RF, "Password is required");
                passwordField.requestFocus();
                return;
            } else if (confirmPassword.isEmpty()) {
                new ErrorMessage(RF, "Confirm password is required");
                confirmPasswordField.requestFocus();
                return;
            } else if (email.isEmpty()) {
                new ErrorMessage(RF, "Email is required");
                emailField.requestFocus();
                return;
            } else if (address.isEmpty()) {
                new ErrorMessage(RF, "Address is required");
                addressField.requestFocus();
                return;
            } else if (gender.equals("Gender")) {
                new ErrorMessage(RF, "Gender is required");
                genderComboBox.requestFocus();
                return;
            } else if (day.equals("Day")) {
                new ErrorMessage(RF, "Day is required");
                dayComboBox.requestFocus();
                return;
            } else if (month.equals("Month")) {
                new ErrorMessage(RF, "Month is required");
                monthComboBox.requestFocus();
                return;
            } else if (year.equals("Year")) {
                new ErrorMessage(RF, "Year is required");
                yearComboBox.requestFocus();
                return;
            } else if (!checkFullName(fullname)) {
//                new ErrorMessage(RF, "Fullname is invalid");
//                fullnameField.requestFocus();
//                return;
            } else if (!checkUsername(username)) {
                new ErrorMessage(RF, "Username must be at least 1 character and can only contain lowercase letters, numbers, and special characters . _ -");
                usernameField.requestFocus();
                return;
            } else if (!checkPassword(password)) {
                new ErrorMessage(RF, "Password must be at least 6 characters");
                passwordField.requestFocus();
                return;
            } else if (!checkEmail(email)) {
                new ErrorMessage(RF, "Email is invalid");
                emailField.requestFocus();
                return;
            } else if (!checkAddress(address)) {
                new ErrorMessage(RF, "Address is invalid");
                addressField.requestFocus();
                return;
            } else if (!checkDOB(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year))) {
                new ErrorMessage(RF, "Date of birth is invalid");
                dayComboBox.requestFocus();
                return;
            } else if (!password.equals(confirmPassword)) {
                new ErrorMessage(RF, "Confirm password does not match");
                passwordField.requestFocus();
                return;
            } else if (!termCheckBox.isSelected()) {
                new ErrorMessage(RF, "You must agree to the terms and conditions before registering");
                return;
            }

            // TODO: check username and email exist
            Profile checkProfile = null;
            try {
                checkProfile = DB.getProfilebyUsername(username);
                if (checkProfile != null) {
                    if (checkProfile.getUsername().equals(username)) {
                        new ErrorMessage(RF, "Username already exists");
                        usernameField.requestFocus();
                        return;
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            try {
                checkProfile = DB.getProfilebyEmail(email);
                if (checkProfile != null) {
                    if (checkProfile.getEmail().equals(email)) {
                        new ErrorMessage(RF, "Email already exists");
                        emailField.requestFocus();
                        return;
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            LocalDate localDate;
            try {
                localDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            } catch (DateTimeException ex) {
                new ErrorMessage(RF, "Date of birth is invalid");
                dayComboBox.requestFocus();
                return;
            }

            // TODO: if exist, show error message
            // new ErrorMessage(RF, "Username or email already exists"); return;

            // TODO: if not exist, insert into database

            // TODO: show success message
            new SuccessMessage(RF, "Register successfully! Please login to continue");

            // TEST & DELETE AFTER: show all info
//            JOptionPane.showMessageDialog(RF, "Fullname: " + fullname + "\nUsername: " + username + "\nPassword: " + password + "\nEmail: " + email + "\nAddress: " + address + "\nGender: " + gender + "\nDate of birth: " + day + "/" + month + "/" + year);


            try {
                DB.saveRegisteredAccount(username, password, fullname, address, localDate, gender.equals("Male"), email);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            // close register frame, open login frame
            RF.dispose();
            LoginFrame LF = new LoginFrame();
            LoginFrameController LFC = new LoginFrameController(socket, LF, DB);
        }
    }

    private class LineListener implements MouseListener {
        private JLabel line;

        public LineListener(JLabel line) {
            this.line = line;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == loginLine) {
                // close register frame
                RF.dispose();
                // open login frame
                LoginFrame LF = new LoginFrame();
                LoginFrameController LFC = new LoginFrameController(socket, LF, DB);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == loginLine) {
                loginLine.setForeground(Constants.COLOR_PRIMARY);
                loginLine.setFont(Constants.FONT_SMALL_BOLD);
                loginLine.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == loginLine) {
                loginLine.setForeground(Constants.COLOR_TEXT_PRIMARY);
                loginLine.setFont(Constants.FONT_SMALL);
                loginLine.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }

        // Không để làm gì nhưng không xóa vì MouseListener bắt buộc phải override
        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
    }
}
