package org.example.views;

import javax.swing.*;

public class SuccessMessage extends JOptionPane {
    public SuccessMessage(JFrame frame, String message) {
        showMessageDialog(frame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
