package com.example.meyepro.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.meyepro.fragments.Admin.view.TabView.DVR.AdminViewTabDVRFragment;
import com.example.meyepro.fragments.Admin.view.TabView.Venue.AdminViewTabVenueFragment;
import com.example.meyepro.fragments.Admin.view.Teacher.ViewTeacherFragment;

public class TabPagerAdapter extends FragmentStateAdapter {

    public TabPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0){
            return new ViewTeacherFragment();
        }
        if(position==1){
            return new AdminViewTabVenueFragment();
        }
        else {
            return   new AdminViewTabDVRFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
