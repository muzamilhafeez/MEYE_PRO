package com.example.meyepro.TeacherDashBoard.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.StudentDashBoard.Adapter.StudentNotificationAdapter;
import com.example.meyepro.StudentDashBoard.Model.StudentNotification;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityTeacherNotificationApprovalBinding;
import com.example.meyepro.login;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherNotificationApprovalActivity extends AppCompatActivity {
ActivityTeacherNotificationApprovalBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityTeacherNotificationApprovalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

                RetrofitClient retrofitClient= RetrofitClient.getInstance();
        Api api= retrofitClient.getMyApi();
        String userID = login.userInfoDetail.getUserID();
        String[] userIDParts = userID.split("\\.");
        Toast.makeText(this, ""+ login.userInfoDetail.getName(), Toast.LENGTH_SHORT).show();
        api.api_get_teacher_notification_data(login.userInfoDetail.getName()).enqueue(new Callback<ArrayList<StudentNotification>>() {
            @Override
            public void onResponse(Call<ArrayList<StudentNotification>> call, Response<ArrayList<StudentNotification>> response) {
                Toast.makeText(getApplicationContext(), ""+response.code(), Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), ""+response.body().size(), Toast.LENGTH_SHORT).show();
                    StudentNotificationAdapter adapter = new
                            StudentNotificationAdapter(response.body(), TeacherNotificationApprovalActivity.this,2);
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    binding.RecycerviewTeacherNotification.setLayoutManager(manager);
                    binding.RecycerviewTeacherNotification.setHasFixedSize(true);
                    binding.RecycerviewTeacherNotification.
                            setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<StudentNotification>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}