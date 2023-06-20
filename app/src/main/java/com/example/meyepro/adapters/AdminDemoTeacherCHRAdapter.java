package com.example.meyepro.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.R;
import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.RecyclerViewAdminSettingDemoCellBinding;
import com.example.meyepro.fragments.Admin.Setting.DemoVideo.AdminSettingDemoVideoTeacherCHRReportActivity;
import com.example.meyepro.fragments.Admin.Setting.TaskDemo.AdminTaskDemoActivity;
import com.example.meyepro.fragments.Admin.Setting.TaskDemo.AdminTaskDemoDetailsShowActivity;
import com.example.meyepro.fragments.Admin.TaskReport.AdminTaskReportDetailsActivity;
import com.example.meyepro.models.TeacherDemoCHR;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminDemoTeacherCHRAdapter extends RecyclerView.Adapter<AdminDemoTeacherCHRAdapter.AdminDemoTeacherCHRViewHolder>{

    private ArrayList<TeacherDemoCHR> List;
    private Context context;
    String ActivtyName="";

    public AdminDemoTeacherCHRAdapter(ArrayList<TeacherDemoCHR> List, Context context) {
        this.List = List;
        this.context = context;

    }
    public AdminDemoTeacherCHRAdapter(ArrayList<TeacherDemoCHR> List, Context context,String ActivityNAme) {
        this.List = List;
        this.context = context;
        this.ActivtyName=ActivityNAme;

    }
    @NonNull
    @Override
    public AdminDemoTeacherCHRViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewAdminSettingDemoCellBinding b  =RecyclerViewAdminSettingDemoCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        AdminDemoTeacherCHRViewHolder vh = new AdminDemoTeacherCHRViewHolder(b);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminDemoTeacherCHRViewHolder holder, int position) {
        TeacherDemoCHR  obj = List.get(position);
        holder.binding.txtViewDemoName.setText(obj.getFile().split("\\.")[0]+"");
        holder.binding.ImageViewThumnail.setImageResource(R.drawable.image_icon);
        if(obj.getThumbnail()!=null){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/demothumbnail/?file="+obj.getThumbnail())).into(holder.binding.ImageViewThumnail);
        }

        holder.binding.lineraLayoutCellClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivtyName.contains("AdminTaskDemoActivity")){
                    Intent i= new Intent(context, AdminTaskDemoDetailsShowActivity.class);
                    i.putExtra("VideoFile", obj.getFile());
                    i.putExtra("Thumbnail", obj.getThumbnail());
                    context.startActivity(i);
                }else {
                    Toast.makeText(context, ""+obj.getFile(), Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(context, AdminSettingDemoVideoTeacherCHRReportActivity.class);
                    i.putExtra("VideoFile", obj.getFile());
                    i.putExtra("Thumbnail", obj.getThumbnail());
                    context.startActivity(i);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    class AdminDemoTeacherCHRViewHolder extends RecyclerView.ViewHolder {
        RecyclerViewAdminSettingDemoCellBinding binding;//<----
        public AdminDemoTeacherCHRViewHolder(
                @NonNull RecyclerViewAdminSettingDemoCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }
}