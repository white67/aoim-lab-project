package com.example.aoim_lab_app.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.aoim_lab_app.R;
import com.example.aoim_lab_app.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<Restaurant>> restaurants;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

        // Initialize the list of restaurants
        restaurants = new MutableLiveData<>();
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant("Koku Sushi", "ul. Wrocławska 33", 4.4, R.drawable.koku_sushi));
        restaurantList.add(new Restaurant("Kyoto Sushi", "ul. Wrocławska 11A", 4.7, R.drawable.kyoto_sushi));
        restaurantList.add(new Restaurant("Ali Baba Król Kebaba", "Czarnowiejska 84", 3.9, R.drawable.alibaba));
        restaurantList.add(new Restaurant("Kebab U Majstra", "Al 29 Listopada 63", 4.5, R.drawable.kebab_u_majstra));
        restaurantList.add(new Restaurant("Cezar Kebab", "Plac Inwalidów B/N", 4.2, R.drawable.cezar_kebab));
        restaurantList.add(new Restaurant("Steakhouse Supreme", "101 High St", 4.7, R.drawable.random1));
        restaurantList.add(new Restaurant("Curry Corner", "202 Spice Ave", 4.4, R.drawable.random2));
        restaurantList.add(new Restaurant("Noodle Nook", "303 Ramen Blvd", 4.6, R.drawable.random3));
        restaurantList.add(new Restaurant("Bagel Bazaar", "404 Breakfast Ln", 4.3, R.drawable.random4));
        restaurantList.add(new Restaurant("Grill & Chill", "505 Sunset Dr", 4.0, R.drawable.random5));
        restaurantList.add(new Restaurant("Seafood Shack", "606 Harbor Rd", 4.2, R.drawable.random6));
        restaurantList.add(new Restaurant("Dessert Dreams", "707 Sweet St", 4.9, R.drawable.random7));

        restaurants.setValue(restaurantList);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Restaurant>> getRestaurants() {
        return restaurants;
    }
}