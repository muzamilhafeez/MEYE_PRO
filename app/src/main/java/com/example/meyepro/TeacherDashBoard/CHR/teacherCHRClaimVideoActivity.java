package com.example.meyepro.TeacherDashBoard.CHR;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.meyepro.adapters.CliamVideoAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityTeacherChrclaimVideoBinding;
import com.example.meyepro.models.ClaimVideo;
import com.example.meyepro.models.DemoVideosResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class teacherCHRClaimVideoActivity extends AppCompatActivity {
ActivityTeacherChrclaimVideoBinding Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding= ActivityTeacherChrclaimVideoBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());


        RetrofitClient retrofitClient= RetrofitClient.getInstance();
        Api api= retrofitClient.getMyApi();
        api.api_teacher_claim(351).enqueue(new Callback<ArrayList<ClaimVideo>>() {
            @Override
            public void onResponse(Call<ArrayList<ClaimVideo>> call, Response<ArrayList<ClaimVideo>> response) {
                CliamVideoAdapter adapter = new
                        CliamVideoAdapter(response.body(), teacherCHRClaimVideoActivity.this);
                LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                Binding.RecycerviewTeacherCHR.setLayoutManager(manager);
                Binding.RecycerviewTeacherCHR.setHasFixedSize(true);
                Binding.RecycerviewTeacherCHR.
                        setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<ClaimVideo>> call, Throwable t) {
                Toast.makeText(teacherCHRClaimVideoActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}