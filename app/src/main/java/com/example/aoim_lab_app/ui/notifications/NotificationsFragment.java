package com.example.aoim_lab_app.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aoim_lab_app.R;
import com.example.aoim_lab_app.databinding.FragmentNotificationsBinding;
import com.example.aoim_lab_app.model.Reservation;
import com.example.aoim_lab_app.model.ReservationsManager;

import java.util.List;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private RecyclerView reservationsRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        reservationsRecyclerView = root.findViewById(R.id.reservations_recycler_view);
        reservationsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Reservation> reservations = ReservationsManager.getReservations();
        if (reservations.isEmpty()) {
            TextView noReservationsText = root.findViewById(R.id.no_reservations_text);
            noReservationsText.setVisibility(View.VISIBLE);
        } else {
            reservationsRecyclerView.setAdapter(new ReservationAdapter(reservations));
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}