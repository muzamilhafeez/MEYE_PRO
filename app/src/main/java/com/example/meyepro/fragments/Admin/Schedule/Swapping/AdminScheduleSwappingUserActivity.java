package com.example.meyepro.fragments.Admin.Schedule.Swapping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.meyepro.adapters.AdminScheduleSwappingUserAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityAdminScheduleSwappingUserBinding;
import com.example.meyepro.fragments.Admin.Schedule.Reschedule.AdminScheduleReScheduleFreeSlotActivity;
import com.example.meyepro.models.MEYE_USER;
import com.example.meyepro.models.Swapping;
import com.example.meyepro.models.SwappingUser;
import com.example.meyepro.models.TimeTable;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AdminScheduleSwappingUserActivity extends AppCompatActivity {
ActivityAdminScheduleSwappingUserBinding binding;
String TimeTableSelectedData;
    TimeTable timeTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAdminScheduleSwappingUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String  intentData = getIntent().getStringExtra("UserIntent");

        TimeTableSelectedData= getIntent().getStringExtra("TimeTableSelectedData");
        timeTable = new Gson().fromJson(TimeTableSelectedData,TimeTable.class);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
//        binding.editTextAdminViewSearch.setText(timeTable.getId()+"");
        api.get_swapping_teacher_data(timeTable.getDay(),timeTable.getStarttime(),timeTable.getEndtime(),timeTable.getId()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Toast.makeText(AdminScheduleSwappingUserActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()){
                    if(response.body().startsWith("[")){
                        AdminScheduleSwappingUserAdapter adapter = new
                                AdminScheduleSwappingUserAdapter(new Gson().fromJson(response.body(), new TypeToken<ArrayList<SwappingUser>>(){}.getType()), AdminScheduleSwappingUserActivity.this);
                        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                        binding.RecycerviewScheduleSwappingUser.setLayoutManager(manager);
                        binding.RecycerviewScheduleSwappingUser.setHasFixedSize(true);
                        binding.RecycerviewScheduleSwappingUser.
                                setAdapter(adapter);
                    }else{
                        Toast.makeText(AdminScheduleSwappingUserActivity.this, ""+response.body(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(AdminScheduleSwappingUserActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void recyclerViewSwappingUserSelectedClick(SwappingUser obj, Context context) {
        RetrofitClient client= RetrofitClient.getInstance();
        Api api= client.getMyApi();
        Swapping swapping= new Swapping();
        swapping.setTimeTableIdFrom(timeTable.getId());
        swapping.setTimeTableIdTo(obj.getTimeTableId());
        swapping.setDate(getDateFromDayOfWeek(timeTable.getDay()));
        swapping.setDay(timeTable.getDay());
        swapping.setStartTime(timeTable.getStarttime());
        swapping.setEndTime(timeTable.getEndtime());
        swapping.setStatus(false);
        api.add_swapping(swapping).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if(response.code()==200) {
                    Map<String, String> reschedule = response.body();
                    if (reschedule.get("data").contains("okay")) {
                        SweetAlertDialog dialog = new SweetAlertDialog(AdminScheduleSwappingUserActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Add Successfully!")
                                .setContentText(reschedule.get("data"))
                                .setConfirmText("OK")
                                .setConfirmClickListener(null);
                        dialog.show();
                    } else {
                        Toast.makeText(getApplicationContext(), "" + reschedule.get("data"), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {

            }
        });
//        binding.editTextAdminViewSearch.setText(new Gson().toJson(swapping));
//        Toast.makeText(context, ""+new Gson().toJson(obj), Toast.LENGTH_SHORT).show();
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