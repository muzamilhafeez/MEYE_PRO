package com.example.meyepro.StudentDashBoard.Notification;

import android.os.Bundle;

import com.example.meyepro.MainActivity;
import com.example.meyepro.StudentDashBoard.Adapter.StudentNotificationAdapter;
import com.example.meyepro.StudentDashBoard.Model.StudentNotification;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityStudentNotificationBinding;
import com.example.meyepro.login;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.meyepro.R;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentNotificationActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityStudentNotificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStudentNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_student_notification);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        //
        String test="[\n" +
                "  {\n" +
                "    \"id\": 11,\n" +
                "    \"name\": \"Dr. Hassan,Waseem\",\n" +
                "    \"startTime\": \"11:30:00.0000000\",\n" +
                "    \"endTime\": \"01:00:00.0000000\",\n" +
                "    \"courseName\": \"AI\",\n" +
                "    \"day\": \"Friday\",\n" +
                "    \"date\": \"2023-06-13\",\n" +
                "    \"image\": \"image_picker1128525661.jpg\"\n" +
                "  }\n" +
                "]";
        RetrofitClient retrofitClient= RetrofitClient.getInstance();
        Api api= retrofitClient.getMyApi();
//        Toast.makeText(this, ""+login.userInfoDetail.getUserID(), Toast.LENGTH_SHORT).show();
        api.api_get_student_notification_data(login.userInfoDetail.getUserID()).enqueue(new Callback<ArrayList<StudentNotification>>() {
            @Override
            public void onResponse(Call<ArrayList<StudentNotification>> call, Response<ArrayList<StudentNotification>> response) {
//                Toast.makeText(StudentNotificationActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()){
//                    Toast.makeText(StudentNotificationActivity.this, ""+response.body().size(), Toast.LENGTH_SHORT).show();
                    StudentNotificationAdapter adapter = new
                            StudentNotificationAdapter(response.body(), StudentNotificationActivity.this);
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    binding.RecycerviewStudentNotification.setLayoutManager(manager);
                    binding.RecycerviewStudentNotification.setHasFixedSize(true);
                    binding.RecycerviewStudentNotification.
                            setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<StudentNotification>> call, Throwable t) {
                Toast.makeText(StudentNotificationActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });

//        ArrayList<StudentNotification> list= new Gson().fromJson(test,new TypeToken<ArrayList<StudentNotification>>(){}.getType());
//        StudentNotificationAdapter adapter = new
//                            StudentNotificationAdapter(list, StudentNotificationActivity.this,2);
//                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
//                    binding.RecycerviewStudentNotification.setLayoutManager(manager);
//                    binding.RecycerviewStudentNotification.setHasFixedSize(true);
//                    binding.RecycerviewStudentNotification.
//                            setAdapter(adapter);

    }


}