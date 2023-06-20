package com.example.meyepro.fragments.Admin.Setting.TaskDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityAdminTaskDemoDetailsShowBinding;
import com.example.meyepro.models.DemoVideosResponse;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminTaskDemoDetailsShowActivity extends AppCompatActivity {
ActivityAdminTaskDemoDetailsShowBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAdminTaskDemoDetailsShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RetrofitClient.TimeSet=900;

        String imageView=getIntent().getStringExtra("Thumbnail");
        if(imageView!=null){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/demothumbnail/?file="+imageView)).into(binding.ImageViewThumnail);
        }
        RetrofitClient retrofitClient= RetrofitClient.getInstance();
        Api api= retrofitClient.getMyApi();

        api.api_Task(getIntent().getStringExtra("VideoFile")).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Toast.makeText(AdminTaskDemoDetailsShowActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                   binding.txtViewResponse.setText(response.body());
                    binding.progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(AdminTaskDemoDetailsShowActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}