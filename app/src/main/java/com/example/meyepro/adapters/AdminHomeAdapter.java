package com.example.meyepro.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.databinding.AdminHomeVenueCellBinding;
import com.example.meyepro.fragments.Admin.AdminHomeFragment;
import com.example.meyepro.models.CAMERA;
import com.example.meyepro.models.Venue;

import java.util.ArrayList;


public class AdminHomeAdapter extends RecyclerView.Adapter<AdminHomeAdapter.AdminHomeViewHolder>{
    private ArrayList<CAMERA> VenueList;
    private Context context;
    private Fragment fragment;

    public AdminHomeAdapter(ArrayList<CAMERA> VenueList, Context context) {
        this.VenueList = VenueList;
        this.context = context;
       // AdminHomeFragment  AdminHome = (Context) context;
    }
    public AdminHomeAdapter(ArrayList<CAMERA> VenueList, Context context,Fragment fragment ) {
        this.VenueList = VenueList;
        this.context = context;
        this.fragment=fragment;
    }
    @NonNull
    @Override
    public AdminHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdminHomeVenueCellBinding b  =AdminHomeVenueCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        AdminHomeViewHolder vh = new AdminHomeViewHolder(b);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminHomeViewHolder holder, int position) {
        CAMERA obj = VenueList.get(position);
      //  Log.d(VenueList.get(0).getNAME(), VenueList.get(0).getNAME());
        holder.binding.textViewVenueCamera.setText(obj.getNO()+"");
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, obj.getID()+"", Toast.LENGTH_SHORT).show();
//            }
//        });
        holder.binding.textViewVenueCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             AdminHomeFragment  AdminHome = (AdminHomeFragment) fragment;
               AdminHome.recyclerviewAdminHomeCellClick(obj,context);
            }
        });





    }

    @Override
    public int getItemCount() {
        return VenueList.size();
    }
    //
    class AdminHomeViewHolder extends RecyclerView.ViewHolder {
        AdminHomeVenueCellBinding binding;//<----
        public AdminHomeViewHolder(
                @NonNull AdminHomeVenueCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }
}
