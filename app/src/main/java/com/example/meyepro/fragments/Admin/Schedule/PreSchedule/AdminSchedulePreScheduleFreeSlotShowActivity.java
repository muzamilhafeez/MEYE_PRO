package com.example.meyepro.fragments.Admin.Schedule.PreSchedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.adapters.AdminScheduleFreeSlotAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.GlobalsDataSave;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityAdminSchedulePreScheduleFreeSlotShowBinding;
import com.example.meyepro.fragments.Admin.Schedule.Reschedule.AdminScheduleReScheduleFreeSlotActivity;
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

public class AdminSchedulePreScheduleFreeSlotShowActivity extends AppCompatActivity {
ActivityAdminSchedulePreScheduleFreeSlotShowBinding binding;
    ArrayList<TimeTable> timeSlots=new ArrayList<>();
    String Discipline=null;
    String  IntentData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAdminSchedulePreScheduleFreeSlotShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Discipline=getIntent().getStringExtra("Discipline");
//        Toast.makeText(this, ""+ID, Toast.LENGTH_SHORT).show();
        binding.progressBar.setVisibility(View.VISIBLE);
        String IntentDays = getIntent().getStringExtra("IntentDays");
       IntentData= getIntent().getStringExtra("UserIntent");
        MEYE_USER user = new Gson().fromJson(IntentData, MEYE_USER.class);
        binding.textViewTeacherNmae.setText(user.getName());
        binding.textViewTeacherNmae.setText(binding.textViewTeacherNmae.getText().toString()+"\nDiscipline: "+Discipline);
        if(user.getImage()!=null){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+user.getRole()+"/"+user.getImage())).into(binding.profileImageTeacher);
        }
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> list = new Gson().fromJson(IntentDays, listType);
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
                            AdminScheduleFreeSlotAdapter(timeSlots, AdminSchedulePreScheduleFreeSlotShowActivity.this,list, "AdminSchedulePreScheduleFreeSlotShowActivity" );
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
                if(selectVenue!="") {
                    Intent i = new Intent(getApplicationContext(), AdminPreScheduleTimeTableShowSelectActivity.class);
                    i.putExtra("PreSchedule",new Gson().toJson(Preschedule) );
                    i.putExtra("UserIntent",IntentData);
                    i.putExtra("Discipline",Discipline);
                    startActivity(i);
                }else {
                    Toast.makeText(AdminSchedulePreScheduleFreeSlotShowActivity.this, "Place Select Venue", Toast.LENGTH_SHORT).show();
                }
//                if(selectVenue!=""){
//
////                    Intent i= new Intent(getApplicationContext(),AdminScheduleRecheduleSelectTimeTableActivity.class);
////                    i.putExtra("SelectedVenue",selectVenue);
////                    startActivity(i);
//
//                    api.add_reschedule(reschedule).enqueue(new Callback<Map<String, String>>() {
//                        @Override
//                        public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
//                            Toast.makeText(AdminSchedulePreScheduleFreeSlotShowActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
//                            if(response.code()==200){
//                                Map<String,String> reschedule= response.body();
//                                if(reschedule.get("data").contains("okay")){
//                                    SweetAlertDialog dialog = new SweetAlertDialog(AdminSchedulePreScheduleFreeSlotShowActivity.this, SweetAlertDialog.SUCCESS_TYPE)
//                                            .setTitleText("Add Successfully!")
//                                            .setContentText(reschedule.get("data"))
//                                            .setConfirmText("OK")
//                                            .setConfirmClickListener(null);
//                                    dialog.show();
//                                }else{
//                                    Toast.makeText(AdminSchedulePreScheduleFreeSlotShowActivity.this, ""+reschedule.get("data"), Toast.LENGTH_SHORT).show();
//                                }
//
////                                Toast.makeText(AdminScheduleReScheduleFreeSlotActivity.this, "ok", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<Map<String, String>> call, Throwable t) {
//                            Toast.makeText(AdminSchedulePreScheduleFreeSlotShowActivity.this, ""+t, Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//                else{
//                    Toast.makeText(AdminSchedulePreScheduleFreeSlotShowActivity.this, "Please Select Venue", Toast.LENGTH_SHORT).show();
//                }


            }
        });

    }
    String selectVenue="";
    Reschedule Preschedule= new Reschedule();
    public void recyclerviewSelectVenueDrowpdown(String venue, String startTime, String EndTime, String Day , Context context) {
        binding.txtViewSelectedVenue.setText("Selected Venue "+venue);
        Preschedule.setDate(getDateFromDayOfWeek(Day));
        Preschedule.setDay(Day);
        Preschedule.setEndtime(EndTime);
        Preschedule.setStarttime(startTime);
        Preschedule.setVenueName(venue);
        Preschedule.setId(0);
//        reschedule.setTeacherSlotId(Integer.parseInt(ID));
        Preschedule.setStatus(false);
//        Toast.makeText(context, ""+new Gson().toJson(reschedule), Toast.LENGTH_SHORT).show();
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