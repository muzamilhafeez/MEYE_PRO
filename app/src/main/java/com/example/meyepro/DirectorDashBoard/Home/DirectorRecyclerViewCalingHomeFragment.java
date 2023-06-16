package com.example.meyepro.DirectorDashBoard.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.meyepro.DirectorDashBoard.Adapter.DirectorHomeAdapter;
import com.example.meyepro.DirectorDashBoard.Model.ScheduleDetailsAndCHR;
import com.example.meyepro.R;
import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.FragmentDirectorRecyclerViewCalingHomeBinding;
import com.example.meyepro.models.MEYE_USER;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class DirectorRecyclerViewCalingHomeFragment extends Fragment {
FragmentDirectorRecyclerViewCalingHomeBinding binding;
    DirectorHomeAdapter adapter;
    MenuItem itemSearch ;
    int menuID= R.id.action_Director_Name;
    ArrayList<ScheduleDetailsAndCHR> scheduleDetailsAndCHRS=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentDirectorRecyclerViewCalingHomeBinding.inflate(getLayoutInflater());
        //code start
//        NavigationView navigationView = getActivity().findViewById(R.id.action_Admin_View);
//        Menu menu = navigationView.getMenu();
//        PopupMenu popup = new PopupMenu(getContext(), binding.imageviewAdminHomeSearchIcon);
//        MenuItem itemSearch =popup.getMenu().getItem(1);
        binding.editTextAdminHomeSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
//                Toast.makeText(getContext(), ""+editable, Toast.LENGTH_SHORT).show();
                ArrayList<ScheduleDetailsAndCHR> filteredList = new ArrayList<>();
                switch (menuID) {
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
                      break;
                }

            }
        });
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
                   adapter = new
                            DirectorHomeAdapter(scheduleDetailsAndCHRS, getActivity(),DirectorRecyclerViewCalingHomeFragment.this);
                    LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                    binding.RecycerviewDirectorHome.setLayoutManager(manager);
                    binding.RecycerviewDirectorHome.setHasFixedSize(true);
                    binding.RecycerviewDirectorHome.
                            setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

        //code end
        return binding.getRoot();
    }


    public void recyclerviewDirectorDashBoarCellClick(ScheduleDetailsAndCHR obj, Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putString("IntentData",new Gson().toJson(obj));
        // Create a new instance of the StudentCourseFragment class
        DirectorHomeViewCHRFragment fragmentLoad = new DirectorHomeViewCHRFragment();
        // Set the arguments for the fragment
        fragmentLoad.setArguments(bundle);
        // Load the fragment
        loadFragment(fragmentLoad);
//        loadFragment(fragmentLoad);

    }

    private void loadFragment(Fragment f){
        FragmentTransaction ft =
                getActivity().getSupportFragmentManager().beginTransaction();
        //manage back track
//        ft.add(R.id.frament_container_admin, f);
        ft.replace(R.id.frament_container_Director, f);
        //   ft.addToBackStack(null);
        ft.commit();
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
                    menuID=item.getItemId();
                    return true;
                }
            });
            popup.show();
        }

    }
}