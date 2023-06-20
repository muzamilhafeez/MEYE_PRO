package com.example.meyepro.fragments.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meyepro.DirectorDashBoard.DirectorDashBoardActivity;
import com.example.meyepro.R;
import com.example.meyepro.databinding.FragmentAdminSettingBinding;
import com.example.meyepro.fragments.Admin.Setting.AssignCourse.AdminSettingAssignCourseActivity;
import com.example.meyepro.fragments.Admin.Setting.CameraSetting.AdminSettingCameraSettingClickFragment;
import com.example.meyepro.fragments.Admin.Setting.DemoVideo.AdminSettingTeacherVideoDemoActivity;
import com.example.meyepro.fragments.Admin.Setting.RuleSetting.AdminSettingRuleSettingClickFragment;
import com.example.meyepro.fragments.Admin.Setting.TaskDemo.AdminTaskDemoActivity;
import com.example.meyepro.fragments.Admin.Setting.TaskDemo.AdminTaskDemoDetailsShowActivity;
import com.example.meyepro.fragments.Admin.Setting.TimeTableUpload.TimetableUploadActivity;
import com.example.meyepro.fragments.Admin.Setting.UserDetails.UserInformationFragment;
import com.example.meyepro.fragments.Admin.TaskReport.AdminTaskReportActivity;


public class AdminSettingFragment extends Fragment {

   public static String   ReportView= null;
FragmentAdminSettingBinding Binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding = FragmentAdminSettingBinding.inflate(inflater, container, false);
        //code

        Binding.txtViewAdminTaskDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AdminTaskDemoActivity.class);
                startActivity(i);
            }
        });
        Binding.txtViewAdminTaskReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getContext(), AdminTaskReportActivity.class);
                startActivity(i);
            }
        });
        Binding.txtViewAdminSettingReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReportView="Admin";
                Intent i= new Intent(getContext(), DirectorDashBoardActivity.class);
                startActivity(i);
            }
        });
        //rule setting listener
        Binding.txtViewAdminSettingRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new AdminSettingRuleSettingClickFragment());
            }
        });
        //rule setting camera listener
        Binding.txtViewAdminSettingRuleCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new AdminSettingCameraSettingClickFragment());
            }
        });

        Binding.txtViewAdminSettingUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new UserInformationFragment());
            }
        });
//        Binding.txtViewAdminSettingTimetableUpload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(getContext(), TimetableUploadActivity.class);
//                startActivity(i);
//            }
//        });

        Binding.txtViewAdminSettingAssignCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AdminSettingAssignCourseActivity.class);
                startActivity(i);
            }
        });

        Binding.txtViewAdminSettingTeacherDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AdminSettingTeacherVideoDemoActivity.class);
                startActivity(i);
            }
        });
        //code end
        return  Binding.getRoot();
    }
    //calling fragment
    private void loadFragment(Fragment f) {
        FragmentTransaction ft =
                getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frament_container_admin, f);
        ft.commit();
    }

}