
package com.example.e_learningplatform.HomeProfessor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.e_learningplatform.R;

public class OptionsActivity extends AppCompatActivity {

    Button back_button, add_course_button, check_exam_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        back_button();
        add_course_button();
        check_exam_button();


    }

    private void check_exam_button(){
        check_exam_button = findViewById(R.id.options_check_exams_button);

        check_exam_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TestEvaluationActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void add_course_button(){
        add_course_button = findViewById(R.id.options_ad_course_button);

        add_course_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AddCourseActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void back_button() {

        back_button = findViewById(R.id.option_back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), HomeProfessorActivity.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //i.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(i);
                finish();
            }
        });


    }
}