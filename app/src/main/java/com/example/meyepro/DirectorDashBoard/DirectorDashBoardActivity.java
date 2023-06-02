package com.example.meyepro.DirectorDashBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;

import com.example.meyepro.DirectorDashBoard.Home.DirectorDashBoardHomeFragment;
import com.example.meyepro.DirectorDashBoard.Home.DirectorHomeSwitchFActivityragment;
import com.example.meyepro.DirectorDashBoard.Home.DirectorHomeViewCHRFragment;
import com.example.meyepro.R;
import com.example.meyepro.StudentDashBoard.Course.StudentCourseFragment;
import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.ActivityDirectorDashBoardBinding;
import com.example.meyepro.models.MEYE_USER;
import com.google.gson.Gson;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;

public class DirectorDashBoardActivity extends AppCompatActivity {
ActivityDirectorDashBoardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDirectorDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String IntentData= getIntent().getStringExtra("IntentData");
        MEYE_USER user= new Gson().fromJson(IntentData,MEYE_USER.class);
        binding.txtViewDirectorName.setText(user.getName());
        if(!user.getImage().isEmpty()){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+"Director"+"/"+user.getImage())).
                    resize(80, 80) // Set the desired target size
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .resize(100, 100)
                    .centerCrop()
                    .noFade()
                    .into(binding.profileImageStudent);
            // Convert Base64 string to byte array


        }

        // Create a new Bundle object with arguments


        // Create a new instance of the StudentCourseFragment class
        DirectorHomeSwitchFActivityragment fragment = new DirectorHomeSwitchFActivityragment();
        Bundle bundle = new Bundle();
        bundle.putString("IntentData", IntentData);
        // Set the arguments for the fragment
        fragment.setArguments(bundle);

        // Load the fragment
        loadFragment(fragment);
    }

    public void onBackPressed() {
        // View mBottomNavigationView = requireActivity().findViewById(R.id.bottom_nav_admin);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frament_container_Director);
        if (fragment instanceof DirectorHomeViewCHRFragment) {
            loadFragment(new DirectorHomeSwitchFActivityragment());
        }
        else { //
             super.onBackPressed();
//            onBackPressed();
        }

    }
    private void loadFragment(Fragment f){
        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();

        //manage back track
//        ft.add(R.id.frament_container_admin, f);
        ft.replace(R.id.frament_container_Director, f);
        //   ft.addToBackStack(null);
        ft.commit();
    }
}