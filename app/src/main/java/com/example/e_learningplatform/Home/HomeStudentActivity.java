package com.example.e_learningplatform.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.e_learningplatform.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_student);

        BottomNavigationView navView = findViewById(R.id.bottom_navigationView);
        navView.setItemIconTintList(null);


    }
}