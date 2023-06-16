package com.example.meyepro.fragments.Admin.Setting.DemoVideo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityAdminSettingDemoVideoTeacherChrreportBinding;
import com.example.meyepro.models.DemoVideosResponse;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminSettingDemoVideoTeacherCHRReportActivity extends AppCompatActivity {
ActivityAdminSettingDemoVideoTeacherChrreportBinding Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding= ActivityAdminSettingDemoVideoTeacherChrreportBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        Binding.TxtViewTotalTimeIn.setVisibility(View.INVISIBLE);
        Binding.TxtViewTotalTimeOut.setVisibility(View.INVISIBLE);
        Binding.TxtViewTotalTimeSit.setVisibility(View.INVISIBLE);
        Binding.TxtViewTotalTimeStand.setVisibility(View.INVISIBLE);
        Binding.lineraLayoutShowDetail.setVisibility(View.INVISIBLE);
        String imageView=getIntent().getStringExtra("Thumbnail");
        if(imageView!=null){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/demothumbnail/?file="+imageView)).into(Binding.ImageViewThumnail);
        }
        RetrofitClient.TimeSet=900;
        RetrofitClient retrofitClient= RetrofitClient.getInstance();
        Api api= retrofitClient.getMyApi();
        api.api_demovideos(getIntent().getStringExtra("VideoFile")).enqueue(new Callback<DemoVideosResponse>() {
            @Override
            public void onResponse(Call<DemoVideosResponse> call, Response<DemoVideosResponse> response) {
                Toast.makeText(AdminSettingDemoVideoTeacherCHRReportActivity.this, "Running", Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()){
                    Binding.TxtViewTotalTimeIn.setVisibility(View.VISIBLE);
                    Binding.TxtViewTotalTimeOut.setVisibility(View.VISIBLE);
                    Binding.TxtViewTotalTimeSit.setVisibility(View.VISIBLE);
                    Binding.TxtViewTotalTimeStand.setVisibility(View.VISIBLE);
                    Binding.lineraLayoutShowDetail.setVisibility(View.VISIBLE);

                    Toast.makeText(AdminSettingDemoVideoTeacherCHRReportActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                    DemoVideosResponse demoVideosResponse= response.body();
                    Binding.TxtViewTotalTimeIn.setText(demoVideosResponse.getTotalTimeIn());
                    Binding.TxtViewTotalTimeOut.setText(demoVideosResponse.getTotalTimeOut());
                    Binding.TxtViewTotalTimeSit.setText(demoVideosResponse.getSitMin()+"");
                    Binding.TxtViewTotalTimeStand.setText(demoVideosResponse.getStandMin()+"");
                    Binding.progressBar.setVisibility(View.GONE);

                }


            }

            @Override
            public void onFailure(Call<DemoVideosResponse> call, Throwable t) {
                Toast.makeText(AdminSettingDemoVideoTeacherCHRReportActivity.this, ""+t, Toast.LENGTH_SHORT).show();
                Binding.progressBar.setVisibility(View.GONE);
            }
        });
    }
}