
package org.example.models;
import java.time.LocalDate;

public class User {
    private String username;
    private String password;
    private String fullname;
    private String address;
    private LocalDate birthday;
    private int gender;
    private String email;
    private boolean isActive;
    private LocalDate creationTime;
    private boolean isLocked;

    public User(String username, String password, String fullname, String address, LocalDate birthday, int gender, String email, boolean isActive, LocalDate creationTime, boolean isLocked) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
        this.isActive = isActive;
        this.creationTime = creationTime;
        this.isLocked = isLocked;
    }

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public String getFullname() { return this.fullname; }
    public String getAddress() { return this.address; }
    public LocalDate getBirthday() { return this.birthday; }
    public int getGender() { return this.gender; }
    public String getEmail() { return this.email; }
    public boolean getIsActive() { return this.isActive; }
    public LocalDate getCreationTime() { return this.creationTime; }
    public boolean getIsLocked() { return this.isLocked; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public void setAddress(String address) { this.address = address; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }
    public void setGender(int gender) { this.gender = gender; }
    public void setEmail(String email) { this.email = email; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }
    public void setCreationTime(LocalDate creationTime) { this.creationTime = creationTime; }
    public void setIsLocked(boolean isLocked) { this.isLocked = isLocked; }
}

