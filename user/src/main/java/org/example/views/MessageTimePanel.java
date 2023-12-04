package org.example.views;
import org.example.utilities.Constants;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

public class MessageTimePanel extends JPanel {
    private LocalDateTime timestamp;
    private JLabel timestampLabel;
    private JPanel timestampPanel;

    public MessageTimePanel(LocalDateTime timestamp) {
        this.timestamp = timestamp;

        setLayout(new GridLayout(1, 1));
        setBackground(null);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy, HH:mm");
        timestampLabel = new JLabel(timestamp.format(formatter));
        timestampLabel.setFont(Constants.SMALL_FONT);
        timestampLabel.setForeground(Color.GRAY);

        timestampPanel = new JPanel();
        timestampPanel.setBackground(null);
        timestampPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        timestampPanel.add(timestampLabel);

        add(timestampPanel);
    }
}