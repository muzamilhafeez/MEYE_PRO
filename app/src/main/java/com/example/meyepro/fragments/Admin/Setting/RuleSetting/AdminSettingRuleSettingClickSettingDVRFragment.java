package com.example.meyepro.fragments.Admin.Setting.RuleSetting;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meyepro.R;
import com.example.meyepro.databinding.FragmentAdminSettingRuleSettingClickSettingDVRBinding;

public class AdminSettingRuleSettingClickSettingDVRFragment extends Fragment {
FragmentAdminSettingRuleSettingClickSettingDVRBinding Binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding = FragmentAdminSettingRuleSettingClickSettingDVRBinding.inflate(getLayoutInflater());
        //code start


        //code end
        return Binding.getRoot();
    }
}