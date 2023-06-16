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

import com.example.meyepro.TeacherDashBoard.CHR.TeacherClaimVideoPlayActivity;
import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.RecyclerviewCliamVideoCellLayoutBinding;
import com.example.meyepro.fragments.Admin.Setting.DemoVideo.AdminSettingDemoVideoTeacherCHRReportActivity;
import com.example.meyepro.models.ClaimVideo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CliamVideoAdapter extends RecyclerView.Adapter<CliamVideoAdapter.CliamVideoViewHolder>{
    private ArrayList<ClaimVideo> VenueList;
    private Context context;

    public CliamVideoAdapter(ArrayList<ClaimVideo> VenueList, Context context) {
        this.VenueList = VenueList;
        this.context = context;

    }
    @NonNull
    @Override
    public CliamVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewCliamVideoCellLayoutBinding b  =RecyclerviewCliamVideoCellLayoutBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        CliamVideoViewHolder vh = new CliamVideoViewHolder(b);
        return vh;



    }

    @Override
    public void onBindViewHolder(@NonNull CliamVideoViewHolder holder, int position) {
        ClaimVideo obj = VenueList.get(position);
        holder.binding.txtViewClaimName.setText(obj.getFile().split("/")[4].split("\\.")[0].split(",")[0]+"-"+obj.getFile().split("/")[4].split("\\.")[0].split(",")[1]);
        holder.binding.txtViewClaimActivityName.setText(obj.getFolder()+"");
        if(obj.getThumbnail()!=null){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/claim-video-thumbnails?file="+obj.getThumbnail())).into(holder.binding.ImageViewThumnail);
        }
        holder.binding.lineraLayoutCellClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+obj.getFile(), Toast.LENGTH_SHORT).show();
                Intent i= new Intent(context, TeacherClaimVideoPlayActivity.class);
                i.putExtra("VideoFile", obj.getFile());
                i.putExtra("Thumbnail", obj.getThumbnail());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return VenueList.size();
    }

    class CliamVideoViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewCliamVideoCellLayoutBinding binding;//<----
        public CliamVideoViewHolder(
                @NonNull RecyclerviewCliamVideoCellLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }
}
