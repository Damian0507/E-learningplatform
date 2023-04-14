 package com.example.e_learningplatform.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.e_learningplatform.Home.HomeStudentActivity;
import com.example.e_learningplatform.R;

 public class SubmitTestActivity extends AppCompatActivity {

     Button back_button;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_submit_test);

         back_button();

     }

     private void back_button() {

         back_button = findViewById(R.id.submit_test_back_button);

         back_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i = new Intent(getApplicationContext(), CoursesStudentActivity.class);
                 startActivity(i);
                 finish();
             }
         });


     }
 }