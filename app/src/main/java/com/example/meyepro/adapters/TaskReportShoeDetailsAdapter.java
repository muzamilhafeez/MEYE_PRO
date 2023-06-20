package com.example.meyepro.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.RecyclerviewTaskReportDetailsCellBinding;
import com.example.meyepro.databinding.RecyclerviewTaskReportShowCellBinding;
import com.example.meyepro.models.TaskReport;
import com.example.meyepro.models.TeacherCHRActivityDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TaskReportShoeDetailsAdapter extends RecyclerView.Adapter<TaskReportShoeDetailsAdapter.TaskReportShoeDetailsViewHolder>{
    private ArrayList<TeacherCHRActivityDetails> List;
    private Context context;
    public TaskReportShoeDetailsAdapter(ArrayList<TeacherCHRActivityDetails> List, Context context) {
        this.List = List;
        this.context = context;
    }
    @NonNull
    @Override
    public TaskReportShoeDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewTaskReportDetailsCellBinding b  =RecyclerviewTaskReportDetailsCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        TaskReportShoeDetailsViewHolder vh = new TaskReportShoeDetailsViewHolder(b);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskReportShoeDetailsViewHolder holder, int position) {
        TeacherCHRActivityDetails obj =  List.get(position);
        holder.binding.txtViewStatus.setText(obj.getStatus());
        holder.binding.txtViewTaskStrat.setText(obj.getTimein().split("T")[1]+"");
        holder.binding.txtViewTaskEnd.setText(obj.getTimeout().split("T")[1]+"");
        if(obj.getImage()!=null){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/claim-video-thumbnails?file="+obj.getImage())).into(holder.binding.ImageViewThumnail);
        }

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    class TaskReportShoeDetailsViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewTaskReportDetailsCellBinding binding;//<----
        public TaskReportShoeDetailsViewHolder(
                @NonNull RecyclerviewTaskReportDetailsCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }
}
