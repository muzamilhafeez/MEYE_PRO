package com.example.meyepro.TeacherDashBoard.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.DirectorDashBoard.Model.ScheduleDetailsAndCHR;
import com.example.meyepro.TeacherDashBoard.CHR.TeacherCHRFragment;
import com.example.meyepro.TeacherDashBoard.CHR.TeacherSelectTimTableCHRActivity;
import com.example.meyepro.databinding.RecyclerviewTeacherChrCellBinding;
import com.google.gson.Gson;

import java.util.ArrayList;

public class TeacherCHRAdapter extends RecyclerView.Adapter<TeacherCHRAdapter.TeacherCHRViewHolder>{
    private ArrayList<ScheduleDetailsAndCHR> List;
    private Context context;
    Fragment fragment;

    public TeacherCHRAdapter(ArrayList<ScheduleDetailsAndCHR> List, Context context, Fragment fragment) {
        this.List = List;
        this.context = context;
        this.fragment=fragment;

    }
    @NonNull
    @Override
    public TeacherCHRViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewTeacherChrCellBinding b  =RecyclerviewTeacherChrCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        TeacherCHRViewHolder vh = new TeacherCHRViewHolder(b);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherCHRViewHolder holder, int position) {
        ScheduleDetailsAndCHR obj = List.get(position);
        holder.binding.txtViewTeacherCourse.setText(obj.getCourseName()+"");
        holder.binding.txtViewTeacherDate.setText(obj.getDate()+"");
        holder.binding.txtViewTeacherDiscipline.setText(obj.getDiscipline()+"");
        holder.binding.txtViewTeacherStatus.setText(obj.getStatus()+"");
        holder.binding.lineraLayoutTeacherCHR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TeacherCHRFragment  teacherCHRFragment= (TeacherCHRFragment) fragment;
                teacherCHRFragment.RecyclerViewTeacherCHRFragmentCellClick(obj,context);

            }
        });

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    class TeacherCHRViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewTeacherChrCellBinding binding;//<----
        public TeacherCHRViewHolder(
                @NonNull RecyclerviewTeacherChrCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }
}