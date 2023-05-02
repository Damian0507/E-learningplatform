package com.example.e_learningplatform.HomeProfessor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.e_learningplatform.R;

public class AddCourseActivity extends AppCompatActivity {
    Button back_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        back_button();
    }

    private void back_button() {

        back_button = findViewById(R.id.add_course_back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), OptionsActivity.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //i.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(i);
                finish();
            }
        });


    }
}