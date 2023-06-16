package com.example.meyepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.meyepro.DirectorDashBoard.DirectorDashBoardActivity;
import com.example.meyepro.StudentDashBoard.StudentDashBoardActivity;
import com.example.meyepro.TeacherDashBoard.TeacherDashBoardActivity;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.GlobalsDataSave;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityAdminLoginBinding;
import com.example.meyepro.databinding.ActivityLoginBinding;
import com.example.meyepro.fragments.Admin.AdminSettingFragment;
import com.example.meyepro.models.MEYE_USER;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class login extends AppCompatActivity {
ActivityLoginBinding Binding;
    public  static  MEYE_USER userInfoDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding= ActivityLoginBinding.inflate(getLayoutInflater());
        RetrofitClient client= RetrofitClient.getInstance();
        Api api= client.getMyApi();
        setContentView(Binding.getRoot());
        SharedPreferences  ref = getApplicationContext().
                getSharedPreferences("store", Context.MODE_PRIVATE);

        String userInfo=  ref.getString("userInfo","");

        MEYE_USER user= new Gson().fromJson(userInfo,new TypeToken<MEYE_USER>(){}.getType());
        if(user!=null){
            Binding.editTxtUser.setText(user.getUserID());
            Binding.editTxtPass.setText(user.getPassword());
        }


        if (Binding.editTxtPass.getText().toString().isEmpty()) {
          //  String enteredText = textInputEditText.getText().toString();
         //   Toast.makeText(this, "null"+user, Toast.LENGTH_SHORT).show();
        }
        Binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user= Binding.editTxtUser.getText().toString();
                String pass= Binding.editTxtPass.getText().toString();
              api.loginUser(user,pass).enqueue(new Callback<MEYE_USER>() {
                  @Override
                  public void onResponse(Call<MEYE_USER> call, Response<MEYE_USER> response) {

                      if(response.isSuccessful()){
                          SharedPreferences ref  = getApplicationContext().
                                  getSharedPreferences("store", Context.MODE_PRIVATE);
                          SharedPreferences.Editor edit= ref.edit();
                          edit.putString("userInfo",new Gson().toJson(response.body()));
                          edit.commit();
                          GlobalsDataSave.meye_user=response.body();
                          MEYE_USER user= response.body();
                          userInfoDetail=response.body();
//                          Toast.makeText(login.this, ""+user.getRole(), Toast.LENGTH_SHORT).show();
                          if(user.getRole().contains("Admin")){

                              Intent i =new Intent(getApplicationContext(),AdminLogin.class);
                              i.putExtra("IntentData",new Gson().toJson(user));
                              startActivity(i);
                          }
                          if(user.getRole().contains("Teacher")){
                              Intent i =new Intent(getApplicationContext(), TeacherDashBoardActivity.class);
                              i.putExtra("IntentData",new Gson().toJson(user));
                              startActivity(i);
                          }
                          if(user.getRole().contains("Student")){
                              Intent i =new Intent(getApplicationContext(), StudentDashBoardActivity.class);
                              i.putExtra("IntentData",new Gson().toJson(user));
                              startActivity(i);
                          }
                          if(user.getRole().contains("Director")){
                              AdminSettingFragment.ReportView=null;
                              Intent i =new Intent(getApplicationContext(), DirectorDashBoardActivity.class);
                              i.putExtra("IntentData",new Gson().toJson(user));
                              startActivity(i);
                          }
//                          Toast.makeText(login.this, "Login", Toast.LENGTH_SHORT).show();
                      }else{
                          Toast.makeText(login.this, "Unsuccessfull"+response.code(), Toast.LENGTH_SHORT).show();
                      }
                  }

                  @Override
                  public void onFailure(Call<MEYE_USER> call, Throwable t) {
                      Toast.makeText(login.this, "i"+t.toString(), Toast.LENGTH_SHORT).show();
                  }
              });


            }
        });
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.100.24:8000/WebApplication1/api/")
//                .build();
//
//      Api  service = retrofit.create(Api.class);
////        RetrofitClient client= RetrofitClient.getInstance();
////        Api api= client.getMyApi();
//        service.dvr_details2().enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Toast.makeText(getApplicationContext(), ""+response.code(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "hhhhhhhhhh"+call.hashCode(), Toast.LENGTH_SHORT).show();
//            }
//        });

        
     // bottomSheetDialog= new BottomSheetDialog(getApplicationContext(),R.style.BottomSheetStyle);
    }

}