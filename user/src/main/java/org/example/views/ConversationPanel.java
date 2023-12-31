package org.example.views;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.example.controllers.ProfileFrameController;
import org.example.models.ChatInfo;
import org.example.models.Message;
import org.example.models.Profile;
import org.example.utilities.Constants;
import org.example.utilities.DatabaseHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Array;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ConversationPanel extends JPanel {
    DatabaseHandler DB = new DatabaseHandler();
    private ChatInfo chatInfo;
    private LocalDateTime lastMessage;
    private JPanel chatNamePanel;
    private JLabel nameLabel;
    private JPanel messagesPanel;
    private JScrollPane messagesScrollPane;
    private JPanel inputPanel;
    private JButton sendButton;
    private JTextField inputField;
    private JPopupMenu moreMenu;
    private JButton moreButton;

    private JMenuItem viewMembers, addMember, leaveGroup, viewProfile, deleteChat, searchMessage, changeGroupName, removeMember;
    private ArrayList<AMessagePanel> messagePanelList;
    private ArrayList<JMenuItem> moreOptions;
    private boolean searching = false;

    public ConversationPanel() {
        this.lastMessage = LocalDateTime.of(1990, 1, 1, 0, 0, 0);
        this.chatInfo = null;
        this.messagePanelList = null;
        this.moreOptions = new ArrayList<JMenuItem>();
        setBackground(Constants.COLOR_BACKGROUND);
        setLayout(new BorderLayout());

        buildChatNamePanel();
        buildMessagesPanel();
        buildInputPanel();
        buildMoreMenu();

        add(chatNamePanel, BorderLayout.NORTH);
        add(messagesScrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }

    public JMenuItem getAddMember() {
        return addMember;
    }

    public JMenuItem getLeaveGroup() {
        return leaveGroup;
    }

    public JMenuItem getViewProfile() {
        return viewProfile;
    }

    public JMenuItem getDeleteChat() {
        return deleteChat;
    }
    public JMenuItem getSearchMessage() { return searchMessage; }

    public boolean isSearching() {
        return searching;
    }
    public void setSearching(boolean searching) {
        this.searching = searching;
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

//        buildMoreMenu();
        moreMenu = new JPopupMenu();
        moreMenu.setBackground(Constants.COLOR_BACKGROUND);
        moreMenu.setPreferredSize(new Dimension(160, 160));
    }

    private void buildMoreMenu() {
        System.out.println("Building more menu constructor");
        searchMessage = new JMenuItem("Search message");
        searchMessage.setFont(Constants.FONT_NORMAL);
        viewMembers = new JMenuItem("View members");
        viewMembers.setFont(Constants.FONT_NORMAL);
        changeGroupName = new JMenuItem("Change group name");
        changeGroupName.setFont(Constants.FONT_NORMAL);
        addMember = new JMenuItem("Add a member");
        addMember.setFont(Constants.FONT_NORMAL);
        removeMember = new JMenuItem("Remove a member");
        removeMember.setFont(Constants.FONT_NORMAL);
        deleteChat = new JMenuItem("Delete chat");
        deleteChat.setFont(Constants.FONT_NORMAL);
        leaveGroup = new JMenuItem("Leave group");
        leaveGroup.setFont(Constants.FONT_NORMAL);


        viewProfile = new JMenuItem("View profile");
        viewProfile.setFont(Constants.FONT_NORMAL);

        searchMessage.addActionListener(e -> {
            String keyword = JOptionPane.showInputDialog(null, "Enter keyword to search");
            if (keyword == null || keyword.isEmpty()) {
                return;
            }
            if (chatInfo.isFriend()) {
                System.out.println(keyword);
                ArrayList<Message> messages = DB.getFriendMessagesWithKeyWord(DB.getLoginedUsername(), chatInfo.getUsername(), keyword);
                if (messages == null || messages.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No message found");
                } else {
                    rebuildConversationPanel(chatInfo, messages);
                    scrollToBottom();
                    searching = true;
                }
            } else if (chatInfo.isGroup()) {
                ArrayList<Message> messages = DB.getGroupMessagesWithKeyWord(DB.getLoginedUsername(), getChatInfo().getGroupId(), keyword);
                if (messages == null || messages.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No message found");
                } else {
                    rebuildConversationPanel(chatInfo, messages);
                    scrollToBottom();
                    searching = true;
                }
            }
        });

        deleteChat.addActionListener(e -> {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the chat?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                if (chatInfo.isGroup()) {
                    try {
                        DB.deleteGroupChat(DB.getLoginedUsername(), chatInfo.getGroupId());
                        rebuildConversationPanel(chatInfo, null);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (chatInfo.isFriend()) {
                    try {
                        DB.deleteFriendChat(DB.getLoginedUsername(), chatInfo.getUsername());
                        rebuildConversationPanel(chatInfo, null);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        viewMembers.addActionListener(e -> {
            ArrayList<ChatInfo> members = DB.viewGroupChatMembers(chatInfo.getGroupId());
            StringBuilder memberList = new StringBuilder();
            for (ChatInfo member : members) {
                if (DB.isAdmin(member.getUsername(), chatInfo.getGroupId())) {
                    memberList.append(member.getUsername()).append(" (admin)").append("\n");
                    continue;
                } else {
                    memberList.append(member.getUsername()).append("\n");
                }
            }
            JOptionPane.showMessageDialog(null, memberList.toString(), "Members", JOptionPane.INFORMATION_MESSAGE);
        });

        changeGroupName.addActionListener(e -> {
            String newGroupName = JOptionPane.showInputDialog(null, "Enter new group name");
            if (newGroupName == null || newGroupName.isEmpty()) {
                return;
            }
            JOptionPane.showMessageDialog(null, "Change group name successfully!");
            DB.changeGroupName(chatInfo.getGroupId(), newGroupName);
            ArrayList<Message> messages = DB.getGroupMessages(DB.getLoginedUsername(), chatInfo.getGroupId());
            rebuildConversationPanel(chatInfo, messages);
        });

        leaveGroup.addActionListener(e -> {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave the group?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                DB.leaveGroup(DB.getLoginedUsername(), chatInfo.getGroupId());
                rebuildConversationPanel(chatInfo, null);
            }
        });

        removeMember.addActionListener(e -> {
            if (chatInfo.isGroup()) {
                if(!DB.isAdmin(DB.getLoginedUsername(), chatInfo.getGroupId())) {
                    JOptionPane.showMessageDialog(null, "You are not admin of this group, you can't remove others!");
                    return;
                }
                ArrayList<ChatInfo> members = DB.getAllMembersNotAdmin(chatInfo.getGroupId());
                try {
                    RemoveMemberFrame AMF = new RemoveMemberFrame(members);
                    AMF.getRemoveMemberButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ArrayList<String> addMembers = new ArrayList<>();
                            ArrayList<JCheckBox> removeMemberBoxes = AMF.getMemberCheckBoxes();

                            for (JCheckBox box : removeMemberBoxes) {
                                if (box.isSelected()) {
                                    addMembers.add(box.getActionCommand());
                                }
                            }

                            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove these members?", "Confirmation", JOptionPane.YES_NO_OPTION);
                            if (dialogResult == JOptionPane.YES_OPTION) {
                                DB.removeMember(chatInfo.getGroupId(), addMembers);
                                AMF.dispose();
                                ArrayList<Message> messages = DB.getGroupMessages(DB.getLoginedUsername(), chatInfo.getGroupId());
                                rebuildConversationPanel(chatInfo, messages);
                            }
                        }
                    });

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

        addMember.addActionListener(e -> {
            if (chatInfo.isGroup()) {
                if(!DB.isAdmin(DB.getLoginedUsername(), chatInfo.getGroupId())) {
                    JOptionPane.showMessageDialog(null, "You are not admin of this group, you can't add others!");
                    return;
                }
                ArrayList<ChatInfo> members = DB.getAllFriendNotMember(DB.getLoginedUsername(), chatInfo.getGroupId());
                try {
                    AddMemberFrame AMF = new AddMemberFrame(members);
                    AMF.getAddMemberButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ArrayList<String> addMembers = new ArrayList<>();
                            ArrayList<JCheckBox> removeMemberBoxes = AMF.getMemberCheckBoxes();

                            for (JCheckBox box : removeMemberBoxes) {
                                if (box.isSelected()) {
                                    addMembers.add(box.getActionCommand());
                                }
                            }

                            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to add these members?", "Confirmation", JOptionPane.YES_NO_OPTION);
                            if (dialogResult == JOptionPane.YES_OPTION) {
                                DB.addMember(chatInfo.getGroupId(), addMembers);
                                AMF.dispose();
                                ArrayList<Message> messages = DB.getGroupMessages(DB.getLoginedUsername(), chatInfo.getGroupId());
                                rebuildConversationPanel(chatInfo, messages);

                            }
                        }
                    });

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        if (chatInfo == null) {
            return;
        }
        if (chatInfo.isGroup()) {
            moreMenu.removeAll();
            moreMenu.setBackground(Constants.COLOR_BACKGROUND);
            moreMenu.setPreferredSize(new Dimension(240, 280));

            // Add JMenuItems to JPopupMenu
            moreMenu.add(searchMessage);
            moreMenu.add(viewMembers);
            moreMenu.add(addMember);
            moreMenu.add(removeMember);
            moreMenu.add(changeGroupName);
            moreMenu.add(deleteChat);
            moreMenu.add(leaveGroup);

        } else if (chatInfo.isFriend()) {
            moreMenu.removeAll();
            moreMenu.setBackground(Constants.COLOR_BACKGROUND);
            moreMenu.setPreferredSize(new Dimension(180, 120));

            // Create JMenuItems
            viewProfile.addActionListener(e -> {
                try {
                    Profile profile = DB.getProfilebyUsername(chatInfo.getUsername());
                    ProfileFrame PF = new ProfileFrame(profile, 2);
                    ProfileFrameController PFC = new ProfileFrameController(null, PF, DB);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });

            // Add JMenuItems to JPopupMenu
            moreMenu.add(viewProfile);
            moreMenu.add(searchMessage);
            moreMenu.add(deleteChat);
        }

//         Create an ActionListener

        moreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("More button clicked");
                Component b = (Component) e.getSource();

                // Get the location of the point 'on the screen'
                Point p = b.getLocationOnScreen();

                moreMenu.show(chatNamePanel, 0, 0);

                // Now set the location of the JPopupMenu
                // This location is relative to the screen
                moreMenu.setLocation(p.x, p.y + b.getHeight());
            }
        });
    }

    private void buildMessagesPanel() {
        messagesPanel = new JPanel();
//        messagePanel.setBackground(Constants.COLOR_BACKGROUND);
        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
        messagesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        messagesPanel.add(new StartConversationPanel("Welcome back!", "", "Please select a user to start chatting."));

        JPanel tmp = new JPanel();
        tmp.setLayout(new BorderLayout());
        tmp.add(messagesPanel, BorderLayout.SOUTH);

        messagesScrollPane = new JScrollPane(tmp);
        messagesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        messagesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        messagesScrollPane.setBorder(null);
        // speed up the scroll speed
        messagesScrollPane.getVerticalScrollBar().setUnitIncrement(2);
    }

    public void addStartConversationPanel(String fullname, String username, String statement) {
        StartConversationPanel scp = new StartConversationPanel(fullname, username, statement);
        this.messagesPanel.add(scp);
    }

    public void addMessage(Message message) {
        AMessagePanel amp = new AMessagePanel(message);
        this.messagesPanel.add(amp);
        this.messagesPanel.revalidate();
        this.messagesPanel.repaint();
        this.messagePanelList.add(amp);
        lastMessage = message.getSentTime();
    }

    public void addTime(LocalDateTime timestamp) {
        MessageTimePanel mtp = new MessageTimePanel(timestamp);
        this.messagesPanel.add(mtp);
    }

    public void addMemberName(String username) {
        MemberNamePanel mnp = new MemberNamePanel(username);
        this.messagesPanel.add(mnp);
    }

    private void buildInputPanel() {
        inputPanel = new JPanel();
        inputPanel.setBackground(Constants.COLOR_BACKGROUND);
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        inputField = new JTextField();
//        inputField.setBackground(Constants.COLOR_BACKGROUND);
//        inputField.setBorder(BorderFactory.createLineBorder(Constants.COLOR_PRIMARY, 1));
        inputField.setFont(Constants.FONT_NORMAL);
//        inputField.setForeground(Constants.COLOR_TEXT_SECONDARY);
//        inputField.setText("Type your message here...");

        sendButton = new JButton("Send");
//        sendButton.setBackground(Constants.COLOR_PRIMARY);
        sendButton.setFont(Constants.FONT_BOLD);
        sendButton.setForeground(Constants.COLOR_TEXT_LIGHT);
        sendButton.setBorderPainted(false);
        sendButton.setPreferredSize(new Dimension(100, 40));

        disableInput();

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
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

    public void rebuildConversationPanel(ChatInfo info, ArrayList<Message> messages) {
        this.chatInfo = info;
        if (this.messagePanelList != null)
            this.messagePanelList.clear();
        this.messagePanelList = new ArrayList<AMessagePanel>();

        setChatName(info.getChatName(), info.isOnline());

        this.messagesPanel.removeAll();
        if (info.isGroup()) {
            buildMoreMenu();
            addStartConversationPanel(info.getChatName(), info.getQuantity() + " members", "You are a member of this group. Let start chatting!");
            enableInput();

            if (messages == null || messages.isEmpty()) {
                repaint();
                revalidate();
                return;
            }

            LocalDateTime lastTime = messages.get(0).getSentTime();
            addTime(lastTime);

            for (Message message : messages) {
                if (message.getSentTime().getDayOfYear() != lastTime.getDayOfYear() || message.getSentTime().getHour() != lastTime.getHour()) {
                    addTime(message.getSentTime());
                    lastTime = message.getSentTime();
                }
                if (!message.isMyMessage()) {
                    addMemberName(message.getSender());
                }
                addMessage(message);
            }

            lastMessage = messages.get(messages.size() - 1).getSentTime();
        } else {
            buildMoreMenu();
            String statement;
            if (info.isFriend()) {
                statement = "You are friend with this user. You can now chat with your friend.";
                enableInput();
            } else {
                statement = "You are not friend with this user. You cannot chat with this user.";
                disableInput();
            }
            addStartConversationPanel(info.getChatName(), info.getUsername(), statement);

            if (messages == null || messages.isEmpty()) {
                repaint();
                revalidate();
                return;
            }

            if(messages != null && !messages.isEmpty() && messages.get(0) != null) {
                LocalDateTime lastTime = messages.get(0).getSentTime();
                addTime(lastTime);

                for (Message message : messages) {
                    if (message.getSentTime().getDayOfYear() != lastTime.getDayOfYear() || message.getSentTime().getHour() != lastTime.getHour()) {
                        addTime(message.getSentTime());
                        lastTime = message.getSentTime();
                    }
                    addMessage(message);
                }
                lastMessage = messages.get(messages.size() - 1).getSentTime();
            } else {
                lastMessage = LocalDateTime.of(1990, 1, 1, 0, 0, 0);
            }
        }
    }

    public JTextField getInputField() {
        return inputField;
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public JButton getMoreButton() {
        return moreButton;
    }
    public JPopupMenu getMoreMenu() { return moreMenu; }
    public ArrayList<JMenuItem> getMoreOptions() { return moreOptions; }

    public ChatInfo getChatInfo() {
        return chatInfo;
    }

    public LocalDateTime getLastMessage() {
        return lastMessage;
    }

    public void disableInput() {
        inputField.setEnabled(false);
        inputField.setBorder(BorderFactory.createLineBorder(Constants.COLOR_TEXT_LIGHT_SECONDARY, 1));
        inputField.setForeground(Constants.COLOR_TEXT_LIGHT_SECONDARY);
        inputField.setText("You cannot chat here.");
        sendButton.setEnabled(false);
        sendButton.setBackground(Constants.COLOR_TEXT_LIGHT_SECONDARY);
    }

    public void enableInput() {
        inputField.setEnabled(true);
        inputField.setBorder(BorderFactory.createLineBorder(Constants.COLOR_PRIMARY, 1));
        if (!inputField.hasFocus()) {
            inputField.setText("Type your message here...");
            inputField.setForeground(Constants.COLOR_TEXT_SECONDARY);
        } else {
            inputField.setText("");
            inputField.setForeground(Constants.COLOR_TEXT_PRIMARY);
        }
        sendButton.setEnabled(true);
        sendButton.setBackground(Constants.COLOR_PRIMARY);
    }

    public JScrollBar getMessagesScrollBar() {
        return messagesScrollPane.getVerticalScrollBar();
    }

    public void scrollToBottom() {
        JScrollBar verticalBar = messagesScrollPane.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }

    private class StartConversationPanel extends JPanel {
        public StartConversationPanel(String fullname, String username, String statement) {
            setLayout(new GridLayout(5, 1));
            setBackground(null);

            JLabel fullnameLabel = new JLabel(fullname);
            fullnameLabel.setFont(Constants.FONT_LARGE_BOLD);
            fullnameLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);

            JLabel usernameLabel = new JLabel(username);
            usernameLabel.setFont(Constants.FONT_NORMAL);
            usernameLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);

            JLabel statementLabel = new JLabel(statement);
            statementLabel.setFont(Constants.FONT_NORMAL);
            statementLabel.setForeground(Constants.COLOR_TEXT_SECONDARY);

            JPanel fullnamePanel = new JPanel();
            fullnamePanel.setBackground(null);
            fullnamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            fullnamePanel.add(fullnameLabel);

            JPanel usernamePanel = new JPanel();
            usernamePanel.setBackground(null);
            usernamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            usernamePanel.add(usernameLabel);

            JPanel statementPanel = new JPanel();
            statementPanel.setBackground(null);
            statementPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            statementPanel.add(statementLabel);

            add(fullnamePanel);
            add(usernamePanel);
            add(new JPanel());
            add(statementPanel);
            add(new JPanel());
        }

    }

    private class AMessagePanel extends JPanel {
        private JLabel mLabel;
        private JPanel mPanel;
        private Message msg;
        private String message;

        public AMessagePanel(Message msg) {
            this.msg = msg;
            this.message = msg.getContent();

            setLayout(new GridLayout(1, 1));
            setBackground(null);

            mLabel = new JLabel(message);
            if (mLabel.getPreferredSize().width > 500) {
                mLabel = new JLabel("<html><p style='width: 600'>" + message + "</p></html>");
            }
            mLabel.setFont(Constants.FONT_NORMAL);

            mPanel = new JPanel();

            if (msg.isMyMessage()) {
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
                mPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(e.getButton() == MouseEvent.BUTTON3){
                            showContextMenu(e.getX(), e.getY());
                        }
                    }
                });
            }

        }
        private void showContextMenu(int x, int y) {
            JPopupMenu contextMenu = new JPopupMenu();

            // Add menu items to the context menu
            JMenuItem reportSpam = new JMenuItem("Report this message as spam");

            // Add your custom action listeners to the menu items
            contextMenu.add(reportSpam);

            reportSpam.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        DB.reportSpamMessage(msg.getId());
                        JOptionPane.showMessageDialog(null, "Report successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            // Show the context menu at the specified location
            contextMenu.show(mPanel, x, y);
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
            graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); //paint background
            graphics.setColor(getBackground());
            graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); //paint border
        }
    }
}