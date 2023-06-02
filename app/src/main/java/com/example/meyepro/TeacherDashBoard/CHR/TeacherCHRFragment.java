package com.example.meyepro.TeacherDashBoard.CHR;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meyepro.DirectorDashBoard.Model.ScheduleDetailsAndCHR;
import com.example.meyepro.R;
import com.example.meyepro.StudentDashBoard.Adapter.StudentOfCourseAttendanceAdapter;
import com.example.meyepro.StudentDashBoard.Attendance.StudentOFCourseAttendanceActivity;
import com.example.meyepro.StudentDashBoard.Model.StudentOFCourseAttendance;
import com.example.meyepro.TeacherDashBoard.Adapter.TeacherCHRAdapter;
import com.example.meyepro.adapters.AdminTimeTableScheduleAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.FragmentTeacherCHRBinding;
import com.example.meyepro.fragments.Admin.Schedule.Reschedule.AdminScheduleRecheduleSelectTimeTableActivity;
import com.example.meyepro.models.MEYE_USER;
import com.example.meyepro.models.TimeTable;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TeacherCHRFragment extends Fragment {
FragmentTeacherCHRBinding binding;
    ArrayList<TimeTable> timeSlots=new ArrayList<>();
    ArrayList<TimeTable> ScheduletimeSlots=new ArrayList<>();
    String fragmentData;
    ArrayList<ScheduleDetailsAndCHR> scheduleDetailsAndCHRS= new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentTeacherCHRBinding.inflate(getLayoutInflater());
        //code start
        fragmentData= getArguments().getString("FragmentData");
        MEYE_USER user= new Gson().fromJson(fragmentData,MEYE_USER.class);
        if(user!=null) {
            if (user.getImage() != null) {
                Picasso.get().load(Uri.parse(Api.BASE_URL + "api/get-user-image/UserImages/" + user.getRole() + "/" + user.getImage())).into(binding.profileImageTeacher);
            }
            binding.textViewTeacherName.setText(user.getName());
        }

//        Toast.makeText(getContext(), ""+fragmentData, Toast.LENGTH_SHORT).show();
        ApiCalling(user.getName());
//        RetrofitClient client= RetrofitClient.getInstance();
//        Api api= client.getMyApi();
//        api.All_timetable_teacher_details().enqueue(new Callback<ArrayList<TimeTable>>() {
//            @Override
//            public void onResponse(Call<ArrayList<TimeTable>> call, Response<ArrayList<TimeTable>> response) {
//                if(response.isSuccessful()){
////                    Toast.makeText(getApplicationContext(), "Successful"+response.body().get(0).getCourseCode(), Toast.LENGTH_SHORT).show();
//                    timeSlots.clear();
//                    timeSlots.addAll(response.body());
//                    AdminTimeTableScheduleAdapter adapter = new
//                            AdminTimeTableScheduleAdapter(timeSlots, AdminScheduleRecheduleSelectTimeTableActivity.this);
//                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
//                    binding.RecycerviewAdminRescheduleTimeTable.setLayoutManager(manager);
//                    //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
//                    binding.RecycerviewAdminRescheduleTimeTable.
//                            setAdapter(adapter);
//api schedule calling
//                    api.timetable_teacher_details("Adeel").enqueue(new Callback<ArrayList<TimeTable>>() {
//                        @Override
//                        public void onResponse(Call<ArrayList<TimeTable>> call, Response<ArrayList<TimeTable>> response) {
//                            if(response.isSuccessful()){
////                                Toast.makeText(getApplicationContext(), "Successful"+response.body().get(0).getCourseCode(), Toast.LENGTH_SHORT).show();
//                                ScheduletimeSlots.clear();
//                                ScheduletimeSlots.addAll(response.body());
//                                AdminTimeTableScheduleAdapter adapter = new
//                                        AdminTimeTableScheduleAdapter(timeSlots, ScheduletimeSlots, getContext(),TeacherCHRFragment.this);
//                                LinearLayoutManager manager = new LinearLayoutManager(getContext());
//                                binding.RecycerviewAdminRescheduleTimeTable.setLayoutManager(manager);
//                                //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
//                                binding.RecycerviewAdminRescheduleTimeTable.
//                                        setAdapter(adapter);
//
//                                binding.progressBar.setVisibility(View.GONE);
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<ArrayList<TimeTable>> call, Throwable t) {
//
//                            Toast.makeText(getContext(), ""+t.toString(), Toast.LENGTH_SHORT).show();
//                            binding.progressBar.setVisibility(View.GONE);
//                            Log.e("MyApiService", "Error getting data: " + t.getMessage());
//                        }
//                    });
//
//                    //end schedule TimeTable
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<TimeTable>> call, Throwable t) {
////                Toast.makeText(getApplicationContext(), ""+t.toString(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getContext(), ""+t.toString(), Toast.LENGTH_SHORT).show();
//                binding.progressBar.setVisibility(View.GONE);
//                Log.e("MyApiService", "Error getting data: " + t.getMessage());
//            }
//        });

        //end code
        return binding.getRoot();
    }
    public  void  ApiCalling(String Name){
        RetrofitClient client = RetrofitClient.getInstance();
        Api api = client.getMyApi();
        api.get_teacher_chr(Name).enqueue(new Callback<ArrayList<ScheduleDetailsAndCHR>>() {
            @Override
            public void onResponse(Call<ArrayList<ScheduleDetailsAndCHR>> call, Response<ArrayList<ScheduleDetailsAndCHR>> response) {

                if(response.isSuccessful()){
                    scheduleDetailsAndCHRS.clear();
                    scheduleDetailsAndCHRS.addAll(response.body());
//                    Toast.makeText(StudentOFCourseAttendanceActivity.this, ""+studentOFCourseAttendances.size(), Toast.LENGTH_SHORT).show();
                    TeacherCHRAdapter adapter = new
                            TeacherCHRAdapter(scheduleDetailsAndCHRS, getContext(),TeacherCHRFragment.this);
                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    binding.RecycerviewAdminRescheduleTimeTable.setLayoutManager(manager);
                    binding.RecycerviewAdminRescheduleTimeTable.setHasFixedSize(true);
                    binding.RecycerviewAdminRescheduleTimeTable.
                            setAdapter(adapter);
                    binding.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ScheduleDetailsAndCHR>> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void RecyclerViewTeacherCHRFragmentCellClick(ScheduleDetailsAndCHR obj, Context context) {
        Intent i = new Intent(context, TeacherSelectTimTableCHRActivity.class);
        i.putExtra("intentData",new Gson().toJson(obj));
        i.putExtra("UserLogin",fragmentData);
        context.startActivity(i);
    }
}