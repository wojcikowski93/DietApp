package com.wojcikowski.kamil.dietapp;

import java.util.Date;
import java.util.List;

public class UserDetails {

    private String gender;
    private Date birthday;
    private int height;
    private Double targetWeight;
    private List<Weight> weightList;

    public UserDetails() {
    }

    public UserDetails(String gender, Date birthday, int height, Weight startWeight, Double targetWeight, List<Weight> weightList) {
        this.gender = gender;
        this.birthday = birthday;
        this.height = height;
        this.targetWeight = targetWeight;
        this.weightList = weightList;
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

    public List<Weight> getWeightList() {
        return weightList;
    }

    public void setWeightList(List<Weight> weightList) {
        this.weightList = weightList;
    }
}
