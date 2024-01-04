package org.example.controllers;

import org.example.utilities.DatabaseHandler;
import org.example.views.ProfileFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

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

    private static class addFriendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    private static class blockButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    private static class unfriendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    private static class acceptButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    private static class removeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
