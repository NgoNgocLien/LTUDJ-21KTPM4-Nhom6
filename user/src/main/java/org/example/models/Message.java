package org.example.models;

import com.mysql.cj.result.LongValueFactory;

import java.time.LocalDateTime;

public class Message {
    private String myUsername;
    private int id;
    private String sender;
    private String toUser;
    private int toGroup;
    private String content;
    private LocalDateTime sentTime;
    private LocalDateTime seenTime;

    public Message(String myUsername, int id, String sender, String toUser, int toGroup, String content, LocalDateTime sentTime, LocalDateTime seenTime) {
        this.myUsername = myUsername;
        this.id = id;
        this.sender = sender;
        this.toUser = toUser;
        this.toGroup = toGroup;
        this.content = content;
        this.sentTime = sentTime;
        this.seenTime = seenTime;
    }

    public String getMyUsername() { return this.myUsername; }
    public int getId() { return this.id; }
    public String getSender() { return this.sender; }
    public String getToUser() { return this.toUser; }
    public int getToGroup() { return this.toGroup; }
    public String getContent() { return this.content; }
    public LocalDateTime getSentTime() { return this.sentTime; }
    public LocalDateTime getSeenTime() { return this.seenTime; }

    public void setMyUsername(String myUsername) { this.myUsername = myUsername; }
    public void setId(int id) { this.id = id; }
    public void setSender(String sender) { this.sender = sender; }
    public void setToUser(String toUser) { this.toUser = toUser; }
    public void setToGroup(int toGroup) { this.toGroup = toGroup; }
    public void setContent(String content) { this.content = content; }
    public void setSentTime(LocalDateTime sentTime) { this.sentTime = sentTime; }
    public void setSeenTime(LocalDateTime seenTime) { this.seenTime = seenTime; }
}
