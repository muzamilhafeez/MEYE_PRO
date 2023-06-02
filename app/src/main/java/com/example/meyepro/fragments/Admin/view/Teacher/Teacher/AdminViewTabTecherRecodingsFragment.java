package com.example.meyepro.fragments.Admin.view.Teacher.Teacher;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.adapters.AdminViewVenueRecordingsAdapter;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.FragmentAdminViewTabTecherRecodingsBinding;
import com.example.meyepro.fragments.Admin.view.Teacher.Teacher.video.AdminViewTabTecherRecodingsVideoViewFragment;

import com.example.meyepro.models.MEYE_USER;
import com.example.meyepro.models.RECORDINGS;
import com.example.meyepro.models.recordings_details_by_teachername;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminViewTabTecherRecodingsFragment extends Fragment {

   FragmentAdminViewTabTecherRecodingsBinding Binding;
    recordings_details_by_teachername  courseRecording;
    MEYE_USER obj;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding= FragmentAdminViewTabTecherRecodingsBinding.inflate(getLayoutInflater());
        //code start
        Bundle b = getArguments();
        String objString= b.getString("id");
        obj= new Gson().fromJson(objString,MEYE_USER.class);
        Toast.makeText(getContext(), ""+obj.getRole(), Toast.LENGTH_SHORT).show();
        if(obj.getImage()!=null){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+obj.getRole()+"/"+obj.getImage())).into(Binding.profileImageTeacher);
        }

        Binding.textViewTeacherName.setText(obj.getName()+"");
//        Bundle b = getArguments();
//        ArrayList<MEYE_USER> getdata = (ArrayList<MEYE_USER>) b.getSerializable("id");
////        getdata.clear();
////        getdata.addAll();
//        Toast.makeText(getContext(), ""+getdata.size(), Toast.LENGTH_SHORT).show();
//        MEYE_USER show= getdata.get(0);
////        show.getImage();
//        Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+show.getRole()+"/"+show.getImage())).into(Binding.profileImageTeacher);
    ArrayList<recordings_details_by_teachername> data = new ArrayList<recordings_details_by_teachername>();
//        data.add(new RECORDINGS());
//        data.add(new RECORDINGS());
//        data.get(0).setFILENAME("Recoding 1");
//        data.add(new RECORDINGS());
//        data.get(1).setFILENAME("Recoding 2");
//        data.add(new RECORDINGS());
//        data.get(2).setFILENAME("Recoding 3");
//        data.add(new RECORDINGS());
//        data.get(3).setFILENAME("Recoding 4");

//        courseRecording = new recordings_details_by_teachername(
//                "ENG325",
//                "CPS",
//                "Abdul Sami",
//                "BCS-8B",
//                "Lt3",
//                "Monday",
//                "08:30",
//                "10:00",
//                "2023-03-05",
//                "Late + Held",
//                1,
//                "Recordings/file,63,start_recording.mp4"
//        );

//        data.get(0).setFILENAME(courseRecording.getCourseCode());
        AdminViewVenueRecordingsAdapter adapter = new
                AdminViewVenueRecordingsAdapter(data, getActivity(), AdminViewTabTecherRecodingsFragment.this);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        Binding.RecycerviewAdminViewVenueRecordings.setLayoutManager(manager);
        //  Binding.RecycerviewAdminViewTeacher.setHasFixedSize(true);
        Binding.RecycerviewAdminViewVenueRecordings.
                setAdapter(adapter);


        //api
        RetrofitClient client= RetrofitClient.getInstance();
        Api api= client.getMyApi();
        api.recordings_details_by_teachername(obj.getName()).enqueue(new Callback<ArrayList<recordings_details_by_teachername>>() {
            @Override
            public void onResponse(Call<ArrayList<recordings_details_by_teachername>> call, Response<ArrayList<recordings_details_by_teachername>> response) {
                if(response.isSuccessful()) {
//                    Toast.makeText(getContext(), "Successful" + response.body().get(0).getCourseCode(), Toast.LENGTH_SHORT).show();
                    data.clear();
                    data.addAll(response.body());
                    Toast.makeText(getContext(), ""+data.size(), Toast.LENGTH_SHORT).show();
                    Binding.RecycerviewAdminViewVenueRecordings.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<recordings_details_by_teachername>> call, Throwable t) {
                Toast.makeText(getContext(), "Successful" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //code end
        return Binding.getRoot();
    }

    public void recyclerviewAdminViewTabTecherRecodingsCellClick(recordings_details_by_teachername obj, Context context) {

        AdminViewTabTecherRecodingsVideoViewFragment m= new AdminViewTabTecherRecodingsVideoViewFragment();
        Bundle args = new Bundle();
       ArrayList<recordings_details_by_teachername > data = new ArrayList<recordings_details_by_teachername >();
        data.add(obj);
        args.putSerializable("id",  data);
        m.setArguments(args);
        loadFragment(m);

    }
    //Freagmet calling
    private void loadFragment(Fragment f) {
        FragmentTransaction ft =
                getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frament_container_admin, f);
        ft.commit();
    }
    //Freagmet calling end
}


