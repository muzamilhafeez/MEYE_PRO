package com.example.meyepro.fragments.Admin.Setting.AssignCourse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.adapters.AdminSettingAssignStudentCourseAdapter;
import com.example.meyepro.adapters.AdminSettingDVRChannelAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityAdminSettingAssignCourseAssignStudentBinding;
import com.example.meyepro.fragments.Admin.Setting.CameraSetting.AdminSettingCameraSettingClickCameraSettingFragment;
import com.example.meyepro.models.SectionOffer;
import com.example.meyepro.models.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminSettingAssignCourseAssignStudentActivity extends AppCompatActivity {
ActivityAdminSettingAssignCourseAssignStudentBinding binding;
    ArrayList<Student> StudentList= new ArrayList<>();
    JSONArray jsonArrayIntent = null;
    JSONArray jsonArraySentApi = null;
    JSONObject jsonObjectIntent=null;
    ArrayList<SectionOffer> sectionOffers;
    ArrayList<Integer> selectCourseID= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAdminSettingAssignCourseAssignStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String IntentData=getIntent().getStringExtra("Data");
        try {
          jsonArrayIntent  = new JSONArray(IntentData);
          jsonArraySentApi  = new JSONArray();
          jsonObjectIntent  = jsonArrayIntent .getJSONObject(0);

//            JSONArray   jsonArrayObj = new JSONArray(jsonObject.getString("sectionOfferId"));
            Gson gson = new Gson();
         sectionOffers=gson.fromJson(jsonObjectIntent.getString("sectionOfferId"), new TypeToken<ArrayList<SectionOffer>>(){}.getType());
            for (SectionOffer sectionO:sectionOffers) {
                selectCourseID.add(sectionO.getId());
            }
//          Toast.makeText(this, ""+sectionOffers.get(0).courseCode, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }




        Animation anim = AnimationUtils.loadAnimation(this, R.anim.fab_animation);
        binding.imageViewSearch.startAnimation(anim);
        // Animate the FAB to move in a circular path for 5 seconds
        ObjectAnimator animator = ObjectAnimator.ofFloat(binding.floatingBtnSave, "translationY", 0f, 100f);
        animator.setDuration(1000);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.REVERSE);

        // Start the animation
        animator.start();

        //api code
        RetrofitClient client = RetrofitClient.getInstance();
        Api api = client.getMyApi();
        AdminSettingAssignStudentCourseAdapter adapter = new
                AdminSettingAssignStudentCourseAdapter(StudentList, AdminSettingAssignCourseAssignStudentActivity.this);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        binding.RecyclerviewAssignCourseCell2.setLayoutManager(manager);
        binding.RecyclerviewAssignCourseCell2.setAdapter(adapter);
        ArrayList<String> courseNames = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
           courseNames = sectionOffers.stream()
                    .map(SectionOffer::getDiscipline)
                    .collect(Collectors.toCollection(ArrayList::new));
        }

//        String allCourses = String.join(", ",String.valueOf(courseNames));
//        Toast.makeText(this, "ee"+allCourses, Toast.LENGTH_SHORT).show();
        //api calling send data

        binding.floatingBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonArraySentApi.toString());
//                Toast.makeText(getApplicationContext(), ""+jsonArraySentApi, Toast.LENGTH_SHORT).show();
                api.student_enroll(requestBody).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(AdminSettingAssignCourseAssignStudentActivity.this, ""+response.body(), Toast.LENGTH_SHORT).show();
                        }
//                        binding.searchBar.setText(response.code());
//                        Toast.makeText(AdminSettingAssignCourseAssignStudentActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
//                        binding.searchBar.setText(t.toString());
//                        Toast.makeText(AdminSettingAssignCourseAssignStudentActivity.this, ""+t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        api.student_offered_courses(courseNames).enqueue(new Callback<ArrayList<Student>>() {
            @Override
            public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                if (response.code() == 200) {
                    StudentList.clear();
                    StudentList.addAll( response.body());
                    // Toast.makeText(getContext(), "hhhhhhhhhh"+response.code()+data.get(0).getName(), Toast.LENGTH_SHORT).show();
                    binding.RecyclerviewAssignCourseCell2.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Student>> call, Throwable t) {

            }
        });


    }
ArrayList<Student> students= new ArrayList<>();

    public void RecyclerviewAssignCourseSelectStudent(Student obj, Context context, String Select) {
//        Toast.makeText(context, "jj", Toast.LENGTH_LONG).show();
        if(Select.contains("Add")){
            students.add(obj);

            try {
                JSONObject jsonObjectSelect = new JSONObject(jsonObjectIntent.toString());
                JSONArray array= new JSONArray(selectCourseID.toString());
                jsonObjectSelect.put("sectionOfferId",array);
                jsonObjectSelect.put("studentID", obj.getAridNo());
                jsonArraySentApi.put(jsonObjectSelect);
//                Toast.makeText(context, jsonArraySentApi.toString(), Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            binding.searchBar.setText(jsonArraySentApi.toString());
        }else{
            for (int i = 0; i < jsonArraySentApi.length(); i++) {
                try {
                    if (jsonArraySentApi.getJSONObject(i).getString("studentID").contains(obj.getAridNo())) {
                        jsonArraySentApi.remove(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            }
    }
}