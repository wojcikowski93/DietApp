package com.wojcikowski.kamil.dietapp.model;

import java.util.List;

public class User {
    private long userid;
    private String username;
    private String password;
    private String email;
    private UserDetails userDetails;
    private List<Measurements> measurementsList;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(long userid, String username, String password, String email) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(long userid, String username, String password, String email, UserDetails userDetails, List<Measurements> measurements) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userDetails = userDetails;
        this.measurementsList = measurements;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
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

    public List<Measurements> getMeasurementsList() {
        return measurementsList;
    }

    public void setMeasurementsList(List<Measurements> measurementsList) {
        this.measurementsList = measurementsList;
    }
}
