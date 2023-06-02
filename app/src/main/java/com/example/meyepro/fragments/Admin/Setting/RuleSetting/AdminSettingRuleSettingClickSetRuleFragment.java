package com.example.meyepro.fragments.Admin.Setting.RuleSetting;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.FragmentAdminSettingRuleSettingClickSetRuleBinding;
import com.example.meyepro.models.TimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminSettingRuleSettingClickSetRuleFragment extends Fragment {
    FragmentAdminSettingRuleSettingClickSetRuleBinding Binding;
    ArrayList<TimeTable> timeSlots = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding = FragmentAdminSettingRuleSettingClickSetRuleBinding.inflate(getLayoutInflater());
        //code start

        RetrofitClient client = RetrofitClient.getInstance();
        Api api = client.getMyApi();
        api.timetable_teacher_details("Adeel").enqueue(new Callback<ArrayList<TimeTable>>() {
            @Override
            public void onResponse(Call<ArrayList<TimeTable>> call, Response<ArrayList<TimeTable>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Successful" + response.body().get(0).getCourseCode(), Toast.LENGTH_SHORT).show();
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

                    //allselect
                    Binding.checkBoxSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                            SelectAllTimetable();
                        }
                    });

                    //  Toast.makeText(getContext(), ""+timeSlots.size(), Toast.LENGTH_SHORT).show();
                    TimetableShow();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<TimeTable>> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        //code end
        return Binding.getRoot();
    }

    ArrayList<TimeTable> timeSlotsSelected;

    public void TimetableShow() {
        timeSlotsSelected = new ArrayList<>();
        for (TimeTable timeTable : timeSlots) {
            //row one
            if (timeTable.getStarttime().contains(Binding.txtViewRowTime1.getText().toString()) && timeTable.getDay().contains("Monday")) {
                Binding.txtViewMondayRow1.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewMondayRow1.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewMondayRow1.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                      //  Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });




            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime1.getText().toString()) && timeTable.getDay().contains("Tuesday")) {
                Binding.txtViewTuesdayRow1.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewTuesdayRow1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewTuesdayRow1.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewTuesdayRow1.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                       // Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime1.getText().toString()) && timeTable.getDay().contains("Wednesday")) {
                Binding.txtViewWednesdayRow1.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewWednesdayRow1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewWednesdayRow1.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewWednesdayRow1.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                     //   Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime1.getText().toString()) && timeTable.getDay().contains("Thursday")) {
                Binding.txtViewThursdayRow1.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewThursdayRow1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewThursdayRow1.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewThursdayRow1.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                       // Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime1.getText().toString()) && timeTable.getDay().contains("Friday")) {
                Binding.txtViewFridayRow1.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewFridayRow1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewFridayRow1.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewFridayRow1.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                     //   Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            //row two
            else if (timeTable.getStarttime().contains(Binding.txtViewRowTime2.getText().toString()) && timeTable.getDay().contains("Monday")) {
                Binding.txtViewMondayRow2.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewMondayRow2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewMondayRow2.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewMondayRow2.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                       // Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime2.getText().toString()) && timeTable.getDay().contains("Tuesday")) {
                Binding.txtViewTuesdayRow2.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewTuesdayRow2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewTuesdayRow2.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewTuesdayRow2.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                        //Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime2.getText().toString()) && timeTable.getDay().contains("Wednesday")) {
                Binding.txtViewWednesdayRow2.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewWednesdayRow2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewWednesdayRow2.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewWednesdayRow2.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                       // Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime2.getText().toString()) && timeTable.getDay().contains("Thursday")) {
                Binding.txtViewThursdayRow2.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewThursdayRow2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewThursdayRow2.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewThursdayRow2.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                       // Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime2.getText().toString()) && timeTable.getDay().contains("Friday")) {
                Binding.txtViewFridayRow2.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewFridayRow2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewFridayRow2.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewFridayRow2.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                       // Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            //row three
            if (timeTable.getStarttime().contains(Binding.txtViewRowTime3.getText().toString()) && timeTable.getDay().contains("Monday")) {
                Binding.txtViewMondayRow3.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewMondayRow3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewMondayRow3.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewMondayRow3.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                      //  Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime3.getText().toString()) && timeTable.getDay().contains("Tuesday")) {
                Binding.txtViewTuesdayRow3.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewTuesdayRow3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewTuesdayRow3.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewTuesdayRow3.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                      //  Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime3.getText().toString()) && timeTable.getDay().contains("Wednesday")) {
                Binding.txtViewWednesdayRow3.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
               Binding.txtViewWednesdayRow3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (timeSlotsSelected.contains(timeTable)) {
                                timeSlotsSelected.remove(timeTable);
                                Binding.txtViewWednesdayRow3.setBackgroundColor(Color.parseColor("#f7f7f7"));
                            } else {
                                timeSlotsSelected.add(timeTable);
                                Binding.txtViewWednesdayRow3.setBackgroundColor(Color.parseColor("#03A9F4"));
                            }
                         //   Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                        }
                    });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime3.getText().toString()) && timeTable.getDay().contains("Thursday")) {
                Binding.txtViewThursdayRow3.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewThursdayRow3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewThursdayRow3.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewThursdayRow3.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                      //  Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime3.getText().toString()) && timeTable.getDay().contains("Friday")) {
                Binding.txtViewFridayRow3.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewFridayRow3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewFridayRow3.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewFridayRow3.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                      //  Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            //row four
            if (timeTable.getStarttime().contains(Binding.txtViewRowTime4.getText().toString()) && timeTable.getDay().contains("Monday")) {
                Binding.txtViewMondayRow4.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewMondayRow4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewMondayRow4.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewMondayRow4.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime4.getText().toString()) && timeTable.getDay().contains("Tuesday")) {
                Binding.txtViewTuesdayRow4.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewTuesdayRow4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewTuesdayRow4.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewTuesdayRow4.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime4.getText().toString()) && timeTable.getDay().contains("Wednesday")) {
                Binding.txtViewWednesdayRow4.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewWednesdayRow4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewWednesdayRow4.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewWednesdayRow4.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime4.getText().toString()) && timeTable.getDay().contains("Thursday")) {
                Binding.txtViewThursdayRow4.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewThursdayRow4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewThursdayRow4.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewThursdayRow4.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime4.getText().toString()) && timeTable.getDay().contains("Friday")) {
                Binding.txtViewFridayRow4.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewFridayRow4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewFridayRow4.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewFridayRow4.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            //row Five
            if (timeTable.getStarttime().contains(Binding.txtViewRowTime5.getText().toString()) && timeTable.getDay().contains("Monday")) {
                Binding.txtViewMondayRow5.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewMondayRow5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewMondayRow5.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewMondayRow5.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime5.getText().toString()) && timeTable.getDay().contains("Tuesday")) {
                Binding.txtViewTuesdayRow5.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewTuesdayRow5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewTuesdayRow5.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewTuesdayRow5.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime5.getText().toString()) && timeTable.getDay().contains("Wednesday")) {
                Binding.txtViewWednesdayRow5.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewWednesdayRow5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewWednesdayRow5.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewWednesdayRow5.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime5.getText().toString()) && timeTable.getDay().contains("Thursday")) {
                Binding.txtViewThursdayRow5.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewThursdayRow5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewThursdayRow5.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewThursdayRow5.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime5.getText().toString()) && timeTable.getDay().contains("Friday")) {
                Binding.txtViewFridayRow5.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
                Binding.txtViewFridayRow5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeSlotsSelected.contains(timeTable)) {
                            timeSlotsSelected.remove(timeTable);
                            Binding.txtViewFridayRow5.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        } else {
                            timeSlotsSelected.add(timeTable);
                            Binding.txtViewFridayRow5.setBackgroundColor(Color.parseColor("#03A9F4"));
                        }
                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            //row end


        }

    }
    //timetable end

    public void SelectAllTimetable() {
        Toast.makeText(getContext(), "hh", Toast.LENGTH_SHORT).show();
            for (TimeTable timeTable : timeSlots) {
                //row one
                if (timeTable.getStarttime().contains(Binding.txtViewRowTime1.getText().toString()) && timeTable.getDay().contains("Monday")) {
                    Drawable backgroundDrawable = Binding.txtViewMondayRow1.getBackground();
                    if (timeSlotsSelected.contains(timeTable)) {
                        timeSlotsSelected.remove(timeTable);
                        Binding.txtViewMondayRow1.setBackgroundColor(Color.parseColor("#f7f7f7"));
                    } else {
                        timeSlotsSelected.add(timeTable);
                        Binding.txtViewMondayRow1.setBackgroundColor(Color.parseColor("#03A9F4"));
                    }
//                    if (backgroundDrawable instanceof ColorDrawable) {
//                        int backgroundColor = ((ColorDrawable) backgroundDrawable).getColor();
//                        //Toast.makeText(getContext(), "c"+backgroundColor+"m"+Color.parseColor("#03A9F4"), Toast.LENGTH_SHORT).show();
//                        // do something with the color
//                        if(backgroundColor==Color.parseColor("#03A9F4")){
//                            timeSlotsSelected.remove(timeTable);
//                            Binding.txtViewMondayRow1.setBackgroundColor(Color.parseColor("#f7f7f7"));
//                        }else{
//                            timeSlotsSelected.add(timeTable);
//                            Binding.txtViewMondayRow1.setBackgroundColor(Color.parseColor("#03A9F4"));
//                        }
//
//                    }else {
//
//                    }



                  //  Binding.txtViewMondayRow1.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                    if (timeTable.getStarttime().contains(Binding.txtViewRowTime1.getText().toString()) && timeTable.getDay().contains("Monday")) {
//
//                    }
//                    if (timeSlotsSelected.contains(timeTable)) {
//
//                    } else {
//
//                    }



                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime1.getText().toString()) && timeTable.getDay().contains("Tuesday")) {
                    Binding.txtViewTuesdayRow1.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime1.getText().toString()) && timeTable.getDay().contains("Wednesday")) {
                    Binding.txtViewWednesdayRow1.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime1.getText().toString()) && timeTable.getDay().contains("Thursday")) {
                    Binding.txtViewThursdayRow1.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime1.getText().toString()) && timeTable.getDay().contains("Friday")) {
                    Binding.txtViewFridayRow1.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                }
                //row two
                else if (timeTable.getStarttime().contains(Binding.txtViewRowTime2.getText().toString()) && timeTable.getDay().contains("Monday")) {
                    Binding.txtViewMondayRow2.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime2.getText().toString()) && timeTable.getDay().contains("Tuesday")) {
                    Binding.txtViewTuesdayRow2.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime2.getText().toString()) && timeTable.getDay().contains("Wednesday")) {
                    Binding.txtViewWednesdayRow2.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime2.getText().toString()) && timeTable.getDay().contains("Thursday")) {
                    Binding.txtViewThursdayRow2.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime2.getText().toString()) && timeTable.getDay().contains("Friday")) {
                    Binding.txtViewFridayRow2.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                }
                //row three
                if (timeTable.getStarttime().contains(Binding.txtViewRowTime3.getText().toString()) && timeTable.getDay().contains("Monday")) {
                    Binding.txtViewMondayRow3.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime3.getText().toString()) && timeTable.getDay().contains("Tuesday")) {
                    Binding.txtViewTuesdayRow3.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime3.getText().toString()) && timeTable.getDay().contains("Wednesday")) {
                    Binding.txtViewWednesdayRow3.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime3.getText().toString()) && timeTable.getDay().contains("Thursday")) {
                    Binding.txtViewThursdayRow3.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime3.getText().toString()) && timeTable.getDay().contains("Friday")) {
                    Binding.txtViewFridayRow3.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                }
                //row four
                if (timeTable.getStarttime().contains(Binding.txtViewRowTime4.getText().toString()) && timeTable.getDay().contains("Monday")) {
                    Binding.txtViewMondayRow4.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime4.getText().toString()) && timeTable.getDay().contains("Tuesday")) {
                    Binding.txtViewTuesdayRow4.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime4.getText().toString()) && timeTable.getDay().contains("Wednesday")) {
                    Binding.txtViewWednesdayRow4.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime4.getText().toString()) && timeTable.getDay().contains("Thursday")) {
                    Binding.txtViewThursdayRow4.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime4.getText().toString()) && timeTable.getDay().contains("Friday")) {
                    Binding.txtViewFridayRow4.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                }
                //row Five
                if (timeTable.getStarttime().contains(Binding.txtViewRowTime5.getText().toString()) && timeTable.getDay().contains("Monday")) {
                    Binding.txtViewMondayRow5.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime5.getText().toString()) && timeTable.getDay().contains("Tuesday")) {
                    Binding.txtViewTuesdayRow5.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime5.getText().toString()) && timeTable.getDay().contains("Wednesday")) {
                    Binding.txtViewWednesdayRow5.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime5.getText().toString()) && timeTable.getDay().contains("Thursday")) {
                    Binding.txtViewThursdayRow5.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                } else if (timeTable.getStarttime().contains(Binding.txtViewRowTime5.getText().toString()) && timeTable.getDay().contains("Friday")) {
                    Binding.txtViewFridayRow5.setText(timeTable.getDiscipline() + "\n" + timeTable.getCourseName() + "\n" + timeTable.getVenue());
//                Binding.txtViewMondayRow1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), ""+timeTable.getVenue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                }

            }
        }
    }
