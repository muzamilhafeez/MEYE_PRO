package com.example.meyepro.fragments.Admin.Schedule.Swapping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meyepro.adapters.AdminTimeTableScheduleAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityAdminScheduleRecheduleSelectTimeTableBinding;
import com.example.meyepro.databinding.ActivityAdminScheduleSwappingTimeTableSelectBinding;
import com.example.meyepro.fragments.Admin.Schedule.Reschedule.AdminScheduleRecheduleSelectTimeTableActivity;
import com.example.meyepro.models.MEYE_USER;
import com.example.meyepro.models.PreSchedule;
import com.example.meyepro.models.Swapping;
import com.example.meyepro.models.TimeTable;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminScheduleSwappingTimeTableSelectActivity extends AppCompatActivity {
ActivityAdminScheduleSwappingTimeTableSelectBinding binding;
    ArrayList<TimeTable> timeSlots=new ArrayList<>();
    ArrayList<TimeTable> ScheduletimeSlots=new ArrayList<>();
    String SwappingDataInten=null;
    Swapping swapping;
    String intentData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAdminScheduleSwappingTimeTableSelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.progressBar.setVisibility(View.VISIBLE);
//        SwappingDataInten= getIntent().getStringExtra("SwappingDataIntent");
//        swapping= new Gson().fromJson(SwappingDataInten,Swapping.class);

       intentData = getIntent().getStringExtra("UserIntent");
      MEYE_USER  user= new Gson().fromJson(intentData, MEYE_USER.class);
        binding.textViewTeacherName.setText(user.getName());
        if(user.getImage()!=null){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+user.getRole()+"/"+user.getImage())).into(binding.profileImageTeacher);
        }
        RetrofitClient client= RetrofitClient.getInstance();
        Api api= client.getMyApi();
        api.All_timetable_teacher_details().enqueue(new Callback<ArrayList<TimeTable>>() {
            @Override
            public void onResponse(Call<ArrayList<TimeTable>> call, Response<ArrayList<TimeTable>> response) {
                if(response.isSuccessful()){
//                    Toast.makeText(getApplicationContext(), "Successful"+response.body().get(0).getCourseCode(), Toast.LENGTH_SHORT).show();
                    timeSlots.clear();
                    timeSlots.addAll(response.body());
//                    AdminTimeTableScheduleAdapter adapter = new
//                            AdminTimeTableScheduleAdapter(timeSlots, AdminScheduleRecheduleSelectTimeTableActivity.this);
//                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
//                    binding.RecycerviewAdminRescheduleTimeTable.setLayoutManager(manager);
//                    //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
//                    binding.RecycerviewAdminRescheduleTimeTable.
//                            setAdapter(adapter);
//api schedule calling
                    api.timetable_teacher_details("Adeel").enqueue(new Callback<ArrayList<TimeTable>>() {
                        @Override
                        public void onResponse(Call<ArrayList<TimeTable>> call, Response<ArrayList<TimeTable>> response) {
                            if(response.isSuccessful()){
//                                Toast.makeText(getApplicationContext(), "Successful"+response.body().get(0).getCourseCode(), Toast.LENGTH_SHORT).show();
                                ScheduletimeSlots.clear();
                                ScheduletimeSlots.addAll(response.body());
                                AdminTimeTableScheduleAdapter adapter = new
                                        AdminTimeTableScheduleAdapter(timeSlots, ScheduletimeSlots, AdminScheduleSwappingTimeTableSelectActivity.this,"AdminScheduleSwappingTimeTableSelectActivity");
                                LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                                binding.RecycerviewAdminRescheduleTimeTable.setLayoutManager(manager);
                                //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
                                binding.RecycerviewAdminRescheduleTimeTable.
                                        setAdapter(adapter);

                                binding.progressBar.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<TimeTable>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), ""+t.toString(), Toast.LENGTH_SHORT).show();
                            binding.progressBar.setVisibility(View.GONE);
                            Log.e("MyApiService", "Error getting data: " + t.getMessage());
                        }
                    });
                    //end schedule TimeTable

                }
            }

            @Override
            public void onFailure(Call<ArrayList<TimeTable>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), ""+t.toString(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), ""+t.toString(), Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.GONE);
                Log.e("MyApiService", "Error getting data: " + t.getMessage());
            }
        });


    }

    public void recyclerViewSelectedTimeTableSlot(TimeTable timeTable, Context context, TextView textView) {
//        PreSchedule preSchedule = new Gson().fromJson(SelectedPreSchedule, PreSchedule.class);

//        swapping.setTimeTableIdTo(timeTable.getId());

        Intent i = new Intent(getApplicationContext(), AdminScheduleSwappingUserActivity.class);
        i.putExtra("UserIntent",intentData);
        i.putExtra("TimeTableSelectedData",new Gson().toJson(timeTable));
        startActivity(i);

//        binding.textViewTeacherName.setText(new Gson().toJson(timeTable));
//        RetrofitClient client = RetrofitClient.getInstance();
//        Api api = client.getMyApi();
//        api.add_swapping(swapping).enqueue(new Callback<Map<String, String>>() {
//            @Override
//            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
//                Toast.makeText(context, "" + response.body(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Map<String, String>> call, Throwable t) {
//                Toast.makeText(context, "" + t, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}