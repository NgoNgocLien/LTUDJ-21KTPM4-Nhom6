package org.example;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world admin!");

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminApp().setVisible(true);
            }
        });

    }
}