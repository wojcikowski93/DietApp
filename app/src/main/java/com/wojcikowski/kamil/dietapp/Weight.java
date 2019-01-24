package com.wojcikowski.kamil.dietapp;

import java.util.Date;

public class Weight {

    Date date;
    Double weight;

    public Weight(Date date, Double weight) {
        this.date = date;
        this.weight = weight;
    }

    public Weight() {
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
}
