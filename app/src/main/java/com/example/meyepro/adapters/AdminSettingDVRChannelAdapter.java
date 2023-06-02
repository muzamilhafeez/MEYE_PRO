package com.example.meyepro.adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.R;
import com.example.meyepro.databinding.AdminSettingRuleSettingDVRCellBinding;
import com.example.meyepro.fragments.Admin.Setting.CameraSetting.AdminSettingCameraSettingClickCameraSettingFragment;
import com.example.meyepro.fragments.Admin.Setting.CameraSetting.AdminSettingCameraSettingClickFragment;
import com.example.meyepro.fragments.Admin.view.TabView.DVR.AdminViewTabDVRFragment;
import com.example.meyepro.models.CAMERA;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AdminSettingDVRChannelAdapter extends RecyclerView.Adapter<AdminSettingDVRChannelAdapter.AdminSettingDVRChannelViewHolder>{
    private ArrayList<CAMERA> DVRChannelList;
    private Context context;
    private  Fragment fragment;
    String CheckActivityName="";
    public AdminSettingDVRChannelAdapter(ArrayList<CAMERA> DVRChannelList, Context context) {
        this.DVRChannelList = DVRChannelList;
        this.context = context;
    }
    public AdminSettingDVRChannelAdapter(ArrayList<CAMERA> DVRChannelList, Context context, Fragment fragment ) {
        this.DVRChannelList = DVRChannelList;
        this.context = context;
        this.fragment=fragment;
    }
    public AdminSettingDVRChannelAdapter(ArrayList<CAMERA> DVRChannelList, Context context, Fragment fragment ,String CheckActivityName) {
        this.DVRChannelList = DVRChannelList;
        this.context = context;
        this.fragment=fragment;
        this.CheckActivityName=CheckActivityName;
    }
    @NonNull
    @Override
    public AdminSettingDVRChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdminSettingRuleSettingDVRCellBinding b  =AdminSettingRuleSettingDVRCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        AdminSettingDVRChannelViewHolder vh = new AdminSettingDVRChannelViewHolder(b);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull AdminSettingDVRChannelViewHolder holder, int position) {
        CAMERA obj = DVRChannelList.get(position);
        holder.binding.textViewSettingDvrChennelRoom.setText("Venue: "+obj.getVenueName()+"");
        holder.binding.textViewSettingDvrChennelNO.setText("Channel No: "+obj.getPortNumber()+"");
        if(CheckActivityName.contains("AdminViewTabDVRClickFragment")){
            holder.binding.imageSettingViewDvrDelete.setVisibility(View.INVISIBLE);
            holder.binding.imageSettingViewDvrEdit.setVisibility(View.INVISIBLE);
        }

        holder.binding.imageSettingViewDvrDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //. Delete  item
                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("You won't be able to recover this file!")
                        .setCancelText("Cancel")
                        .setConfirmText("Delete!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                AdminSettingCameraSettingClickCameraSettingFragment adminSettingCameraSettingClickCameraSettingFragment = (AdminSettingCameraSettingClickCameraSettingFragment) fragment;
                                adminSettingCameraSettingClickCameraSettingFragment.recyclerviewAdminSettingChannelDeleteellClick(obj,context);
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();

            }
        });
        holder.binding.imageSettingViewDvrEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSettingCameraSettingClickCameraSettingFragment adminSettingCameraSettingClickCameraSettingFragment = (AdminSettingCameraSettingClickCameraSettingFragment) fragment;
                adminSettingCameraSettingClickCameraSettingFragment.recyclerviewAdminSettingChannelUpdatelClick(obj,context);
            }
        });
    }
    @Override
    public int getItemCount() {
        return DVRChannelList.size();
    }

    public void removeItem(int position) {
        DVRChannelList.remove(position);
        notifyItemRemoved(position);
    }
    public void filterList(ArrayList<CAMERA> filteredList) {
        DVRChannelList=filteredList;
        notifyDataSetChanged();
    }

    class AdminSettingDVRChannelViewHolder extends RecyclerView.ViewHolder {
        AdminSettingRuleSettingDVRCellBinding binding;//<----
        public AdminSettingDVRChannelViewHolder(
                @NonNull AdminSettingRuleSettingDVRCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }

    public static class SwipeMenuCallback extends ItemTouchHelper.SimpleCallback {

        private AdminSettingDVRChannelAdapter adapter;

         public SwipeMenuCallback(AdminSettingDVRChannelAdapter adapter) {
            super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            this.adapter = adapter;
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            adapter.removeItem(position);
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                float dX, float dY, int actionState, boolean isCurrentlyActive) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                // Get the swipe menu layout for the current item
                View swipeMenuLayout = viewHolder.itemView.findViewById(R.id.rowBG);

                // Adjust the swipe menu layout's position based on the swiping gesture
                swipeMenuLayout.setTranslationX(dX);
            } else {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }
    }

}
