package com.example.meyepro.fragments.Admin.view.TabView.DVR;

import android.content.Context;
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
import com.example.meyepro.adapters.AdminViewDVRAdapter;
import com.example.meyepro.adapters.AdminViewTeacherAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.FragmentAdminViewTabDVRBinding;
import com.example.meyepro.fragments.Admin.view.Teacher.ViewTeacherFragment;
import com.example.meyepro.models.DVR;
import com.example.meyepro.models.MEYE_USER;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminViewTabDVRFragment extends Fragment {
FragmentAdminViewTabDVRBinding Binding;
    AdminViewDVRAdapter adapter;
    ArrayList<DVR> dvrList= new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding= FragmentAdminViewTabDVRBinding.inflate(getLayoutInflater());
        //code start

        Binding.editTextAdminViewSearchDvr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
//                Toast.makeText(getContext(), ""+editable, Toast.LENGTH_SHORT).show();
                ArrayList<DVR> filteredList = new ArrayList<>();
                for (DVR item :dvrList) {
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
//        ArrayList<DVR> data = new ArrayList<DVR>();
//        data.add(new DVR());
//        data.get(0).setNAME("DVR 1");
//        data.get(0).setHOST("admin");
//        data.get(0).setIP("192.168.2.1");
//        data.add(new DVR());
//        data.get(1).setNAME("DVR 2");
//        data.get(1).setHOST("admin");
//        data.get(1).setIP("192.168.3.1");
//        data.add(new DVR());
//        data.get(2).setNAME("DVR 2");
//        data.get(2).setHOST("admin");
//        data.get(2).setIP("192.168.3.1");
//        AdminViewDVRAdapter adapter = new
//                AdminViewDVRAdapter (data, getActivity() , AdminViewTabDVRFragment.this,"AdminViewTabDVRFragment");
//        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//        Binding.RecycerviewAdminViewDVR.setLayoutManager(manager);
        //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
//        Binding.RecycerviewAdminViewDVR.
//                setAdapter(adapter);
        //create API instance
        RetrofitClient client= RetrofitClient.getInstance();
        Api api= client.getMyApi();
        //Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
        //api calling
        api.dvr_details().enqueue(new Callback<ArrayList<DVR>>() {
            @Override
            public void onResponse(Call<ArrayList<DVR>> call, Response<ArrayList<DVR>> response) {
                if (response.code() == 200) {
                    try{
                    //    Map<String, ArrayList<DVR>> map = response.body();
                        String msg = "";
                        dvrList.clear();
                        dvrList.addAll( response.body());
                        Binding.RecycerviewAdminViewDVR.getAdapter().notifyDataSetChanged();

                    }catch (Exception e){
                        System.out.println(e.toString());
                    }
//                     User u= response.body().get(0);


                }
            }

            @Override
            public void onFailure(Call<ArrayList<DVR>> call, Throwable t) {

            }
        });

         adapter = new
                AdminViewDVRAdapter (dvrList, getActivity() , AdminViewTabDVRFragment.this,"AdminViewTabDVRFragment");
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        Binding.RecycerviewAdminViewDVR.setLayoutManager(manager);
          Binding.RecycerviewAdminViewDVR.setHasFixedSize(true);
        Binding.RecycerviewAdminViewDVR.
                setAdapter(adapter);

        //code end
        return Binding.getRoot();
    }
    // view calinng DVRadapter
    public void recyclerviewAdminViewTabDVRCellClick(DVR obj, Context context) {
        AdminViewTabDVRClickFragment fragment= new AdminViewTabDVRClickFragment();
        Bundle bundle= new Bundle();
        bundle.putString("Data",new Gson().toJson(obj));
        fragment.setArguments(bundle);
        loadFragment( fragment);

    }
    //calling fragment
    private void loadFragment(Fragment f) {
        FragmentTransaction ft =
                getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frament_container_admin, f);
        ft.commit();
    }
}