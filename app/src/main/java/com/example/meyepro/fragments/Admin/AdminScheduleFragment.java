package com.example.meyepro.fragments.Admin;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.adapters.AdminViewTeacherAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.CustomAdminScheduleLayoutBinding;
import com.example.meyepro.databinding.FragmentAdminScheduleBinding;
import com.example.meyepro.fragments.Admin.Schedule.PreSchedule.AdminSchedulePreScheduleSelectRangeActivity;
import com.example.meyepro.fragments.Admin.Schedule.Reschedule.AdminScheduleRescheduleFreeSlotSelectionActivity;
import com.example.meyepro.fragments.Admin.Schedule.Swapping.AdminScheduleSwappingFreeSlotActivity;
import com.example.meyepro.fragments.Admin.Schedule.Swapping.AdminScheduleSwappingTimeTableSelectActivity;
import com.example.meyepro.models.MEYE_USER;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class AdminScheduleFragment extends Fragment {
    FragmentAdminScheduleBinding Binding;
    ArrayList<MEYE_USER> data = new ArrayList<>();
    AdminViewTeacherAdapter adapter;
    MEYE_USER user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Create a new Bundle object
        Bundle bundle = new Bundle();

        // Inflate the layout for this fragment
        Binding = FragmentAdminScheduleBinding.inflate(inflater, container, false);
        //code
        String FragmentData = getArguments().getString("FragmentData");
        user= new Gson().fromJson(FragmentData,MEYE_USER.class);

        Binding.editTextAdminViewSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
//                Toast.makeText(getContext(), ""+editable, Toast.LENGTH_SHORT).show();
                ArrayList<MEYE_USER> filteredList = new ArrayList<>();
                for (MEYE_USER item : data) {
                    if (item.getName().toLowerCase().contains(editable.toString().toLowerCase())) {
                        filteredList.add(item);
                    }
                }
//                AdminViewTeacherAdapter     adaptersearch = new
//                        AdminViewTeacherAdapter(filteredList, getActivity(), ViewTeacherFragment.this,"ViewTeacherFragment");
//                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//                Binding.RecycerviewAdminViewTeacher.setLayoutManager(manager);
//                //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
//                Binding.RecycerviewAdminViewTeacher.
//                        setAdapter(adaptersearch);
//                recyclerview function calling
                adapter.filterList(filteredList);
            }
        });
