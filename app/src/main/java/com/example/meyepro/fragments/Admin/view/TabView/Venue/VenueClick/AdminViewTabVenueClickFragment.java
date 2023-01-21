package com.example.meyepro.fragments.Admin.view.TabView.Venue.VenueClick;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meyepro.adapters.AdminViewVenueAdapter;
import com.example.meyepro.databinding.FragmentAdminViewTabVenueClickBinding;
import com.example.meyepro.models.Venue;

import java.util.ArrayList;


public class AdminViewTabVenueClickFragment extends Fragment {
FragmentAdminViewTabVenueClickBinding Binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding = FragmentAdminViewTabVenueClickBinding.inflate(getLayoutInflater());
        //code start
        ArrayList<Venue> data = new ArrayList<Venue>();
        data.add(new Venue());
        data.get(0).setNAME("LT 1");
        data.add(new Venue());
        data.get(1).setNAME("Lab 3");
        data.add(new Venue());
        data.get(2).setNAME("LT 5");
        data.add(new Venue());
        data.get(3).setNAME("Lab 7");
        AdminViewVenueAdapter adapter = new
                AdminViewVenueAdapter(data, getActivity(),AdminViewTabVenueClickFragment.this);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        Binding.RecycerviewAdminViewVenue.setLayoutManager(manager);
        //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
        Binding.RecycerviewAdminViewVenue.
                setAdapter(adapter);
        //code end
        return Binding.getRoot();
    }
}