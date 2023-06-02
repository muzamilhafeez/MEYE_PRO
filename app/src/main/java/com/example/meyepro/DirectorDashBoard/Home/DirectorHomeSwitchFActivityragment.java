package com.example.meyepro.DirectorDashBoard.Home;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.databinding.FragmentDirectorDataTableBinding;
import com.example.meyepro.databinding.FragmentDirectorHomeSwitchFActivityragmentBinding;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;


public class DirectorHomeSwitchFActivityragment extends Fragment {
FragmentDirectorHomeSwitchFActivityragmentBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentDirectorHomeSwitchFActivityragmentBinding.inflate(getLayoutInflater());
        // Inflate the layout for this fragment

        //start code
        DirectorDashBoardHomeFragment m= new DirectorDashBoardHomeFragment();
        Bundle bundle = new Bundle();
//      String intent=  getArguments().getString("IntentData");
//      bundle.putString("IntentData",intent);
        loadFragment(m);
        binding.fabMenuItem1.setVisibility(View.GONE);    // true , false
        binding.fabMenuItem2.setVisibility(View.GONE);
        SpeedDialView speedDialView = binding.speedDial;
        SpeedDialActionItem fabMenuItem1 = new SpeedDialActionItem.Builder(R.id.fabMenuItem1, R.drawable.table_icon)
                .setLabel("Switch to DataTable")
                .setFabImageTintColor(getResources().getColor(R.color.blue_500))
                .setFabBackgroundColor(Color.WHITE)
                .create();
        SpeedDialActionItem fabMenuItem2 = new SpeedDialActionItem.Builder(R.id.fabMenuItem2, R.drawable.swap_box_icon)
                .setLabel("Switch to CHR")
                .setFabImageTintColor(getResources().getColor(R.color.blue_500))
                .setFabBackgroundColor(Color.WHITE)
                .create();

        speedDialView.addActionItem(fabMenuItem1);
        speedDialView.setElevation(0);
        speedDialView.setSelected(false);
        speedDialView.addActionItem(fabMenuItem2);
         // Remove shadow

//        SpeedDialActionItem fabMenuItemcheck=        speedDialView.getActionItems().get(0);

//        speedDialView.
        //if using support app compat
        // Set elevation to 0 to remove shadow
//        fabMenuItem1.setCompatElevation(16.0f);
        // Add the following code after setting the elevation to 0
        speedDialView.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            private boolean isFirstSelection = false;
            @Override
            public boolean onActionSelected(SpeedDialActionItem speedDialActionItem) {
                if (isFirstSelection) {
                    isFirstSelection = false;
                    Drawable fabImage = speedDialActionItem.getFabImageDrawable(getContext());
                    if (fabImage != null) {
                        fabImage.setCallback(null); // Remove the callback to prevent memory leaks
                        fabImage.setCallback(speedDialView); // Set the SpeedDialView as the new callback
                        fabImage.setAlpha(255); // Reset the alpha value to its original state
                        fabImage.clearColorFilter(); // Clear any applied color filter
                    }
                }

                switch (speedDialActionItem.getId()) {
                    case R.id.fabMenuItem1:
                        // Handle click on Item 1
                        loadFragment(new DirectorDataTableFragment());
                        break;
                    case R.id.fabMenuItem2:
                        // Handle click on Item 2
                        DirectorDashBoardHomeFragment m = new DirectorDashBoardHomeFragment();
                        loadFragment(m);
                        break;
                }
                return false;
            }
        });


        //end code
        return binding.getRoot();
    }
    private void loadFragment(Fragment f){
        FragmentTransaction ft =
                getActivity().getSupportFragmentManager().beginTransaction();
        //manage back track
//        ft.add(R.id.frament_container_admin, f);
        ft.replace(R.id.frame_layout_switch_activty, f);
        //   ft.addToBackStack(null);
        ft.commit();
    }
}