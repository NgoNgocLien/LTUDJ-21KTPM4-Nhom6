package org.example.views;

import org.example.utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchPanel extends JPanel {
    private JTextField searchField;
    private JButton searchButton;

    public SearchPanel() {
        setPreferredSize(new Dimension(Constants.SEARCH_PANEL_WIDTH, Constants.SEARCH_PANEL_HEIGHT));
        setBackground(null);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(Constants.SEARCH_FIELD_WIDTH, Constants.SEARCH_FIELD_HEIGHT));
        searchField.setBackground(null);
        // searchField.setForeground(Constants.DARK_BLUE);
        searchField.setBorder(BorderFactory.createLineBorder(Constants.DARK_BLUE));
        searchField.setFont(Constants.NORMAL_FONT);

        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(Constants.SEARCH_BUTTON_WIDTH, Constants.SEARCH_BUTTON_HEIGHT));
        searchButton.setBackground(Constants.DARK_BLUE);
        searchButton.setForeground(Color.WHITE);
        searchButton.setBorder(BorderFactory.createLineBorder(Constants.DARK_BLUE));
        searchButton.setFont(Constants.BUTTON_FONT);

        add(searchField);
        add(searchButton);
    }
}
