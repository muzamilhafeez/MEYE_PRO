package com.example.meyepro.StudentDashBoard.Course;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.StudentDashBoard.Adapter.StudentCourseAdapter;
import com.example.meyepro.StudentDashBoard.Attendance.StudentOFCourseAttendanceActivity;
import com.example.meyepro.StudentDashBoard.Model.StudentCourse;
import com.example.meyepro.StudentDashBoard.Model.StudentOFCourseAttendance;
import com.example.meyepro.StudentDashBoard.StudentDashBoardActivity;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.FragmentStudentCourseBinding;
import com.example.meyepro.models.MEYE_USER;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class StudentCourseFragment extends Fragment {
    FragmentStudentCourseBinding binding;
    String IntentData;
    ArrayList<StudentCourse> studentCourses= new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentStudentCourseBinding.inflate(getLayoutInflater());

        IntentData= getArguments().getString("IntentData");
        MEYE_USER user= new Gson().fromJson(IntentData,MEYE_USER.class);
        //code start

        ApiCalling(user );
        //code end
        return binding.getRoot();
    }

    public  void  ApiCalling(MEYE_USER user ){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        api.get_student_courses(user.getUserID()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){

                    studentCourses= new Gson().fromJson(response.body(), new TypeToken<ArrayList<StudentCourse>>() {}.getType());
//                    studentCourses.addAll();
                    StudentCourseAdapter adapter = new
                            StudentCourseAdapter(studentCourses, getContext(),StudentCourseFragment.this);
                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    binding.RecycerviewStudentHome.setLayoutManager(manager);
                    binding.RecycerviewStudentHome.setHasFixedSize(true);
                    binding.RecycerviewStudentHome.
                            setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void recyclerviewStudentCourseCellClick(StudentCourse obj, Fragment context) {

        Intent i = new Intent(getContext(), StudentOFCourseAttendanceActivity.class);
        i.putExtra("IntentDataUser",IntentData);
        i.putExtra("IntentDataCourse",new Gson().toJson(obj));
        startActivity(i);
    }
}