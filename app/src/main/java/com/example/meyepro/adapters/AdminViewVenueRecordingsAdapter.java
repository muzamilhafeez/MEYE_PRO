package com.example.meyepro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.databinding.AdminViewVenueRecordingsCellBinding;
import com.example.meyepro.models.RECORDINGS;
import com.example.meyepro.models.Venue;

import java.util.ArrayList;

public class AdminViewVenueRecordingsAdapter extends RecyclerView.Adapter<AdminViewVenueRecordingsAdapter.AdminViewVenueRecordingsViewHolder>{

    private  Fragment fragment;
    private ArrayList<RECORDINGS> TeacherList;
    private Context context;

    public AdminViewVenueRecordingsAdapter(ArrayList<RECORDINGS> TeacherList, Context context) {
        this.TeacherList = TeacherList;
        this.context = context;
    }
    public AdminViewVenueRecordingsAdapter (ArrayList<RECORDINGS> TeacherList, Context context, Fragment fragment ) {
        this.TeacherList = TeacherList;
        this.context = context;
        this.fragment=fragment;
    }
        @NonNull
    @Override
    public AdminViewVenueRecordingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            AdminViewVenueRecordingsCellBinding b  =AdminViewVenueRecordingsCellBinding.
                    inflate(LayoutInflater
                                    .from(parent.getContext()),
                            parent, false);

            AdminViewVenueRecordingsViewHolder vh = new AdminViewVenueRecordingsViewHolder(b);
            return vh;

        }

    @Override
    public void onBindViewHolder(@NonNull AdminViewVenueRecordingsViewHolder holder, int position) {
        RECORDINGS obj = TeacherList.get(position);
        holder.binding.textViewTeacherRecordingsDetails.setText(obj.getFILENAME()+"");

    }

    @Override
    public int getItemCount() {
        return TeacherList.size();
    }

    class AdminViewVenueRecordingsViewHolder extends RecyclerView.ViewHolder {
    AdminViewVenueRecordingsCellBinding binding;//<----
    public AdminViewVenueRecordingsViewHolder(
            @NonNull AdminViewVenueRecordingsCellBinding itemView) {
        super(itemView.getRoot());
        binding = itemView;//<----
    }
}
}

