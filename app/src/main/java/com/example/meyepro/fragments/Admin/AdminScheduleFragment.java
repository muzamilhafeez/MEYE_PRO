package com.example.meyepro.fragments.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meyepro.R;
import com.example.meyepro.databinding.FragmentAdminScheduleBinding;



public class AdminScheduleFragment extends Fragment {
    FragmentAdminScheduleBinding Binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding = FragmentAdminScheduleBinding.inflate(inflater, container, false);
        //code

        return  Binding.getRoot();
    }
}