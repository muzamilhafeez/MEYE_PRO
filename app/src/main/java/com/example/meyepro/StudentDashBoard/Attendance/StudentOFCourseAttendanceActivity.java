package com.example.meyepro.StudentDashBoard.Attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.StudentDashBoard.Adapter.StudentCourseAdapter;
import com.example.meyepro.StudentDashBoard.Adapter.StudentOfCourseAttendanceAdapter;
import com.example.meyepro.StudentDashBoard.Course.StudentCourseFragment;
import com.example.meyepro.StudentDashBoard.Model.StudentCourse;
import com.example.meyepro.StudentDashBoard.Model.StudentOFCourseAttendance;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityStudentOfcourseAttendanceBinding;
import com.example.meyepro.models.MEYE_USER;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentOFCourseAttendanceActivity extends AppCompatActivity {
ActivityStudentOfcourseAttendanceBinding binding;
    ArrayList<StudentOFCourseAttendance> studentOFCourseAttendances= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityStudentOfcourseAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String IntentDataUser= getIntent().getStringExtra("IntentDataUser");
        String IntentDataCourse= getIntent().getStringExtra("IntentDataCourse");
        MEYE_USER user=new Gson().fromJson(IntentDataUser,MEYE_USER.class);
        StudentCourse Course=new Gson().fromJson(IntentDataCourse,StudentCourse.class);

        binding.textViewTeacherName.setText(Course.getTeacherName());
        if(!Course.getImage().isEmpty()){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+"Teacher"+"/"+Course.getImage())).into(binding.profileImageTeacher);
        }
//        Toast.makeText(this, ""+IntentDataCourse+">\n"+IntentDataUser, Toast.LENGTH_SHORT).show();
        ApiCalling(user.getUserID(), Course.getCourseName());

    }

    public  void  ApiCalling(String UserID, String Course ){
        RetrofitClient client = RetrofitClient.getInstance();
        Api api = client.getMyApi();
        api.get_course_attendance(UserID,Course).enqueue(new Callback<ArrayList<StudentOFCourseAttendance>>() {
            @Override
            public void onResponse(Call<ArrayList<StudentOFCourseAttendance>> call, Response<ArrayList<StudentOFCourseAttendance>> response) {
                if(response.isSuccessful()){
                    studentOFCourseAttendances.clear();
                    studentOFCourseAttendances.addAll(response.body());
//                    Toast.makeText(StudentOFCourseAttendanceActivity.this, ""+studentOFCourseAttendances.size(), Toast.LENGTH_SHORT).show();
                    StudentOfCourseAttendanceAdapter adapter = new
                            StudentOfCourseAttendanceAdapter(studentOFCourseAttendances, getApplicationContext());
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    binding.RecycerviewStudentOFCourseAttendance.setLayoutManager(manager);
                    binding.RecycerviewStudentOFCourseAttendance.setHasFixedSize(true);
                    binding.RecycerviewStudentOFCourseAttendance.
                            setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<StudentOFCourseAttendance>> call, Throwable t) {
                Toast.makeText(StudentOFCourseAttendanceActivity.this, "yy"+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}