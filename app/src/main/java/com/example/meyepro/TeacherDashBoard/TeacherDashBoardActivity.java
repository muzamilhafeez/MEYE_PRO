package com.example.meyepro.TeacherDashBoard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.TeacherDashBoard.Attendance.TeacherAttendanceMarkActivity;
import com.example.meyepro.TeacherDashBoard.CHR.TeacherCHRFragment;
import com.example.meyepro.TeacherDashBoard.Home.TeacherDashBoardDetailFragment;
import com.example.meyepro.TeacherDashBoard.TaskReport.TeacherTaskReportFragment;
import com.example.meyepro.databinding.ActivityTeacherDashBoardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class TeacherDashBoardActivity extends AppCompatActivity {
ActivityTeacherDashBoardBinding Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding= ActivityTeacherDashBoardBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        String Intendata= getIntent().getStringExtra("IntentData");
        TeacherDashBoardDetailFragment fragment= new TeacherDashBoardDetailFragment();
        Bundle bundle= new Bundle();
        bundle.putString("FragmentData",Intendata);
        fragment.setArguments(bundle);
        loadFragment(fragment);
        Binding.bottomNavTeacher.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_Teacher_Home:
                        TeacherDashBoardDetailFragment fragment= new TeacherDashBoardDetailFragment();
                        Bundle bundle= new Bundle();
                        bundle.putString("FragmentData",Intendata);
                        fragment.setArguments(bundle);
                        loadFragment(fragment);
                        break;
                    case R.id.action_Teacher_Attendance:
                        Intent i = new Intent(getApplicationContext(),TeacherAttendanceMarkActivity.class);
                        i.putExtra("IntentData",Intendata);
                        startActivity(i);
//                        loadFragment(new TeacherAttendanceMarkActivity());
                        break;

                    case R.id.action_Teacher_Task_Report:
                        loadFragment(new TeacherTaskReportFragment());
//                        loadFragment(new TeacherAttendanceMarkActivity());
                        break;

                    default:
                        TeacherCHRFragment teacherCHRFragment= new TeacherCHRFragment();
                        Bundle bundleteacher= new Bundle();
                        bundleteacher.putString("FragmentData",Intendata);
                        teacherCHRFragment.setArguments(bundleteacher);
                        loadFragment(teacherCHRFragment);
                        break;
                }
                return true;
            }
        });
//
//        String imageName= pices[0][0].name
//        ArrayList<Drawable> drawables= new ArrayList<>();
//        drawables.add(imageName)

    }

    //calling fragment
    private void loadFragment(Fragment f){
        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frament_container_Teacher, f);
        ft.commit();
    }
}