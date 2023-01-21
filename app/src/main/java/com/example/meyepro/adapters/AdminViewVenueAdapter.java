package com.example.meyepro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.databinding.AdminHomeVenueCellBinding;
import com.example.meyepro.databinding.AdminViewVenueCellBinding;
import com.example.meyepro.models.Venue;

import java.util.ArrayList;

public class AdminViewVenueAdapter extends RecyclerView.Adapter<AdminViewVenueAdapter.AdminViewVenueViewHolder>{
    private Fragment fragment;
    private ArrayList<Venue> VenueList;
    private Context context;

    public AdminViewVenueAdapter(ArrayList<Venue> VenueList, Context context) {
        this.VenueList = VenueList;
        this.context = context;
    }
    public AdminViewVenueAdapter (ArrayList<Venue> VenueList, Context context, Fragment fragment ) {
        this.VenueList = VenueList;
        this.context = context;
        this.fragment=fragment;
    }

    @NonNull
    @Override
    public AdminViewVenueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdminViewVenueCellBinding b  =AdminViewVenueCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        AdminViewVenueViewHolder vh = new AdminViewVenueViewHolder(b);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewVenueViewHolder holder, int position) {
        Venue obj = VenueList.get(position);
        holder.binding.textViewVenue1.setText(obj.getNAME()+"");

    }

    @Override
    public int getItemCount() {
        return VenueList.size();
    }

    class AdminViewVenueViewHolder extends RecyclerView.ViewHolder {
        AdminViewVenueCellBinding binding;//<----
        public AdminViewVenueViewHolder(
                @NonNull AdminViewVenueCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }
}

