package com.example.meyepro.fragments.Admin.Setting.TimeTableUpload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityTeacherDashBoardBinding;
import com.example.meyepro.databinding.ActivityTimetableUploadBinding;

import java.io.IOException;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimetableUploadActivity extends AppCompatActivity {
ActivityTimetableUploadBinding Binding;
    private int createRequestCode = 1;
    private int openRequestCode = 2;
    private Uri sourceUri;
    private Uri targetUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding= ActivityTimetableUploadBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        Binding.btnUploadTimetable.setVisibility(View.INVISIBLE);
        Binding.btnImageXlsx.setVisibility(View.INVISIBLE);

        Binding.btnSelectTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFile();
            }
        });
        Binding.btnUploadTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitClient client= RetrofitClient.getInstance();
                Api api= client.getMyApi();
                MultipartBody.Part file = null;
                try {
                    file = api.prepareFilePart("file", sourceUri, getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                api.UploadTimetable(file).enqueue(new Callback<Map<String, String>>() {
                    @Override
                    public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                        Toast.makeText(TimetableUploadActivity.this, ""+response.body().get("data"), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Map<String, String>> call, Throwable t) {
                        Toast.makeText(TimetableUploadActivity.this, ""+t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == openRequestCode && resultCode == RESULT_OK) {
            sourceUri = resultData.getData();
            Toast.makeText(this, "Selected", Toast.LENGTH_SHORT).show();
            Binding.btnUploadTimetable.setVisibility(View.VISIBLE);
            Binding.btnImageXlsx.setVisibility(View.VISIBLE);
        }

    }
    private void openFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        String[] mimeTypes = {"application/xml", "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(Intent.createChooser(intent, "Select a file"), openRequestCode);


    }

}