package com.example.meyepro.DirectorDashBoard.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.meyepro.DirectorDashBoard.Adapter.DirectorHomeDataTableAdapter;
import com.example.meyepro.DirectorDashBoard.Model.ScheduleDetailsAndCHR;
import com.example.meyepro.R;
import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.FragmentDirectorHomeShortReportBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class director_home_short_report_Fragment extends Fragment {
FragmentDirectorHomeShortReportBinding binding;
    DirectorHomeDataTableAdapter adapter;
    ArrayList<ScheduleDetailsAndCHR> scheduleDetailsAndCHRS=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDirectorHomeShortReportBinding.inflate(getLayoutInflater());
        //start code
        binding.imageviewAdminHomeSearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup();
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        api.get_all_teacher_chr().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){

                    scheduleDetailsAndCHRS.clear();



                    scheduleDetailsAndCHRS.addAll(new Gson().fromJson(response.body(),new TypeToken<ArrayList<ScheduleDetailsAndCHR>>(){}.getType()));

                    ArrayList<ScheduleDetailsAndCHR> scheduleDetaShortList=new ArrayList<>();
                    for (ScheduleDetailsAndCHR scheduleData: scheduleDetailsAndCHRS) {
                        if(scheduleData.getStatus().contains("Late")){
                            scheduleDetaShortList.add(scheduleData);
                        }
                    }
                   adapter = new
                            DirectorHomeDataTableAdapter(scheduleDetaShortList, getActivity(),director_home_short_report_Fragment.this);
                    LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                    binding.RecycerviewDirectorShortReport.setLayoutManager(manager);
                    binding.RecycerviewDirectorShortReport.setHasFixedSize(true);
                    binding.RecycerviewDirectorShortReport.
                            setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });



        //end code
        return binding.getRoot();
    }
    public void showPopup() {
        if(scheduleDetailsAndCHRS.size()!=0){
            PopupMenu popup = new PopupMenu(getContext(), binding.imageviewAdminHomeSearchIcon);
            popup.getMenuInflater().inflate(R.menu.popup_director_search_menu, popup.getMenu());
            Object menuHelper;
            Class[] argTypes;
            ArrayList<ScheduleDetailsAndCHR> filteredList = new ArrayList<>();
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_Director_Name:
                            // call member class or function here

                            for (ScheduleDetailsAndCHR checkItem : scheduleDetailsAndCHRS) {
                                if (checkItem.getTeacherName().toLowerCase().contains(binding.editTextAdminHomeSearch.getText().toString().toLowerCase())) {
                                    filteredList.add(checkItem);
                                }
                            }
                            adapter.filterList(filteredList);
                            if(filteredList.size()==0){

                                Toast.makeText(getContext(), "Not Found", Toast.LENGTH_SHORT).show();
                            }
//
//                        DirectorHomeAdapter adapter = new
//                                DirectorHomeAdapter(filteredList, getActivity(),DirectorRecyclerViewCalingHomeFragment.this);
//                        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//                        binding.RecycerviewDirectorHome.setLayoutManager(manager);
//                        binding.RecycerviewDirectorHome.setHasFixedSize(true);
//                        binding.RecycerviewDirectorHome.
//                                setAdapter(adapter);
//                            Toast.makeText(getContext(), "name", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.action_Director_Course:

                            for (ScheduleDetailsAndCHR checkItem : scheduleDetailsAndCHRS) {
                                if (checkItem.getCourseName().toLowerCase().contains(binding.editTextAdminHomeSearch.getText().toString().toLowerCase())) {
                                    filteredList.add(checkItem);
                                }
                            }
                            adapter.filterList(filteredList);
                            if(filteredList.size()==0){

                                Toast.makeText(getContext(), "Not Found", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case R.id.action_Director_Date:
                            for (ScheduleDetailsAndCHR checkItem : scheduleDetailsAndCHRS) {
                                if (checkItem.getDate().toLowerCase().contains(binding.editTextAdminHomeSearch.getText().toString().toLowerCase())) {
                                    filteredList.add(checkItem);
                                }
                            }
                            adapter.filterList(filteredList);
                            if(filteredList.size()==0){

                                Toast.makeText(getContext(), "Not Found", Toast.LENGTH_SHORT).show();
                            }
                            // call My Buddies class or function here
                            break;
                        case R.id.action_Director_Discipline:
                            for (ScheduleDetailsAndCHR checkItem : scheduleDetailsAndCHRS) {
                                if (checkItem.getDiscipline().toLowerCase().contains(binding.editTextAdminHomeSearch.getText().toString().toLowerCase())) {
                                    filteredList.add(checkItem);
                                }
                            }
                            adapter.filterList(filteredList);
                            if(filteredList.size()==0){

                                Toast.makeText(getContext(), "Not Found", Toast.LENGTH_SHORT).show();
                            }
                            // call My Buddies class or function here
                            return true;
                    }
                    return true;
                }
            });
            popup.show();
        }

    }
}