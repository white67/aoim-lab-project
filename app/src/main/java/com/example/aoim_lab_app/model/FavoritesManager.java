package com.example.aoim_lab_app.model;

import java.util.ArrayList;
import java.util.List;

public class FavoritesManager {
    private static final List<Restaurant> favoriteRestaurants = new ArrayList<>();

    public static void addFavorite(Restaurant restaurant) {
        if (!favoriteRestaurants.contains(restaurant)) {
            favoriteRestaurants.add(restaurant);
        }
    }

    public static List<Restaurant> getFavorites() {
        return favoriteRestaurants;
    }

    public static void removeFavorite(Restaurant restaurant) {
        favoriteRestaurants.remove(restaurant);
    }

    public static boolean isFavorite(Restaurant restaurant) {
        return favoriteRestaurants.contains(restaurant);
    }
}
