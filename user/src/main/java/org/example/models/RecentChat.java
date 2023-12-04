package org.example.models;

import java.util.ArrayList;

public class RecentChat {
    private String username;
    private ArrayList<ArrayList<Object>> chats;
    public RecentChat(String username) {
        this.username = username;
        this.chats = new ArrayList<>();
    }

    public void addChat(String friendUsername, String message, boolean seen) {
        ArrayList<Object> row = new ArrayList<>();
        row.add(friendUsername);
        row.add(message);
        row.add(seen);
        this.chats.add(row);
    }

    public ArrayList<ArrayList<Object>> getChatList() {
        return this.chats;
    }
}
