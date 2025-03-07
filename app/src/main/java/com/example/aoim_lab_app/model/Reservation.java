package com.example.aoim_lab_app.model;

import java.util.Date;
import com.example.aoim_lab_app.model.Restaurant;

public class Reservation {
    private final Restaurant restaurant;
    private final String dateTime;

    public Reservation(Restaurant restaurant, String dateTime) {
        this.restaurant = restaurant;
        this.dateTime = dateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public String getDateTime() {
        return dateTime;
    }
}