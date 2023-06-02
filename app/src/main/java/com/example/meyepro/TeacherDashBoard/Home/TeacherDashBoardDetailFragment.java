package com.example.meyepro.TeacherDashBoard.Home;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.TeacherDashBoard.Adapter.SessionComingSliderAdapter;
import com.example.meyepro.TeacherDashBoard.Adapter.TeacherNotificationAdapter;
import com.example.meyepro.TeacherDashBoard.Adapter.TeacherTimeTableAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.FragmentTeacherDashBoardDetailBinding;
import com.example.meyepro.models.MEYE_USER;
import com.example.meyepro.models.TimeTable;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class TeacherDashBoardDetailFragment extends Fragment {
FragmentTeacherDashBoardDetailBinding binding;
ArrayList<TimeTable> timeSlots=new ArrayList<>();

    private SessionComingSliderAdapter adapter;
    private SessionComingSliderAdapter adapterpast;
    private int currentPosition = 0;
    Runnable runnable;
    private Handler handler = new Handler();
    ArrayList<TimeTable> pastTimes = new ArrayList<>();
    ArrayList<TimeTable> comingTimes = new ArrayList<>();
    Handler sliderhandler = new Handler();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentTeacherDashBoardDetailBinding.inflate(getLayoutInflater());
        ArrayList<String> data= new ArrayList<>();
        data.add("Muzamil");
        data.add("ali");

        binding.spinnerDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDay = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getContext().getApplicationContext(), "Selected day: " + selectedDay, Toast.LENGTH_SHORT).show();
                ArrayList<TimeTable> timeSlotsTodays=new ArrayList<>();
                for (TimeTable timeslot: timeSlots) {
                    if(timeslot.getDay().contains(selectedDay)){
                        timeSlotsTodays.add(timeslot) ;
                    }

                }
                TeacherTimeTableAdapter adapter = new
                        TeacherTimeTableAdapter(timeSlotsTodays, getActivity());
                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                binding.RecycerviewTeacherHome.setLayoutManager(manager);
                binding.RecycerviewTeacherHome.setHasFixedSize(true);
                binding.RecycerviewTeacherHome.
                        setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
//        PopupWindow popupWindow = new PopupWindow(getContext());
//
//        RecyclerView recyclerView = new RecyclerView(getContext());
//        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//
//        // Create and set the layout manager for the RecyclerView (e.g., LinearLayoutManager)
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);
//
//
//        // Create the RecyclerView and set its adapter
//        TeacherNotificationAdapter adapterNotification = new
//                TeacherNotificationAdapter(data, getActivity());
//        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(manager);
//
//        // Replace with your own adapter
//        recyclerView.setAdapter(adapterNotification);
//
//        // Set layout manager and other properties for the RecyclerView
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setHasFixedSize(true);
//
//        // Set the width and height of the PopupWindow
//        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        // Set the content view of the PopupWindow to the RecyclerView
//        popupWindow.setContentView(recyclerView);
//
//        // Show the popup window anchored to the notification icon
////        View notificationIcon = null;
//        popupWindow.showAsDropDown(binding.btNotification);
        binding.btNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        String fragmentData= getArguments().getString("FragmentData");
        MEYE_USER user= new Gson().fromJson(fragmentData,MEYE_USER.class);
