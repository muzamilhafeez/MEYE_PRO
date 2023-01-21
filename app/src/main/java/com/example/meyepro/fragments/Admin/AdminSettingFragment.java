package com.example.meyepro.fragments.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meyepro.R;
import com.example.meyepro.databinding.FragmentAdminSettingBinding;


public class AdminSettingFragment extends Fragment {

FragmentAdminSettingBinding Binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding = FragmentAdminSettingBinding.inflate(inflater, container, false);
        //code

        return  Binding.getRoot();
    }
}