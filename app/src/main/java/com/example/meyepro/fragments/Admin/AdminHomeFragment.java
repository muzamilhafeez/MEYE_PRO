package com.example.meyepro.fragments.Admin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meyepro.R;
import com.example.meyepro.adapters.AdminHomeAdapter;
import com.example.meyepro.databinding.FragmentAdminHomeBinding;
import com.example.meyepro.fragments.Admin.Hone.AdminHomeCameraClickDetailFragment;
import com.example.meyepro.models.CAMERA;

import java.util.ArrayList;

public class AdminHomeFragment extends Fragment {
FragmentAdminHomeBinding Binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        Binding = FragmentAdminHomeBinding.inflate(getLayoutInflater());
        //code
        Binding.imageviewAdminHomeSearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start alterDialog


                AlertDialog.Builder builde =new AlertDialog.Builder(getActivity());
                builde.setTitle("Search");
                String [] choice= new String[]{"Search by ID ","Search By Venue","Search by Section"};
                //builder (array, click listener create)
                builde.setItems(choice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //list first item select in i
                        if(i==0){

                        }
                    }
                });
                //builde show
                builde.show();
                //end alterDialog
            }
        });
        //search icon end
        ArrayList<CAMERA> data = new ArrayList<CAMERA>();
        //Venue v= new Venue(8,"kkk");
        data.add(new CAMERA());
        data.get(0).setNO("Lab 1");
        data.add(new CAMERA());
        data.get(1).setNO("Lab 2");
        data.add(new CAMERA());
        data.get(2).setNO("LT 3");
       // data.add(v);
        AdminHomeAdapter adapter = new
                AdminHomeAdapter(data, getActivity(),AdminHomeFragment.this);
        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
       // LinearLayoutManager manager = new LinearLayoutManager(getActivity());
       Binding.RecycerviewAdminHome.setLayoutManager(manager);
        Binding.RecycerviewAdminHome.setHasFixedSize(true);
        Binding.RecycerviewAdminHome.
                setAdapter(adapter);

        return  Binding.getRoot();
    }

    //recyclerview for calling Adapter
    public void recyclerviewAdminHomeCellClick(CAMERA obj, Context context) {
        AdminHomeCameraClickDetailFragment m= new AdminHomeCameraClickDetailFragment();
        Bundle args = new Bundle();
        ArrayList<CAMERA> data = new ArrayList<CAMERA>();
        data.add(obj);
        args.putSerializable("id",data);
        m.setArguments(args);
        loadFragment(m);
    }
    //recyclerview for calling Adapter
    //Freagmet calling
    private void loadFragment(Fragment f) {
        FragmentTransaction ft =
                getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frament_container_admin, f);
        ft.commit();
    }
    //Freagmet calling end
}