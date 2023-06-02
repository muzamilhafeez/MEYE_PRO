package com.example.meyepro.TeacherDashBoard.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.databinding.RecyclerviewTeacherNotificationCellBinding;

import java.util.ArrayList;

public class TeacherNotificationAdapter extends RecyclerView.Adapter<TeacherNotificationAdapter.TeacherNotificationViewHolder>{
    private ArrayList<String> VenueList;
    private Context context;

    public TeacherNotificationAdapter(ArrayList<String> VenueList, Context context) {
        this.VenueList = VenueList;
        this.context = context;

    }
    @NonNull
    @Override
    public TeacherNotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewTeacherNotificationCellBinding b  =RecyclerviewTeacherNotificationCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        TeacherNotificationViewHolder vh = new TeacherNotificationViewHolder(b);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherNotificationViewHolder holder, int position) {
        String obj = VenueList.get(position);
        holder.binding.txtViewTeacherNotificationName.setText(obj);

    }

    @Override
    public int getItemCount() {
        return VenueList.size();
    }

    class TeacherNotificationViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewTeacherNotificationCellBinding binding;//<----
        public TeacherNotificationViewHolder(
                @NonNull RecyclerviewTeacherNotificationCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }
}