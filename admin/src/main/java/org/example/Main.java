package org.example;

import org.example.utilities.AdminSocket;
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world admin!");
        AdminSocket.start();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.out.println("Hello server!");
                new AdminApp(AdminSocket.getSocket()).setVisible(true);
            }
        });
    }
}

// Main -> AdminSocket  -> chatServer
//      -> AdminApp     -> ActionPerformed -> Admin Worker -> Admin Handler