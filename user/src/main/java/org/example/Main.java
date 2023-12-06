package org.example;

import org.example.utilities.Client_Socket;
import org.example.views.LoginSignUpScreen;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Client_Socket.start();
        SwingUtilities.invokeLater(() -> new LoginSignUpScreen(Client_Socket.getSocket()).setVisible(true));
    }
}