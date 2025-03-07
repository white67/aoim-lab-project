package com.example.aoim_lab_app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.aoim_lab_app.R;
import com.example.aoim_lab_app.model.FavoritesManager;
import com.example.aoim_lab_app.model.ReservationsManager;
import com.example.aoim_lab_app.model.Restaurant;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.Toast;

import java.util.Calendar;

public class RestaurantDetailFragment extends Fragment {

    private TextView name, address, rating, description;
    private ImageButton favoriteButton;
    private Button reserveButton, backButton;
    private Restaurant selectedRestaurant;

    private View rootView;

    public RestaurantDetailFragment() {
        // Required empty public constructor
    }

    public static RestaurantDetailFragment newInstance(Restaurant restaurant) {
        RestaurantDetailFragment fragment = new RestaurantDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("restaurant", restaurant);  // Pass the restaurant object
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_restaurant_detail, container, false);

        if (getArguments() != null) {
            selectedRestaurant = getArguments().getParcelable("restaurant");
        }

        ImageView image = rootView.findViewById(R.id.restaurant_image_detail);
        if (selectedRestaurant != null) {
            // Ustaw obraz restauracji
            image.setImageResource(selectedRestaurant.getImageResId());
        }

        name = rootView.findViewById(R.id.restaurant_name);
        address = rootView.findViewById(R.id.restaurant_address);
        rating = rootView.findViewById(R.id.restaurant_rating);
        description = rootView.findViewById(R.id.restaurant_description);
        favoriteButton = rootView.findViewById(R.id.favorite_button);
        reserveButton = rootView.findViewById(R.id.reserve_button);
        backButton = rootView.findViewById(R.id.back_button);

        // Populate the views with restaurant details
        name.setText(selectedRestaurant.getName());
        address.setText(selectedRestaurant.getAddress());
        rating.setText(String.valueOf(selectedRestaurant.getRating()));
        description.setText("Details about " + selectedRestaurant.getName());

        // Set initial state of the favorite button
        updateFavoriteButton();

        // Handle favorite button click
        favoriteButton.setOnClickListener(v -> {
            if (FavoritesManager.isFavorite(selectedRestaurant)) {
                FavoritesManager.removeFavorite(selectedRestaurant);
                Toast.makeText(getContext(), "Removed from Favorites", Toast.LENGTH_SHORT).show();
            } else {
                FavoritesManager.addFavorite(selectedRestaurant);
                Toast.makeText(getContext(), "Added to Favorites", Toast.LENGTH_SHORT).show();
            }
            updateFavoriteButton();
        });

        reserveButton.setOnClickListener(v -> {
            // Show date and time picker
            showDateTimePicker();
        });

        backButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .remove(RestaurantDetailFragment.this) // Removes the current fragment
                    .replace(R.id.nav_host_fragment_activity_main, new HomeFragment()) // Replaces with HomeFragment
                    .commit();
        });

        return rootView;
    }

    private void updateFavoriteButton() {
        // Update the button's selected state based on whether the restaurant is a favorite
        boolean isFavorite = FavoritesManager.isFavorite(selectedRestaurant);
        favoriteButton.setSelected(isFavorite);
    }

    private void showDateTimePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePicker = new DatePickerDialog(getContext(),
                (view, year, month, dayOfMonth) -> {
                    TimePickerDialog timePicker = new TimePickerDialog(getContext(),
                            (timeView, hourOfDay, minute) -> {
                                calendar.set(year, month, dayOfMonth, hourOfDay, minute);
                                ReservationsManager.addReservation(selectedRestaurant, calendar.getTime());
                                Toast.makeText(getContext(), "Reservation Made", Toast.LENGTH_SHORT).show();
                            },
                            calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                    timePicker.show();
                },
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.show();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Handle back press to show HomeFragment again
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
                navController.popBackStack();  // Use NavController to pop back the stack
            }
        });
    }

    // Setter to receive the rootView
    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Now, update the BottomNavigationView to select the "Home" item
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);  // Assuming `navigation_home` is the ID for the Home tab


        View rootView = getActivity().findViewById(R.id.nav_host_fragment_activity_main);
        // Restore visibility of HomeFragment when this fragment is destroyed
        if (rootView != null) {
            rootView.setVisibility(View.VISIBLE);
        }
    }
}