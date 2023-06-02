package com.example.meyepro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityAdminLoginBinding;
import com.example.meyepro.fragments.Admin.AdminHomeFragment;
import com.example.meyepro.fragments.Admin.AdminScheduleFragment;
import com.example.meyepro.fragments.Admin.AdminSettingFragment;
import com.example.meyepro.fragments.Admin.AdminViewFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminLogin extends AppCompatActivity {
ActivityAdminLoginBinding Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityAdminLoginBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        loadFragment(new AdminHomeFragment());
        FragmentManager fm= getSupportFragmentManager();
       // getSupportActionBar().setTitle("Whatever title");
        String IntentData= getIntent().getStringExtra("IntentData");
        Binding.bottomNavAdmin.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.action_Admin_Home:
                    //    fm.popBackStack(0,FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        loadFragment(new AdminHomeFragment());
                     //   ft.addToBackStack("0");
                        break;
                    case R.id.action_Admin_View:
                        loadFragment(new AdminViewFragment());
                       // ft.addToBackStack(null);
                        break;
                    case R.id.action_Admin_Setting:
                        loadFragment(new AdminSettingFragment());
                        break;

                    default:
                        AdminScheduleFragment adminScheduleFragment= new AdminScheduleFragment();
                        Bundle bundle= new Bundle();
                        bundle.putString("FragmentData",IntentData);
                        adminScheduleFragment.setArguments(bundle);
                        loadFragment(adminScheduleFragment);
                        break;
                }
                return true;
            }
        });


    }
    @Override public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frament_container_admin);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_admin);
//        if ((fragment instanceof AdminViewFragment)) {
//            bottomNavigationView.setSelectedItemId(R.id.action_Admin_Home);
//          // loadFragment(new AdminHomeFragment());
//        }
       if ((fragment instanceof AdminSettingFragment)) {
            bottomNavigationView.setSelectedItemId(R.id.action_Admin_View);
            // loadFragment(new AdminHomeFragment());
        }
      else  if ((fragment instanceof AdminScheduleFragment)) {
            bottomNavigationView.setSelectedItemId(R.id.action_Admin_Setting);
            // loadFragment(new AdminHomeFragment());
        }
        else { super.onBackPressed();
        }
    }

//    @Override
//    public void onBackPressed() {
//        // Get the bottom navigation view
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_admin);
//
//        // Check if the current selected item is not the home item
//        if (bottomNavigationView.getSelectedItemId() != R.id.action_Admin_Home) {
//            // Select the home item
//            bottomNavigationView.setSelectedItemId(R.id.action_Admin_Home);
//        }
//        if (bottomNavigationView.getSelectedItemId() != R.id.action_Admin_View) {
//            // Select the home item
//            bottomNavigationView.setSelectedItemId(R.id.action_Admin_View);
//        }
//        if (bottomNavigationView.getSelectedItemId() != R.id.action_Admin_Setting) {
//            // Select the home item
//            bottomNavigationView.setSelectedItemId(R.id.action_Admin_Setting);
//        }else {
//            // Allow the default back button behavior to execute
//            super.onBackPressed();
//        }
//    }

    private void loadFragment(Fragment f){
        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();

        //manage back track
//        ft.add(R.id.frament_container_admin, f);
        ft.replace(R.id.frament_container_admin, f);
     //   ft.addToBackStack(null);
        ft.commit();
    }
void Backpress(){

}
//    @Override
//    public void onResume() {
//        super.onResume();
//
//        // Set selected item in Bottom Navigation based on currently displayed Fragment
//        if (Binding.bottomNavAdmin != null) {
//            switch (this.getTaskId()) {
//                case R.id.action_Admin_Home:
//                    Binding.bottomNavAdmin.setSelectedItemId(R.id.action_Admin_Home);
//                    break;
//                case  R.id.action_Admin_View:
//                    Binding.bottomNavAdmin.setSelectedItemId(R.id.action_Admin_View);
//                    break;
//                case R.id.action_Admin_Setting:
//                    Binding.bottomNavAdmin.setSelectedItemId(R.id.action_Admin_Setting);
//                    break;
//                default:
//                    Binding.bottomNavAdmin.setSelectedItemId(R.id.action_Admin_Scheduale);
//                    break;
//                // Add cases for other Fragments here
//            }
//        }
//    }
//
//
//    public void onDetach() {
//      //  super.onDetach();
//
//        // Reset selected item in Bottom Navigation when Fragment is detached
//        if (Binding.bottomNavAdmin != null) {
//            Binding.bottomNavAdmin.setSelectedItemId(0);
//        }
//    }
//public void onBackPressed() {
//        // Handle back button press here
//        // ...
//
//        // Call super.onBackPressed() to allow the default behavior
//    super.onBackPressed();
//    }

}