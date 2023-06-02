package com.example.meyepro.fragments.Admin.Setting.CameraSetting;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.adapters.AdminViewDVRAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.AddDvrCustomLayoutBinding;
import com.example.meyepro.databinding.FragmentAdminSettingCameraSettingClickBinding;
import com.example.meyepro.databinding.FragmentAdminSettingRuleSettingClickBinding;
import com.example.meyepro.fragments.Admin.Setting.RuleSetting.AdminSettingRuleSettingClickSetRuleFragment;
import com.example.meyepro.fragments.Admin.view.TabView.DVR.AdminViewTabDVRClickFragment;
import com.example.meyepro.fragments.Admin.view.TabView.DVR.AdminViewTabDVRFragment;
import com.example.meyepro.fragments.Admin.view.Teacher.Teacher.AdminViewTabTecherRecodingsFragment;
import com.example.meyepro.models.CAMERA;
import com.example.meyepro.models.DVR;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminSettingCameraSettingClickFragment extends Fragment {
FragmentAdminSettingCameraSettingClickBinding Binding;
    AdminViewDVRAdapter adapter;
    ArrayList<DVR> dvrList= new ArrayList<>();
    RetrofitClient client= RetrofitClient.getInstance();
    Api api= client.getMyApi();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding = FragmentAdminSettingCameraSettingClickBinding.inflate(getLayoutInflater());
        //code start
        Binding.editTextAdminSettingSearchDVR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
//                Toast.makeText(getContext(), ""+editable, Toast.LENGTH_SHORT).show();
                ArrayList<DVR> filteredList = new ArrayList<>();
                for (DVR item :dvrList) {
                    if ((item.getName()+"").toLowerCase().contains(editable.toString().toLowerCase())) {
                        filteredList.add(item);
                    }
                }
//                AdminViewTeacherAdapter     adaptersearch = new
//                        AdminViewTeacherAdapter(filteredList, getActivity(), ViewTeacherFragment.this,"ViewTeacherFragment");
//                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//                Binding.RecycerviewAdminViewTeacher.setLayoutManager(manager);
//                //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
//                Binding.RecycerviewAdminViewTeacher.
//                        setAdapter(adaptersearch);
//                recyclerview function calling
                adapter.filterList(filteredList);
            }
        });
//        ArrayList<DVR> data = new ArrayList<DVR>();
//        data.add(new DVR());
//        data.get(0).setName("DVR 1");
//        data.get(0).setHost("admin");
//        data.get(0).setIp("192.168.2.1");
//        data.add(new DVR());
//        data.get(1).setNAME("DVR 2");
//        data.get(1).setHOST("admin");
////        data.get(1).setIP("192.168.3.1");
//        AdminViewDVRAdapter adapter = new
//                AdminViewDVRAdapter (data, getActivity() , AdminSettingCameraSettingClickFragment.this,"AdminSettingCameraSettingClickFragment");
//        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//        Binding.RecycerviewAdminViewDVR.setLayoutManager(manager);
        //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
//        Binding.RecycerviewAdminViewDVR.
//                setAdapter(adapter);

        //Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
        //api calling
         adapter = new
                AdminViewDVRAdapter (dvrList, getActivity() , AdminSettingCameraSettingClickFragment.this,"AdminSettingCameraSettingClickFragment");
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        Binding.RecycerviewAdminViewDVR.setLayoutManager(manager);
        Binding.RecycerviewAdminViewDVR.setHasFixedSize(true);
        Binding.RecycerviewAdminViewDVR.
                setAdapter(adapter);

        APICalling();

        //calling floatting button
       Binding.floatingActionButtonAdminAddDVR.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AddDvrCustomLayoutBinding DVRBinding=AddDvrCustomLayoutBinding.inflate(getLayoutInflater());
               // Create dialog and set content view to custom layout
               Dialog customDialog = new Dialog(getActivity());
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

                        String ip = DVRBinding.editTxtIp.getText().toString();
                        String name = DVRBinding.editTxtName.getText().toString();
                        String channel = DVRBinding.editTxtChannel.getText().toString();
                        String host = DVRBinding.editTxtHost.getText().toString();
                        String password = DVRBinding.editTxtPass.getText().toString();
                        DVR dvr= new DVR(0,ip,name,channel,host,password);
                        api.saveDVR(dvr).enqueue(new Callback<DVR>() {
                            @Override
                            public void onResponse(Call<DVR> call, Response<DVR> response) {
                                Toast.makeText(getContext(), ""+response.code(), Toast.LENGTH_SHORT).show();
                                if (response.code() == 200) {
                                    Toast.makeText(getContext(), "Save", Toast.LENGTH_SHORT).show();
                                    loadFragment(new AdminSettingCameraSettingClickFragment());
                                    customDialog.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<DVR> call, Throwable t) {

                            }
                        });
                    }
                });
               // Show dialog
               customDialog.show();
           }
       });


        //code end
        return Binding.getRoot();
    }

    public void recyclerviewAdminSettingRuleSettingClick(DVR obj, Context context) {
//      loadFragment(new AdminSettingCameraSettingClickCameraSettingFragment());
        AdminSettingCameraSettingClickCameraSettingFragment fragment= new AdminSettingCameraSettingClickCameraSettingFragment();
        Bundle bundle= new Bundle();
        bundle.putString("Data",new Gson().toJson(obj));
        fragment.setArguments(bundle);
        loadFragment( fragment);
    }

    //calling fragment
    private void loadFragment(Fragment f) {
        FragmentTransaction ft =
                getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frament_container_admin, f);
        ft.commit();
    }
    public  void APICalling(){

        api.dvr_details().enqueue(new Callback<ArrayList<DVR>>() {
            @Override
            public void onResponse(Call<ArrayList<DVR>> call, Response<ArrayList<DVR>> response) {
                if (response.code() == 200) {
                    try {
                        //    Map<String, ArrayList<DVR>> map = response.body();
                        //String msg = "";
                        dvrList.clear();
                        dvrList.addAll(response.body());
                        Binding.RecycerviewAdminViewDVR.getAdapter().notifyDataSetChanged();

                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                }

            }

            @Override
            public void onFailure(Call<ArrayList<DVR>> call, Throwable t) {

            }
        });
    }

    public void AdminSettingCameraSettingClickFragmentRefresh(DVR obj, Context context) {
        APICalling();
    }

    public void AdminSettingCameraSettingClickFragmentRemoveDVR(DVR obj, Context context) {
        api.delete_dvr_details(obj).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
//                Toast.makeText(context, ""+response.code(), Toast.LENGTH_SHORT).show();
                if (response.code() == 200) {
                    try {
                        // 1. Success message
                        new SweetAlertDialog(getContext())
                                .setTitleText("Successful Deleted")
                                .show();
//                        Toast.makeText(context, "Successful Deleted", Toast.LENGTH_SHORT).show();
                        APICalling();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                }
                if(response.code() == 500){
                    new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("First, remove all connected cameras from the DVR to avoid conflicts with the DELETE statement in the SQL server error.!")
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                Toast.makeText(context, ""+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}