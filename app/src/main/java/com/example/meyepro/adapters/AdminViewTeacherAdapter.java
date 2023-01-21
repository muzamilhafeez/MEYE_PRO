package com.example.meyepro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.databinding.AdminViewTeacherCellBinding;
import com.example.meyepro.fragments.Admin.Setting.AdminSettingRuleSettingClickFragment;
import com.example.meyepro.fragments.Admin.view.TabView.Venue.AdminViewTabVenueFragment;
import com.example.meyepro.fragments.Admin.view.Teacher.ViewTeacherFragment;
import com.example.meyepro.models.MEYE_USER;

import java.util.ArrayList;

public class AdminViewTeacherAdapter extends RecyclerView.Adapter<AdminViewTeacherAdapter.AdminViewTeacherViewHolder>{
    private ArrayList<MEYE_USER> TeacherList;
    private Context context;
    private String FromCaliingOnPageName="";
    private Fragment fragment;

    public AdminViewTeacherAdapter(ArrayList<MEYE_USER> TeacherList, Context context) {
        this.TeacherList = TeacherList;
        this.context = context;
    }
    public AdminViewTeacherAdapter(ArrayList<MEYE_USER> TeacherList, Context context,Fragment fragment) {
        this.TeacherList = TeacherList;
        this.context = context;
        this.fragment=fragment;
    }
    public AdminViewTeacherAdapter(ArrayList<MEYE_USER> TeacherList, Context context, Fragment fragment,String FromCaliingOnPageName) {
        this.TeacherList = TeacherList;
        this.context = context;
        this.fragment=fragment;
        this.FromCaliingOnPageName=FromCaliingOnPageName;
    }
    @NonNull
    @Override
    public AdminViewTeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdminViewTeacherCellBinding  b=AdminViewTeacherCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        AdminViewTeacherViewHolder vh = new AdminViewTeacherViewHolder(b);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewTeacherViewHolder holder, int position) {
        MEYE_USER obj =  TeacherList.get(position);
        holder.binding.textViewVenue1.setText(obj.getNAME()+"");

        //calling venue
        if(FromCaliingOnPageName=="AdminViewTabVenueFragment"){
            holder.binding.textViewVenue1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   AdminViewTabVenueFragment AdminHome = (AdminViewTabVenueFragment) fragment;
                   AdminHome.recyclerviewAdminViewCellClick(obj,context);
                }
            });
        }//calling Teacher Side
        else if(FromCaliingOnPageName=="ViewTeacherFragment"){
            holder.binding.textViewVenue1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewTeacherFragment AdminHome = (ViewTeacherFragment) fragment;
                    AdminHome.recyclerviewAdminViewTeacherCellClick(obj,context);
                }
            });
        }
        //calling Setting Side
        else if(FromCaliingOnPageName=="ViewTeacherFragment"){
            holder.binding.textViewVenue1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AdminSettingRuleSettingClickFragment AdminSetting = (AdminSettingRuleSettingClickFragment) fragment;
                    AdminSetting.recyclerviewAdminSettingRuleCellClick(obj,context);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return TeacherList.size();
    }

    class AdminViewTeacherViewHolder extends RecyclerView.ViewHolder {
        AdminViewTeacherCellBinding binding;//<----
        public AdminViewTeacherViewHolder(
                @NonNull AdminViewTeacherCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }

}
