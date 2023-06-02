package com.example.meyepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.meyepro.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Binding=ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(Binding.getRoot());
        Binding.startMEyePro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),login.class);
                startActivity(i);
            }
        });

    }
}