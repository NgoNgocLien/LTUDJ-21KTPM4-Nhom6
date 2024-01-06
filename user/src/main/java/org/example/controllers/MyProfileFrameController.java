package org.example.controllers;

import org.example.models.Profile;
import org.example.utilities.DatabaseHandler;
import org.example.views.ErrorMessage;
import org.example.views.MyProfileFrame;
import org.example.views.SuccessMessage;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyProfileFrameController {
    private MyProfileFrame MPF;
    private DatabaseHandler DB;
    private Profile myProfile;
    private JTextField fullnameField;
    private JPasswordField currentPasswordField;
    private JPasswordField newPasswordField;
    private JTextField emailField;
    private JTextField addressField;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;
    private JButton editProfileButton;

    public MyProfileFrameController(MyProfileFrame MPF, DatabaseHandler DB) {
        this.MPF = MPF;
        this.DB = DB;

        this.MPF.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // close my profile frame, open main frame
                if (isChanged()) {
                    int confirm = JOptionPane.showConfirmDialog(MPF, "Are you sure you want to close this window? Your changes will not be saved.", "Warning", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        MPF.dispose();
                    } else {
                        MPF.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                    }
                } else {
                    MPF.dispose();
                }
            }
        });

        this.myProfile = MPF.getMyProfile();
        this.fullnameField = MPF.getFullnameField();
        this.currentPasswordField = MPF.getCurrentPasswordField();
        this.newPasswordField = MPF.getNewPasswordField();
        this.emailField = MPF.getEmailField();
        this.addressField = MPF.getAddressField();
        this.genderComboBox = MPF.getGenderComboBox();
        this.dayComboBox = MPF.getDayComboBox();
        this.monthComboBox = MPF.getMonthComboBox();
        this.yearComboBox = MPF.getYearComboBox();
        this.editProfileButton = MPF.getEditProfileButton();

        this.fullnameField.addKeyListener(new InputFieldListener(fullnameField));
        this.currentPasswordField.addKeyListener(new InputFieldListener(currentPasswordField));
        this.newPasswordField.addKeyListener(new InputFieldListener(newPasswordField));
        this.emailField.addKeyListener(new InputFieldListener(emailField));
        this.addressField.addKeyListener(new InputFieldListener(addressField));

        this.editProfileButton.addActionListener(new EditProfileButtonListener());
    }

    private boolean isChanged() {
        boolean isChanged = false;
        String fullname = fullnameField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String currentPassword = new String(currentPasswordField.getPassword());
        String newPassword = new String(newPasswordField.getPassword());
        String gender = (String) genderComboBox.getSelectedItem();
        String day = (String) dayComboBox.getSelectedItem();
        String month = (String) monthComboBox.getSelectedItem();
        String year = (String) yearComboBox.getSelectedItem();

        if (!fullname.equals(myProfile.getFullname()) ||
                !email.equals(myProfile.getEmail()) ||
                !address.equals(myProfile.getAddress()) ||
                !currentPassword.isEmpty() ||
                !newPassword.isEmpty() ||
                (gender.equals("Male") && myProfile.getGender() == 0) ||
                (gender.equals("Female") && myProfile.getGender() == 1) ||
                (!day.equals("Day") && Integer.parseInt(day) != myProfile.getBirthdate().getDayOfMonth()) ||
                (!month.equals("Month") && Integer.parseInt(month) != myProfile.getBirthdate().getMonthValue()) ||
                (!year.equals("Year") && Integer.parseInt(year) != myProfile.getBirthdate().getYear())) {
            isChanged = true;
        }
        return isChanged;
    }

    private boolean checkUsername(String username) {
        String regex = "^[a-z0-9._-]{1,}$";
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
                String email = emailField.getText();
                String address = addressField.getText();
                String currentPassword = new String(currentPasswordField.getPassword());

                if (inputField == fullnameField && !fullname.isEmpty()) {
                    emailField.requestFocus();
                } else if (inputField == emailField && !email.isEmpty()) {
                    addressField.requestFocus();
                } else if (inputField == addressField && !address.isEmpty()) {
                    currentPasswordField.requestFocus();
                } else if (inputField == currentPasswordField && !currentPassword.isEmpty()) {
                    newPasswordField.requestFocus();
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

    private class EditProfileButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String fullname = fullnameField.getText();
            String currentPassword = new String(currentPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());
            String email = emailField.getText();
            String address = addressField.getText();
            String gender = (String) genderComboBox.getSelectedItem();

            String day = (String) dayComboBox.getSelectedItem();
            String month = (String) monthComboBox.getSelectedItem();
            String year = (String) yearComboBox.getSelectedItem();

            if (fullname.isEmpty()) {
                new ErrorMessage(MPF, "Fullname is required");
                fullnameField.requestFocus();
                return;
            } else if (!newPassword.isEmpty() && currentPassword.isEmpty()) {
                new ErrorMessage(MPF, "Current password is required if you want to change password");
                currentPasswordField.requestFocus();
                return;
            } else if (email.isEmpty()) {
                new ErrorMessage(MPF, "Email is required");
                emailField.requestFocus();
                return;
            } else if (address.isEmpty()) {
                new ErrorMessage(MPF, "Address is required");
                addressField.requestFocus();
                return;
            } else if (gender.equals("Gender")) {
                new ErrorMessage(MPF, "Gender is required");
                genderComboBox.requestFocus();
                return;
            } else if (day.equals("Day")) {
                new ErrorMessage(MPF, "Day is required");
                dayComboBox.requestFocus();
                return;
            } else if (month.equals("Month")) {
                new ErrorMessage(MPF, "Month is required");
                monthComboBox.requestFocus();
                return;
            } else if (year.equals("Year")) {
                new ErrorMessage(MPF, "Year is required");
                yearComboBox.requestFocus();
                return;
            } else if (!checkPassword(currentPassword)) {
                new ErrorMessage(MPF, "Current password is invalid");
                currentPasswordField.requestFocus();
                return;
            } else if (!newPassword.isEmpty() && !checkPassword(newPassword)) {
                new ErrorMessage(MPF, "New password is invalid");
                newPasswordField.requestFocus();
                return;
            } else if (!checkEmail(email)) {
                new ErrorMessage(MPF, "Email is invalid");
                emailField.requestFocus();
                return;
            } else if (!checkAddress(address)) {
                new ErrorMessage(MPF, "Address is invalid");
                addressField.requestFocus();
                return;
            } else if (!checkDOB(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year))) {
                new ErrorMessage(MPF, "Date of birth is invalid");
                dayComboBox.requestFocus();
                return;
            }

            // TODO: check if username or email already exists

            // TODO: if (!currentPassword.isEmpty()) check if current password is correct

            // TODO: update profile

            // TODO: show success message
            new SuccessMessage(MPF, "Your profile has been updated successfully");

            // TEST & DELETE AFTER: show all info
            JOptionPane.showMessageDialog(MPF, "Fullname: " + fullname + "\nEmail: " + email + "\nAddress: " + address + "\nGender: " + gender + "\nDate of birth: " + day + "/" + month + "/" + year + "\nCurrent password: " + currentPassword + "\nNew password: " + newPassword);

            if(month.length() == 1) month = "0" + month;
            String dateString = String.format("%s-%s-%s", day, month, year);
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            Profile newProfile = new Profile(myProfile.getDateJoined(), fullname, myProfile.getUsername(), (gender.equals("Male")) ? 1 : 0, LocalDate.parse(dateString, formatters), email, address, newPassword);
            try {
                DB.updateMyProfile(newProfile, myProfile.getPassword());
                MPF.dispose();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
