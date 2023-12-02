package org.example.models;

//import java.time.LocalDate;
//
//public class User {
//    private String username;
//    private String password;
//    private String fulllname;
//    private String address;
//    private LocalDate birthday;
//    private int gender; // 0: male - 1: female
//    private String email;
//    private LocalDate creation_time;
//
//
//}

public class User {
    private String fullname;
    private String username;

    public User(String fullname, String username) {
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
