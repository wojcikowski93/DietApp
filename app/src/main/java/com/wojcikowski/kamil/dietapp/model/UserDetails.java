package com.wojcikowski.kamil.dietapp.model;

import java.util.Date;
import java.util.List;

public class UserDetails {

    private long userID;
    private String gender;
    private Date birthday;
    private int height;
    private Double targetWeight;
    private List<Measurements> measurementsList;

    public UserDetails() {
    }

    public UserDetails(long userID, String gender, Date birthday, int height, Double targetWeight) {
        this.userID = userID;
        this.gender = gender;
        this.birthday = birthday;
        this.height = height;
        this.targetWeight = targetWeight;
    }

    public UserDetails(long userID, String gender, Date birthday, int height, Double targetWeight, List<Measurements> measurementsList) {
        this.userID = userID;
        this.gender = gender;
        this.birthday = birthday;
        this.height = height;
        this.targetWeight = targetWeight;
        this.measurementsList = measurementsList;
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

    public Date getBirthday() {
        return birthday;
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

    public List<Measurements> getMeasurementsList() {
        return measurementsList;
    }

    public void setMeasurementsList(List<Measurements> measurementsList) {
        this.measurementsList = measurementsList;
    }
}
