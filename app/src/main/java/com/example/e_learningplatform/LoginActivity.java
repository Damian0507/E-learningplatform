package com.example.e_learningplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.e_learningplatform.Home.HomeStudentActivity;

public class LoginActivity extends AppCompatActivity {
Button sing_in_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sing_in_btn = findViewById(R.id.sing_in_button);

        sing_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), HomeStudentActivity.class);
                startActivity(i);

            }
        });

    }
}