package com.example.meyepro.fragments.Admin.Setting.RuleSetting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meyepro.adapters.AdminTimeTableScheduleAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityAdminSettingRuleSettingSetRuleBinding;
import com.example.meyepro.models.MEYE_USER;
import com.example.meyepro.models.Rules;
import com.example.meyepro.models.TimeTable;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminSettingRuleSettingSetRuleActivity extends AppCompatActivity {
ActivityAdminSettingRuleSettingSetRuleBinding binding;
    AdminTimeTableScheduleAdapter adapter;
    ArrayList<TimeTable> timeSlots=new ArrayList<>();
    ArrayList<TimeTable> ScheduletimeSlots=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAdminSettingRuleSettingSetRuleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.textViewTeacherName.setText("sds");
        binding.progressBar.setVisibility(View.VISIBLE);
        MEYE_USER UserIntent=new Gson().fromJson(getIntent().getStringExtra("UserIntent"),MEYE_USER.class);
        if(UserIntent.getImage()!=null){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+UserIntent.getRole()+"/"+UserIntent.getImage())).into(binding.profileImageTeacher);
        }
        binding.textViewTeacherName.setText(UserIntent.getName());
        //select all schedule
        binding.checkBoxSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                Toast.makeText(AdminSettingRuleSettingSetRuleActivity.this, ""+b, Toast.LENGTH_SHORT).show();
            if(ScheduletimeSlots.size()!=0){
                if(b){
                    timeSlotsSelected.clear();
                    timeSlotsSelected.addAll(ScheduletimeSlots);
                    AdminTimeTableScheduleAdapter adapter = new
                            AdminTimeTableScheduleAdapter(timeSlots, ScheduletimeSlots, AdminSettingRuleSettingSetRuleActivity.this,true, "");
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    binding.RecycerviewAdminRuleSetTimeTable.setLayoutManager(manager);
                    binding.RecycerviewAdminRuleSetTimeTable.
                            setAdapter(adapter);
                }else{
                    timeSlotsSelected.clear();
                    AdminTimeTableScheduleAdapter adapter = new
                            AdminTimeTableScheduleAdapter(timeSlots, ScheduletimeSlots, AdminSettingRuleSettingSetRuleActivity.this,false,"AdminSettingRuleSettingSetRuleActivity");
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    binding.RecycerviewAdminRuleSetTimeTable.setLayoutManager(manager);
                    binding.RecycerviewAdminRuleSetTimeTable.
                            setAdapter(adapter);
                }
            }else{
                compoundButton.setChecked(false);
                Toast.makeText(AdminSettingRuleSettingSetRuleActivity.this, "Please Wait Loading then select all", Toast.LENGTH_SHORT).show();
            }
            }
        });
        //save btn click
        binding.btnSetRuleSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timeSlotsSelected.size()!=0){
                    ArrayList<Rules> rulesList= new ArrayList<>();

                    for (TimeTable table :timeSlotsSelected) {
                        Rules rules= new Rules();
                        rules.setId(0);
                        if(binding.checkBoxFirst10.isChecked()){
                            rules.setStartRecord(1);
                        }
                        if(binding.checkBoxLast10Min.isChecked()){
                            rules.setEndRecord(1);
                        }
                        if(binding.checkBoxMid10Min.isChecked()){
                            rules.setMidRecord(1);
                        }
                        if(binding.checkBoxFull10Min.isChecked()){
                            rules.setFullRecord(1);
                        }
                        rules.setTimeTableId(table.getId());
                        rulesList.add(rules);
                    }//end foreach


                    RetrofitClient client = RetrofitClient.getInstance();
                    Api api = client.getMyApi();
                    api.add_rules(UserIntent.getName(),rulesList).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.isSuccessful()){
                                new SweetAlertDialog(AdminSettingRuleSettingSetRuleActivity.this)
                                        .setTitleText(response.body())
                                        .show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(AdminSettingRuleSettingSetRuleActivity.this, ""+t.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
//                    new SweetAlertDialog(AdminSettingRuleSettingSetRuleActivity.this)
//                            .setTitleText(timeSlotsSelected.size()+":"+new Gson().toJson(rulesList))
//                                    .show();
                    }else{

                    Toast.makeText(AdminSettingRuleSettingSetRuleActivity.this, "Please select Schedule", Toast.LENGTH_SHORT).show();
                }


            }
        });
        //api calling
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
                    api.timetable_teacher_details(UserIntent.getName()).enqueue(new Callback<ArrayList<TimeTable>>() {
                        @Override
                        public void onResponse(Call<ArrayList<TimeTable>> call, Response<ArrayList<TimeTable>> response) {
                            if(response.isSuccessful()){
//                                Toast.makeText(getApplicationContext(), "Successful"+response.body().get(0).getCourseCode(), Toast.LENGTH_SHORT).show();
                                ScheduletimeSlots.clear();
                                ScheduletimeSlots.addAll(response.body());

                                AdminTimeTableScheduleAdapter adapter = new
                                        AdminTimeTableScheduleAdapter(timeSlots, ScheduletimeSlots, AdminSettingRuleSettingSetRuleActivity.this,"AdminSettingRuleSettingSetRuleActivity");
                                LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                                binding.RecycerviewAdminRuleSetTimeTable.setLayoutManager(manager);
                                //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
                                binding.RecycerviewAdminRuleSetTimeTable.
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
                Toast.makeText(getApplicationContext(), ""+t.toString(), Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.GONE);
                Log.e("MyApiService", "Error getting data: " + t.getMessage());
            }
        });
        //end api

    }

    ArrayList<TimeTable>  timeSlotsSelected = new ArrayList<>();
    public void recyclerViewSelectedTimeTableSlot(TimeTable timeTable, Context context, TextView textView) {
//
        if (timeSlotsSelected.contains(timeTable)) {
            timeSlotsSelected.remove(timeTable);
            textView.setBackgroundColor(Color.TRANSPARENT);
        } else {
            timeSlotsSelected.add(timeTable);
            textView.setBackgroundColor(Color.parseColor("#03A9F4"));
        }

//            textView.setBackgroundColor(Color.TRANSPARENT);
        }
//    // Helper method to check if the list contains a matching timeTable object
//    private boolean containsTimeTable(List<TimeTable> timeSlotsSelected, TimeTable timeTable) {
//        for (TimeTable selectedTimeTable : timeSlotsSelected) {
//            Toast.makeText(this, ""+selectedTimeTable.getId()+""+timeTable.getId(), Toast.LENGTH_SHORT).show();
//            if (selectedTimeTable.getId()==timeTable.getId()) {
//
//                return true;
//            }
//        }
//        return false;
//    }
//
//    // Helper method to remove the matching timeTable object from the list
//    private void removeTimeTable(List<TimeTable> timeSlotsSelected, TimeTable timeTable) {
//        Iterator<TimeTable> iterator = timeSlotsSelected.iterator();
//        while (iterator.hasNext()) {
//            TimeTable selectedTimeTable = iterator.next();
//            if (selectedTimeTable.equals(timeTable)) {
//                iterator.remove();
//                break;
//            }
//        }
//    }
    }
