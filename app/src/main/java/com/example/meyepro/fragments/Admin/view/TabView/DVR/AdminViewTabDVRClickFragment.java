package com.example.meyepro.fragments.Admin.view.TabView.DVR;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meyepro.R;
import com.example.meyepro.databinding.FragmentAdminViewTabDVRBinding;
import com.example.meyepro.databinding.FragmentAdminViewTabDVRClickBinding;


public class AdminViewTabDVRClickFragment extends Fragment {
FragmentAdminViewTabDVRClickBinding Binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding = FragmentAdminViewTabDVRClickBinding.inflate(getLayoutInflater());
        //code start

        //code end
        return Binding.getRoot();
    }
}