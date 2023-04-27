package com.example.e_learningplatform.HomeProfessor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.e_learningplatform.Home.ChatStudentFragment;
import com.example.e_learningplatform.Home.HomeStudentFragment;
import com.example.e_learningplatform.Home.PersonalHomeFragment;
import com.example.e_learningplatform.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeProfessorActivity extends AppCompatActivity {

    BottomNavigationView professor_bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_professor);

        professor_bottomNavigationView = findViewById(R.id.bottom_professor_navigationView);
        professor_bottomNavigationView.setItemIconTintList(null);

        set_fragment();
    }

    public void set_fragment()
    {
        HomeProfessorFragment homeProfessor_fragment = new HomeProfessorFragment();
        ChatProfessorFragment chatProfessor_fragment = new ChatProfessorFragment();

        professor_bottomNavigationView = findViewById(R.id.bottom_professor_navigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.materii_professor_frameLayout,homeProfessor_fragment).commit();

        BadgeDrawable badgeDrawable = professor_bottomNavigationView.getOrCreateBadge(R.id.chat);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);

//        BadgeDrawable badgeDrawablePersonal = student_bottomNavigationView.getOrCreateBadge(R.id.person);
//        badgeDrawablePersonal.setVisible(true);
//        badgeDrawablePersonal.setNumber(3);

        professor_bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.materii_professor_frameLayout,homeProfessor_fragment).commit();
                        return true;

                    case R.id.chat:
                        getSupportFragmentManager().beginTransaction().replace(R.id.materii_professor_frameLayout,chatProfessor_fragment).commit();
                        return true;


                }
                return false;
            }
        });


    }




}