package org.example.models;

// interface
public class ChatInfo {
    private String chatName;
    private String username;
    private String subTitle; // last message or group members or username
    private boolean isGroup;
    private int groupId;
    private int quantity;
    private boolean isOnline;
    private boolean isUnread;

    // friend
    public ChatInfo(String chatName, String username, String subTitle, boolean isOnline, boolean isUnread) {
        this.chatName = chatName;
        this.isGroup = false;
        this.groupId = -1;
        this.quantity = -1;
        this.username = username;
        this.subTitle = subTitle;
        this.isOnline = isOnline;
        this.isUnread = isUnread;
    }

    // group
    public ChatInfo(String chatName, int groupId, int quantity, String subTitle, boolean isUnread) {
        this.chatName = chatName;
        this.isGroup = true;
        this.groupId = groupId;
        this.quantity = quantity;
        this.username = "";
        this.subTitle = subTitle;
        this.isOnline = false;
        this.isUnread = isUnread;
    }

    public String getChatName() { return chatName; }
    public String getUsername() { return username; }
    public String getSubTitle() { return subTitle; }
    public boolean isGroup() { return isGroup; }
    public int getGroupId() { return groupId; }
    public int getQuantity() { return quantity; }
    public boolean isOnline() { return isOnline; }
    public boolean isUnread() { return isUnread; }
}
