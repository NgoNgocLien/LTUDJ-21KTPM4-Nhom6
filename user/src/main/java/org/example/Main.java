//package org.example;
//
//import org.example.utilities.Client_Socket;
//import org.example.views.LoginSignUpScreen;
//import javax.swing.*;
//
//public class Main {
//    public static void main(String[] args) {
//        Client_Socket.start();
//        SwingUtilities.invokeLater(() -> new LoginSignUpScreen(Client_Socket.getSocket()).setVisible(true));
//    }
//}

package org.example;

import org.example.utilities.DatabaseHandler;
import org.example.views.MainFrame;
import org.example.controllers.MainFrameController;
import org.example.models.ChatInfo;


import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DatabaseHandler DB = new DatabaseHandler();
            ArrayList<ChatInfo> allChats = null;
            try {
                allChats= DB.getAllChats("hlong");
            } catch (SQLException se) {
                se.printStackTrace();
            }
            MainFrame mainFrame = new MainFrame(allChats);
            MainFrameController mainFrameController = new MainFrameController(mainFrame, DB, "hlong");
        });
    }
}