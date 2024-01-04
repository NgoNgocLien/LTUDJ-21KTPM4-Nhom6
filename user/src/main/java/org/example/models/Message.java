package org.example.models;

import java.time.LocalDateTime;

public class Message {
    private int id;
    private String sender;
    private String toUser;
    private int toGroup;
    private String content;
    private LocalDateTime sentTime;
    private boolean myMessage;

    public Message(int id, String sender, String toUser, int toGroup, String content, LocalDateTime sentTime, boolean myMessage) {
        this.id = id;
        this.sender = sender;
        this.toUser = toUser;
        this.toGroup = toGroup;
        this.content = content;
        this.sentTime = sentTime;
        this.myMessage = myMessage;
    }

    public int getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getToUser() {
        return toUser;
    }

    public int getToGroup() {
        return toGroup;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public boolean isMyMessage() {
        return myMessage;
    }
}
