package com.wojcikowski.kamil.dietapp.model;

import java.util.Date;
import java.util.List;

public class UserDetails {

    private long detailsid;
    private long userID;
    private String gender;
    private Date birthday;
    private int height;
    private Double targetWeight;

    public UserDetails() {
    }

    public UserDetails(long userID, String gender, Date birthday, int height, Double targetWeight) {
        this.userID = userID;
        this.gender = gender;
        this.birthday = birthday;
        this.height = height;
        this.targetWeight = targetWeight;
    }

    public UserDetails(long detailsid, long userID, String gender, Date birthday, int height, Double targetWeight) {
        this.detailsid = detailsid;
        this.userID = userID;
        this.gender = gender;
        this.birthday = birthday;
        this.height = height;
        this.targetWeight = targetWeight;
    }

    public long getDetailsid() {
        return detailsid;
    }

    public void setDetailsid(long detailsid) {
        this.detailsid = detailsid;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        if(birthday != null) {
            return birthday.toString();
        } else return null;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(Double targetWeight) {
        this.targetWeight = targetWeight;
    }
}