//        Toast.makeText(getContext(), ""+fragmentData, Toast.LENGTH_SHORT).show();


        RetrofitClient client= RetrofitClient.getInstance();
        Api api= client.getMyApi();
        api.timetable_teacher_details(user.getName()).enqueue(new Callback<ArrayList<TimeTable>>() {
            @Override
            public void onResponse(Call<ArrayList<TimeTable>> call, Response<ArrayList<TimeTable>> response) {
                if(response.isSuccessful()){
//                    Toast.makeText(getContext(), "Successful"+response.body().get(0).getCourseCode(), Toast.LENGTH_SHORT).show();
                    timeSlots.clear();
                    timeSlots.addAll(response.body());

                    ArrayList<String> dataList= new ArrayList<>();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        dataList.addAll(timeSlots.stream().distinct().map(t -> t.getDay()).distinct().collect(Collectors.toList()));
                    }

                    ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(getContext(), com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item,dataList);
                    binding.spinnerDays.setAdapter(adapterSpinner);

                    TimeZone timeZone = TimeZone.getTimeZone("Asia/Karachi");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeZone(timeZone); // Set the desired time zone if needed
                    String currentDay = dateFormat.format(calendar.getTime());
                    if(currentDay !=null){
                        String selectedItemText = currentDay; // Text of the item you want to select
                        int selectedIndex = adapterSpinner.getPosition(selectedItemText);
//                        Toast.makeText(getContext(), ""+selectedIndex, Toast.LENGTH_SHORT).show();
                        if(selectedIndex!=-1){
                            binding.spinnerDays.setSelection(selectedIndex);
                            ArrayList<TimeTable> timeSlotsTodays=new ArrayList<>();
                            for (TimeTable timeslot: timeSlots) {
                                if(timeslot.getDay().contains(currentDay )){
                                    timeSlotsTodays.add(timeslot) ;
                                }

                            }
                        TeacherTimeTableAdapter adapter = new
                                TeacherTimeTableAdapter(timeSlotsTodays, getActivity());
                        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                        binding.RecycerviewTeacherHome.setLayoutManager(manager);
                        binding.RecycerviewTeacherHome.setHasFixedSize(true);
                        binding.RecycerviewTeacherHome.
                                setAdapter(adapter);
                        }
                        else {
                            Toast.makeText(getContext(), "Today No Class", Toast.LENGTH_SHORT).show();
                        }
//
                    }
                    else {
                        Toast.makeText(getContext(), "Today No Class", Toast.LENGTH_SHORT).show();
                    }


                  //  Toast.makeText(getContext(), ""+timeSlots.size(), Toast.LENGTH_SHORT).show();
//                    SessionTime();
//                    sliderPlay();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TimeTable>> call, Throwable t) {
                Toast.makeText(getContext(), ""+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });



        //code end
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        handler.removeCallbacks(runnable);
    }

//    private void sliderPlay(){
//        if(comingTimes.size()==0){
//            binding.SliderComingActivityShowOrHide.setVisibility(View.GONE);
//
//        }else {
//            binding.comingSessionNo.setVisibility(View.GONE);
//            runnable = new Runnable() {
//                @Override
//                public void run() {
//                    if (currentPosition == adapter.getItemCount() - 1) {
//                        currentPosition = 0;
//                    } else {
//                        currentPosition++;
//                    }
//
//                    binding.sliderPagerTeacherScheduleComing.setCurrentItem(currentPosition, true);
//                    handler.postDelayed(this, 2000);
//                }
//            };
//
//            //Toast.makeText(getContext(), ""+pastTimes.get(0).getDay(), Toast.LENGTH_SHORT).show();
//            adapter = new SessionComingSliderAdapter(comingTimes, getContext(), TeacherDashBoardDetailFragment.this);
//            binding.sliderPagerTeacherScheduleComing.setAdapter(adapter);
//            handler.postDelayed(runnable, 9000);
//        }
//        adapterpast = new SessionComingSliderAdapter(pastTimes,getContext(),TeacherDashBoardDetailFragment.this);
//        binding.sliderPagerTeacherSchedulePast.setAdapter(adapterpast);
//    }

    private void showRecyclerViewPopup() {
        // Create a new PopupWindow

    }



    private void SessionTime(){
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Karachi");
        Calendar calendar = Calendar.getInstance(timeZone);
        Date currentTime = calendar.getTime();

     //   Toast.makeText(getContext(), ""+timeSlots.size(), Toast.LENGTH_SHORT).show();
        for (TimeTable timeSlot : timeSlots) {
            Date sessionTime = null;
         //   Toast.makeText(getContext(), "uuuu", Toast.LENGTH_SHORT).show();
            // Convert session date and time to Pakistan time zone
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE,hh:mm");
//            Toast.makeText(getContext(), "uu"+dateFormat, Toast.LENGTH_SHORT).show();
            dateFormat.setTimeZone(timeZone);
            try {
                sessionTime = dateFormat.parse(timeSlot.getDay() + "," + timeSlot.getStarttime());
           //     Toast.makeText(getContext(), ""+sessionTime, Toast.LENGTH_SHORT).show();
            } catch (ParseException e) {
                e.printStackTrace();
            }
//            Toast.makeText(getContext(), sessionTime+"", Toast.LENGTH_SHORT).show();
            if (sessionTime.before(currentTime)) {

             //   Toast.makeText(getContext(), "c:"+currentTime, Toast.LENGTH_SHORT).show();
                pastTimes.add(timeSlot);
            } else {
//                Toast.makeText(getContext(), ""+timeSlot.getTeacherName(), Toast.LENGTH_SHORT).show();
                comingTimes.add(timeSlot);
            }
        }

    }


}