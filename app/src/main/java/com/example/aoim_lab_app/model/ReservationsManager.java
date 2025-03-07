package com.example.aoim_lab_app.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationsManager {
    private static final List<Reservation> reservations = new ArrayList<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // Customize the format as needed

    public static void addReservation(Restaurant restaurant, Date date) {
        String formattedDate = dateFormat.format(date); // Convert Date to String
        reservations.add(new Reservation(restaurant, formattedDate));
    }

    public static List<Reservation> getReservations() {
        return reservations;
    }
}