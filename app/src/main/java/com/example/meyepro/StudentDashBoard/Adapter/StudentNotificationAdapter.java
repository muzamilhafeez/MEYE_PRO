package com.example.meyepro.StudentDashBoard.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.StudentDashBoard.Model.StudentNotification;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.RecyclerviewStudentNotificationCellLayoutBinding;
import com.example.meyepro.databinding.TeacherNotificationApproveCellLayoutBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentNotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<StudentNotification> VenueList;
    private Context context;
    static  int Student_Notification_Layout=1;
     int Layout_run;

    public StudentNotificationAdapter(ArrayList<StudentNotification> VenueList, Context context) {
        this.VenueList = VenueList;
        this.context = context;

    }
    public StudentNotificationAdapter(ArrayList<StudentNotification> VenueList, Context context ,int layout_run_Teacher) {
        this.VenueList = VenueList;
        this.context = context;
        this.Layout_run=layout_run_Teacher;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (Layout_run) {
            case 2:
                TeacherNotificationApproveCellLayoutBinding teacherNotification = TeacherNotificationApproveCellLayoutBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new TeacherNotificationViewHolder(teacherNotification);

            default:
                RecyclerviewStudentNotificationCellLayoutBinding studentNotification = RecyclerviewStudentNotificationCellLayoutBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new StudentNotificationViewHolder(studentNotification);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (Layout_run==2){

            StudentNotification obj = VenueList.get(position);
            ((TeacherNotificationViewHolder) holder).binding.txViewName.setText(obj.getName()+"");
            ((TeacherNotificationViewHolder) holder).binding.txViewDate.setText(obj.getDate()+"");
            ((TeacherNotificationViewHolder) holder).binding.txViewClass.setText(obj.getCourseName()+"");
            ((TeacherNotificationViewHolder) holder).binding.txViewDay.setText(obj.getDay()+"");

            if(!obj.getImage().isEmpty()){
                Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+"Student"+"/"+obj.getImage())).into(  ((TeacherNotificationViewHolder) holder).binding.ImageViewTeacher);
            }

            ((TeacherNotificationViewHolder) holder).binding.imageViewCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show();
                    RetrofitClient retrofitClient= RetrofitClient.getInstance();
                    Api api= retrofitClient.getMyApi();
                    api.api_update_attendance(obj.getId(),false).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(context, ""+response.code(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(context, ""+t, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            ((TeacherNotificationViewHolder) holder).binding.imageViewAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Accept", Toast.LENGTH_SHORT).show();
                    RetrofitClient retrofitClient= RetrofitClient.getInstance();
                    Api api= retrofitClient.getMyApi();
                    api.api_update_attendance(obj.getId(),true).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(context, ""+response.code(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(context, ""+t, Toast.LENGTH_SHORT).show();
                        }
                    });

                    //end approve

                    //end cannel
                }
            });
            //student
        }else {
            StudentNotification obj = VenueList.get(position);
            ((StudentNotificationViewHolder) holder).binding.txViewName.setText(obj.getName()+"");
            ((StudentNotificationViewHolder) holder).binding.txViewDate.setText(obj.getDate()+"");
            ((StudentNotificationViewHolder) holder).binding.txViewClass.setText(obj.getCourseName()+"");
            ((StudentNotificationViewHolder) holder).binding.txViewDay.setText(obj.getDay()+"");

            if(!obj.getImage().isEmpty()){
                Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+"Teacher"+"/"+obj.getImage())).into(  ((StudentNotificationViewHolder) holder).binding.ImageViewTeacher);
            }
            ((StudentNotificationViewHolder) holder).binding.btnCliam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RetrofitClient retrofitClient= RetrofitClient.getInstance();
                    Api api= retrofitClient.getMyApi();
                    JSONObject cliam= new JSONObject();
                    try {
                        cliam.put("id",0);
                        cliam.put("attendanceId",obj.getId());
                        cliam.put("teacherName",obj.getName().split(",")[0]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                holder.binding.txViewDate.setText(cliam.toString());
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), cliam.toString());

//                Toast.makeText(context, ""+cliam.toString(), Toast.LENGTH_SHORT).show();
                    api.api_student_attendance_claim(requestBody).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(context, ""+response.code(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(context, ""+t, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }


    }



    @Override
    public int getItemCount() {
        return VenueList.size();
    }

    class StudentNotificationViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewStudentNotificationCellLayoutBinding binding;//<----
        public StudentNotificationViewHolder(
                @NonNull RecyclerviewStudentNotificationCellLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }

    class TeacherNotificationViewHolder extends RecyclerView.ViewHolder {
        TeacherNotificationApproveCellLayoutBinding binding;//<----
        public TeacherNotificationViewHolder(
                @NonNull TeacherNotificationApproveCellLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }
}