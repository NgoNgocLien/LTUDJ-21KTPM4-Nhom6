package org.example.views;

import javax.swing.*;

public class WarningMessage extends JOptionPane {
    public WarningMessage(JFrame frame, String message) {
        showMessageDialog(frame, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }
}
