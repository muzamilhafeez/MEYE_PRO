package com.example.meyepro.fragments.Admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.meyepro.R;
import com.example.meyepro.adapters.AdminViewTeacherAdapter;
import com.example.meyepro.adapters.TabPagerAdapter;
import com.example.meyepro.databinding.FragmentAdminHomeBinding;
import com.example.meyepro.databinding.FragmentAdminViewBinding;
import com.example.meyepro.fragments.Admin.AdminHomeFragment;
import com.example.meyepro.models.MEYE_USER;
import com.example.meyepro.models.Venue;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;



public class AdminViewFragment extends Fragment {
FragmentAdminViewBinding Binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding = FragmentAdminViewBinding.inflate(inflater, container, false);
        //code
        Binding.TabLayout.addTab(
                Binding.TabLayout.newTab()
                        .setText("Teacher"));
        Binding.TabLayout.addTab(
                Binding.TabLayout.newTab()
                        .setText("Venue"));
        Binding.TabLayout.addTab(
                Binding.TabLayout.newTab()
                        .setText("DVR"));
        //create tap object
        TabPagerAdapter adapter =
                new TabPagerAdapter(
                        getActivity().getSupportFragmentManager(),
                        getLifecycle()
                );
        Binding.viewpagerContainerTab.setAdapter(adapter);
        //end tap object
        //pager change calling
        Binding.viewpagerContainerTab.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Binding.TabLayout.selectTab(
                        Binding.TabLayout.getTabAt(position));

            }
        });
        //pager change calling end
        Binding.TabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Binding.viewpagerContainerTab.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //code
        return  Binding.getRoot();
    }
    private void loadFragment(Fragment f) {
        FragmentTransaction ft =
                getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frament_container_admin, f);
        ft.commit();
    }

//    //on back press
//    @Override public void onBackPressed() {
//        Fragment fragment = requireActivity().getgetSupportFragmentManager().findFragmentById(R.id.fragment_container);
//        if (!(fragment instanceof HomeFragment)) {
//            loadFragment(new HomeFragment());
//        }
//        else { super.onBackPressed();
//        }
//    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        System.out.println("jjjjjjjj");
//        View view = inflater.inflate(R.layout.fragment_admin_view, container, false);
//        Toolbar toolbar = view.findViewById(R.id.fragment_admin_view);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        return view;
//    }
//public void onBackPressed() {
//    System.out.println("jjjjjjjj");
//    // View mBottomNavigationView = requireActivity().findViewById(R.id.bottom_nav_admin);
//    Fragment fragment = requireActivity().getSupportFragmentManager().findFragmentById(R.id.frament_container_admin);
//    if (!(fragment instanceof AdminViewFragment)) {
//        loadFragment(new AdminHomeFragment());
//      System.out.println("jjjjjjjj");
//    }
//    else { //super.onBackPressed();
//        //super.requireActivity().onBackPressed();
//    }
//
//}


}