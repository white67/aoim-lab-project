package com.example.aoim_lab_app.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aoim_lab_app.databinding.FragmentDashboardBinding;
import com.example.aoim_lab_app.model.FavoritesManager;
import com.example.aoim_lab_app.model.Restaurant;
import com.example.aoim_lab_app.ui.home.RestaurantAdapter;

import java.util.List;

import com.example.aoim_lab_app.R;
import com.example.aoim_lab_app.ui.home.RestaurantDetailFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private RecyclerView favoritesRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        favoritesRecyclerView = root.findViewById(R.id.favorites_recycler_view);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Restaurant> favorites = FavoritesManager.getFavorites();
        if (favorites.isEmpty()) {
            TextView noFavoritesText = root.findViewById(R.id.no_favorites_text);
            noFavoritesText.setVisibility(View.VISIBLE);
        } else {
            favoritesRecyclerView.setAdapter(new RestaurantAdapter(favorites, restaurant -> {
                // Handle clicking on a favorite restaurant, e.g., open RestaurantDetailFragment
                openRestaurantDetailFragment(restaurant);
            }));
        }

        return root;
    }

    private void openRestaurantDetailFragment(Restaurant restaurant) {
        View rootView = getView();
        if (rootView != null) {
            rootView.setVisibility(View.INVISIBLE); // Ukryj HomeFragment
        }

        // Begin the fragment transaction
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        // Create the fragment instance and pass the selected restaurant as an argument
        RestaurantDetailFragment detailFragment = RestaurantDetailFragment.newInstance(restaurant);

        // Replace the current fragment (DashboardFragment) with the RestaurantDetailFragment
        // and add the transaction to the back stack
        transaction.replace(R.id.nav_host_fragment_activity_main, detailFragment);
        transaction.addToBackStack(null);  // This allows the user to go back to the previous fragment (DashboardFragment)
        transaction.commit();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}