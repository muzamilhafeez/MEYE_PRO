package com.example.meyepro.DirectorDashBoard.Home;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.meyepro.DirectorDashBoard.Adapter.DirectorHomeAdapter;
import com.example.meyepro.DirectorDashBoard.DirectorDashBoardActivity;
import com.example.meyepro.DirectorDashBoard.Model.ScheduleDetailsAndCHR;
import com.example.meyepro.R;
import com.example.meyepro.TeacherDashBoard.Adapter.TeacherCHRAdapter;
import com.example.meyepro.TeacherDashBoard.CHR.TeacherCHRFragment;
import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.FragmentDirectorDashBoardHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class DirectorDashBoardHomeFragment extends Fragment {
FragmentDirectorDashBoardHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentDirectorDashBoardHomeBinding.inflate(getLayoutInflater());
        //code start

//        String json="[\n" +
//                "  {\n" +
//                "    \"id\": 24,\n" +
//                "    \"courseName\": \"CPS\",\n" +
//                "    \"day\": \"Monday\",\n" +
//                "    \"discipline\": \"BCS-8B\",\n" +
//                "    \"startTime\": \"08:30:00.0000000\",\n" +
//                "    \"endTime\": \"10:00:00.0000000\",\n" +
//                "    \"totalTimeIn\": \"1\",\n" +
//                "    \"totalTimeOut\": \"1\",\n" +
//                "    \"status\": \"Late + Held\",\n" +
//                "    \"date\": \"2023-03-05\",\n" +
//                "    \"teacherName\": \"Abdul Sami\",\n" +
//                "    \"image\": \"image_picker1988187421526006098.jpg\",\n" +
//                "    \"teacherCHRActivityDetails\": [\n" +
//                "      {\n" +
//                "        \"timein\": \"2023-03-05T10:31:00\",\n" +
//                "        \"timeout\": \"2023-03-05T10:32:00\",\n" +
//                "        \"sit\": 0,\n" +
//                "        \"stand\": 1,\n" +
//                "        \"mobile\": 0\n" +
//                "      }\n" +
//                "    ]\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"id\": 25,\n" +
//                "    \"courseName\": \"CPS\",\n" +
//                "    \"day\": \"Monday\",\n" +
//                "    \"discipline\": \"BCS-8B\",\n" +
//                "    \"startTime\": \"08:30:00.0000000\",\n" +
//                "    \"endTime\": \"10:00:00.0000000\",\n" +
//                "    \"totalTimeIn\": \"2\",\n" +
//                "    \"totalTimeOut\": \"0\",\n" +
//                "    \"status\": \"Held\",\n" +
//                "    \"date\": \"2023-03-05\",\n" +
//                "    \"teacherName\": \"Abdul Sami\",\n" +
//                "    \"image\": \"image_picker1988187421526006098.jpg\",\n" +
//                "    \"teacherCHRActivityDetails\": [\n" +
//                "      {\n" +
//                "        \"timein\": \"2023-03-05T12:02:00\",\n" +
//                "        \"timeout\": \"2023-03-05T12:04:00\",\n" +
//                "        \"sit\": 2,\n" +
//                "        \"stand\": 0,\n" +
//                "        \"mobile\": 0\n" +
//                "      }\n" +
//                "    ]\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"id\": 39,\n" +
//                "    \"courseName\": \"CPS\",\n" +
//                "    \"day\": \"Monday\",\n" +
//                "    \"discipline\": \"BCS-8B\",\n" +
//                "    \"startTime\": \"08:30:00.0000000\",\n" +
//                "    \"endTime\": \"10:00:00.0000000\",\n" +
//                "    \"totalTimeIn\": \"2\",\n" +
//                "    \"totalTimeOut\": \"2\",\n" +
//                "    \"status\": \"Late + Held\",\n" +
//                "    \"date\": \"2023-04-18\",\n" +
//                "    \"teacherName\": \"Abdul Sami\",\n" +
//                "    \"image\": \"image_picker1988187421526006098.jpg\",\n" +
//                "    \"teacherCHRActivityDetails\": [\n" +
//                "      {\n" +
//                "        \"timein\": \"2023-04-18T15:38:00\",\n" +
//                "        \"timeout\": \"2023-04-18T15:39:00\",\n" +
//                "        \"sit\": 0,\n" +
//                "        \"stand\": 1,\n" +
//                "        \"mobile\": 0\n" +
//                "      },\n" +
//                "      {\n" +
//                "        \"timein\": \"2023-04-18T15:41:00\",\n" +
//                "        \"timeout\": \"2023-04-18T15:42:00\",\n" +
//                "        \"sit\": 0,\n" +
//                "        \"stand\": 1,\n" +
//                "        \"mobile\": 0\n" +
//                "      }\n" +
//                "    ]\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"id\": 40,\n" +
//                "    \"courseName\": \"CPS\",\n" +
//                "    \"day\": \"Monday\",\n" +
//                "    \"discipline\": \"BCS-8B\",\n" +
//                "    \"startTime\": \"08:30:00.0000000\",\n" +
//                "    \"endTime\": \"10:00:00.0000000\",\n" +
//                "    \"totalTimeIn\": \"0\",\n" +
//                "    \"totalTimeOut\": \"4\",\n" +
//                "    \"status\": \"Not Held\",\n" +
//                "    \"date\": \"2023-04-20\",\n" +
//                "    \"teacherName\": \"Abdul Sami\",\n" +
//                "    \"image\": \"image_picker1988187421526006098.jpg\",\n" +
//                "    \"teacherCHRActivityDetails\": [\n" +
//                "      {\n" +
//                "        \"timein\": null,\n" +
//                "        \"timeout\": null,\n" +
//                "        \"sit\": null,\n" +
//                "        \"stand\": null,\n" +
//                "        \"mobile\": null\n" +
//                "      }\n" +
//                "    ]\n" +
//                "  }\n" +
//                "]";
//        //
        loadFragmentHome(new DirectorRecyclerViewCalingHomeFragment() );
//        Fragment curren=getParentFragment();
        binding.topNavDirector.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_Drrector_Home:
                        // Create an instance of the previous fragment
//                        PreviousFragment previousFragment = new PreviousFragment();
//
//// Set the saved state as arguments
//                        Bundle bundle = new Bundle();
//                        bundle.putString("key", "value");
//                        previousFragment.setArguments(bundle);
//                        Fragment fragment = requireActivity().getSupportFragmentManager().findFragmentById(R.id.frament_container_Director);
//                        if (fragment instanceof DirectorDashBoardHomeFragment) {
//                            Toast.makeText(getContext(), "kk", Toast.LENGTH_SHORT).show();
//                            loadFragment(curren);
//                        }
                        loadFragmentHome(new DirectorRecyclerViewCalingHomeFragment() );
//                        loadFragment(curren);
//                        loadFragment(new DirectorHomeSwitchFActivityragment());
                        break;

                    default:
                        loadFragmentHome(new director_home_short_report_Fragment());
//                        loadFragment(new director_home_short_report_Fragment());
                        break;
                }
                return true;
            }
        });



        //end code
        return binding.getRoot();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the fragment
        outState.putString("key", "value");
    }


//    private void loadFragment(Fragment f){
//        FragmentTransaction ft =
//                getActivity().getSupportFragmentManager().beginTransaction();
//        //manage back track
////        ft.add(R.id.frament_container_admin, f);
//        ft.replace(R.id.frament_container_Director, f);
//        //   ft.addToBackStack(null);
//        ft.commit();
//    }
    private void loadFragmentHome(Fragment f) {
        FragmentTransaction ft =
                getActivity().getSupportFragmentManager().beginTransaction();
        //manage back track
//        ft.add(R.id.frament_container_admin, f);
        ft.replace(R.id.frament_container_Director_dashboard, f);
        //   ft.addToBackStack(null);
        ft.commit();
    }

}