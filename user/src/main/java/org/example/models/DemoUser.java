package org.example.models;

public class DemoUser {
    private String fullname;
    private String username;

    public DemoUser(String fullname, String username) {
        this.fullname = fullname;
        this.username = username;
    }

    public String getFullname() {
        return this.fullname;
    }

    public String getUsername() {
        return this.username;
    }
}
