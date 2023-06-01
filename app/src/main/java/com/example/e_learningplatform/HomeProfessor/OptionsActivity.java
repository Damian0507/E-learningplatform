
package com.example.e_learningplatform.HomeProfessor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.e_learningplatform.R;

public class OptionsActivity extends AppCompatActivity {

    Button back_button, add_course_button, check_exam_button, add_test_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        back_button();
        add_course_button();
        add_test_button();
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

        Intent i = getIntent();
        String nume = i.getStringExtra("Materie");

        add_course_button = findViewById(R.id.options_ad_course_button);

        add_course_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AddCourseActivity.class);
                i.putExtra("Materie", nume);
                startActivity(i);
                finish();
            }
        });
    }
    private void add_test_button(){

        Intent i = getIntent();
        String nume = i.getStringExtra("Materie");

        add_test_button = findViewById(R.id.options_ad_test_button);

        add_test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AddTestActivity.class);
                i.putExtra("Materie", nume);
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
                startActivity(i);
                finish();
            }
        });


    }
}