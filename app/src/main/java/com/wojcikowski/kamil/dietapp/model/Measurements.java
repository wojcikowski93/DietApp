package com.wojcikowski.kamil.dietapp.model;

import java.util.Date;

class Measurements {

    private Date date;
    private Double weight;
    private Double biceps;
    private Double forearm;
    private Double tight;
    private Double calf;
    private Double chest;
    private Double stomach;
    private Double hips;

    public Measurements() {
    }

    public Measurements(Date date, Double weight, Double biceps, Double forearm, Double tight, Double calf, Double chest, Double stomach, Double hips) {
        this.date = date;
        this.weight = weight;
        this.biceps = biceps;
        this.forearm = forearm;
        this.tight = tight;
        this.calf = calf;
        this.chest = chest;
        this.stomach = stomach;
        this.hips = hips;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getBiceps() {
        return biceps;
    }

    public void setBiceps(Double biceps) {
        this.biceps = biceps;
    }

    public Double getForearm() {
        return forearm;
    }

    public void setForearm(Double forearm) {
        this.forearm = forearm;
    }

    public Double getTight() {
        return tight;
    }

    public void setTight(Double tight) {
        this.tight = tight;
    }

    public Double getCalf() {
        return calf;
    }

    public void setCalf(Double calf) {
        this.calf = calf;
    }

    public Double getChest() {
        return chest;
    }

    public void setChest(Double chest) {
        this.chest = chest;
    }

    public Double getStomach() {
        return stomach;
    }

    public void setStomach(Double stomach) {
        this.stomach = stomach;
    }

    public Double getHips() {
        return hips;
    }

    public void setHips(Double hips) {
        this.hips = hips;
    }
}
