package org.example.views;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.example.models.ChatInfo;
import org.example.utilities.Constants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ChatListPanel extends JPanel {
    private ArrayList<ChatInfo> infos;
    private JLabel titleLabel;
    private JTextField searchField;
    private JButton searchButton;
    private JScrollPane chatPanelsScrollPane;
    private ArrayList<AChatPanel> chatPanels;
    private String inputFieldPlaceholder;
    private Icon plusIcon;
    private JPanel chatPanelsPanel;

    public ChatListPanel(ArrayList<ChatInfo> infos) {
        this.infos = infos;
        this.inputFieldPlaceholder = "Search for a message";
        chatPanels = new ArrayList<>();

        setBackground(Constants.COLOR_SECONDARY);
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Constants.COLOR_SECONDARY);
        searchPanel.setLayout(new BorderLayout());
        searchPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        titleLabel = new JLabel();
        titleLabel.setText("Chats");
        titleLabel.setForeground(Constants.COLOR_TEXT_LIGHT);
        titleLabel.setFont(Constants.FONT_LARGER_BOLD);
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        titleLabel.setIcon(null);

        // plus icon
        plusIcon = null;

        searchField = new JTextField();
        searchField.setBackground(Constants.COLOR_BACKGROUND);
        searchField.setFont(Constants.FONT_NORMAL);
        searchField.setForeground(Constants.COLOR_TEXT_SECONDARY);
        searchField.setText(inputFieldPlaceholder);

        IconFontSwing.register(FontAwesome.getIconFont());
        Icon searchIcon = IconFontSwing.buildIcon(FontAwesome.SEARCH, 20, Constants.COLOR_ICON_PRIMARY);
        searchButton = new JButton();
        searchButton.setBackground(Constants.COLOR_PRIMARY);
        searchButton.setIcon(searchIcon);
        searchButton.setPreferredSize(new Dimension(40, 40));
        searchButton.setBorderPainted(false);

        searchPanel.add(titleLabel, BorderLayout.NORTH);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        buildChatPanelsScrollPane(infos);

        add(searchPanel, BorderLayout.NORTH);
        add(chatPanelsScrollPane, BorderLayout.CENTER);
    }

    public void buildChatPanelsScrollPane(ArrayList<ChatInfo> infos) {
        chatPanelsScrollPane = new JScrollPane();
        chatPanelsScrollPane.setBackground(Constants.COLOR_SECONDARY);
        chatPanelsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chatPanelsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        chatPanelsScrollPane.setBorder(null);
        // speed up the scroll speed
        chatPanelsScrollPane.getVerticalScrollBar().setUnitIncrement(4);

        chatPanelsPanel = new JPanel();
        chatPanelsPanel.setBackground(Constants.COLOR_SECONDARY);
        chatPanelsPanel.setLayout(new BoxLayout(chatPanelsPanel, BoxLayout.Y_AXIS));

        if (infos == null || infos.isEmpty()) {
            SentencePanel noChatPanel = new SentencePanel("Empty");
            chatPanelsPanel.add(noChatPanel);
        } else {
            for (ChatInfo info : infos) {
                AChatPanel chatPanel = new AChatPanel(info, 0);
                chatPanels.add(chatPanel);
                chatPanelsPanel.add(chatPanel);
                if (info.isUnread()) {
                    chatPanel.setUnread();
                }
                if (info.isOnline()) {
                    chatPanel.setOnlineLabel(true);
                }
            }
        }
        JPanel tmp = new JPanel();
        tmp.setLayout(new BorderLayout());
        tmp.setBackground(Constants.COLOR_SECONDARY);
        tmp.add(chatPanelsPanel, BorderLayout.NORTH);
        chatPanelsScrollPane.setViewportView(tmp);
    }

    public void addSentenceToChatList(String sentence) {
        SentencePanel sentencePanel = new SentencePanel(sentence);
        chatPanelsPanel.add(sentencePanel);
    }

    public void rebuildChatPanelsScrollPane(ArrayList<ChatInfo> infos, int mode, boolean suggestedSentence, ChatInfo currentChatInfo) {
        chatPanelsPanel.removeAll();
        if (infos == null || infos.isEmpty()) {
            SentencePanel noChatPanel = new SentencePanel("Empty");
            chatPanelsPanel.add(noChatPanel);
        } else {
            if (suggestedSentence) {
                addSentenceToChatList("Users you may know");
            }
            for (ChatInfo info : infos) {
                AChatPanel chatPanel = new AChatPanel(info, mode);
                if (info.equals(currentChatInfo)) {
                    System.out.println("equal");
                    chatPanel.setHighlighted();
                }
                chatPanels.add(chatPanel);
                chatPanelsPanel.add(chatPanel);
                if (info.isUnread()) {
                    chatPanel.setUnread();
                }
                if (info.isOnline()) {
                    chatPanel.setOnlineLabel(true);
                }
            }
        }
        JPanel tmp = new JPanel();
        tmp.setLayout(new BorderLayout());
        tmp.setBackground(Constants.COLOR_SECONDARY);
        tmp.add(chatPanelsPanel, BorderLayout.NORTH);
        chatPanelsScrollPane.setViewportView(tmp);
    }

    public void setTitleLabel(String title, boolean hasPlusIcon) {
        titleLabel.setText(title);
        titleLabel.setIcon(null);

        if (hasPlusIcon) {
            IconFontSwing.register(FontAwesome.getIconFont());
            this.plusIcon = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 35, Constants.COLOR_ICON_PRIMARY);
            titleLabel.setIcon(this.plusIcon);
            titleLabel.setHorizontalTextPosition(JLabel.LEFT);
            titleLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            titleLabel.setToolTipText("Create a new group");
            ToolTipManager.sharedInstance().setInitialDelay(100);
        } else {
            titleLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            titleLabel.setToolTipText(null);
        }
    }

    public ArrayList<AChatPanel> getChatPanels() {
        return chatPanels;
    }

    public void setFocusChatPanel(AChatPanel chatPanel) {
        chatPanels.forEach(panel -> panel.setBackground(Constants.COLOR_SECONDARY));
        chatPanel.setHighlighted();
    }

    public JScrollPane getChatPanelsScrollPane() {
        return chatPanelsScrollPane;
    }

    public JTextField getInputField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public Icon getPlusIcon() {
        return this.plusIcon;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public String getInputFieldPlaceholder() {
        return inputFieldPlaceholder;
    }

    public void setInputFieldPlaceholder(String placeholder) {
        this.inputFieldPlaceholder = placeholder;
        if (this.searchField.hasFocus()) {
            return;
        } else {
            searchField.setText(placeholder);
            searchField.setForeground(Constants.COLOR_TEXT_SECONDARY);
        }
    }

    private class SentencePanel extends JPanel {
        String sentence;

        public SentencePanel(String sentence) {
            setBackground(Constants.COLOR_SECONDARY);
            setLayout(new GridLayout(1, 1, 0, 0));
            setBorder(new EmptyBorder(10, 10, 0, 10));
            setPreferredSize(new Dimension(300, 45));

            JLabel sentenceLabel = new JLabel();
            sentenceLabel.setText(sentence);
            sentenceLabel.setForeground(Constants.COLOR_TEXT_LIGHT);
            sentenceLabel.setFont(Constants.FONT_ITALIC);

            add(sentenceLabel);
        }
    }

    public class AChatPanel extends JPanel {
        private int mode; // true: open chat, 1: open profile
        private ChatInfo info;
        private JLabel titleLabel;
        private JLabel subtitleLabel;
        private JLabel onlineLabel;
        private Icon onlineIcon;

        public AChatPanel(ChatInfo info, int mode) {
            this.mode = mode;
            this.info = info;
            setBackground(Constants.COLOR_SECONDARY);
            setLayout(new GridLayout(2, 1, 0, 0));
            setPreferredSize(new Dimension(300, 70));
            setBorder(new EmptyBorder(10, 10, 10, 10));

            titleLabel = new JLabel();
            titleLabel.setText(info.getChatName());
            titleLabel.setForeground(Constants.COLOR_TEXT_LIGHT);
            titleLabel.setFont(Constants.FONT_BOLD);

            setOnlineLabel(info.isOnline());

            subtitleLabel = new JLabel();
            subtitleLabel.setText(info.getSubTitle());
            subtitleLabel.setForeground(Constants.COLOR_TEXT_LIGHT_SECONDARY);
            subtitleLabel.setFont(Constants.FONT_SMALL);

            add(titleLabel);
            add(subtitleLabel);
        }

        public void setHighlighted() {
            setBackground(Constants.COLOR_TERTIARY);
        }

        public void setUnread() {
            titleLabel.setForeground(Constants.COLOR_TEXT_LIGHT);
            subtitleLabel.setForeground(Constants.COLOR_TEXT_LIGHT);
            subtitleLabel.setFont(Constants.FONT_SMALL_BOLD);
        }

        public void setOnlineLabel(boolean isOnline) {
            if (!isOnline) {
                titleLabel.setIcon(null);
                return;
            }
            IconFontSwing.register(FontAwesome.getIconFont());
            onlineIcon = IconFontSwing.buildIcon(FontAwesome.CIRCLE, 10, Constants.COLOR_ONLINE); // initial color is secondary
            onlineLabel = new JLabel();
            onlineLabel.setIcon(onlineIcon);
            onlineLabel.setPreferredSize(new Dimension(20, 20));

            // set icon to the right
            titleLabel.setIcon(onlineIcon);
            titleLabel.setHorizontalTextPosition(JLabel.LEFT);
        }

        public void setTitleLabel(String title) {
            titleLabel.setText(title);
        }

        public void setSubtitleLabel(String subtitle) {
            subtitleLabel.setText(subtitle);
        }

        public ChatInfo getInfo() {
            return info;
        }

        public int getMode() {
            return mode;
        }
    }
}