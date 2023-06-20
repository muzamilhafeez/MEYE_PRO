package com.example.meyepro.fragments.Admin.TaskReport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.meyepro.DirectorDashBoard.Model.ScheduleDetailsAndCHR;
import com.example.meyepro.adapters.TaskReportShoeDetailsAdapter;
import com.example.meyepro.adapters.TaskReportShowAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.ActivityAdminTaskReportDetailsBinding;
import com.example.meyepro.models.TaskReport;
import com.example.meyepro.models.TeacherCHRActivityDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminTaskReportDetailsActivity extends AppCompatActivity {
com.example.meyepro.databinding.ActivityAdminTaskReportDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminTaskReportDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TaskReport obj = null;
        try{
           String key= getIntent().getStringExtra("key");
         obj= new Gson().fromJson(key,new TypeToken<TaskReport>(){}.getType());
        }catch (Exception e){
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }

        if(obj!=null){

            binding.txtViewStatus.setText(obj.getStatus());
            binding.txtViewTaskCourse.setText(obj.getCourseName());
            binding.txtViewTaskDate.setText(obj.getDate());
            binding.txtViewTaskDay.setText(obj.getDay());
            binding.txtViewTeacherName.setText(obj.getTeacherName());
            binding.txtViewTaskDiscipline.setText(obj.getDiscipline());
            binding.txtViewTaskSit.setText(obj.getSit());
            binding.txtViewTaskTime.setText(obj.getStartTime().split(":")[0]+":"+obj.getStartTime().split(":")[1]);
            binding.txtViewTaskTimeOut.setText(obj.getTotalTimeOut());
            binding.txtViewTaskVenue.setText(obj.getVenue());
            binding.txtViewTaskTimeIn.setText(obj.getTotalTimeIn());
            binding.txtViewTaskStand.setText(obj.getStand());
            if(!obj.getImage().isEmpty()){
                Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+"Teacher"+"/"+obj.getImage())).
                        resize(100, 100) // Set the desired target size
                        .into(binding.profileImageTeacher);

            }

            ArrayList<TeacherCHRActivityDetails> list= new ArrayList<>();
            for (TeacherCHRActivityDetails teacher :obj.getTeacherCHRActivityDetails()) {
                if(teacher.getStatus().contains("Sit")){
                    list.add(teacher);
                }
            }
            TaskReportShoeDetailsAdapter adapter = new
                    TaskReportShoeDetailsAdapter(list, AdminTaskReportDetailsActivity.this);
            LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
            binding.RecycerviewAdminTaskReportDetails.setLayoutManager(manager);
            binding.RecycerviewAdminTaskReportDetails.setHasFixedSize(true);
            binding.RecycerviewAdminTaskReportDetails.
                    setAdapter(adapter);
        }
    }

}