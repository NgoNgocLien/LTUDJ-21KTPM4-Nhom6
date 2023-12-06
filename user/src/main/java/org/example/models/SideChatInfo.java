package org.example.models;

import java.util.ArrayList;

public class SideChatInfo {
    private String myUsername;
    private String chatId;
    private String chatName;
    private String subtitle;
    private boolean seen;
    private boolean isGroup;
    public SideChatInfo(String myUsername, String chatId, String chatName, String subtitle, boolean seen, boolean isGroup) {
        this.myUsername = myUsername;
        this.chatId = chatId;
        this.chatName = chatName;
        this.subtitle = subtitle;
        this.seen = seen;
        this.isGroup = isGroup;
    }

    public String getMyUsername() { return this.myUsername; }
    public String getChatId() { return this.chatId; }
    public String getChatName() { return this.chatName; }
    public String getSubtitle() { return this.subtitle; }
    public boolean getSeen() { return this.seen; }
    public boolean getIsGroup() { return this.isGroup; }

    public void setMyUsername(String myUsername) { this.myUsername = myUsername; }
    public void setChatId(String chatId) { this.chatId = chatId; }
    public void setChatName(String chatName) { this.chatName = chatName; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }
    public void setSeen(boolean seen) { this.seen = seen; }
    public void setIsGroup(boolean isGroup) { this.isGroup = isGroup; }
}
