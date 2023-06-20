package com.example.meyepro.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.DirectorDashBoard.Home.DirectorRecyclerViewCalingHomeFragment;
import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.AdminViewDvrCellBinding;
import com.example.meyepro.databinding.AdminViewVenueCellBinding;
import com.example.meyepro.databinding.RecyclerviewTaskReportShowCellBinding;
import com.example.meyepro.fragments.Admin.TaskReport.AdminTaskReportActivity;
import com.example.meyepro.fragments.Admin.TaskReport.AdminTaskReportDetailsActivity;
import com.example.meyepro.models.MEYE_USER;
import com.example.meyepro.models.Student;
import com.example.meyepro.models.TaskReport;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TaskReportShowAdapter extends RecyclerView.Adapter<TaskReportShowAdapter.TaskReportShowViewHolder>{
    private ArrayList<TaskReport> List;
    private Context context;
    String ActivityName="";
    public TaskReportShowAdapter(ArrayList<TaskReport> List, Context context) {
        this.List = List;
        this.context = context;
    }
    public TaskReportShowAdapter(ArrayList<TaskReport> List, Context context, String ActivityName) {
        this.List = List;
        this.context = context;
        this.ActivityName=ActivityName;
    }
    @NonNull
    @Override
    public TaskReportShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewTaskReportShowCellBinding b  =RecyclerviewTaskReportShowCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        TaskReportShowViewHolder vh = new TaskReportShowViewHolder(b);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskReportShowViewHolder holder, int position) {
        TaskReport obj =  List.get(position);

//       if(obj.getShown()){
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

           holder.binding.lineraLayoutCellClick.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if(ActivityName.contains("TeacherTaskReportFragment")){
                       Intent i = new Intent(context, AdminTaskReportDetailsActivity.class);
                       i.putExtra("key", new Gson().toJson(obj));
                       context.startActivity(i);
                   }else {
                       AdminTaskReportActivity adminTaskReportActivity= (AdminTaskReportActivity) context;
                       adminTaskReportActivity.recyclerViewClickCell(obj,context);
                   }



               }
           });
//       }else {
//           holder.binding.lineraLayoutHide.setVisibility(View.GONE);
//       }
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    class TaskReportShowViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewTaskReportShowCellBinding binding;//<----
        public TaskReportShowViewHolder(
                @NonNull RecyclerviewTaskReportShowCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }
}
