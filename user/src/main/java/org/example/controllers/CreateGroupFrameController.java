package org.example.controllers;

import org.example.models.ChatInfo;
import org.example.utilities.DatabaseHandler;
import org.example.views.CreateGroupFrame;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreateGroupFrameController {
    private Socket socket;
    private CreateGroupFrame CGF;
    private DatabaseHandler DB;

    public CreateGroupFrameController(Socket socket, CreateGroupFrame CGF, DatabaseHandler DB) {
        this.socket = socket;
        this.CGF = CGF;
        this.DB = DB;

        CGF.getCreateGroupButton().addActionListener(new CreateGroupButtonListener());

    }

    private class CreateGroupButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String groupName = CGF.getGroupNameTextField().getText();
            ArrayList<String> members = new ArrayList<>();
            for (JCheckBox checkBox : CGF.getMemberCheckBoxes()) {
                if (checkBox.isSelected()) {
                    members.add(checkBox.getActionCommand());
                }
            }
//            try {
//                DB.createGroup(groupName, members);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
        }
    }


}
