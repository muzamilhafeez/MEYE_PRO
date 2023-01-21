package com.example.meyepro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.meyepro.databinding.ActivityAdminLoginBinding;
import com.example.meyepro.fragments.Admin.AdminHomeFragment;
import com.example.meyepro.fragments.Admin.AdminScheduleFragment;
import com.example.meyepro.fragments.Admin.AdminSettingFragment;
import com.example.meyepro.fragments.Admin.AdminViewFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminLogin extends AppCompatActivity {
ActivityAdminLoginBinding Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityAdminLoginBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        loadFragment(new AdminHomeFragment());
       // getSupportActionBar().setTitle("Whatever title");
        Binding.bottomNavAdmin.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_Admin_Home:
                        loadFragment(new AdminHomeFragment());
                        break;
                    case R.id.action_Admin_View:
                        loadFragment(new AdminViewFragment());
                        break;
                    case R.id.action_Admin_Setting:
                        loadFragment(new AdminSettingFragment());
                        break;

                    default:
                        loadFragment(new AdminScheduleFragment());
                        break;
                }
                return true;
            }
        });

    }
    private void loadFragment(Fragment f){
        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frament_container_admin, f);
        ft.commit();
    }
}