package org.example.controllers;

import org.example.utilities.DatabaseHandler;
import org.example.views.MainFrame;

import java.net.Socket;

public class MainFrameController {
    IconPanelController iconPanelController;
    ChatListPanelController chatListPanelController;
    ConversationPanelController conversationPanelController;
    private String myUsername;
    private DatabaseHandler DB;
    private MainFrame mainFrame;
    private Socket socket;

    public MainFrameController(Socket socket, MainFrame mainFrame, DatabaseHandler DB, String myUsername) {
        this.myUsername = myUsername;
        this.DB = DB;
        this.mainFrame = mainFrame;
        this.socket = socket;
        mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    // TODO: update status to offline


                    DB.closeConnection();
                } catch (Exception e) {
                    System.out.println("Error closing window: " + e);
                }
            }
        });

        iconPanelController = new IconPanelController(socket, this);
        chatListPanelController = new ChatListPanelController(this);
        conversationPanelController = new ConversationPanelController(this);
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
