package com.example.meyepro.fragments.Admin.Setting.AssignCourse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.adapters.AdminSettingAssignCourseAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityAdminSettingAssignCourseBinding;
import com.example.meyepro.models.SectionOffer;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminSettingAssignCourseActivity extends AppCompatActivity {
ActivityAdminSettingAssignCourseBinding binding;
    AdminSettingAssignCourseAdapter adapter;
   ArrayList<SectionOffer> sectionOffers= new ArrayList<SectionOffer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAdminSettingAssignCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        List<String> disciplines = null;

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectCourseObj.size()!=0){
                    JSONArray jsonArray = new JSONArray();
                    JSONObject jsonObject = new JSONObject();
                    jsonArray.put(jsonObject);
                    //search ID List
//                    int secID = 0; // set a default value if no matching SectionOffer is found
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                        secID = sectionOffers.stream()
//                                .filter(s -> s.getDiscipline().contains(binding.spinnerDiscipline.getSelectedItem().toString()))
//                                .findFirst()
//                                .map(SectionOffer::getId)
//                                .orElse(-1);
//                    }
//                    Toast.makeText(AdminSettingAssignCourseActivity.this, ""+ secID, Toast.LENGTH_SHORT).show();


                    try {
                        Gson gson= new Gson();
                        jsonObject.put("id", 0);
                        jsonObject.put("sectionOfferId",gson.toJson(selectCourseObj));
                        jsonObject.put("discipline", binding.spinnerDiscipline.getSelectedItem());
//
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

//                    Toast.makeText(AdminSettingAssignCourseActivity.this, ""+jsonArray.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),AdminSettingAssignCourseAssignStudentActivity.class);
                intent.putExtra("Data",jsonArray.toString());
                startActivity(intent);

                }else{
                    Toast.makeText(AdminSettingAssignCourseActivity.this, "Place select Course ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        RetrofitClient client = RetrofitClient.getInstance();
        Api api = client.getMyApi();

        api.Section_Offer_details().enqueue(new Callback<ArrayList<SectionOffer>>() {
            @Override
            public void onResponse(Call<ArrayList<SectionOffer>> call, Response<ArrayList<SectionOffer>> response) {
//               Toast.makeText(getApplicationContext(), "Successful"+response.body().get(0).courseCode, Toast.LENGTH_SHORT).show();
               if(response.isSuccessful()){
                   sectionOffers.clear();
                   sectionOffers.addAll(response.body());

                   List<String> disciplines = new ArrayList<>();
                   //Set<String> seen = new HashSet<>();
                   for (SectionOffer course : sectionOffers) {
                       if (!disciplines.contains(course.discipline)) {
                           disciplines.add(course.discipline);
                       }
                   }
                   ArrayAdapter<String> adapterDiscipline = new ArrayAdapter<String>(getApplicationContext(),
                           android.R.layout.simple_spinner_item, disciplines);
                   binding.spinnerDiscipline.setAdapter(adapterDiscipline);

                   ArrayList<SectionOffer> selectcourse = new ArrayList<>();
                   //Set<String> seen = new HashSet<>();
                   for (SectionOffer course : sectionOffers) {
                       if(course.discipline.contains(binding.spinnerDiscipline.getSelectedItem().toString())){
                           if (!selectcourse.contains(course)) {
                               selectcourse.add(course);
                           }
                       }

                   }
                   adapter = new
                           AdminSettingAssignCourseAdapter(selectcourse, AdminSettingAssignCourseActivity.this);
                   LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                   binding.RecyclerviewAssignCourseCell2.setLayoutManager(manager);
                   binding.RecyclerviewAssignCourseCell2.
                           setAdapter(adapter);
               }

            }

            @Override
            public void onFailure(Call<ArrayList<SectionOffer>> call, Throwable t) {

            }
        });

        binding.spinnerDiscipline.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<SectionOffer> selectcourse = new ArrayList<>();
                //Set<String> seen = new HashSet<>();
                String item = binding.spinnerDiscipline.getItemAtPosition(i).toString();
                for (SectionOffer course : sectionOffers) {
                    if(course.discipline.contains(item)){
//                      //search name create unique value
                        boolean search=true;
                        for (SectionOffer coursesearch : selectcourse) {
                           if( coursesearch.courseName.contains(course.courseName)){
                               search =false;
                             //  Toast.makeText(AdminSettingAssignCourseActivity.this, ""+coursesearch.courseName, Toast.LENGTH_SHORT).show();
                            }
                        }
                        if (!selectcourse.contains(course)&& search) {
                            selectcourse.add(course);
                        }
                    }
                    //end unique

                }
                //calling recyclerview
                adapter = new
                        AdminSettingAssignCourseAdapter(selectcourse, AdminSettingAssignCourseActivity.this);
                LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                binding.RecyclerviewAssignCourseCell2.setLayoutManager(manager);
                binding.RecyclerviewAssignCourseCell2.
                        setAdapter(adapter);
                selectCourse=new ArrayList<>();
                selectCourseObj= new ArrayList<>();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        AdminSettingAssignCourseAdapter adapter = new
//                AdminSettingAssignCourseAdapter(sectionOffers, AdminSettingAssignCourseActivity.this);
//        binding.RecyclerviewAssignCourseCell2.
//                setAdapter(adapter);
     //   Toast.makeText(this, ""+sectionOffers.size(), Toast.LENGTH_SHORT).show();

//       List<String> uniquegetDiscipline = null;
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            uniquegetDiscipline = sectionOffers.stream()
//                    .map(course -> course.getDiscipline())
//                    .distinct()
//                    .collect(Collectors.toList());
//        }
        //Toast.makeText(this, ""+uniquegetDiscipline.get(0), Toast.LENGTH_SHORT).show();


    }
    ArrayList<Integer> selectCourse= new ArrayList<>();
    ArrayList<SectionOffer> selectCourseObj= new ArrayList<>();
    public void RecyclerviewAssignCourseSelect(SectionOffer obj, Context context, String select) {
           if(select.contains("ADD")){
//               selectCourseObj.add(obj);
               selectCourseObj.add(obj);
           }
            else{
               selectCourseObj.remove(obj);
////               selectCourseObj.remove(obj);
//            int index=   selectCourse.indexOf(obj.getId());
//            selectCourse.remove(index);
////               if(selectCourse.contains()&& selectCourse!=null){
////                   selectCourse.remove(obj.getId());
////               }

           }


//        String allCourses=null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            // change arrayList to string
//            allCourses =selectCourseObj.stream()
//                    .map(s->String.valueOf(s.getId()))
//                    .collect(Collectors.joining(", "));
//        }
        String allCourses = String.join(", ",String.valueOf(selectCourse));
//        Toast.makeText(context, binding.spinnerDiscipline.getSelectedItem()+"ss"+allCourses, Toast.LENGTH_SHORT).show();
//


    }


}