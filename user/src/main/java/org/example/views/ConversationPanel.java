package org.example.views;

import org.example.utilities.Constants;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ConversationPanel extends JPanel {
    private JPanel chatNamePanel;
    private JLabel nameLabel;
    private JPanel messagesPanel;
    private JScrollPane messagesScrollPane;
    private JPanel inputPanel;
//    private User info;
//    private ArrayList<Message> messages;

    //    private ArrayList<MessagePanel> messagePanels;
    private JButton sendButton;
    private JTextField inputField;
    private JPopupMenu moreMenu;
    private JButton moreButton;

    public ConversationPanel() {
        setBackground(Constants.COLOR_BACKGROUND);
        setLayout(new BorderLayout());

        buildChatNamePanel();
        buildMessagesPanel();
        buildInputPanel();

        add(chatNamePanel, BorderLayout.NORTH);
        add(messagesScrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }

    private void buildChatNamePanel() {
        chatNamePanel = new JPanel();
        chatNamePanel.setBackground(Constants.COLOR_BACKGROUND);
        chatNamePanel.setLayout(new BorderLayout());

        nameLabel = new JLabel("");
        nameLabel.setFont(Constants.FONT_LARGE_BOLD);
        nameLabel.setForeground(Constants.COLOR_PRIMARY);
        nameLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // Register the IconFont for online status
        IconFontSwing.register(FontAwesome.getIconFont());
        Icon onlineIcon = IconFontSwing.buildIcon(FontAwesome.CIRCLE, 10, Constants.COLOR_BACKGROUND);
        nameLabel.setIcon(onlineIcon);
        nameLabel.setHorizontalTextPosition(JLabel.LEFT);

        moreButton = new JButton();
        // Register the IconFont for more options
        Icon moreIcon = IconFontSwing.buildIcon(FontAwesome.ELLIPSIS_H, 30, Constants.COLOR_PRIMARY);
        moreButton.setIcon(moreIcon);
        moreButton.setBorder(new EmptyBorder(15, 20, 15, 20));
        moreButton.setBackground(Constants.COLOR_BACKGROUND);
        moreButton.setBorderPainted(false);

        chatNamePanel.add(nameLabel, BorderLayout.WEST);
        chatNamePanel.add(moreButton, BorderLayout.EAST);

        buildMoreMenu();
    }

    private void buildMoreMenu() {
        moreMenu = new JPopupMenu();
        moreMenu.setBackground(Constants.COLOR_BACKGROUND);
        moreMenu.setPreferredSize(new Dimension(160, 160));

        // Create JMenuItems
        JMenuItem deleteChat, clearChat, viewProfile, blockUser;
        deleteChat = new JMenuItem("Delete Chat");
        deleteChat.setFont(Constants.FONT_NORMAL);
        clearChat = new JMenuItem("Clear Chat");
        clearChat.setFont(Constants.FONT_NORMAL);
        viewProfile = new JMenuItem("View Profile");
        viewProfile.setFont(Constants.FONT_NORMAL);
        blockUser = new JMenuItem("Block User");
        blockUser.setFont(Constants.FONT_NORMAL);

        // Add JMenuItems to JPopupMenu
        moreMenu.add(deleteChat);
        moreMenu.add(clearChat);
        moreMenu.add(viewProfile);
        moreMenu.add(blockUser);

        // Create an ActionListener
        moreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("More button clicked");
                Component b=(Component)e.getSource();

                // Get the location of the point 'on the screen'
                Point p=b.getLocationOnScreen();

                moreMenu.show(chatNamePanel,0,0);

                // Now set the location of the JPopupMenu
                // This location is relative to the screen
                moreMenu.setLocation(p.x,p.y+b.getHeight());
            }
        });
    }

    private void buildMessagesPanel() {
        messagesPanel = new JPanel();
//        messagePanel.setBackground(Constants.COLOR_BACKGROUND);
        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
        messagesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        boolean isMyMessage;
        for (int i = 0; i < 20; i++) {
            if (i % 5 == 0) {
                MessageTimePanel mtp = new MessageTimePanel(LocalDateTime.now());
                this.messagesPanel.add(mtp);
            }
            if (i % 2 == 0) {
                isMyMessage = true;
            } else {
                isMyMessage = false;

                MemberNamePanel mnp = new MemberNamePanel("lien_");
                this.messagesPanel.add(mnp);
            }
            AMessagePanel amp = new AMessagePanel("Hello, my name is Lien. Lorem ipsum dolor sit. Ame to siesta. Boenure. Nice.", isMyMessage);
            this.messagesPanel.add(amp);
        }

        messagesScrollPane = new JScrollPane(messagesPanel);
//        messagesScrollPane.setBackground(Constants.COLOR_BACKGROUND);
        messagesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        messagesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        messagesScrollPane.setBorder(null);
        // speed up the scroll speed
        messagesScrollPane.getVerticalScrollBar().setUnitIncrement(8);
    }

    private class AMessagePanel extends JPanel {
        private String message;
        private JLabel mLabel;
        private JPanel mPanel;

        public AMessagePanel(String message, boolean isMyMessage) {
            setLayout(new GridLayout(1, 1));
            setBackground(null);

            mLabel = new JLabel(message);
            if (mLabel.getPreferredSize().width > 500){
                mLabel = new JLabel("<html><p style='width: 600'>" + message + "</p></html>");
            }
            mLabel.setFont(Constants.FONT_NORMAL);

            mPanel = new JPanel();

            if (isMyMessage) {
                mLabel.setForeground(Color.WHITE);

                JPanel backgroundPanel = new RoundedPanel(45, Constants.COLOR_PRIMARY);
//                backgroundPanel.setBackground(Constants.DARK_BLUE);
                backgroundPanel.setOpaque(false);
                backgroundPanel.setBorder(new EmptyBorder(5, 10, 8, 10));
                backgroundPanel.add(mLabel);

                mPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 5));
                mPanel.add(backgroundPanel);

                add(mPanel);
            } else {
                mLabel.setForeground(Constants.COLOR_TEXT_PRIMARY);

                JPanel backgroundPanel = new RoundedPanel(45, Constants.COLOR_TEXT_LIGHT_SECONDARY);
//                backgroundPanel.setBackground(Color.LInstants.COLOR_TEXT_SECONDARY);
                backgroundPanel.setOpaque(false);
                backgroundPanel.setBorder(new EmptyBorder(5, 10, 8, 10));
                backgroundPanel.add(mLabel);

                mPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
                mPanel.add(backgroundPanel);

                add(mPanel);
            }

        }
    }

    private class MessageTimePanel extends JPanel {
        private LocalDateTime timestamp;
        private JLabel timestampLabel;
        private JPanel timestampPanel;

        public MessageTimePanel(LocalDateTime timestamp) {
            this.timestamp = timestamp;

            setLayout(new GridLayout(1, 1));
            setBackground(null);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy, HH:mm");
            timestampLabel = new JLabel(timestamp.format(formatter));
            timestampLabel.setFont(Constants.FONT_SMALL);
            timestampLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);

            timestampPanel = new JPanel();
            timestampPanel.setBackground(null);
            timestampPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
            timestampPanel.add(timestampLabel);

            add(timestampPanel);
        }
    }

    private class MemberNamePanel extends JPanel {
        private JLabel memberNameLabel;
        private JPanel memberNamePanel;

        public MemberNamePanel(String username) {
            setLayout(new GridLayout(1, 1));
            setBackground(null);

            memberNameLabel = new JLabel(username);
            memberNameLabel.setFont(Constants.FONT_SMALL);
            memberNameLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);

            memberNamePanel = new JPanel();
            memberNamePanel.setBackground(null);
            memberNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
            memberNamePanel.add(memberNameLabel);

            add(memberNamePanel);
        }
    }

    private void buildInputPanel() {
        inputPanel = new JPanel();
        inputPanel.setBackground(Constants.COLOR_BACKGROUND);
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        inputField = new JTextField();
        inputField.setBackground(Constants.COLOR_BACKGROUND);
        // border background color
//        inputField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Constants.COLOR_BACKGROUND));
        inputField.setBorder(BorderFactory.createLineBorder(Constants.COLOR_PRIMARY, 1));
        inputField.setFont(Constants.FONT_NORMAL);
        inputField.setForeground(Constants.COLOR_TEXT_PRIMARY);
//        inputField.setText("Type your message here...");

        sendButton = new JButton("Send");
        sendButton.setBackground(Constants.COLOR_PRIMARY);
        sendButton.setFont(Constants.FONT_BOLD);
        sendButton.setForeground(Constants.COLOR_TEXT_LIGHT);
        sendButton.setBorderPainted(false);
        sendButton.setPreferredSize(new Dimension(100, 40));

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
    }

    private class RoundedPanel extends JPanel {
        private Color backgroundColor;
        private int cornerRadius = 15;

        public RoundedPanel(LayoutManager layout, int radius) {
            super(layout);
            cornerRadius = radius;
        }

        public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
            super(layout);
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        public RoundedPanel(int radius) {
            super();
            cornerRadius = radius;
        }

        public RoundedPanel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //Draws the rounded panel with borders.
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setColor(getBackground());
            }
            graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
            graphics.setColor(getBackground());
            graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
        }
    }

    public void setChatName(String chatName, boolean isOnline) {
        this.nameLabel.setText(chatName);
        if (isOnline) {
            IconFontSwing.register(FontAwesome.getIconFont());
            Icon onlineIcon = IconFontSwing.buildIcon(FontAwesome.CIRCLE, 10, Constants.COLOR_ONLINE);
            this.nameLabel.setIcon(onlineIcon);
        } else {
            IconFontSwing.register(FontAwesome.getIconFont());
            Icon onlineIcon = IconFontSwing.buildIcon(FontAwesome.CIRCLE, 10, Constants.COLOR_BACKGROUND);
            this.nameLabel.setIcon(onlineIcon);
        }
    }
}