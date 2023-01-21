package com.example.meyepro.fragments.Admin.view.Teacher.Teacher;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.meyepro.R;
import com.example.meyepro.databinding.FragmentAdminViewTabTeacherScheduleBinding;

import java.util.ArrayList;


public class AdminViewTabTeacherScheduleFragment extends Fragment {
FragmentAdminViewTabTeacherScheduleBinding Binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Binding= FragmentAdminViewTabTeacherScheduleBinding.inflate(getLayoutInflater());
        // Inflate the layout for this fragment
        //code start
        ArrayList<String> floor= new ArrayList<String>();
        floor.add("Ground Floor");
        floor.add("First Floor");
        //create drowpdownlist
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_layout_timetable, floor);
        //calling  ArrayAdapter ,display spinner view list
        Binding.spinnerRowOneMonday.setAdapter(arrayAdapter);
       Binding.spinnerRowOneTuesday.setAdapter(arrayAdapter);
        Binding.spinnerRowOneThursday.setAdapter(arrayAdapter);
        Binding.spinnerRowOneWednesday.setAdapter(arrayAdapter);
        Binding.spinnerRowOneFriday.setAdapter(arrayAdapter);

        //code end
        return Binding.getRoot();
    }
}