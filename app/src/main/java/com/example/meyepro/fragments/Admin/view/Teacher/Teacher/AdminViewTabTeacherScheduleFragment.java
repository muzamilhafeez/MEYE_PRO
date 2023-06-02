package com.example.meyepro.fragments.Admin.view.Teacher.Teacher;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.FragmentAdminViewTabTeacherScheduleBinding;
import com.example.meyepro.models.MEYE_USER;
import com.example.meyepro.models.TimeTable;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.function.Function;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminViewTabTeacherScheduleFragment extends Fragment {
FragmentAdminViewTabTeacherScheduleBinding Binding;
    ArrayList<TimeTable> timeSlots=new ArrayList<>();
    MEYE_USER obj;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Binding= FragmentAdminViewTabTeacherScheduleBinding.inflate(getLayoutInflater());
        // Inflate the layout for this fragment
        //code start

        Bundle b = getArguments();
        String objString= b.getString("IntentOBJ");
        obj= new Gson().fromJson(objString, MEYE_USER.class);
        Binding.textViewTeacherName.setText(obj.getName());
        if(obj.getImage()!=null){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+obj.getRole()+"/"+obj.getImage())).into(Binding.profileImageTeacher);
        }
//        ArrayList<String> floor= new ArrayList<String>();
//        floor.add("Ground Floor");
//        floor.add("First Floor");
        //create drowpdownlist
//        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_layout_timetable, floor);
        RetrofitClient client= RetrofitClient.getInstance();
        Api api= client.getMyApi();
        api.timetable_teacher_details(obj.getName()).enqueue(new Callback<ArrayList<TimeTable>>() {
            @Override
            public void onResponse(Call<ArrayList<TimeTable>> call, Response<ArrayList<TimeTable>> response) {
                if(response.isSuccessful()){
//                    Toast.makeText(getContext(), "Successful"+response.body().get(0).getCourseCode(), Toast.LENGTH_SHORT).show();
                    timeSlots.clear();
                    timeSlots.addAll(response.body());

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
                    TimetableShow();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TimeTable>> call, Throwable t) {
                Toast.makeText(getContext(), ""+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        //code end


        return Binding.getRoot();
    }


    public void TimetableShow(){
        for (TimeTable timeTable: timeSlots) {
            //row one
            if(timeTable.getStarttime().contains(Binding.txtViewRowTime1.getText().toString())&& timeTable.getDay().contains("Monday")){
                Binding.txtViewMondayRow1.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }

          else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime1.getText().toString())&& timeTable.getDay().contains("Tuesday")){
                Binding.txtViewTuesdayRow1.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime1.getText().toString())&& timeTable.getDay().contains("Wednesday")){
                Binding.txtViewWednesdayRow1.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime1.getText().toString())&& timeTable.getDay().contains("Thursday")){
                Binding.txtViewThursdayRow1.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime1.getText().toString())&& timeTable.getDay().contains("Friday")){
                Binding.txtViewFridayRow1.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            //row two
           else if(timeTable.getStarttime().contains(Binding.txtViewRowTime2.getText().toString())&& timeTable.getDay().contains("Monday")){
                Binding.txtViewMondayRow2.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }

            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime2.getText().toString())&& timeTable.getDay().contains("Tuesday")){
                Binding.txtViewTuesdayRow2.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime2.getText().toString())&& timeTable.getDay().contains("Wednesday")){
                Binding.txtViewWednesdayRow2.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime2.getText().toString())&& timeTable.getDay().contains("Thursday")){
                Binding.txtViewThursdayRow2.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime2.getText().toString())&& timeTable.getDay().contains("Friday")){
                Binding.txtViewFridayRow2.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            //row three
            if(timeTable.getStarttime().contains(Binding.txtViewRowTime3.getText().toString())&& timeTable.getDay().contains("Monday")){
                Binding.txtViewMondayRow3.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }

            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime3.getText().toString())&& timeTable.getDay().contains("Tuesday")){
                Binding.txtViewTuesdayRow3.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime3.getText().toString())&& timeTable.getDay().contains("Wednesday")){
                Binding.txtViewWednesdayRow3.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime3.getText().toString())&& timeTable.getDay().contains("Thursday")){
                Binding.txtViewThursdayRow3.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime3.getText().toString())&& timeTable.getDay().contains("Friday")){
                Binding.txtViewFridayRow3.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            //row four
            if(timeTable.getStarttime().contains(Binding.txtViewRowTime4.getText().toString())&& timeTable.getDay().contains("Monday")){
                Binding.txtViewMondayRow4.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }

            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime4.getText().toString())&& timeTable.getDay().contains("Tuesday")){
                Binding.txtViewTuesdayRow4.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime4.getText().toString())&& timeTable.getDay().contains("Wednesday")){
                Binding.txtViewWednesdayRow4.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime4.getText().toString())&& timeTable.getDay().contains("Thursday")){
                Binding.txtViewThursdayRow4.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime4.getText().toString())&& timeTable.getDay().contains("Friday")){
                Binding.txtViewFridayRow4.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            //row Five
            if(timeTable.getStarttime().contains(Binding.txtViewRowTime5.getText().toString())&& timeTable.getDay().contains("Monday")){
                Binding.txtViewMondayRow5.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }

            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime5.getText().toString())&& timeTable.getDay().contains("Tuesday")){
                Binding.txtViewTuesdayRow5.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime5.getText().toString())&& timeTable.getDay().contains("Wednesday")){
                Binding.txtViewWednesdayRow5.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime5.getText().toString())&& timeTable.getDay().contains("Thursday")){
                Binding.txtViewThursdayRow5.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            else   if(timeTable.getStarttime().contains(Binding.txtViewRowTime5.getText().toString())&& timeTable.getDay().contains("Friday")){
                Binding.txtViewFridayRow5.setText(timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }

            //row end


        }


    }
}