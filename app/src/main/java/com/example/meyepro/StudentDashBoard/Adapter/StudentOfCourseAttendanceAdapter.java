package com.example.meyepro.StudentDashBoard.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.StudentDashBoard.Model.StudentOFCourseAttendance;
import com.example.meyepro.databinding.RecyclerviewStudentOfCourseAttendanceCellBinding;

import java.util.ArrayList;

public class StudentOfCourseAttendanceAdapter extends RecyclerView.Adapter<StudentOfCourseAttendanceAdapter.StudentOfCourseAttendanceViewHolder>{
    private ArrayList<StudentOFCourseAttendance> ListCourse;
    private Context context;

    public StudentOfCourseAttendanceAdapter(ArrayList<StudentOFCourseAttendance> List, Context context) {
        this.ListCourse = List;
        this.context = context;

    }
    @NonNull
    @Override
    public StudentOfCourseAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewStudentOfCourseAttendanceCellBinding b  =RecyclerviewStudentOfCourseAttendanceCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        StudentOfCourseAttendanceViewHolder vh = new StudentOfCourseAttendanceViewHolder(b);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentOfCourseAttendanceViewHolder holder, int position) {
        StudentOFCourseAttendance obj = ListCourse.get(position);
        holder.binding.txtViewStudentDateCell.setText(obj.getDate()+"");
        if(Boolean.parseBoolean(obj.getStatus())){
            holder.binding.txtViewStudentStatusCell.setText("P");
        }else{
            holder.binding.txtViewStudentStatusCell.setText("A");
        }

    }

    @Override
    public int getItemCount() {
        return ListCourse.size();
    }

    class StudentOfCourseAttendanceViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewStudentOfCourseAttendanceCellBinding binding;//<----
        public StudentOfCourseAttendanceViewHolder(
                @NonNull RecyclerviewStudentOfCourseAttendanceCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }
}