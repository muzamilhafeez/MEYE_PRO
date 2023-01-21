package com.example.meyepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.meyepro.databinding.ActivityAdminLoginBinding;
import com.example.meyepro.databinding.ActivityLoginBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class login extends AppCompatActivity {
ActivityLoginBinding Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding= ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(Binding.getRoot());
        Binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),AdminLogin.class);
                startActivity(i);
            }
        });
     // bottomSheetDialog= new BottomSheetDialog(getApplicationContext(),R.style.BottomSheetStyle);
    }

}