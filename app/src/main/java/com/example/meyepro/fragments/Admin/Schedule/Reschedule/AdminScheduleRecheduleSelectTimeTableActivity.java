package com.example.meyepro.fragments.Admin.Schedule.Reschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.adapters.AdminScheduleFreeSlotAdapter;
import com.example.meyepro.adapters.AdminTimeTableScheduleAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityAdminScheduleRecheduleSelectTimeTableBinding;
import com.example.meyepro.databinding.ActivityAdminScheduleRescheduleFreeSlotSelectionBinding;
import com.example.meyepro.models.TimeTable;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminScheduleRecheduleSelectTimeTableActivity extends AppCompatActivity {
ActivityAdminScheduleRecheduleSelectTimeTableBinding binding;
    ArrayList<TimeTable> timeSlots=new ArrayList<>();
    ArrayList<TimeTable> ScheduletimeSlots=new ArrayList<>();
    String SelectedVenue=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAdminScheduleRecheduleSelectTimeTableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.progressBar.setVisibility(View.VISIBLE);
        SelectedVenue= getIntent().getStringExtra("SelectedVenue");
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
                                        AdminTimeTableScheduleAdapter(timeSlots, ScheduletimeSlots,AdminScheduleRecheduleSelectTimeTableActivity.this,"AdminScheduleRecheduleSelectTimeTableActivity");
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


}