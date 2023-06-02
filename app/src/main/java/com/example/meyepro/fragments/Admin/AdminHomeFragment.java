package com.example.meyepro.fragments.Admin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.meyepro.R;
import com.example.meyepro.adapters.AdminHomeAdapter;
import com.example.meyepro.adapters.LiveCameraViewAdapter.LiveCamera;
import com.example.meyepro.adapters.LiveCameraViewAdapter.LiveCameraAdapter;
import com.example.meyepro.databinding.FragmentAdminHomeBinding;
import com.example.meyepro.fragments.Admin.Hone.AdminHomeCameraClickDetailFragment;
import com.example.meyepro.models.CAMERA;

import org.videolan.libvlc.LibVLC;

import java.util.ArrayList;
import java.util.List;

public class AdminHomeFragment extends Fragment {
FragmentAdminHomeBinding Binding;
    private RecyclerView recyclerView;
    private LiveCameraAdapter cameraAdapter;
    private LibVLC libVLC;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        Binding = FragmentAdminHomeBinding.inflate(getLayoutInflater());
        //code
        //btn search
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
        //Live camera display
        // Initialize the libvlc library
        ArrayList<String> options = new ArrayList<>();
        options.add("--no-drop-late-frames");
        options.add("--no-skip-frames");
        libVLC = new LibVLC(getContext(), options);

        // Initialize the RecyclerView
      //  recyclerView = findViewById(R.id.streams_recycler_view21);
        GridLayoutManager manager = new GridLayoutManager(getActivity(),1);
//        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
       Binding.RecycerviewAdminHome.setLayoutManager(manager);
     // Binding.RecycerviewAdminHome.setHasFixedSize(true);
      //Binding.RecycerviewAdminHome.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the camera adapter
        List<LiveCamera> cameras = new ArrayList<>();
//        cameras.add(new LiveCamera("Camera 1", "http://192.168.235.67:8000/video?path=Recordings/file,63,start_recording.mp4"));
        cameras.add(new LiveCamera("Lab", "http://192.168.43.35:8080/video"));
        cameras.add(new LiveCamera("Lab", "http://192.168.43.26:8080/video"));
      //  cameras.add(new LiveCamera("Camera 3", "rtsp://192.168.100.27:8080/"));
        cameraAdapter = new LiveCameraAdapter(getContext(),libVLC,cameras,AdminHomeFragment.this);
        Binding.RecycerviewAdminHome.setAdapter(cameraAdapter);
//        ArrayList<CAMERA> data = new ArrayList<CAMERA>();
//        //Venue v= new Venue(8,"kkk");
//        data.add(new CAMERA());
//        data.get(0).setNO("Lab 1");
//        data.add(new CAMERA());
//        data.get(1).setNO("Lab 2");
//        data.add(new CAMERA());
//        data.get(2).setNO("LT 3");
       // data.add(v);

//
//        AdminHomeAdapter adapter = new
//                AdminHomeAdapter(data, getActivity(),AdminHomeFragment.this);
//        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
//       // LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//       Binding.RecycerviewAdminHome.setLayoutManager(manager);
//        Binding.RecycerviewAdminHome.setHasFixedSize(true);
//        Binding.RecycerviewAdminHome.
//                setAdapter(adapter);


        //end code
        return  Binding.getRoot();
    }

    //recyclerview for calling Adapter
    public void recyclerviewAdminHomeCellClick(Context context) {
        AdminHomeCameraClickDetailFragment m= new AdminHomeCameraClickDetailFragment();

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

    public void recyclerviewAdminHomeViewLiveCellClick(Fragment context) {
//        Toast.makeText(, "ii", Toast.LENGTH_SHORT).show();
        AdminHomeCameraClickDetailFragment m= new AdminHomeCameraClickDetailFragment();
        loadFragment(m);
    }
    //Freagmet calling end

}