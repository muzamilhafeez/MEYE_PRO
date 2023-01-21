package com.example.meyepro.fragments.Admin.Hone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meyepro.R;
import com.example.meyepro.databinding.FragmentAdminHomeCameraClickDetailBinding;
import com.example.meyepro.models.CAMERA;
import com.example.meyepro.models.Venue;

import java.util.ArrayList;


public class AdminHomeCameraClickDetailFragment extends Fragment {
FragmentAdminHomeCameraClickDetailBinding Binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding= FragmentAdminHomeCameraClickDetailBinding.inflate(getLayoutInflater());
        //start code
        Bundle b = getArguments();
        ArrayList<CAMERA> data = (ArrayList<CAMERA>) b.getSerializable("id");

        Binding.txtViewCameraDetail.setText(data.get(0).getNO()+"");
        //end
        return Binding.getRoot();

    }
}