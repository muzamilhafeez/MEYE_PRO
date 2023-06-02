package com.example.meyepro.fragments.Admin.Schedule.Reschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.adapters.AdminScheduleFreeSlotAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityAdminScheduleReScheduleFreeSlotBinding;
import com.example.meyepro.models.MEYE_USER;
import com.example.meyepro.models.Reschedule;
import com.example.meyepro.models.TimeTable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminScheduleReScheduleFreeSlotActivity extends AppCompatActivity {
ActivityAdminScheduleReScheduleFreeSlotBinding binding;
    ArrayList<TimeTable> timeSlots=new ArrayList<>();
    String ID=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAdminScheduleReScheduleFreeSlotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ID=getIntent().getStringExtra("ID");
//        Toast.makeText(this, ""+ID, Toast.LENGTH_SHORT).show();
        binding.progressBar.setVisibility(View.VISIBLE);
        String intentData = getIntent().getStringExtra("IntentData");
        MEYE_USER user= new Gson().fromJson(getIntent().getStringExtra("UserSelect"),MEYE_USER.class);
        binding.textViewTeacherNmae.setText(user.getName());
        if(user.getImage()!=null){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+user.getRole()+"/"+user.getImage())).into(binding.profileImageTeacher);
        }

        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> list = new Gson().fromJson(intentData, listType);
//        Toast.makeText(this, ""+list.get(0), Toast.LENGTH_SHORT).show();
        RetrofitClient client= RetrofitClient.getInstance();
        Api api= client.getMyApi();
        api.All_timetable_teacher_details().enqueue(new Callback<ArrayList<TimeTable>>() {
            @Override
            public void onResponse(Call<ArrayList<TimeTable>> call, Response<ArrayList<TimeTable>> response) {
                if(response.isSuccessful()){
//                    Toast.makeText(getApplicationContext(), "Successful"+response.body().get(0).getCourseCode(), Toast.LENGTH_SHORT).show();
                    timeSlots.clear();
                    timeSlots.addAll(response.body());

                    AdminScheduleFreeSlotAdapter adapter = new
                            AdminScheduleFreeSlotAdapter(timeSlots, AdminScheduleReScheduleFreeSlotActivity.this,list,"AdminScheduleReScheduleFreeSlotActivity" );
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    binding.RecycerviewAdminReschedule.setLayoutManager(manager);
                    //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
                    binding.RecycerviewAdminReschedule.
                            setAdapter(adapter);
//      binding.RecycerviewAdminReschedule.getAdapter().notifyDataSetChanged();

                    binding.progressBar.setVisibility(View.GONE);
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {


                        List<String> uniqueStartTimes = timeSlots.stream().distinct()
                                .map(t -> t.getStarttime()).distinct()

//                                .sorted((t1, t2) -> {
//                                    try {
//                                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//                                        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Karachi"));
//                                        Date d1 = sdf.parse(t1);
//                                        Date d2 = sdf.parse(t2);
//                                        return d1.compareTo(d2);
//                                    } catch (ParseException e) {
//                                        return 0;
//                                    }
//                                })
                                .collect(Collectors.toList());

//            List<TimeTable> uniqueTimeTables = timeSlots.stream().distinct()
//        .filter(t -> uniqueStartTimes.contains(t.getStarttime()))
//        .collect(Collectors.toList());
//
//    List<TimeTable> TeacherTimeTables = timeSlots.stream()
//            .filter(t -> t.getTeacherName().contains("Naeem"))
//            .collect(Collectors.toList());

//
//                        for (TimeTable timeTable : uniqueTimeTables) {
//                            System.out.println(timeTable.getStarttime());
//                        }
//
//                        for (TimeTable timeTable : TeacherTimeTables) {
//                            System.out.println(timeTable.getTeacherName()+"/"+timeTable.getDay());
//                        }
                    }

                    //  Toast.makeText(getContext(), ""+timeSlots.size(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TimeTable>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), ""+t.toString(), Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.GONE);
            }
        });

        binding.btnNextReSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectVenue!=""){

//                    Intent i= new Intent(getApplicationContext(),AdminScheduleRecheduleSelectTimeTableActivity.class);
//                    i.putExtra("SelectedVenue",selectVenue);
//                    startActivity(i);

                    api.add_reschedule(reschedule).enqueue(new Callback<Map<String, String>>() {
                        @Override
                        public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                            Toast.makeText(AdminScheduleReScheduleFreeSlotActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                            if(response.code()==200){
                                Map<String,String> reschedule= response.body();
                                if(reschedule.get("data").contains("okay")){
                                    SweetAlertDialog dialog = new SweetAlertDialog(AdminScheduleReScheduleFreeSlotActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                            .setTitleText("Add Successfully!")
                                            .setContentText(reschedule.get("data"))
                                            .setConfirmText("OK")
                                            .setConfirmClickListener(null);
                                    dialog.show();
                                }else{
                                    Toast.makeText(AdminScheduleReScheduleFreeSlotActivity.this, ""+reschedule.get("data"), Toast.LENGTH_SHORT).show();
                                }

//                                Toast.makeText(AdminScheduleReScheduleFreeSlotActivity.this, "ok", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Map<String, String>> call, Throwable t) {
                            Toast.makeText(AdminScheduleReScheduleFreeSlotActivity.this, ""+t, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(AdminScheduleReScheduleFreeSlotActivity.this, "Please Select Venue", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    String selectVenue="";
    Reschedule reschedule= new Reschedule();
    public void recyclerviewSelectVenueDrowpdown(String venue, String startTime,String EndTime,String Day ,Context context) {
        binding.txtViewSelectedVenue.setText("Selected Venue "+venue);
        reschedule.setDate(getDateFromDayOfWeek(Day));
        reschedule.setDay(Day);
        reschedule.setEndtime(EndTime);
        reschedule.setStarttime(startTime);
        reschedule.setVenueName(venue);
        reschedule.setId(0);
        reschedule.setTeacherSlotId(Integer.parseInt(ID));
        reschedule.setStatus(false);
//        Toast.makeText(context, ""+startTime+""+EndTime+""+Day+getDateFromDayOfWeek(Day), Toast.LENGTH_SHORT).show();
        selectVenue=venue;
    }
    // Function to get the date from a day of the week
    public String getDateFromDayOfWeek(String dayOfWeek) {
        // Convert the day of the week to uppercase for comparison
        dayOfWeek = dayOfWeek.toUpperCase();

        // Get the current date
        LocalDate currentDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
        }

        // Get the target day of the week
        DayOfWeek targetDayOfWeek = DayOfWeek.valueOf(dayOfWeek);

        // Calculate the number of days to add or subtract to reach the desired day of the week
        int daysToAdd = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            daysToAdd = targetDayOfWeek.getValue() - currentDate.getDayOfWeek().getValue();
        }

        if (daysToAdd <= 0) {
            daysToAdd += 7;
        }

        // Add or subtract the calculated number of days
        LocalDate targetDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            targetDate = currentDate.plusDays(daysToAdd);
        }

        // Return the date in the desired format (e.g., "YYYY-MM-DD")
        return targetDate.toString();
    }
}