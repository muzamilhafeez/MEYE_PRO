package com.example.meyepro.fragments.Admin.view.Teacher.Teacher;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meyepro.adapters.AdminViewVenueRecordingsAdapter;
import com.example.meyepro.databinding.FragmentAdminViewTabTecherRecodingsBinding;
import com.example.meyepro.models.RECORDINGS;

import java.util.ArrayList;


public class AdminViewTabTecherRecodingsFragment extends Fragment {

   FragmentAdminViewTabTecherRecodingsBinding Binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding= FragmentAdminViewTabTecherRecodingsBinding.inflate(getLayoutInflater());
        //code start
        ArrayList<RECORDINGS> data = new ArrayList<RECORDINGS>();
        data.add(new RECORDINGS());
        data.get(0).setFILENAME("Recoding 1");
        data.add(new RECORDINGS());
        data.get(1).setFILENAME("Recoding 2");
        data.add(new RECORDINGS());
        data.get(2).setFILENAME("Recoding 3");
        data.add(new RECORDINGS());
        data.get(3).setFILENAME("Recoding 4");
        AdminViewVenueRecordingsAdapter adapter = new
                AdminViewVenueRecordingsAdapter(data, getActivity(), AdminViewTabTecherRecodingsFragment.this);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        Binding.RecycerviewAdminViewVenueRecordings.setLayoutManager(manager);
        //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
        Binding.RecycerviewAdminViewVenueRecordings.
                setAdapter(adapter);

        //code end
        return Binding.getRoot();
    }
}