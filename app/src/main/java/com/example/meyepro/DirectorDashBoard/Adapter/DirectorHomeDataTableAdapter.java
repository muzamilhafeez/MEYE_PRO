package com.example.meyepro.DirectorDashBoard.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.DirectorDashBoard.Home.DirectorDashBoardHomeFragment;
import com.example.meyepro.DirectorDashBoard.Model.ScheduleDetailsAndCHR;
import com.example.meyepro.DirectorDashBoard.Model.TeacherCHRActivityDetails;
import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.RecyclerviewDirectorHomeCellBinding;
import com.example.meyepro.databinding.RecyclerviewDirectorHomeDataTableCellBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DirectorHomeDataTableAdapter extends RecyclerView.Adapter<DirectorHomeDataTableAdapter.DirectorHomeDataTableViewHolder>{
    private ArrayList<ScheduleDetailsAndCHR> List;
    private Context context;
    Fragment fragment;

    public DirectorHomeDataTableAdapter(ArrayList<ScheduleDetailsAndCHR > List, Context context) {
        this.List = List;
        this.context = context;


    }
    public DirectorHomeDataTableAdapter(ArrayList<ScheduleDetailsAndCHR> List, Context context, Fragment fragment) {
        this.List = List;
        this.context = context;
        this.fragment =fragment;
    }
    @NonNull
    @Override
    public DirectorHomeDataTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewDirectorHomeDataTableCellBinding b  =RecyclerviewDirectorHomeDataTableCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        DirectorHomeDataTableViewHolder vh = new DirectorHomeDataTableViewHolder(b);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull DirectorHomeDataTableViewHolder holder, int position) {
        ScheduleDetailsAndCHR  obj = List.get(position);

        holder.binding.txtViewDirectorStNo.setText(position+1+"");
        holder.binding.txtViewDirectorCourse.setText(obj.getCourseName()+"");
        holder.binding.txtViewDirectorDate.setText(obj.getDate()+"");
        holder.binding.txtViewDirectorTeacher.setText(obj.getTeacherName()+"");
        holder.binding.txtViewDirectorStatus.setText(obj.getStatus()+"");
        holder.binding.txtViewDirectorSit.setText(obj.getSit()+"");
        holder.binding.txtViewDirectorMobile.setText(obj.getMobile()+"");
        holder.binding.txtViewDirectorStrand.setText(obj.getStand()+"");

//        ArrayList<TeacherCHRActivityDetails> teacherCHRActivityDetails= obj.getTeacherCHRActivityDetails();
//        for (TeacherCHRActivityDetails chr:teacherCHRActivityDetails) {
//            holder.binding.txtViewDirectorSit.setText(chr.getSit()+"");
//            holder.binding.txtViewDirectorMobile.setText(chr.getMobile()+"");
//            holder.binding.txtViewDirectorStrand.setText(chr.getStand()+"");
//        }
        if(obj.getStatus().contains("Late")){
            holder.binding.lineraLayoutDatatable.setBackgroundColor(Color.RED);
        }


//
//        if(!obj.getImage().isEmpty()){
//            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+"Teacher"+"/"+obj.getImage())).
//                    resize(100, 100) // Set the desired target size
//                    .into(holder.binding.profileImageTeacher);
//
//        }
//        holder.binding.lineraLayoutDirectorHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DirectorDashBoardHomeFragment DirectorHome = (DirectorDashBoardHomeFragment) fragment;
//                DirectorHome.recyclerviewDirectorDashBoarCellClick(obj,fragment);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void filterList(ArrayList<ScheduleDetailsAndCHR> filteredList) {
      List=  filteredList;
      notifyDataSetChanged();
    }

    class DirectorHomeDataTableViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewDirectorHomeDataTableCellBinding binding;//<----
        public DirectorHomeDataTableViewHolder(
                @NonNull RecyclerviewDirectorHomeDataTableCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }
}
