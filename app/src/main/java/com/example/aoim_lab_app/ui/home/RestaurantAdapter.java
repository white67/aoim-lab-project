package com.example.aoim_lab_app.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aoim_lab_app.R;
import com.example.aoim_lab_app.model.Restaurant;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurantList;
    private OnRestaurantClickListener onRestaurantClickListener;

    public RestaurantAdapter(List<Restaurant> restaurantList, OnRestaurantClickListener onRestaurantClickListener) {
        this.restaurantList = restaurantList;
        this.onRestaurantClickListener = onRestaurantClickListener;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.name.setText(restaurant.getName());
        holder.address.setText(restaurant.getAddress());
        holder.rating.setText(String.valueOf(restaurant.getRating()));

        // Set click listener for the entire item
        holder.itemView.setOnClickListener(v -> onRestaurantClickListener.onRestaurantClick(restaurant));

        holder.image.setImageResource(restaurant.getImageResId()); // Ensure this line points to the correct ImageView
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        TextView name, address, rating;
        ImageView image;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvRestaurantName);
            address = itemView.findViewById(R.id.tvRestaurantAddress);
            rating = itemView.findViewById(R.id.tvRestaurantRating);
            image = itemView.findViewById(R.id.restaurant_image);
        }
    }

    // Interface for handling click events
    public interface OnRestaurantClickListener {
        void onRestaurantClick(Restaurant restaurant);
    }
}