package com.example.meyepro.fragments.Admin.Setting.RuleSetting;

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
import com.example.meyepro.databinding.FragmentAdminSettingRuleSettingClickBinding;
import com.example.meyepro.fragments.Admin.view.Teacher.Teacher.AdminViewTabTecherRecodingsFragment;
import com.example.meyepro.models.CAMERA;
import com.example.meyepro.models.DVR;
import com.example.meyepro.models.MEYE_USER;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminSettingRuleSettingClickFragment extends Fragment {

   FragmentAdminSettingRuleSettingClickBinding Binding;
    AdminViewTeacherAdapter adapter;
    ArrayList<MEYE_USER> data= new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding= FragmentAdminSettingRuleSettingClickBinding.inflate(getLayoutInflater());
        //code start
        Binding.editTextAdminSettingSearchTeacher.addTextChangedListener(new TextWatcher() {
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
                for (MEYE_USER item :data) {
                    if ((item.getName()+"").toLowerCase().contains(editable.toString().toLowerCase())) {
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
//        ArrayList<MEYE_USER> data = new ArrayList<MEYE_USER>();
//        data.add(new MEYE_USER());
//        data.get(0).setNAME("Ali");
//        data.add(new MEYE_USER());
//        data.get(1).setNAME("Muzamil");
//        data.add(new MEYE_USER());
//        data.get(2).setNAME("Muzamil");
//        data.add(new MEYE_USER());
//        data.get(3).setNAME("Muzamil");
        RetrofitClient client= RetrofitClient.getInstance();
        Api api= client.getMyApi();
        adapter = new
                AdminViewTeacherAdapter(data, getActivity(), AdminSettingRuleSettingClickFragment.this,"AdminSettingRuleSettingClickFragment");
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        Binding.RecycerviewAdminSettingTeacherRule.setLayoutManager(manager);
        //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
        Binding.RecycerviewAdminSettingTeacherRule.
                setAdapter(adapter);
        api.user_details().enqueue(new Callback<ArrayList<MEYE_USER>>() {
            @Override
            public void onResponse(Call<ArrayList<MEYE_USER>> call, Response<ArrayList<MEYE_USER>> response) {
                if(response.code()==200){
                    data.clear();
                    data.addAll( response.body());
                    // Toast.makeText(getContext(), "hhhhhhhhhh"+response.code()+data.get(0).getName(), Toast.LENGTH_SHORT).show();
                    Binding.RecycerviewAdminSettingTeacherRule.getAdapter().notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<ArrayList<MEYE_USER>> call, Throwable t) {
                Toast.makeText(getContext(), "Faild"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //code end
        return Binding.getRoot();
    }
    //Recyclerview calling method from AminViewTeacherAdapter
    public void recyclerviewAdminSettingRuleCellClick(MEYE_USER obj, Context context) {
        Intent i= new Intent(getContext(),AdminSettingRuleSettingSetRuleActivity.class);
        i.putExtra("UserIntent",new Gson().toJson(obj));
        startActivity(i);
//       loadFragment(new AdminSettingRuleSettingClickSetRuleFragment());

    }

    //calling fragment
    private void loadFragment(Fragment f) {
        FragmentTransaction ft =
                getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frament_container_admin, f);
        ft.commit();
    }


}