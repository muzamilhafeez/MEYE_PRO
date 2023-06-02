package com.example.meyepro.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meyepro.R;
import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.AdminViewTeacherCellBinding;
import com.example.meyepro.fragments.Admin.AdminScheduleFragment;
import com.example.meyepro.fragments.Admin.Setting.RuleSetting.AdminSettingRuleSettingClickFragment;
import com.example.meyepro.fragments.Admin.view.TabView.Venue.AdminViewTabVenueFragment;
import com.example.meyepro.fragments.Admin.view.Teacher.ViewTeacherFragment;
import com.example.meyepro.models.MEYE_USER;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println("teacher show"+this.TeacherList);
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
        holder.binding.textViewVenue1.setText(obj.getName()+"");
        holder.Bindmage(obj );

        //Picasso.get().load(Uri.parse("http://192.168.0.241:8000/api/get-user-image/UserImages/Teacher/image_picker1231572622.jpg")).into(holder.binding.profileImageTeacher);
        //calling venue
        if(FromCaliingOnPageName=="AdminViewTabVenueFragment"){
            holder.binding.TeacherCellClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   AdminViewTabVenueFragment AdminHome = (AdminViewTabVenueFragment) fragment;
                   AdminHome.recyclerviewAdminViewCellClick(obj,context);
                }
            });
        }//calling Teacher Side
        else if(FromCaliingOnPageName=="ViewTeacherFragment"){
           // Toast.makeText(context, "recy"+obj.getRole(), Toast.LENGTH_SHORT).show();
            holder.binding.TeacherCellClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewTeacherFragment AdminHome = (ViewTeacherFragment) fragment;
                    AdminHome.recyclerviewAdminViewTeacherCellClick(obj,context);
                }
            });
        }
        //calling Setting Side
        else if(FromCaliingOnPageName=="AdminSettingRuleSettingClickFragment"){
            holder.binding.TeacherCellClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AdminSettingRuleSettingClickFragment AdminSetting = (AdminSettingRuleSettingClickFragment) fragment;
                    AdminSetting.recyclerviewAdminSettingRuleCellClick(obj,context);
                }
            });
        }
        //calling Setting Side
        else if(FromCaliingOnPageName=="AdminScheduleFragment"){
            holder.binding.TeacherCellClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AdminScheduleFragment AdminSchedule = (AdminScheduleFragment) fragment;
                    AdminSchedule.recyclerviewAdminViewTeacherCellClick(obj,context);
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
        public  void Bindmage(MEYE_USER obj ){
            binding.profileImageTeacher.setImageResource(R.drawable.image_icon);


            if(obj.getImage()!=null){

                Picasso.get()
                        .load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+obj.getRole()+"/"+obj.getImage()))// Replace with your placeholder image
                        .error(R.drawable.person_search_icon) // Replace with your error image
                        .into(binding.profileImageTeacher);
//            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+obj.getRole()+"/"+obj.getImage())).into(holder.binding.profileImageTeacher);
            }
        }
    }
    public void filterList(ArrayList<MEYE_USER> filteredList) {
        TeacherList = filteredList;
        notifyDataSetChanged();
    }
}
