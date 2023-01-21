package com.example.meyepro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.databinding.AdminViewDvrCellBinding;
import com.example.meyepro.fragments.Admin.view.TabView.DVR.AdminViewTabDVRFragment;
import com.example.meyepro.models.DVR;

import java.util.ArrayList;

public class AdminViewDVRAdapter extends RecyclerView.Adapter<AdminViewDVRAdapter.AdminViewDVRViewHolder>{
    private  Fragment fragment;
    private ArrayList<DVR> DVRList;
    private Context context;

    public AdminViewDVRAdapter(ArrayList<DVR> DVRList, Context context) {
        this.DVRList = DVRList;
        this.context = context;
    }
    public AdminViewDVRAdapter(ArrayList<DVR> DVRList, Context context, Fragment fragment ) {
        this.DVRList = DVRList;
        this.context = context;
        this.fragment=fragment;
    }
    @NonNull
    @Override
    public AdminViewDVRViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdminViewDvrCellBinding b  =AdminViewDvrCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        AdminViewDVRViewHolder vh = new AdminViewDVRViewHolder(b);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewDVRViewHolder holder, int position) {
        DVR obj = DVRList.get(position);
        holder.binding.textViewViewDvrName.setText(obj.getNAME()+"");
        holder.binding.textViewViewDvrHost.setText(obj.getHOST()+"");
        holder.binding.textViewViewDvrIp.setText(obj.getIP()+"");
        holder.binding.adminViewDVRSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminViewTabDVRFragment AdminViewTabDVR = (AdminViewTabDVRFragment) fragment;
                AdminViewTabDVR.recyclerviewAdminViewTabDVRCellClick(obj,context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return DVRList.size();
    }

    class AdminViewDVRViewHolder extends RecyclerView.ViewHolder {
        AdminViewDvrCellBinding binding;//<----
        public AdminViewDVRViewHolder(
                @NonNull AdminViewDvrCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }
}
