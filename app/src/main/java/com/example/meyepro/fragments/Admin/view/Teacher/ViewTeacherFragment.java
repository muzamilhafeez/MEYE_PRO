package com.example.meyepro.fragments.Admin.view.Teacher;

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
import com.example.meyepro.databinding.FragmentViewTeacherBinding;
import com.example.meyepro.fragments.Admin.view.Teacher.Teacher.AdminViewTabTeacherScheduleFragment;
import com.example.meyepro.fragments.Admin.view.Teacher.Teacher.AdminViewTabTecherRecodingsFragment;
import com.example.meyepro.models.MEYE_USER;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;


public class ViewTeacherFragment extends Fragment {
    BottomSheetDialogFragment bottomSheetDialog;
FragmentViewTeacherBinding Binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding = FragmentViewTeacherBinding.inflate(inflater, container, false);
        //code
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
                AdminViewTeacherAdapter(data, getActivity(), ViewTeacherFragment.this,"ViewTeacherFragment");
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        Binding.RecycerviewAdminViewTeacher.setLayoutManager(manager);
        //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
        Binding.RecycerviewAdminViewTeacher.
                setAdapter(adapter);

        return  Binding.getRoot();

      //  bottomSheetDialog= new BottomSheetDialog(getActivity().getApplicationContext());

    }
// calling AdminviewTeacher Adapter click
    public void recyclerviewAdminViewTeacherCellClick(MEYE_USER obj, Context context) {
        //bottomsheet calling
   // BottomsheetDialogTeacherBinding bindingTeacher= BottomsheetDialogTeacherBinding.inflate(getLayoutInflater());
        View bottomsheet_teacher = getLayoutInflater().inflate(R.layout.bottomsheet_dialog_teacher,null);
        BottomSheetDialog dialog = new BottomSheetDialog(getContext(),R.style.BottomSheetStyle);
        bottomsheet_teacher.findViewById(R.id.txt_Recodings_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new AdminViewTabTecherRecodingsFragment());
               // Toast.makeText(getActivity(), "dafds", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        bottomsheet_teacher.findViewById(R.id.txt_Schedule_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new AdminViewTabTeacherScheduleFragment());
                // Toast.makeText(getActivity(), "dafds", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.setContentView(bottomsheet_teacher);
        dialog.show();




    }
    //calling fragment
    private void loadFragment(Fragment f) {
        FragmentTransaction ft =
                getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frament_container_admin, f);
        ft.commit();
    }
}