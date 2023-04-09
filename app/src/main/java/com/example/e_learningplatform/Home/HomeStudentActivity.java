package com.example.e_learningplatform.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;

import com.example.e_learningplatform.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeStudentActivity extends AppCompatActivity {

    BottomNavigationView student_bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_student);

        BottomNavigationView navView = findViewById(R.id.bottom_navigationView);
        navView.setItemIconTintList(null);

        set_fragment();


    }

    public void set_fragment()
    {
         HomeStudentFragment homeStudent_fragment = new HomeStudentFragment();
         ChatStudentFragment chatStudent_fragment = new ChatStudentFragment();
         PersonalHomeFragment personalStudent_fragment = new PersonalHomeFragment();

         student_bottomNavigationView = findViewById(R.id.bottom_navigationView);

         getSupportFragmentManager().beginTransaction().replace(R.id.materii_frameLayout,homeStudent_fragment).commit();

        BadgeDrawable badgeDrawable = student_bottomNavigationView.getOrCreateBadge(R.id.chat);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);

        BadgeDrawable badgeDrawablePersonal = student_bottomNavigationView.getOrCreateBadge(R.id.person);
        badgeDrawablePersonal.setVisible(true);
        badgeDrawablePersonal.setNumber(3);

         student_bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 switch (item.getItemId())
                 {
                     case R.id.home:
                         getSupportFragmentManager().beginTransaction().replace(R.id.materii_frameLayout,homeStudent_fragment).commit();
                         return true;

                     case R.id.chat:
                         getSupportFragmentManager().beginTransaction().replace(R.id.materii_frameLayout,chatStudent_fragment).commit();
                         return true;

                     case R.id.person:
                         getSupportFragmentManager().beginTransaction().replace(R.id.materii_frameLayout,personalStudent_fragment).commit();
                         return true;
                 }
                 return false;
             }
         });


    }
}