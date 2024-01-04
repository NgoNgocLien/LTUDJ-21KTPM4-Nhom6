package org.example.controllers;

import org.example.utilities.DatabaseHandler;
import org.example.views.ProfileFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.sql.SQLException;

public class ProfileFrameController {
    private ProfileFrame PF;
    private DatabaseHandler DB;
    private JButton leftButton;
    private JButton rightButton;

    private int mode;

    public ProfileFrameController(Socket socket, ProfileFrame PF, DatabaseHandler DB) {
        this.PF = PF;
        this.DB = DB;

        this.leftButton = PF.getLeftButton();
        this.rightButton = PF.getRightButton();
        this.mode = PF.getMode();

        if (mode == 1) {
            leftButton.addActionListener(new addFriendButtonListener());
            rightButton.addActionListener(new blockButtonListener());
        } else if (mode == 2) {
            leftButton.addActionListener(new unfriendButtonListener());
            rightButton.addActionListener(new blockButtonListener());
        } else if (mode == 3) {
            leftButton.addActionListener(new acceptButtonListener());
            rightButton.addActionListener(new removeButtonListener());
        }
    }

    private class addFriendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DB.addFriend(DB.getLoginedUsername(), PF.getProfile().getUsername());
                JOptionPane.showMessageDialog(null, "Friend added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                PF.dispose();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
    private class blockButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DB.blockFriend(DB.getLoginedUsername(), PF.getProfile().getUsername());
                JOptionPane.showMessageDialog(null, "Friend blocked successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                PF.dispose();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    private class unfriendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DB.unFriend(DB.getLoginedUsername(), PF.getProfile().getUsername());
                JOptionPane.showMessageDialog(null, "Friend removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                PF.dispose();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
    private class acceptButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DB.acceptFriend(DB.getLoginedUsername(), PF.getProfile().getUsername());
                JOptionPane.showMessageDialog(null, "Friend request accepted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                PF.dispose();
            }catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    private static class removeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
