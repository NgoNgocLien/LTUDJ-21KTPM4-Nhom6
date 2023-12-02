package org.example.views;

import org.example.utilities.Constants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class InputPanel extends JPanel {
    private JTextField inputTextArea;
    private JButton sendButton;

    public InputPanel() {
        setLayout(new BorderLayout(10, 0));
        setBackground(null);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        inputTextArea = new JTextField();
        sendButton = new JButton("Send");

        inputTextArea.setFont(Constants.NORMAL_FONT);
        inputTextArea.setBackground(Color.WHITE);
        inputTextArea.setForeground(Color.BLACK);
        inputTextArea.setBorder(BorderFactory.createLineBorder(Constants.DARK_BLUE));

        sendButton.setPreferredSize(new Dimension(Constants.SEARCH_BUTTON_WIDTH, Constants.SEARCH_BUTTON_HEIGHT));
        sendButton.setFont(Constants.BUTTON_FONT);
        sendButton.setBackground(Constants.DARK_BLUE);
        sendButton.setForeground(Color.WHITE);

        add(inputTextArea, BorderLayout.CENTER);
        add(sendButton, BorderLayout.EAST);
    }
}
