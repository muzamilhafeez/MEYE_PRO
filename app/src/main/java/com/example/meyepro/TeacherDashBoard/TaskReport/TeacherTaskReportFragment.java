package com.example.meyepro.TeacherDashBoard.TaskReport;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.adapters.TaskReportShowAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.FragmentTeacherTaskReportBinding;
import com.example.meyepro.fragments.Admin.TaskReport.AdminTaskReportActivity;
import com.example.meyepro.login;
import com.example.meyepro.models.TaskReport;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TeacherTaskReportFragment extends Fragment {
FragmentTeacherTaskReportBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTeacherTaskReportBinding.inflate(getLayoutInflater());
        //start code

        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        Api api= retrofitClient.getMyApi();
        api.api_taskreport().enqueue(new Callback<ArrayList<TaskReport>>() {
            @Override
            public void onResponse(Call<ArrayList<TaskReport>> call, Response<ArrayList<TaskReport>> response) {
                if (response.isSuccessful()){

                    ArrayList<TaskReport> list= new ArrayList<>();
                    for (TaskReport teacher :response.body()) {
                        if(teacher.getShown()&& teacher.getTeacherName().contains(login.userInfoDetail.getName())){
                            list.add(teacher);
                        }
                    }
                    TaskReportShowAdapter adapter = new
                            TaskReportShowAdapter(list, getContext(), "TeacherTaskReportFragment");
                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    binding.RecycerviewTeacherTaskReport.setLayoutManager(manager);
                    binding.RecycerviewTeacherTaskReport.setHasFixedSize(true);
                    binding.RecycerviewTeacherTaskReport.
                            setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TaskReport>> call, Throwable t) {
                Toast.makeText(getContext(), ""+t, Toast.LENGTH_SHORT).show();
            }
        });


        //end code
        return binding.getRoot();
    }
}