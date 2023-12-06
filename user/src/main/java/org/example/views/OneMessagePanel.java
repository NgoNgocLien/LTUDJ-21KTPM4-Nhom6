package org.example.views;
import org.example.utilities.Constants;

import javax.swing.*;
import java.awt.*;

public class OneMessagePanel extends JPanel {
    private String message;
    private JLabel messageLabel;
    private JPanel messagePanel;

    public OneMessagePanel(String message, boolean isMyMessage) {
        setLayout(new GridLayout(1, 1));
        setBackground(null);

        messageLabel = new JLabel(message);
        if (messageLabel.getPreferredSize().width > 500){
            messageLabel = new JLabel("<html><p style='width: 600'>" + message + "</p></html>");
        }
        messageLabel.setFont(Constants.NORMAL_FONT);

        messagePanel = new JPanel();

        if (isMyMessage) {
            messageLabel.setForeground(Color.WHITE);

            JPanel backgroundPanel = new JPanel();
            backgroundPanel.setBackground(Constants.DARK_BLUE);
            backgroundPanel.add(messageLabel);

            messagePanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            messagePanel.add(backgroundPanel);

            add(messagePanel);
        } else {
            messageLabel.setForeground(Color.BLACK);

            JPanel backgroundPanel = new JPanel();
            backgroundPanel.setBackground(Color.LIGHT_GRAY);
            backgroundPanel.add(messageLabel);

            messagePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
            messagePanel.add(backgroundPanel);

            add(messagePanel);
        }
    }
}

