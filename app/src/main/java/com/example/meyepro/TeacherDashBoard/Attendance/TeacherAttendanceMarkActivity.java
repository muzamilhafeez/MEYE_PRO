package com.example.meyepro.TeacherDashBoard.Attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.meyepro.TeacherDashBoard.Adapter.TeacherAttendanceMarkStudentAdapter;
import com.example.meyepro.TeacherDashBoard.TeacherDashBoardActivity;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityTeacherAttendanceMarkBinding;
import com.example.meyepro.models.Attendance;
import com.example.meyepro.models.MEYE_USER;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherAttendanceMarkActivity extends AppCompatActivity {
ActivityTeacherAttendanceMarkBinding binding;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
ArrayList<Attendance>  attendances= new ArrayList<>();
Uri sourceUri;
    MultipartBody.Part file = null;
    private static final int CAMERA_REQUEST_CODE = 1;
    TeacherAttendanceMarkStudentAdapter adapter;
    private int openRequestCode = 5;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding.progressBar.setVisibility(View.VISIBLE);
        binding= ActivityTeacherAttendanceMarkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MEYE_USER user= new Gson().fromJson(getIntent().getStringExtra("IntentData"),MEYE_USER.class);

        binding.textViewTeacherName.setText(user.getName());
        if(user.getImage()!=null){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+user.getRole()+"/"+user.getImage())).into(binding.profileImageTeacher);
        }
//        captureImage();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        }
        // Open the camera to capture an image
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create a file where the captured image should be saved
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "My Image");
            imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            // Set the URI of the file as the output for the camera app
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

            // Start the camera activity
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }


        binding.btnSaveAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(attendances.size()!=0){
                    RetrofitClient client = RetrofitClient.getInstance();
                    Api api = client.getMyApi();
                    api.add_attendance(attendances).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(TeacherAttendanceMarkActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                            if(response.isSuccessful()){
                                Toast.makeText(TeacherAttendanceMarkActivity.this, ""+response.body(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(TeacherAttendanceMarkActivity.this, ""+t.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(TeacherAttendanceMarkActivity.this, "Please Wait Marked Attendance", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);

//        Toast.makeText(this, ""+resultCode, Toast.LENGTH_SHORT).show();
        if (requestCode == CAMERA_REQUEST_CODE  && resultCode == RESULT_OK) {
//            Bitmap photo = (Bitmap) resultData.getExtras().get("data");
            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP


            sourceUri = imageUri;
            if ( sourceUri != null) {
                ApiCallingAttendanceMark();
//                try {
//                    InputStream inputStream = getContentResolver().openInputStream( sourceUri);
//                    // rest of your code
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            } else {
                Toast.makeText(this, "Unable to capture image.", Toast.LENGTH_SHORT).show();
            }

        }
        else{
            finish();
//            Intent i= new Intent(getApplicationContext(), TeacherDashBoardActivity.class);
//            startActivity(i);
        }

    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100,bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


    public void ApiCallingAttendanceMark(){
        RetrofitClient.TimeSet=600;
        RetrofitClient client= RetrofitClient.getInstance();
        Api api= client.getMyApi();

        try {
            file = api.prepareFilePart("file", sourceUri, getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        api.mark_attendance(file).enqueue(new Callback<ArrayList<Attendance>>() {
            @Override
            public void onResponse(Call<ArrayList<Attendance>> call, Response<ArrayList<Attendance>> response) {
//                Toast.makeText(TeacherAttendanceMarkActivity.this, ""+response.body().get(0).getDate(), Toast.LENGTH_SHORT).show();

                if(response.isSuccessful()){
                    attendances.clear();
                    attendances.addAll(response.body());

                    // Create a set to store unique teacher names
                    Set<String> uniqueTeacherNames = new HashSet<>();
                    for (Attendance data : attendances) {
                        uniqueTeacherNames.add(data.getName());
                    }

                    // Create a new list of objects with distinct teacher names
                    ArrayList<Attendance> uniqueObjects = new ArrayList<>();
                    for (Attendance data : attendances) {
                        if (uniqueTeacherNames.contains(data.getName())) {
                            uniqueObjects.add(data);
                            uniqueTeacherNames.remove(data.getName());
                        }
                    }
                    attendances.clear();
                    attendances.addAll(uniqueObjects);
                     adapter = new
                            TeacherAttendanceMarkStudentAdapter(attendances, TeacherAttendanceMarkActivity.this);
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    binding.RecycerviewTeacherAttendanceMark.setLayoutManager(manager);
                    binding.RecycerviewTeacherAttendanceMark.setHasFixedSize(true);
                    binding.RecycerviewTeacherAttendanceMark.
                            setAdapter(adapter);
//                    ArrayAdapter<String> adapter = new ArrayAdapter(this,
//                            android.R.layout.simple_list_item_1, attendances);
//                    binding.listViewShow.setAdapter(adapter);
                    binding.progressBar.setVisibility(View.GONE);

                }

            }

            @Override
            public void onFailure(Call<ArrayList<Attendance>> call, Throwable t) {
                Toast.makeText(TeacherAttendanceMarkActivity.this, ""+t.toString(), Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start the camera activity here
            } else {
                // Permission denied, handle the situation here
            }
        }
    }

    public void RecyclerViewAttendanceChangeStatus(Attendance obj, Context context) {
        if(attendances.contains(obj)){
           int index= attendances.indexOf(obj);
           if(attendances.get(index).isStatus()){
               attendances.get(index).setStatus(false);
           }else{
               attendances.get(index).setStatus(true);
           }
        }
        adapter.UpdateList(attendances);
    }

}