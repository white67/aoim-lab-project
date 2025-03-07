package com.example.aoim_lab_app.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aoim_lab_app.R;
import com.example.aoim_lab_app.model.Restaurant;
import com.example.aoim_lab_app.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements RestaurantAdapter.OnRestaurantClickListener {

    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private HomeViewModel homeViewModel; // Declare HomeViewModel
    private List<Restaurant> restaurantList = new ArrayList<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = rootView.findViewById(R.id.rvRestaurants);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get the HomeViewModel instance
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // Clear the restaurant list before adding items to avoid duplicates
        restaurantList.clear();

        // Populate restaurant list
        // Observe the restaurant list from ViewModel
        homeViewModel.getRestaurants().observe(getViewLifecycleOwner(), restaurants -> {
            // When the list of restaurants changes, update the adapter
            if (restaurants != null) {
                adapter = new RestaurantAdapter(restaurants, this);
                recyclerView.setAdapter(adapter);
            }
        });

        return rootView;
    }

    @Override
    public void onRestaurantClick(Restaurant restaurant) {
        View rootView = getView();
        if (rootView != null) {
            rootView.setVisibility(View.INVISIBLE); // Ukryj HomeFragment
        }

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        RestaurantDetailFragment detailFragment = RestaurantDetailFragment.newInstance(restaurant);

        transaction.replace(R.id.nav_host_fragment_activity_main, detailFragment);
        transaction.addToBackStack(null); // Dodaj fragment do stosu powrotu, aby móc wrócić
        transaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}