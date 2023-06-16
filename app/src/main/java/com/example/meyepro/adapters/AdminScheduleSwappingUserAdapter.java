package com.example.meyepro.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.R;
import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.RecyclerviewAdminScheduleSwappingUserLayoutBinding;
import com.example.meyepro.fragments.Admin.Schedule.Swapping.AdminScheduleSwappingUserActivity;
import com.example.meyepro.models.SwappingUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminScheduleSwappingUserAdapter  extends RecyclerView.Adapter<AdminScheduleSwappingUserAdapter.AdminScheduleSwappingUserViewHolder>{
    private ArrayList<SwappingUser> List;
    private Context context;

    public AdminScheduleSwappingUserAdapter(ArrayList<SwappingUser> List, Context context) {
        this.List = List;
        this.context = context;

    }
    @NonNull
    @Override
    public AdminScheduleSwappingUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewAdminScheduleSwappingUserLayoutBinding b  =RecyclerviewAdminScheduleSwappingUserLayoutBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        AdminScheduleSwappingUserViewHolder vh = new AdminScheduleSwappingUserViewHolder(b);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminScheduleSwappingUserViewHolder holder, int position) {
        SwappingUser obj = List.get(position);
        holder.binding.txtName.setText(obj.getName()+"");
        holder.binding.txtViewDiscipline.setText(obj.getDiscipline()+"");
        holder.binding.txtViewVenue.setText(obj.getVenue()+"");
        if (obj.getImage() != null) {
            Picasso.get()
                    .load(Uri.parse(Api.BASE_URL + "api/get-user-image/UserImages/" + obj.getRole() + "/" + obj.getImage()))
                    .resize(200, 200)
                    .centerCrop()
                    .into(holder.binding.imageViewProfile);
        } else {
            holder.binding.imageViewProfile.setImageResource(R.drawable.image_icon);
        }

        holder.binding.lineraLayoutCellClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminScheduleSwappingUserActivity adminScheduleSwappingUserActivity= (AdminScheduleSwappingUserActivity) context;
                adminScheduleSwappingUserActivity.recyclerViewSwappingUserSelectedClick(obj, context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    class AdminScheduleSwappingUserViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewAdminScheduleSwappingUserLayoutBinding binding;//<----
        public AdminScheduleSwappingUserViewHolder(
                @NonNull RecyclerviewAdminScheduleSwappingUserLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }
}
