package org.example.views;
import org.example.utilities.Constants;

import java.awt.*;

import javax.swing.*;

public class SeenPanel extends JPanel {
    private boolean seen;
    private JLabel seenLabel;
    private JPanel seenPanel;

    public SeenPanel(boolean seen) {
        this.seen = seen;

        setLayout(new GridLayout(1, 1));
        setBackground(null);

        seenLabel = new JLabel("Seen");
        seenLabel.setFont(Constants.SMALL_FONT);
        if (seen)
            seenLabel.setForeground(Color.GRAY);
        else
            seenLabel.setForeground(null);

        seenPanel = new JPanel();
        seenPanel.setBackground(null);
        seenPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        seenPanel.add(seenLabel);

        add(seenPanel);
    }
}