package com.example.meyepro.fragments.Admin.Schedule.Reschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.util.Pair;

import com.example.meyepro.R;
import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.ActivityAdminScheduleRescheduleFreeSlotSelectionBinding;
import com.example.meyepro.models.MEYE_USER;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class AdminScheduleRescheduleFreeSlotSelectionActivity extends AppCompatActivity {
ActivityAdminScheduleRescheduleFreeSlotSelectionBinding binding;
    private LinearLayout dateRangeLayout;
    private TextView dateRangeText;
    String RescheduleList;
    String IntentData;
    ArrayList<Map<String,String>> maplist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAdminScheduleRescheduleFreeSlotSelectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RescheduleList= getIntent().getStringExtra("RescheduleList");
        maplist= new Gson().fromJson(RescheduleList, new TypeToken<ArrayList<Map<String,String>>>(){}.getType());
        IntentData= getIntent().getStringExtra("UserIntent");
        MEYE_USER user = new Gson().fromJson(IntentData, MEYE_USER.class);
        binding.textViewTeacherName.setText(user.getName());
        if(user.getImage()!=null){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+user.getRole()+"/"+user.getImage())).into(binding.profileImageTeacher);
        }

//        String datajson= "[\n" +
//                "  {\n" +
//                "    \"id\": 65,\n" +
//                "    \"discipline\": \"BCS-2C\"\n" +
//                "  },  {\n" +
//                "    \"id\": 88,\n" +
//                "    \"discipline\": \"BCS-2C\"\n" +
//                "  }\n" +
//                "]";
//
//        maplist= new Gson().fromJson(datajson, new TypeToken<ArrayList<Map<String,String>>>(){}.getType());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            ArrayList<String> dataList = maplist.stream()
                    .map(map -> map.get("discipline")) // Replace "key" with the actual key you want to extract from the map
                    .collect(Collectors.toCollection(ArrayList::new));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, dataList);
            binding.spinnerTeacherDiscipline.setAdapter(adapter);

        }



//        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
//        builder.setTitleText("Select date range");
//        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
//        constraintsBuilder.setValidator(DateValidatorPointForward.now());
//        builder.setCalendarConstraints(constraintsBuilder.build());
//        MaterialDatePicker<Pair<Long, Long>> picker = builder.build();
//
//        picker.show(getSupportFragmentManager(), picker.toString());

//        CalendarPickerView.SelectionMode selectionMode = CalendarPickerView.SelectionMode.RANGE;
//
//        Calendar minDate = Calendar.getInstance();
//        minDate.add(Calendar.MONTH, -1);
//
//        Calendar maxDate = Calendar.getInstance();
//        maxDate.add(Calendar.MONTH, 1);
//        // Create a List of Calendar objects to represent any preselected dates
//
//        // Create a List of Calendar objects to represent any preselected dates
//        List<Calendar> preselectedDates = new ArrayList<>();
//        preselectedDates.add(minDate);
//        preselectedDates.add(maxDate);
//
//// Convert List<Calendar> to List<Date>
//        List<Date> selectedDates = new ArrayList<>();
//        for (Calendar calendar : preselectedDates) {
//            selectedDates.add(calendar.getTime());
//        }
//
//// Initialize CalendarPickerView with selected dates
//        CalendarPickerView calendarView = findViewById(R.id.calendar_view);
//        calendarView.init(new Date(), maxDate.getTime())
//                .inMode(selectionMode)
//                .withSelectedDates(selectedDates);


//        Date today = new Date();
//        Calendar nextYear = Calendar.getInstance();
//        nextYear.add(Calendar.YEAR, 1);
//
//        Calendar lastYear = Calendar.getInstance();
//        lastYear.add(Calendar.YEAR, -1);
//
//        CalendarPickerView datePicker = findViewById(R.id.calendar_view);
//        datePicker.init(today, nextYear.getTime())
//                .inMode(CalendarPickerView.SelectionMode.RANGE)
//                .withSelectedDate(today);

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
                    Intent i = new Intent(getApplicationContext(), AdminScheduleReScheduleFreeSlotActivity.class);
                    i.putExtra("IntentData",new Gson().toJson(selectDaysName));
                    i.putExtra("UserSelect", IntentData);
                    i.putExtra("ID",maplist.get(selectedPosition).get("id"));
                    startActivity(i);
                }else {
                    Toast.makeText(AdminScheduleRescheduleFreeSlotSelectionActivity.this, "Place select the range", Toast.LENGTH_SHORT).show();
                }

            }
        });






//        datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
//            @Override
//            public void onDateSelected(Date date) {
//                //String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(date);
//
//                Calendar calSelected = Calendar.getInstance();
//                calSelected.setTime(date);
//
//                String selectedDate = "" + calSelected.get(Calendar.DAY_OF_MONTH)
//                        + " " + (calSelected.get(Calendar.MONTH) + 1)
//                        + " " + calSelected.get(Calendar.YEAR);
//
//                Toast.makeText(getApplicationContext(), selectedDate, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onDateUnselected(Date date) {
//
//            }
//        });


//
//        dateRangeLayout = findViewById(R.id.date_range_layout);
//        dateRangeText = findViewById(R.id.date_range_text);
//
//        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
//        final MaterialDatePicker<Pair<Long, Long>> picker = builder.build();
//
//        dateRangeLayout.setOnClickListener(v -> {
//            picker.show(getSupportFragmentManager(), picker.toString());
//        });


//        picker.addOnPositiveButtonClickListener(selection -> {
//            String startDate = picker.getHeaderTextFromDate(selection.first);
//            String endDate = picker.getHeaderTextFromDate(selection.second);
//            String dateRange = String.format("%s - %s", startDate, endDate);
//            dateRangeText.setText(dateRange);
//        });
//    }


    }

}