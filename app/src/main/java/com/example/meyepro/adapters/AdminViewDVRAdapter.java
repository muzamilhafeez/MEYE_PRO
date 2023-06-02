package com.example.meyepro.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.AddDvrCustomLayoutBinding;
import com.example.meyepro.databinding.AdminViewDvrCellBinding;
import com.example.meyepro.fragments.Admin.Setting.CameraSetting.AdminSettingCameraSettingClickFragment;
import com.example.meyepro.fragments.Admin.Setting.RuleSetting.AdminSettingRuleSettingClickFragment;
import com.example.meyepro.fragments.Admin.view.TabView.DVR.AdminViewTabDVRFragment;
import com.example.meyepro.fragments.Admin.view.TabView.Venue.AdminViewTabVenueFragment;
import com.example.meyepro.models.DVR;
import com.example.meyepro.models.MEYE_USER;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminViewDVRAdapter extends RecyclerView.Adapter<AdminViewDVRAdapter.AdminViewDVRViewHolder>{
    private  Fragment fragment;
    private ArrayList<DVR> DVRList;
    private Context context;
    private String FromCaliingOnPageName="";

    public AdminViewDVRAdapter(ArrayList<DVR> DVRList, Context context) {
        this.DVRList = DVRList;
        this.context = context;
    }
    public AdminViewDVRAdapter(ArrayList<DVR> DVRList, Context context, Fragment fragment ) {
        this.DVRList = DVRList;
        this.context = context;
        this.fragment=fragment;
    }
    public AdminViewDVRAdapter(ArrayList<DVR> DVRList, Context context, Fragment fragment, String FromCaliingOnPageName ) {
        this.DVRList = DVRList;
        this.context = context;
        this.fragment=fragment;
        this.FromCaliingOnPageName=FromCaliingOnPageName;

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
        holder.binding.textViewViewDvrName.setText(obj.getName()+"");
        holder.binding.textViewSettingDvrIP.setText(obj.getIp()+"");
        holder.binding.textViewViewDvrPassword.setText(obj.getPassword()+"");
        holder.binding.textViewViewDvrChannel.setText(obj.getChannel()+"");
        //calling AdminViewTabDVRFragment dvr view
        if(FromCaliingOnPageName=="AdminViewTabDVRFragment"){
            holder.binding.imageViewViewDvrDelete.setVisibility(View.INVISIBLE);
            holder.binding.imageViewViewDvrEdit.setVisibility(View.INVISIBLE);
            holder.binding.adminViewDVRSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AdminViewTabDVRFragment AdminViewTabDVR = (AdminViewTabDVRFragment) fragment;
                    AdminViewTabDVR.recyclerviewAdminViewTabDVRCellClick(obj,context);
                }
            });
        }
        //calling AdminViewTabDVRFragment dvr view
        if(FromCaliingOnPageName=="AdminSettingCameraSettingClickFragment"){
            holder.binding.adminViewDVRSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AdminSettingCameraSettingClickFragment AdminViewSetRuleDVR = (AdminSettingCameraSettingClickFragment) fragment;
                    AdminViewSetRuleDVR.recyclerviewAdminSettingRuleSettingClick(obj,context);
                }
            });
            holder.binding.imageViewViewDvrEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Edit"+holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    AddDvrCustomLayoutBinding DVRBinding=AddDvrCustomLayoutBinding.inflate(LayoutInflater.from(context));
                   //Set Value
                    DVRBinding.txtViewDVRTitle.setText("Update DVR");
                    DVRBinding.btnAddDvr.setText("Update");
                    DVRBinding.editTxtHost.setText(obj.getHost());
                    DVRBinding.editTxtPass.setText(obj.getPassword());
                    DVRBinding.editTxtChannel.setText(obj.getChannel());
                    DVRBinding.editTxtIp.setText(obj.getIp());
                    DVRBinding.editTxtName.setText(obj.getName());
                    // Create dialog and set content view to custom layout
                    Dialog customDialog = new Dialog(context);
                    customDialog.setContentView(DVRBinding.getRoot());
                    // Set additional properties for dialog
                    customDialog.setTitle("Custom Dialog");
                    customDialog.setCancelable(true);
                    customDialog.setCanceledOnTouchOutside(true);
                    customDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    // customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    //btn save
                    DVRBinding.btnAddDvr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //code

                            int id= obj.getId();
                            String ip = DVRBinding.editTxtIp.getText().toString();
                            String name = DVRBinding.editTxtName.getText().toString();
                            String channel = DVRBinding.editTxtChannel.getText().toString();
                            String host = DVRBinding.editTxtHost.getText().toString();
                            String password = DVRBinding.editTxtPass.getText().toString();
                            DVR dvr= new DVR(id,ip,name,channel,host,password);
                            RetrofitClient client= RetrofitClient.getInstance();
                            Api api= client.getMyApi();
                            api.update_dvr_details(dvr).enqueue(new Callback<DVR>() {
                                @Override
                                public void onResponse(Call<DVR> call, Response<DVR> response) {
//                                    Toast.makeText(context, ""+response.code(), Toast.LENGTH_SHORT).show();
                                    if (response.code() == 200) {
                                        new SweetAlertDialog(context)
                                                .setTitleText("Successful Updated")
                                                .show();
//                                        Toast.makeText(context, "Successful Updated"+name, Toast.LENGTH_SHORT).show();
//                                        loadFragment(new AdminSettingCameraSettingClickFragment());
                                        customDialog.dismiss();
                                        // Update the data

                                // Notify the adapter that the data has changed
                                        AdminSettingCameraSettingClickFragment AdminSettingDVR = (AdminSettingCameraSettingClickFragment) fragment;
                                        AdminSettingDVR.AdminSettingCameraSettingClickFragmentRefresh(obj,context);
                                    }
                                }

                                @Override
                                public void onFailure(Call<DVR> call, Throwable t) {
                                    Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    // Show dialog
                    customDialog.show();

                }
            });

            holder.binding.imageViewViewDvrDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, "Delete"+position, Toast.LENGTH_SHORT).show();
                    //. Delete  item
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Are you sure?")
                            .setContentText("You won't be able to recover this file!")
                            .setCancelText("Cancel")
                            .setConfirmText("Delete!")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    AdminSettingCameraSettingClickFragment AdminSettingDVR = (AdminSettingCameraSettingClickFragment) fragment;
                                    AdminSettingDVR.AdminSettingCameraSettingClickFragmentRemoveDVR(obj,context);
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

        }
    }

    @Override
    public int getItemCount() {
        return DVRList.size();
    }

    public void filterList(ArrayList<DVR> filteredList) {
        DVRList=filteredList;
        notifyDataSetChanged();
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
