package com.example.meyepro.fragments.Admin.view.Teacher;

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
import com.example.meyepro.adapters.AdminViewTeacherAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.FragmentViewTeacherBinding;
import com.example.meyepro.fragments.Admin.AdminHomeFragment;
import com.example.meyepro.fragments.Admin.AdminViewFragment;
import com.example.meyepro.fragments.Admin.view.Teacher.Teacher.AdminViewTabTeacherScheduleFragment;
import com.example.meyepro.fragments.Admin.view.Teacher.Teacher.AdminViewTabTecherRecodingsFragment;
import com.example.meyepro.fragments.Admin.view.Teacher.Teacher.video.AdminViewTabTecherRecodingsVideoViewFragment;
import com.example.meyepro.models.MEYE_USER;
import com.example.meyepro.models.recordings_details_by_teachername;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewTeacherFragment extends Fragment {
    BottomSheetDialogFragment bottomSheetDialog;
FragmentViewTeacherBinding Binding;
    ArrayList<MEYE_USER> data = new ArrayList<>();
    AdminViewTeacherAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding = FragmentViewTeacherBinding.inflate(inflater, container, false);
        //code

        //recyclerview search code
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


//        ArrayList<MEYE_USER> data = new ArrayList<MEYE_USER>();
//        data.add(new MEYE_USER());
//        data.get(0).setNAME("Ali");
//        data.add(new MEYE_USER());
//        data.get(1).setNAME("Muzamil");
//        data.add(new MEYE_USER());
//        data.get(2).setNAME("Muzamil");
//        data.add(new MEYE_USER());
//        data.get(3).setNAME("Muzamil");
//        data.add(new MEYE_USER());
//        data.get(4).setNAME("Muzamil");
//        data.add(new MEYE_USER());
//        data.get(5).setNAME("Muzamil");
        //api code
        RetrofitClient client= RetrofitClient.getInstance();
        Api api= client.getMyApi();
        api.user_details().enqueue(new Callback<ArrayList<MEYE_USER>>() {
            @Override
            public void onResponse(Call<ArrayList<MEYE_USER>> call, Response<ArrayList<MEYE_USER>> response) {
                if(response.code()==200){
                    data.clear();
                    data.addAll( response.body());
                   // Toast.makeText(getContext(), "hhhhhhhhhh"+response.code()+data.get(0).getName(), Toast.LENGTH_SHORT).show();
                    Binding.RecycerviewAdminViewTeacher.getAdapter().notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<MEYE_USER>> call, Throwable t) {
                Toast.makeText(getContext(), "Faild"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        //end
                adapter = new
                AdminViewTeacherAdapter(data, getActivity(), ViewTeacherFragment.this,"ViewTeacherFragment");
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        Binding.RecycerviewAdminViewTeacher.setLayoutManager(manager);
        //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
        Binding.RecycerviewAdminViewTeacher.
                setAdapter(adapter);

        return  Binding.getRoot();

      //  bottomSheetDialog= new BottomSheetDialog(getActivity().getApplicationContext());

    }
// calling AdminviewTeacher Adapter click
    public void recyclerviewAdminViewTeacherCellClick(MEYE_USER obj, Context context) {
        //bottomsheet calling
   // BottomsheetDialogTeacherBinding bindingTeacher= BottomsheetDialogTeacherBinding.inflate(getLayoutInflater());
        View bottomsheet_teacher = getLayoutInflater().inflate(R.layout.bottomsheet_dialog_teacher,null);
        BottomSheetDialog dialog = new BottomSheetDialog(getContext(),R.style.BottomSheetStyle);
        bottomsheet_teacher.findViewById(R.id.txt_Recodings_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminViewTabTecherRecodingsFragment m= new AdminViewTabTecherRecodingsFragment();
                Bundle args = new Bundle();
//                ArrayList<MEYE_USER> datasend = new ArrayList<MEYE_USER>();
//                Toast.makeText(context, ""+obj.getImage(), Toast.LENGTH_SHORT).show();
//                data.add(obj);
//                args.putSerializable("id", datasend);
//                m.setArguments(args);
                Gson gson= new Gson();
                args.putString("id",gson.toJson(obj));
                m.setArguments(args);
                loadFragment(m);
               // Toast.makeText(getActivity(), "dafds", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        bottomsheet_teacher.findViewById(R.id.txt_Schedule_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminViewTabTeacherScheduleFragment m= new AdminViewTabTeacherScheduleFragment();
                Bundle args = new Bundle();
                Gson gson= new Gson();
                args.putString("IntentOBJ",gson.toJson(obj));
                m.setArguments(args);
                loadFragment(m);
                // Toast.makeText(getActivity(), "dafds", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.setContentView(bottomsheet_teacher);
        dialog.show();




    }
    //calling fragment
    private void loadFragment(Fragment f) {
        FragmentTransaction ft =
                getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frament_container_admin, f);
        ft.commit();
    }
//    @Override
//    public void onDetach() {
//        super.onDetach();
//
//        // Reset selected item in Bottom Navigation when Fragment is detached
//        if (mBottomNavigationView != null) {
//            mBottomNavigationView.setSelectedItemId(0);
//        }
//    }
//

}