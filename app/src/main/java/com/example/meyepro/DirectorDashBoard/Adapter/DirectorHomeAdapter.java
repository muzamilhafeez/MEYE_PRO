package com.example.meyepro.DirectorDashBoard.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.DirectorDashBoard.Home.DirectorDashBoardHomeFragment;
import com.example.meyepro.DirectorDashBoard.Home.DirectorHomeViewCHRFragment;
import com.example.meyepro.DirectorDashBoard.Home.DirectorRecyclerViewCalingHomeFragment;
import com.example.meyepro.DirectorDashBoard.Model.ScheduleDetailsAndCHR;
import com.example.meyepro.StudentDashBoard.Course.StudentCourseFragment;
import com.example.meyepro.StudentDashBoard.Model.StudentCourse;
import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.RecyclerviewDirectorHomeCellBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DirectorHomeAdapter extends RecyclerView.Adapter<DirectorHomeAdapter.DirectorHomeViewHolder>{
    private ArrayList<ScheduleDetailsAndCHR> List;
    private Context context;
    Fragment fragment;

    public DirectorHomeAdapter(ArrayList<ScheduleDetailsAndCHR > List, Context context) {
        this.List = List;
        this.context = context;


    }
    public DirectorHomeAdapter(ArrayList<ScheduleDetailsAndCHR> List, Context context, Fragment fragment) {
        this.List = List;
        this.context = context;
        this.fragment =fragment;
    }
    @NonNull
    @Override
    public DirectorHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewDirectorHomeCellBinding b  =RecyclerviewDirectorHomeCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        DirectorHomeViewHolder vh = new DirectorHomeViewHolder(b);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull DirectorHomeViewHolder holder, int position) {
        ScheduleDetailsAndCHR  obj = List.get(position);
        holder.binding.txtCourse.setText(obj.getCourseName()+"");
        holder.binding.txtViewTeacherName.setText(obj.getTeacherName()+"");
        holder.binding.txtDate.setText(obj.getDate()+"");
        holder.binding.txtDiscipline.setText(obj.getDiscipline()+"");
        holder.binding.txtViewStatus.setText(obj.getStatus()+"");

        if(!obj.getImage().isEmpty()){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+"Teacher"+"/"+obj.getImage())).
                    resize(100, 100) // Set the desired target size
                    .into(holder.binding.profileImageTeacher);

        }
        holder.binding.lineraLayoutDirectorHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DirectorRecyclerViewCalingHomeFragment DirectorHome = (DirectorRecyclerViewCalingHomeFragment) fragment;
                DirectorHome.recyclerviewDirectorDashBoarCellClick(obj,fragment);
            }
        });

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    class DirectorHomeViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewDirectorHomeCellBinding binding;//<----
        public DirectorHomeViewHolder(
                @NonNull RecyclerviewDirectorHomeCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }

    public void filterList(ArrayList<ScheduleDetailsAndCHR> filteredList) {
        List = filteredList;
        notifyDataSetChanged();
    }
}
