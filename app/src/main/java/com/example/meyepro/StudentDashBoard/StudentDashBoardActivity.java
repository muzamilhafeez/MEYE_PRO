package com.example.meyepro.StudentDashBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.StudentDashBoard.Adapter.StudentCourseAdapter;
import com.example.meyepro.StudentDashBoard.Course.StudentCourseFragment;
import com.example.meyepro.StudentDashBoard.Model.StudentCourse;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityStudentDashBoardBinding;
import com.example.meyepro.models.MEYE_USER;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentDashBoardActivity extends AppCompatActivity {
ActivityStudentDashBoardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityStudentDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String IntentData= getIntent().getStringExtra("IntentData");
        MEYE_USER user= new Gson().fromJson(IntentData,MEYE_USER.class);
        binding.txtViewStudentName.setText(user.getName());
        if(!user.getImage().isEmpty()){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+"Student"+"/"+user.getImage())).into(binding.profileImageStudent);
        }
        // Create a new Bundle object with arguments
        Bundle bundle = new Bundle();
        bundle.putString("IntentData", IntentData);

        // Create a new instance of the StudentCourseFragment class
        StudentCourseFragment fragment = new StudentCourseFragment();

        // Set the arguments for the fragment
        fragment.setArguments(bundle);

        // Load the fragment
        loadFragment(fragment);
    }

    private void loadFragment(Fragment f){
        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();

        //manage back track
//        ft.add(R.id.frament_container_admin, f);
        ft.replace(R.id.frament_container_admin, f);
        //   ft.addToBackStack(null);
        ft.commit();
    }
}