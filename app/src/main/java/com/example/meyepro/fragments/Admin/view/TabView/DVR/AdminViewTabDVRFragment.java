package com.example.meyepro.fragments.Admin.view.TabView.DVR;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meyepro.R;
import com.example.meyepro.adapters.AdminViewDVRAdapter;
import com.example.meyepro.databinding.FragmentAdminViewTabDVRBinding;
import com.example.meyepro.models.DVR;

import java.util.ArrayList;


public class AdminViewTabDVRFragment extends Fragment {
FragmentAdminViewTabDVRBinding Binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding= FragmentAdminViewTabDVRBinding.inflate(getLayoutInflater());
        //code start
        ArrayList<DVR> data = new ArrayList<DVR>();
        data.add(new DVR());
        data.get(0).setNAME("DVR 1");
        data.get(0).setHOST("admin");
        data.get(0).setIP("192.168.2.1");
        data.add(new DVR());
        data.get(1).setNAME("DVR 2");
        data.get(1).setHOST("admin");
        data.get(1).setIP("192.168.3.1");
        AdminViewDVRAdapter adapter = new
                AdminViewDVRAdapter (data, getActivity() , AdminViewTabDVRFragment.this);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        Binding.RecycerviewAdminViewDVR.setLayoutManager(manager);
        //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
        Binding.RecycerviewAdminViewDVR.
                setAdapter(adapter);
        //code end
        return Binding.getRoot();
    }
    // view calinng DVRadapter
    public void recyclerviewAdminViewTabDVRCellClick(DVR obj, Context context) {
        loadFragment( new AdminViewTabDVRClickFragment());
    }
    //calling fragment
    private void loadFragment(Fragment f) {
        FragmentTransaction ft =
                getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frament_container_admin, f);
        ft.commit();
    }
}