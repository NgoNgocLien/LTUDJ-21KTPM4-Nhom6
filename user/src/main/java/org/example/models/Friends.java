package org.example.models;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Friends {
    private String currentUser;
    private ArrayList<ArrayList<Object>> friends;
    public Friends(String currentUser) {
        this.currentUser = currentUser;
        this.friends = new ArrayList<>();
    }

    public void addFriend(String friendUsername, LocalDate deleteChat) {
        ArrayList<Object> row = new ArrayList<>();
        row.add(friendUsername);
        row.add(deleteChat);
        friends.add(row);
    }

    public ArrayList<ArrayList<Object>> getAllFriends() {
        return friends;
    }
}
