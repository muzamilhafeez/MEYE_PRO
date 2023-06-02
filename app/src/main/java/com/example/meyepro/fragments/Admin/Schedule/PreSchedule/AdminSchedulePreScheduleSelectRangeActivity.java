package com.example.meyepro.fragments.Admin.Schedule.PreSchedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.adapters.AdminTimeTableScheduleAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityAdminSchedulePreScheduleSelectRangeBinding;
import com.example.meyepro.fragments.Admin.Schedule.Reschedule.AdminScheduleReScheduleFreeSlotActivity;
import com.example.meyepro.fragments.Admin.Schedule.Reschedule.AdminScheduleRecheduleSelectTimeTableActivity;
import com.example.meyepro.fragments.Admin.Schedule.Reschedule.AdminScheduleRescheduleFreeSlotSelectionActivity;
import com.example.meyepro.models.MEYE_USER;
import com.example.meyepro.models.TimeTable;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminSchedulePreScheduleSelectRangeActivity extends AppCompatActivity {
ActivityAdminSchedulePreScheduleSelectRangeBinding binding;
    String ReScheduleListDiscipline;
    String IntentData;
    ArrayList<Map<String,String>> maplist;
    ArrayList<TimeTable> ScheduletimeSlots=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAdminSchedulePreScheduleSelectRangeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        ReScheduleListDiscipline= getIntent().getStringExtra("PreScheduleList");
////        maplist= new Gson().fromJson(RescheduleList, new TypeToken<ArrayList<Map<String,String>>>(){}.getType());
        IntentData= getIntent().getStringExtra("UserIntent");
        MEYE_USER user = new Gson().fromJson(IntentData, MEYE_USER.class);
        binding.textViewTeacherName.setText(user.getName());
        if(user.getImage()!=null){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+user.getRole()+"/"+user.getImage())).into(binding.profileImageTeacher);
        }
        RetrofitClient client= RetrofitClient.getInstance();
        Api api= client.getMyApi();
           //api schedule calling
                    api.timetable_teacher_details(user.getName()).enqueue(new Callback<ArrayList<TimeTable>>() {
                        @Override
                        public void onResponse(Call<ArrayList<TimeTable>> call, Response<ArrayList<TimeTable>> response) {
                            if(response.isSuccessful()){
//                                Toast.makeText(AdminSchedulePreScheduleSelectRangeActivity.this, ""+new Gson().toJson(response.body()), Toast.LENGTH_SHORT).show();
//                                Toast.makeText(getApplicationContext(), "Successful"+response.body().get(0).getCourseCode(), Toast.LENGTH_SHORT).show();
                                ScheduletimeSlots.clear();
                                ScheduletimeSlots.addAll(response.body());
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    //            ArrayList<String> dataList = maplist.stream()
                    //                    .map(map -> map.get("discipline")) // Replace "key" with the actual key you want to extract from the map
                    //                    .collect(Collectors.toCollection(ArrayList::new));
//                                    Toast.makeText(getApplicationContext(), ""+new Gson().toJson(ScheduletimeSlots), Toast.LENGTH_SHORT).show();
                                    ArrayList<String> dataList= new ArrayList<>();
                                    dataList.addAll(ScheduletimeSlots.stream().distinct().map(t -> t.getDiscipline()).distinct().collect(Collectors.toList()));
                                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                                            android.R.layout.simple_list_item_1,dataList );
//                                    binding.spinnerTeacherDiscipline.setAdapter(adapter);
                                    int selectedIndex = 6; // Index of the item you want to display
                                    binding.spinnerTeacherDiscipline.setSelection(selectedIndex);
                                    binding.spinnerTeacherDiscipline.setAdapter(adapter);


                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<TimeTable>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), ""+t.toString(), Toast.LENGTH_SHORT).show();
                            Log.e("MyApiService", "Error getting data: " + t.getMessage());
                        }
                    });
                    //end schedule TimeTable

        Date today = new Date();
        Calendar nextWeek = Calendar.getInstance();
        nextWeek.setTime(today);
        nextWeek.add(Calendar.DAY_OF_WEEK, 7); // Add 7 days to get the end date
        CalendarPickerView datePicker = findViewById(R.id.calendar_view);
        datePicker.init(today, nextWeek.getTime())
                .inMode(CalendarPickerView.SelectionMode.RANGE)
                .withSelectedDate(today);

        final List<Date> selectedDates = new ArrayList<>();
        selectedDates.add(today);
        final List<String>  selectDaysName=new ArrayList<>();
        final List<String> selectedDayNames = new ArrayList<>(); // To store day names

        datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {

                selectedDates.add(date);
                if (selectedDates.size() == 2) {
                    Date startDate = selectedDates.get(0);
                    Date endDate = selectedDates.get(1);

                    // Get day names within the range
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(startDate);
                    while (!calendar.getTime().after(endDate)) {
                        String dayName = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(calendar.getTime());
                        selectedDayNames.add(dayName);
                        calendar.add(Calendar.DAY_OF_WEEK, 1);
                    }
                    // Include the end date if it's not already included
                    String lastDayName = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(endDate);
                    if (!selectedDayNames.contains(lastDayName)) {
                        selectedDayNames.add(lastDayName);
                    }
                    // Do something with the start and end dates...
                    // For example, convert them to strings using SimpleDateFormat
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String startDateStr = sdf.format(startDate);
                    String endDateStr = sdf.format(endDate);
                    if(startDate.before(endDate)){
                        //show start date and end date
//                        Toast.makeText(AdminScheduleRescheduleFreeSlotSelectionActivity.this, ""+"Start date: " + startDateStr + ", End date: " + endDateStr, Toast.LENGTH_SHORT).show();
                    }

                    // Do something with the selected day names
                    for (String dayName : selectedDayNames) {
                        Log.d("TAG", "Selected Day: " + dayName);
                        // Perform any other operations with the day names
                    }
                    //get range days of name
                    selectDaysName.addAll(selectedDayNames);
//                    Toast.makeText(AdminScheduleRescheduleFreeSlotSelectionActivity.this, "rr"+new Gson().toJson(selectedDayNames), Toast.LENGTH_SHORT).show();
                    // Clear the selected dates and day names lists

                    selectedDayNames.clear();
                    selectedDates.clear();

                }else {
                    selectDaysName.clear();
                }

            }

            @Override
            public void onDateUnselected(Date date) {
                selectedDates.remove(date);
            }
        });


        binding.btnSaveReSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedPosition = binding.spinnerTeacherDiscipline.getSelectedItemPosition();
//                Toast.makeText(getApplication(), ""+ maplist.get(selectedPosition).get("id")+"id"+selectedPosition, Toast.LENGTH_SHORT).show();
//                Toast.makeText(AdminScheduleRescheduleFreeSlotSelectionActivity.this, ""+new Gson().toJson(selectDaysName), Toast.LENGTH_SHORT).show();
                if(selectDaysName.size()!=0){
                    Intent i = new Intent(getApplicationContext(), AdminSchedulePreScheduleFreeSlotShowActivity.class);
                    i.putExtra("IntentDays",new Gson().toJson(selectDaysName));
                    i.putExtra("UserIntent",IntentData);
//                    i.putExtra("UserSelect", IntentData);
                    i.putExtra("Discipline",binding.spinnerTeacherDiscipline.getSelectedItem().toString());
                    startActivity(i);
                }else {
                    Toast.makeText(getApplicationContext(), "Place select the range", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}