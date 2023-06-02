package com.example.meyepro.fragments.Admin.view.TabView.DVR;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.adapters.AdminSettingDVRChannelAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.FragmentAdminViewTabDVRBinding;
import com.example.meyepro.databinding.FragmentAdminViewTabDVRClickBinding;
import com.example.meyepro.fragments.Admin.Setting.CameraSetting.AdminSettingCameraSettingClickCameraSettingFragment;
import com.example.meyepro.models.CAMERA;
import com.example.meyepro.models.DVR;
import com.example.meyepro.models.MEYE_USER;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminViewTabDVRClickFragment extends Fragment {
FragmentAdminViewTabDVRClickBinding Binding;
    ArrayList<CAMERA> cameraList= new ArrayList<>();
    RetrofitClient client= RetrofitClient.getInstance();
    Api api= client.getMyApi();
    AdminSettingDVRChannelAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding = FragmentAdminViewTabDVRClickBinding.inflate(getLayoutInflater());
        //code start
        Binding.editTextAdminViewSearchDvr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
//                Toast.makeText(getContext(), ""+editable, Toast.LENGTH_SHORT).show();
                ArrayList<CAMERA> filteredList = new ArrayList<>();
                for (CAMERA item :cameraList) {
                    if ((item.getId()+"").toLowerCase().contains(editable.toString().toLowerCase())) {
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

//        Toast.makeText(getContext(), ""+getArguments().get("Data"), Toast.LENGTH_SHORT).show();
        DVR obj= new Gson().fromJson(getArguments().get("Data").toString(), DVR.class);


        Binding.textViewViewDvrIp.setText(""+obj.getIp());
        Binding.textViewViewDvrChannel.setText(obj.getChannel());
        Binding.textViewViewDvrHost.setText(obj.getHost());
        Binding.textViewViewDvrName.setText(obj.getName());

         adapter = new
                AdminSettingDVRChannelAdapter(cameraList, getActivity() , AdminViewTabDVRClickFragment.this,"AdminViewTabDVRClickFragment");
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        Binding.RecycerviewAdminViewDVRChannel.setLayoutManager(manager);
        Binding.RecycerviewAdminViewDVRChannel.setAdapter(adapter);

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
                        Binding.RecycerviewAdminViewDVRChannel.getAdapter().notifyDataSetChanged();

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

        //code end
        return Binding.getRoot();
    }
}