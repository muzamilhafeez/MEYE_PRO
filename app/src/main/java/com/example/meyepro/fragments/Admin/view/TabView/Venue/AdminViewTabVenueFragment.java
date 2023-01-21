package com.example.meyepro.fragments.Admin.view.TabView.Venue;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meyepro.R;
import com.example.meyepro.adapters.AdminViewTeacherAdapter;
import com.example.meyepro.databinding.FragmentAdminViewTabVenueBinding;
import com.example.meyepro.fragments.Admin.view.TabView.Venue.VenueClick.AdminViewTabVenueClickFragment;
import com.example.meyepro.models.MEYE_USER;

import java.util.ArrayList;

public class AdminViewTabVenueFragment extends Fragment {

FragmentAdminViewTabVenueBinding Binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Binding= FragmentAdminViewTabVenueBinding.inflate(getLayoutInflater());
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
                AdminViewTeacherAdapter(data, getActivity(),AdminViewTabVenueFragment.this,"AdminViewTabVenueFragment");
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        Binding.RecycerviewAdminViewVenue.setLayoutManager(manager);
        //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
        Binding.RecycerviewAdminViewVenue.
                setAdapter(adapter);


        //end
        // Inflate the layout for this fragment
        return Binding.getRoot();
    }
// view calinng adapter
    public void recyclerviewAdminViewCellClick(MEYE_USER obj, Context context) {
        loadFragment( new AdminViewTabVenueClickFragment());
    }
    //calling fragment
    private void loadFragment(Fragment f) {
        FragmentTransaction ft =
                getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frament_container_admin, f);
        ft.commit();
    }
}