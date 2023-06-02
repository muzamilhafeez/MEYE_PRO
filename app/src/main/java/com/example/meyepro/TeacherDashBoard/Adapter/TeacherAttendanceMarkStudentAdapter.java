package com.example.meyepro.TeacherDashBoard.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.TeacherDashBoard.Attendance.TeacherAttendanceMarkActivity;
import com.example.meyepro.databinding.RecyclerviewTeacherAttendanceMarkCellBinding;
import com.example.meyepro.models.Attendance;

import java.util.ArrayList;

public class TeacherAttendanceMarkStudentAdapter extends RecyclerView.Adapter<TeacherAttendanceMarkStudentAdapter.TeacherAttendanceMarkStudentViewHolder>{
    private ArrayList<Attendance> List;
    private Context context;

    public TeacherAttendanceMarkStudentAdapter(ArrayList<Attendance> List, Context context) {
        this.List = List;
        this.context = context;

    }
    @NonNull
    @Override
    public TeacherAttendanceMarkStudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewTeacherAttendanceMarkCellBinding b  =RecyclerviewTeacherAttendanceMarkCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);
        TeacherAttendanceMarkStudentViewHolder vh = new TeacherAttendanceMarkStudentViewHolder(b);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherAttendanceMarkStudentViewHolder holder, int position) {
        Attendance obj = List.get(position);
        holder.binding.txtViewName.setText(obj.getName()+"");
        if(obj.isStatus()){
            holder.binding.txtViewStatus.setText("P");
        }else {
            holder.binding.txtViewStatus.setText("A");
        }
        holder.binding.txtViewStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TeacherAttendanceMarkActivity teacherAttendanceMarkActivity= (TeacherAttendanceMarkActivity) context;
                teacherAttendanceMarkActivity.RecyclerViewAttendanceChangeStatus(obj,context);

//                Toast.makeText(context, "pp", Toast.LENGTH_SHORT).show();
//                if(!obj.isStatus()){
//                    holder.binding.txtViewStatus.setText("P");
//                }else {
//                    holder.binding.txtViewStatus.setText("A");
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    class TeacherAttendanceMarkStudentViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewTeacherAttendanceMarkCellBinding binding;//<----
        public TeacherAttendanceMarkStudentViewHolder(
                @NonNull RecyclerviewTeacherAttendanceMarkCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }
    public void UpdateList(ArrayList<Attendance> attendancesList){
        List=attendancesList;
        notifyDataSetChanged();
    }
}