package com.wojcikowski.kamil.dietapp;

public class User {
    private long user_id;
    private String username;
    private String password;
    private String email;
    private UserDetails userDetails;

    public User() {
    }

    public User(long user_id, String username, String password, String email) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(long user_id, String username, String password, String email, UserDetails userDetails) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userDetails = userDetails;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
