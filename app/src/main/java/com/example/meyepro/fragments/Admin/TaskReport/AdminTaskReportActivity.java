package com.example.meyepro.fragments.Admin.TaskReport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.meyepro.TeacherDashBoard.CHR.teacherCHRClaimVideoActivity;
import com.example.meyepro.adapters.CliamVideoAdapter;
import com.example.meyepro.adapters.TaskReportShowAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityAdminTaskReportBinding;
import com.example.meyepro.models.TaskReport;
import com.example.meyepro.models.TeacherCHRActivityDetails;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminTaskReportActivity extends AppCompatActivity {
ActivityAdminTaskReportBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAdminTaskReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        Api api= retrofitClient.getMyApi();
        api.api_taskreport().enqueue(new Callback<ArrayList<TaskReport>>() {
            @Override
            public void onResponse(Call<ArrayList<TaskReport>> call, Response<ArrayList<TaskReport>> response) {
                if (response.isSuccessful()){

                    ArrayList<TaskReport> list= new ArrayList<>();
                    for (TaskReport teacher :response.body()) {
                        if(teacher.getShown()){
                            list.add(teacher);
                        }
                    }
                    TaskReportShowAdapter adapter = new
                            TaskReportShowAdapter(list, AdminTaskReportActivity.this);
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    binding.RecycerviewAdminTaskReport.setLayoutManager(manager);
                    binding.RecycerviewAdminTaskReport.setHasFixedSize(true);
                    binding.RecycerviewAdminTaskReport.
                            setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TaskReport>> call, Throwable t) {
                Toast.makeText(AdminTaskReportActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void recyclerViewClickCell(TaskReport obj, Context context) {
        Intent i = new Intent(getApplicationContext(), AdminTaskReportDetailsActivity.class);
        i.putExtra("key", new Gson().toJson(obj));
        startActivity(i);

    }
}