package com.example.meyepro.fragments.Admin.Setting.CameraSetting;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.adapters.AdminSettingDVRChannelAdapter;
import com.example.meyepro.adapters.AdminViewDVRAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.AddCameraCustomLayoutBinding;
import com.example.meyepro.databinding.AddDvrCustomLayoutBinding;
import com.example.meyepro.databinding.FragmentAdminSettingCameraSettingClickBinding;
import com.example.meyepro.databinding.FragmentAdminSettingCameraSettingClickCameraSettingBinding;
import com.example.meyepro.fragments.Admin.view.TabView.DVR.AdminViewTabDVRFragment;
import com.example.meyepro.models.CAMERA;
import com.example.meyepro.models.DVR;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminSettingCameraSettingClickCameraSettingFragment extends Fragment {
    FragmentAdminSettingCameraSettingClickCameraSettingBinding Binding;
    ArrayList<CAMERA> cameraList= new ArrayList<>();
    AdminSettingDVRChannelAdapter adapter;
    RetrofitClient client= RetrofitClient.getInstance();
    Api api= client.getMyApi();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding = FragmentAdminSettingCameraSettingClickCameraSettingBinding.inflate(getLayoutInflater());
        DVR obj = null;
        Type type = new TypeToken<DVR>(){}.getType();
        obj= new Gson().fromJson(getArguments().get("Data").toString(), type);

//        try {
//            JSONObject jsonObject= new JSONObject(getArguments().get("Data").toString());
//            Toast.makeText(getContext(), ""+jsonObject.getString("ip"), Toast.LENGTH_SHORT).show();
//
//            Binding.textViewViewDvrIp.setText(""+jsonObject.getString("ip").toString());
//        } catch (JSONException e) {
//            Toast.makeText(getContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }

        if(obj!=null){
            Binding.textViewViewDvrIp.setText(""+obj.getIp());
            Binding.textViewViewDvrChannel.setText(obj.getChannel().toString());
            Binding.textViewViewDvrHost.setText(obj.getHost());
            Binding.textViewViewDvrName.setText(obj.getName());
        }


        //code start
        Binding.editTextAdminSettingSearchCamera.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
//                Toast.makeText(getContext(), ""+editable, Toast.LENGTH_SHORT).show();
                ArrayList<CAMERA> filteredList = new ArrayList<>();
                for (CAMERA item :cameraList) {
                    if ((item.getId()+"").toLowerCase().contains(editable.toString().toLowerCase())) {
                        filteredList.add(item);
                    }
                }
                DVR obj= new Gson().fromJson(getArguments().get("Data").toString(), DVR.class);


                Binding.textViewViewDvrIp.setText(obj.getIp());
                Binding.textViewViewDvrChannel.setText(obj.getChannel());
                Binding.textViewViewDvrHost.setText(obj.getHost());
                Binding.textViewViewDvrName.setText(obj.getName());
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
//        ArrayList<CAMERA> data = new ArrayList<CAMERA>();
//        data.add(new CAMERA());
//        data.get(0).setNO("123");
//        data.get(0).setID(0);
//        data.get(0).setV_ID(1);
//        data.add(new CAMERA());
//        data.get(1).setNO("123");
//        data.get(1).setID(0);
//        data.get(1).setV_ID(1);
         adapter = new
                AdminSettingDVRChannelAdapter(cameraList, getActivity() , AdminSettingCameraSettingClickCameraSettingFragment.this);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        Binding.RecycerviewAdminSettingDVRChannel.setLayoutManager(manager);
        Binding.RecycerviewAdminSettingDVRChannel.setAdapter(adapter);
        DVR dvr= new Gson().fromJson(getArguments().getString("Data"),DVR.class);
        //api function
        api.Camera_details(dvr.getId()).enqueue(new Callback<Map<String, ArrayList<CAMERA>>>() {
            @Override
            public void onResponse(Call<Map<String, ArrayList<CAMERA>>> call, Response<Map<String, ArrayList<CAMERA>>> response) {
                Toast.makeText(getContext(), ""+response.code(), Toast.LENGTH_SHORT).show();
                if (response.code() == 200) {
                    try {
                        //    Map<String, ArrayList<DVR>> map = response.body();
                        //  String msg = "";
                        cameraList.clear();
                        cameraList.addAll(response.body().get("data"));
                        Binding.RecycerviewAdminSettingDVRChannel.getAdapter().notifyDataSetChanged();

                    } catch (Exception e) {
                        Toast.makeText(getContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
                        System.out.println(e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<Map<String, ArrayList<CAMERA>>> call, Throwable t) {

            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new AdminSettingDVRChannelAdapter.SwipeMenuCallback(adapter));
        itemTouchHelper.attachToRecyclerView(Binding.RecycerviewAdminSettingDVRChannel);
        //
        Binding.floatingActionButtonAdminAddCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCameraCustomLayoutBinding CameraBinding=AddCameraCustomLayoutBinding.inflate(getLayoutInflater());
                // Create dialog and set content view to custom layout
                Dialog customDialog = new Dialog(getActivity());
                customDialog.setContentView(CameraBinding.getRoot());
                // Set additional properties for dialog
                customDialog.setTitle("Custom Dialog");
                customDialog.setCancelable(true);
                customDialog.setCanceledOnTouchOutside(true);
                customDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                // customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //btn save
                CameraBinding.btnAddCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //code

                       int DvrID= Integer.parseInt(CameraBinding.editTxtDvrID.getText().toString());
                        int VenueId = Integer.parseInt(CameraBinding.editTxtVenueId.getText().toString());
                        String PortNumber = CameraBinding.editTxtPortNumber.getText().toString();
                        String venueName = CameraBinding.editTxtVenueName.getText().toString();

                        CAMERA camera= new CAMERA(0,DvrID,VenueId,PortNumber, venueName);


                        api.saveCamera(camera).enqueue(new Callback<CAMERA>() {
                            @Override
                            public void onResponse(Call<CAMERA> call, Response<CAMERA> response) {
                                Toast.makeText(getContext(), ""+response.code(), Toast.LENGTH_SHORT).show();
                                if (response.code() == 200) {
                                    Toast.makeText(getContext(), "Save", Toast.LENGTH_SHORT).show();
                                    loadFragment(new AdminSettingCameraSettingClickCameraSettingFragment());
                                    customDialog.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<CAMERA> call, Throwable t) {

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

    //calling fragment
    private void loadFragment(Fragment f) {
        FragmentTransaction ft =
                getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frament_container_admin, f);
        ft.commit();
    }

    public void recyclerviewAdminSettingChannelDeleteellClick(CAMERA obj, Context context) {
        api.delete_camera_details(obj).enqueue(new Callback<Map<String, String>>() {
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
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                Toast.makeText(context, ""+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void recyclerviewAdminSettingChannelUpdatelClick(CAMERA obj, Context context) {
        AddCameraCustomLayoutBinding CameraBinding=AddCameraCustomLayoutBinding.inflate(getLayoutInflater());
        // Create dialog and set content view to custom layout

        CameraBinding.editTxtDvrID.setText(""+obj.getDvrID());
        CameraBinding.editTxtVenueId.setText(""+obj.getVenueID());
        CameraBinding.editTxtPortNumber.setText(""+obj.getPortNumber());
        CameraBinding.editTxtVenueId.setText(""+obj.getVenueID());
        CameraBinding.editTxtVenueName.setText(""+obj.getVenueName());
        CameraBinding.registrationTitle.setText("Update Camera");
        CameraBinding.btnAddCamera.setText("Update");

        Dialog customDialog = new Dialog(getActivity());
        customDialog.setContentView(CameraBinding.getRoot());
        // Set additional properties for dialog
        customDialog.setTitle("Custom Dialog");
        customDialog.setCancelable(true);
        customDialog.setCanceledOnTouchOutside(true);
        customDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //btn save
        CameraBinding.btnAddCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code
                try{
                    int id = obj.getId();
                    int DvrID= Integer.parseInt(CameraBinding.editTxtDvrID.getText().toString());
                    int VenueId = Integer.parseInt(CameraBinding.editTxtVenueId.getText().toString());
                    String PortNumber = CameraBinding.editTxtPortNumber.getText().toString();
                    String venueName = CameraBinding.editTxtVenueName.getText().toString();
                    CAMERA camera= new CAMERA(id,DvrID,VenueId,PortNumber, venueName);

                    api.update_camera_details(camera).enqueue(new Callback<Map<String, CAMERA>>() {
                        @Override
                        public void onResponse(Call<Map<String, CAMERA>> call, Response<Map<String, CAMERA>> response) {
                            Toast.makeText(getContext(), ""+response.code(), Toast.LENGTH_SHORT).show();
                            if (response.code() == 200) {
                                Toast.makeText(getContext(), "Update", Toast.LENGTH_SHORT).show();

//                            loadFragment(new AdminSettingCameraSettingClickCameraSettingFragment());
                                customDialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<Map<String, CAMERA>> call, Throwable t) {

                        }
                    });
                }catch (Exception e){
                    Toast.makeText(context, ""+e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
        // Show dialog
        customDialog.show();
    }
}