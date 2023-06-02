package com.example.meyepro.TeacherDashBoard.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.databinding.SessionCommingSliderCellBinding;
import com.example.meyepro.models.TimeTable;

import java.util.ArrayList;

public class SessionComingSliderAdapter extends RecyclerView.Adapter<SessionComingSliderAdapter.SessionComingSliderViewHolder>{
    private ArrayList<TimeTable> List;
    private Context context;
    Fragment fragment;
    public SessionComingSliderAdapter(ArrayList<TimeTable> List, Context context) {
        this.List = List;
        this.context = context;
    }
    public SessionComingSliderAdapter(ArrayList<TimeTable> List, Context context, Fragment fragment ) {
        this.List = List;
        this.context = context;
        this.fragment=fragment;
    }

    @NonNull
    @Override
    public SessionComingSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SessionCommingSliderCellBinding b  =SessionCommingSliderCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        SessionComingSliderViewHolder vh = new SessionComingSliderViewHolder(b);
        View view = vh.itemView;
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) vh.itemView.getLayoutParams();
        layoutParams.setMargins(30, 20, 30, -60); // Set margin to 8dp or your desired value
        vh.itemView.setLayoutParams(layoutParams);

       // return new viewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull SessionComingSliderViewHolder holder, int position) {
        TimeTable obj = List.get(position);
        holder.binding.textViewSessionSliderTeacherName.setText(obj.getTeacherName()+"");
        holder.binding.textViewSessionSliderScheduleDay.setText(obj.getDay()+"");
        holder.binding.textViewSessionSliderScheduleSeesion.setText(obj.getSessionId()+"");
       // Toast.makeText(context, ""+obj.getSessionName()+"", Toast.LENGTH_SHORT).show();
        holder.binding.textViewSessionSliderScheduleTime.setText(obj.getStarttime()+"\n"+obj.getEndtime());
        holder.binding.textViewSessionSliderScheduleVenue.setText(obj.getVenue()+"");
        holder.binding.textViewSessionSliderScheduleCourse.setText(obj.getCourseName()+"");
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    class SessionComingSliderViewHolder extends RecyclerView.ViewHolder {
        SessionCommingSliderCellBinding binding;//<----
        public SessionComingSliderViewHolder(
                @NonNull SessionCommingSliderCellBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;//<----
        }
    }
}
