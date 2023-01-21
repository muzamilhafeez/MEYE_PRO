package com.example.meyepro.fragments.Admin.Setting;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meyepro.R;
import com.example.meyepro.adapters.AdminViewTeacherAdapter;
import com.example.meyepro.databinding.FragmentAdminSettingRuleSettingClickBinding;
import com.example.meyepro.fragments.Admin.view.Teacher.ViewTeacherFragment;
import com.example.meyepro.models.MEYE_USER;

import java.util.ArrayList;


public class AdminSettingRuleSettingClickFragment extends Fragment {

   FragmentAdminSettingRuleSettingClickBinding Binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding= FragmentAdminSettingRuleSettingClickBinding.inflate(getLayoutInflater());
        //code start
        ArrayList<MEYE_USER> data = new ArrayList<MEYE_USER>();
        data.add(new MEYE_USER());
        data.get(0).setNAME("Ali");
        data.add(new MEYE_USER());
        data.get(1).setNAME("Muzamil");
        data.add(new MEYE_USER());
        data.get(2).setNAME("Muzamil");
        data.add(new MEYE_USER());
        data.get(3).setNAME("Muzamil");
        AdminViewTeacherAdapter adapter = new
                AdminViewTeacherAdapter(data, getActivity(), AdminSettingRuleSettingClickFragment.this,"AdminSettingRuleSettingClickFragment");
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        Binding.RecycerviewAdminSettingTeacherRule.setLayoutManager(manager);
        //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
        Binding.RecycerviewAdminSettingTeacherRule.
                setAdapter(adapter);
        //code end

        return Binding.getRoot();
    }
    //Recyclerview calling method from AminViewTeacherAdapter
    public void recyclerviewAdminSettingRuleCellClick(MEYE_USER obj, Context context) {

    }
}