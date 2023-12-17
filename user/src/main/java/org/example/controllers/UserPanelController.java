package org.example.controllers;

import org.example.models.Message;
import org.example.utilities.Constants;
import org.example.utilities.DatabaseHandler;
import org.example.views.MainChatView;
import org.example.views.UserPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserPanelController extends MouseAdapter {
    private UserPanel userPanel;
    private MainChatView mainChatView;
    private DatabaseHandler DB;
    public UserPanelController(MainChatView mainChatView, DatabaseHandler DB, UserPanel userPanel) {
        this.mainChatView = mainChatView;
        this.DB = DB;
        this.userPanel = userPanel;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        userPanel.setBackground(Constants.HOVER_COLOR);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        userPanel.setBackground(Constants.BACKGROUND_COLOR);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        userPanel.setBackground(Constants.HOVER_COLOR);
        mainChatView.getChatPanel().getChatnamePanel().setChatName(userPanel.getChatInfo().getChatName());
        mainChatView.getChatPanel().getChatnamePanel().setStatus("Unknown");

        try {
            ArrayList<Message> messages = DB.getMessages(userPanel.getChatInfo());
            mainChatView.getChatPanel().getMessagePanel().buildMessagePanel(userPanel.getChatInfo(), messages);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
