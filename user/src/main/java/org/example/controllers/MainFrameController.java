package org.example.controllers;

import org.example.models.ChatInfo;
import org.example.utilities.DatabaseHandler;
import org.example.views.IconPanel;
import org.example.views.MainFrame;

public class MainFrameController {
    private String myUsername;
    private DatabaseHandler DB;
    private MainFrame mainFrame;
    IconPanelController iconPanelController;
    ChatListPanelController chatListPanelController;
    public MainFrameController(MainFrame mainFrame, DatabaseHandler DB, String myUsername) {
        this.myUsername = myUsername;
        this.DB = DB;
        this.mainFrame = mainFrame;

        mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    // user status
                    DB.closeConnection();
                } catch (Exception e) {
                    System.out.println("Error closing window: " + e);
                }
            }
        });

        iconPanelController = new IconPanelController(this);
        chatListPanelController = new ChatListPanelController(this);
    }

    public String getMyUsername() {
        return myUsername;
    }
    public DatabaseHandler getDB() {
        return DB;
    }
    public MainFrame getMainFrame() {
        return mainFrame;
    }
    public IconPanelController getIconPanelController() {
        return iconPanelController;
    }
    public ChatListPanelController getChatListPanelController() {
        return chatListPanelController;
    }
}
