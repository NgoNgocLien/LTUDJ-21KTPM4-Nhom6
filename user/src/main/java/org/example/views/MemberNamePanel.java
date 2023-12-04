package org.example.views;
import org.example.utilities.Constants;

import java.awt.*;

import javax.swing.*;

public class MemberNamePanel extends JPanel {
    private JLabel memberNameLabel;
    private JPanel memberNamePanel;

    public MemberNamePanel(String username) {
        setLayout(new GridLayout(1, 1));
        setBackground(null);

        memberNameLabel = new JLabel(username);
        memberNameLabel.setFont(Constants.SMALL_FONT);
        memberNameLabel.setForeground(Color.GRAY);

        memberNamePanel = new JPanel();
        memberNamePanel.setBackground(null);
        memberNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        memberNamePanel.add(memberNameLabel);

        add(memberNamePanel);
    }
}
