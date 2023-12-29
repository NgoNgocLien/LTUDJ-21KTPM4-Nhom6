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

import org.example.controllers.LoginFrameController;
import org.example.utilities.*;
import org.example.views.LoginFrame;
import org.example.views.MainFrame;
import org.example.controllers.MainFrameController;
import org.example.models.ChatInfo;


import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import org.example.utilities.*;

public class Main {
    public static void main(String[] args) {
        Client_Socket.start();

        SwingUtilities.invokeLater(() -> {
            DatabaseHandler DB = new DatabaseHandler();

//            LoginFrame loginFrame = new LoginFrame();
            LoginFrame loginFrame = new LoginFrame();
            LoginFrameController loginFrameController = new LoginFrameController(Client_Socket.getSocket(), loginFrame, DB);
        });
    }
}