package org.example.models;

// interface
public class ChatInfo {
    private String chatName;
    private String subTitle; // last message or username
    private boolean isGroup;
    private int groupId;
    private boolean isOnline;
    private boolean isUnread;


    public ChatInfo(String chatName, String subTitle) {
        this.chatName = chatName;
        this.subTitle = subTitle;
        this.isGroup = false;
        this.groupId = -1;
        this.isOnline = false;
        this.isUnread = false;
    }
    public ChatInfo(String chatName, String subTitle, boolean isOnline) {
        this.chatName = chatName;
        this.subTitle = subTitle;
        this.isGroup = false;
        this.groupId = -1;
        this.isOnline = isOnline;
        this.isUnread = false;
    }
    public ChatInfo(String chatName, String subTitle, boolean isGroup, int groupId) {
        this.chatName = chatName;
        this.subTitle = subTitle;
        this.isGroup = isGroup;
        this.groupId = groupId;
        this.isOnline = false;
        this.isUnread = false;
    }
    public ChatInfo(String chatName, String subTitle, boolean isGroup, int groupId, boolean isOnline, boolean isUnread) {
        this.chatName = chatName;
        this.subTitle = subTitle;
        this.isGroup = isGroup;
        this.groupId = groupId;
        this.isOnline = isOnline;
        this.isUnread = isUnread;
    }

    public String getChatName() { return chatName; }
    public String getSubTitle() { return subTitle; }
    public boolean isGroup() { return isGroup; }
    public int getGroupId() { return groupId; }
    public boolean isOnline() { return isOnline; }
    public boolean isUnread() { return isUnread; }
}
