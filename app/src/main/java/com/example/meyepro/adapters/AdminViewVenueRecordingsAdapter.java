package com.example.meyepro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.databinding.AdminViewVenueRecordingsCellBinding;
import com.example.meyepro.fragments.Admin.view.TabView.DVR.AdminViewTabDVRFragment;
import com.example.meyepro.fragments.Admin.view.Teacher.Teacher.AdminViewTabTecherRecodingsFragment;
import com.example.meyepro.models.RECORDINGS;
import com.example.meyepro.models.Venue;
import com.example.meyepro.models.recordings_details_by_teachername;

import java.util.ArrayList;

public class AdminViewVenueRecordingsAdapter extends RecyclerView.Adapter<AdminViewVenueRecordingsAdapter.AdminViewVenueRecordingsViewHolder>{

    private  Fragment fragment;
    private ArrayList<recordings_details_by_teachername> RecordingList;
    private Context context;
//    private ArrayList<RECORDINGS> RecordingList;

    public AdminViewVenueRecordingsAdapter(ArrayList<recordings_details_by_teachername> RecordingList, Context context) {
        this.RecordingList = RecordingList;
        this.context = context;
    }
    public AdminViewVenueRecordingsAdapter (ArrayList<recordings_details_by_teachername> RecordingList, Context context, Fragment fragment ) {
        this.RecordingList= RecordingList;
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
        recordings_details_by_teachername obj = RecordingList.get(position);
        holder.binding.textViewTeacherRecordingsDetailsDate.setText(obj.getDate()+"");
        holder.binding.textViewTeacherRecordingsDetailsCourseName.setText("Course Name "+obj.getCourseName()+"");
        holder.binding.textViewTeacherRecordingsDetailsSection.setText("Section "+obj.getDiscipline()+"");
        holder.binding.textViewTeacherRecordingsDetailsStartRecording.setText(obj.getFileName().split(",")[2]+"");

        holder.binding.textViewTeacherRecordingsDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminViewTabTecherRecodingsFragment AdminViewTabDVR = (AdminViewTabTecherRecodingsFragment) fragment;
                AdminViewTabDVR.recyclerviewAdminViewTabTecherRecodingsCellClick(obj,context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return RecordingList.size();
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

