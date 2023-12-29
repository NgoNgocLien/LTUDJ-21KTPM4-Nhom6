package org.example;

import org.example.utilities.AdminSocket;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world admin!");
        AdminSocket.start();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.out.println("Hello server!");
                try {
                    new AdminApp(AdminSocket.getSocket()).setVisible(true);
                } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                         IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

//(admin) Main -> AdminSocket  -> chatServer
//             -> AdminApp     -> ActionPerformed -> (utilities) -> (server) AdminHandler  -> AdminDatabase