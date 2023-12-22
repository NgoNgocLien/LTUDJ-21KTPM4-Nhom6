package org.example.models;

import java.time.LocalDateTime;

public class Message {
    private int id;
    private String sender;
    private String receiver;
    private String content;
    private LocalDateTime sentTime;

    public Message(int id, String sender, String receiver, String content, LocalDateTime sentTime) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.sentTime = sentTime;
    }
}
