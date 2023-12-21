package org.example.controllers;

import org.example.models.ChatInfo;
import org.example.utilities.DatabaseHandler;
import org.example.views.IconPanel;
import org.example.views.MainFrame;


import javax.xml.crypto.Data;

public class MainFrameController {
    String myUsername;
    DatabaseHandler DB;
    MainFrame mainFrame;
    IconPanelController iconPanelController;
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

        iconPanelController = new IconPanelController(mainFrame, DB, myUsername);
    }
}
