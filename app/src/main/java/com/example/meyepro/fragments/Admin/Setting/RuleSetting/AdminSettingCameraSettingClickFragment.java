package com.example.meyepro.fragments.Admin.Setting.RuleSetting;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meyepro.R;
import com.example.meyepro.databinding.FragmentAdminSettingRuleSettingClickBinding;


public class AdminSettingCameraSettingClickFragment extends Fragment {
FragmentAdminSettingRuleSettingClickBinding Binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding = FragmentAdminSettingRuleSettingClickBinding.inflate(getLayoutInflater());
        //code start


        //code end
        return Binding.getRoot();
    }
}