//        // calling ScheduleReschedule
//            Binding.txtViewAdminScheduleReschedule.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    // Add data to the Bundle object using a key-value pair
//                    bundle.putString("SaveButton", "SchedulePreschedule");
//                    // Create a new instance of the receiving fragment
//                    AdminScheduleSelectTeacherFragment fragment = new AdminScheduleSelectTeacherFragment();
//                    // Set the arguments for the receiving fragment
//                    fragment.setArguments(bundle);
//                    loadFragment(fragment);
//                }
//            });
//
//        // calling Schedule Preschedule
//        Binding.txtViewAdminSchedulePreschedule.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Add data to the Bundle object using a key-value pair
//                bundle.putString("SaveButton", "SchedulePreschedule");
//                // Create a new instance of the receiving fragment
//                AdminScheduleSelectTeacherFragment fragment = new AdminScheduleSelectTeacherFragment();
//                // Set the arguments for the receiving fragment
//                fragment.setArguments(bundle);
//                loadFragment(fragment);
//            }
//        });
//
//        // calling Schedule Swap
//        Binding.txtViewAdminScheduleSwap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Add data to the Bundle object using a key-value pair
//                bundle.putString("SaveButton", "SchedulePreschedule");
//                // Create a new instance of the receiving fragment
//                AdminScheduleSelectTeacherFragment fragment = new AdminScheduleSelectTeacherFragment();
//                // Set the arguments for the receiving fragment
//                fragment.setArguments(bundle);
//                loadFragment(fragment);
//            }
//        });





        //api calling
        RetrofitClient client= RetrofitClient.getInstance();
        Api api= client.getMyApi();
        api.user_details().enqueue(new Callback<ArrayList<MEYE_USER>>() {
            @Override
            public void onResponse(Call<ArrayList<MEYE_USER>> call, Response<ArrayList<MEYE_USER>> response) {
//                Toast.makeText(getContext(), ""+FragmentData, Toast.LENGTH_SHORT).show();
                if(response.code()==200){
                    data.clear();
                    data.addAll( response.body());
                    // Toast.makeText(getContext(), "hhhhhhhhhh"+response.code()+data.get(0).getName(), Toast.LENGTH_SHORT).show();
                    Binding.RecycerviewAdminSchedule.getAdapter().notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<MEYE_USER>> call, Throwable t) {
                Toast.makeText(getContext(), "Faild"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        //end
         adapter = new
                AdminViewTeacherAdapter(data, getActivity(), AdminScheduleFragment.this,"AdminScheduleFragment");
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        Binding.RecycerviewAdminSchedule.setLayoutManager(manager);
        //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
        Binding.RecycerviewAdminSchedule.
                setAdapter(adapter);


        return  Binding.getRoot();
    }
// calling AdminviewTeacher Adapter click
    public void recyclerviewAdminViewTeacherCellClick(MEYE_USER obj, Context context) {
        // Create dialog and set content view to custom layout
        CustomAdminScheduleLayoutBinding CustomBinding=CustomAdminScheduleLayoutBinding.inflate(getLayoutInflater());
        Dialog customDialog = new Dialog(getActivity());
        customDialog.setContentView(CustomBinding.getRoot());
        // Set additional properties for dialog
        customDialog.setTitle("Custom Dialog");
        customDialog.setCancelable(true);
        customDialog.setCanceledOnTouchOutside(true);
        customDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customDialog.show();


        CustomBinding.txtViewAdminScheduleReschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build();

                Api api = retrofit.create(Api.class);
                api.check_teacher_reschedule(obj.getName()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
//                        Toast.makeText(context, ""+response.code(), Toast.LENGTH_SHORT).show();
                        if(response.code()==200){
                            try {
                                if(response.body().startsWith("\"")){
                                    new SweetAlertDialog(getContext())
                                            .setTitleText(response.body())
                                            .show();
                                    // The string cannot be parsed as an integer
                                    System.out.println("The string is not an integer");
                                }if(response.body().startsWith("[")){
                                    ArrayList<Map<String,String>> maplist;
                                    Type type= new TypeToken<ArrayList<Map<String,String>>>(){}.getType();
//                                    Toast.makeText(context, ""+response.body()+type, Toast.LENGTH_SHORT).show();

                                      maplist= new Gson().fromJson(response.body(),type );
                                if(maplist.size()!=0)
                                {
//                                    Toast.makeText(context, ""+maplist.get(0).get("discipline"), Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getContext(), AdminScheduleRescheduleFreeSlotSelectionActivity.class);
                                    i.putExtra("RescheduleList",response.body()+"");
                                    i.putExtra("UserIntent",new Gson().toJson(obj));
                                    startActivity(i);
                                }
                                }
//
//                                int value = Integer.parseInt(response.body()+"");
//
//
                                // The string can be parsed as an integer
                                // Perform actions with the integer value
//                                System.out.println("The string is an integer: " + value);
                            } catch (NumberFormatException e) {

                            }
//                            Toast.makeText(getContext(), ""+response.body(), Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getContext(), ""+t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
//
                    customDialog.dismiss();

            }
        });

        CustomBinding.txtViewAdminSchedulePreschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code
                Intent i= new Intent(getContext(), AdminSchedulePreScheduleSelectRangeActivity.class);
                i.putExtra("UserIntent",new Gson().toJson(obj));
                startActivity(i);
                customDialog.dismiss();
            }
        });
        CustomBinding.txtViewAdminScheduleSwap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code
                Intent i= new Intent(getContext(), AdminScheduleSwappingTimeTableSelectActivity.class);
                i.putExtra("UserIntent",new Gson().toJson(obj));
                startActivity(i);
                customDialog.dismiss();
            }
        });


        //bottomsheet calling
        // BottomsheetDialogTeacherBinding bindingTeacher= BottomsheetDialogTeacherBinding.inflate(getLayoutInflater());
//        View bottomsheet_teacher = getLayoutInflater().inflate(R.layout.bottomsheet_dialog_teacher,null);
//        BottomSheetDialog dialog = new BottomSheetDialog(getContext(),R.style.BottomSheetStyle);
//        bottomsheet_teacher.findViewById(R.id.txt_Recodings_click).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loadFragment(new AdminViewTabTecherRecodingsFragment());
//                // Toast.makeText(getActivity(), "dafds", Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//            }
//        });
//        bottomsheet_teacher.findViewById(R.id.txt_Schedule_click).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loadFragment(new AdminViewTabTeacherScheduleFragment());
//                // Toast.makeText(getActivity(), "dafds", Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//            }
//        });
//        dialog.setContentView(bottomsheet_teacher);
//        dialog.show();




    }

    private void loadFragment(Fragment f) {
        FragmentTransaction ft =
                getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frament_container_admin, f);
        ft.commit();
    }
    public boolean CallingAPIReSchedule(String TeacherName){
        final boolean[] check = {false};

        return check[0];
    }
}