package com.example.meyepro.StudentDashBoard.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.StudentDashBoard.Course.StudentCourseFragment;
import com.example.meyepro.StudentDashBoard.Model.StudentCourse;
import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.RecyclerviewStudentCourseCellBinding;
import com.example.meyepro.fragments.Admin.view.Teacher.ViewTeacherFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudentCourseAdapter extends RecyclerView.Adapter<StudentCourseAdapter.StudentCourseViewHolder>{
    private ArrayList<StudentCourse> List;
    private Context context;
    private Fragment fragment;


    public StudentCourseAdapter(ArrayList<StudentCourse> List, Context context) {
        this.List = List;
        this.context = context;

    }
    public StudentCourseAdapter(ArrayList<StudentCourse> List, Context context,Fragment fragment) {
        this.List = List;
        this.context = context;
        this.fragment =fragment;
    }
    @NonNull
    @Override
    public StudentCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewStudentCourseCellBinding b  =RecyclerviewStudentCourseCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        StudentCourseViewHolder vh = new StudentCourseViewHolder(b);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentCourseViewHolder holder, int position) {
        StudentCourse obj = List.get(position);
        holder.binding.txtTeacherName.setText(obj.getTeacherName()+"");
        holder.binding.txtViewCourseName.setText(obj.getCourseName()+"");
        holder.binding.txtViewPercentage.setText(obj.getPercentage()+"");


        if(!obj.getImage().isEmpty()){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+"Teacher"+"/"+obj.getImage())).into(holder.binding.profileImageTeacher);
        }
        holder.binding.lineraLayoutStudentCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentCourseFragment StudentHome = (StudentCourseFragment) fragment;
                StudentHome.recyclerviewStudentCourseCellClick(obj,fragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    class StudentCourseViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewStudentCourseCellBinding binding;//<----
        public StudentCourseViewHolder(
                @NonNull RecyclerviewStudentCourseCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }
}