package com.example.meyepro.fragments.Admin.Setting.TaskDemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.meyepro.adapters.AdminDemoTeacherCHRAdapter;
import com.example.meyepro.adapters.TaskReportShowAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityAdminTaskDemoBinding;
import com.example.meyepro.fragments.Admin.Setting.DemoVideo.AdminSettingTeacherVideoDemoActivity;
import com.example.meyepro.fragments.Admin.TaskReport.AdminTaskReportActivity;
import com.example.meyepro.models.TaskReport;
import com.example.meyepro.models.TeacherDemoCHR;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminTaskDemoActivity extends AppCompatActivity {
ActivityAdminTaskDemoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminTaskDemoBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        RetrofitClient retrofitClient= RetrofitClient.getInstance();
        Api api= retrofitClient.getMyApi();
        api.api_demo().enqueue(new Callback<ArrayList<TeacherDemoCHR>>() {
            @Override
            public void onResponse(Call<ArrayList<TeacherDemoCHR>> call, Response<ArrayList<TeacherDemoCHR>> response) {
                if(response.isSuccessful()){
                    AdminDemoTeacherCHRAdapter adapter = new
                            AdminDemoTeacherCHRAdapter(response.body(), AdminTaskDemoActivity.this,"AdminTaskDemoActivity");
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    binding.RecycerviewAdminSettingTaskDemo.setLayoutManager(manager);
                    binding.RecycerviewAdminSettingTaskDemo.setHasFixedSize(true);
                    binding.RecycerviewAdminSettingTaskDemo.
                            setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TeacherDemoCHR>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), ""+t, Toast.LENGTH_SHORT).show();
            }
        });

    }
}