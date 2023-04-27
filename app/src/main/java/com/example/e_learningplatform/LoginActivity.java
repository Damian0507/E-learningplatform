package com.example.e_learningplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.e_learningplatform.Home.HomeStudentActivity;
import com.example.e_learningplatform.HomeProfessor.HomeProfessorActivity;

public class LoginActivity extends AppCompatActivity {

    Button sing_in_btn;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sing_in();


    }


    public void sing_in(){

        sing_in_btn = findViewById(R.id.sing_in_button);
        username = findViewById(R.id.student_ID_editText);

        sing_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(username.getText().toString().equals("student")) {
                    Intent i = new Intent(getApplicationContext(), HomeStudentActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Intent i = new Intent(getApplicationContext(), HomeProfessorActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
}

}

