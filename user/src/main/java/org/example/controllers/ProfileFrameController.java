package org.example.controllers;

import org.example.utilities.DatabaseHandler;
import org.example.views.LoginFrame;
import org.example.views.ProfileFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class ProfileFrameController {
    private ProfileFrame PF;
    private DatabaseHandler DB;
    private JButton friendButton;
    private JButton blockButton;

    public ProfileFrameController(Socket socket, ProfileFrame PF, DatabaseHandler DB) {
        this.PF = PF;
        this.DB = DB;

        this.friendButton = PF.getFriendButton();
        this.blockButton = PF.getBlockButton();

        this.friendButton.addActionListener(new FriendButtonListener());
//        this.blockButton.addMouseListener(new BlockButtonListener());
    }

    private class FriendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (friendButton.getText().equals("Add friend")) {
                friendButton.setText("Remove friend");
            } else {
                friendButton.setText("Add friend");
            }
        }
    }
}
