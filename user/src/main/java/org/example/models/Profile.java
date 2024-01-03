package org.example.models;

import java.time.LocalDate;

public class Profile {
    private LocalDate dateJoined;
    private String fullname;
    private String username;
    private String password;
    private int gender; // Male, Female
    private LocalDate birthdate;
    private String email;
    private String address;

    public Profile(LocalDate dateJoined, String fullname, String username, int gender, LocalDate birthdate, String email, String address , String password) {
        this.dateJoined = dateJoined;
        this.fullname = fullname;
        this.username = username;
        this.gender = gender;
        this.birthdate = birthdate;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public int getGender() {
        return gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }
}
