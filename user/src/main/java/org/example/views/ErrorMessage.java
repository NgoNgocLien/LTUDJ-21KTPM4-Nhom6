package org.example.views;

import javax.swing.*;

public class ErrorMessage extends JOptionPane {
    public ErrorMessage(JFrame frame, String message) {
        showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}