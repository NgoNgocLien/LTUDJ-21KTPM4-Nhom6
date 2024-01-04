package org.example.views;

import org.example.models.ChatInfo;
import org.example.utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    // Icon Panel
    private IconPanel iconPanel;
    private ChatListPanel chatListPanel;
    private ConversationPanel conversationPanel;
    private String currentIcon;
    private String currentChat; // Chat currentChat


    public MainFrame(ArrayList<ChatInfo> infos) {
        // set windows look and feel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println("Error setting Windows look and feel: " + e);
        }

        setTitle(Constants.APP_NAME);
//        setSize(new Dimension(1000, 700));
        setMinimumSize(new Dimension(1000, 700));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Constants.COLOR_BACKGROUND);
        setLayout(new BorderLayout());
        setVisible(true);
        addComponentListener(new MainFrameComponentListener(this));

        // build the icon panel
        iconPanel = new IconPanel();
        // build the chat list panel
        chatListPanel = new ChatListPanel(infos);

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BorderLayout());

        sidePanel.add(iconPanel, BorderLayout.WEST);
        sidePanel.add(chatListPanel, BorderLayout.EAST);

        // build the conversation panel
        conversationPanel = new ConversationPanel();

        add(sidePanel, BorderLayout.WEST);
        add(conversationPanel, BorderLayout.CENTER);
    }

    public IconPanel getIconPanel() {
        return iconPanel;
    }

    public ChatListPanel getChatListPanel() {
        return chatListPanel;
    }

    public ConversationPanel getConversationPanel() {
        return conversationPanel;
    }

    private class MainFrameComponentListener extends ComponentAdapter {
        private MainFrame mainFrame;

        public MainFrameComponentListener(MainFrame mainFrame) {
            this.mainFrame = mainFrame;
        }

        @Override
        public void componentResized(ComponentEvent e) {
            super.componentResized(e);

            mainFrame.revalidate();
            mainFrame.repaint();
        }
    }
}
