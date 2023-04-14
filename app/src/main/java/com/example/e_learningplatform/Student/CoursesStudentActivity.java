package com.example.e_learningplatform.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.e_learningplatform.Home.HomeStudentActivity;
import com.example.e_learningplatform.HomeAdapters.HomeAdapter;
import com.example.e_learningplatform.R;
import com.example.e_learningplatform.StudentAdapters.CoursesAdapter;
import com.example.e_learningplatform.StudentClasses.Cursuri;

import java.util.ArrayList;

public class CoursesStudentActivity extends AppCompatActivity {

    Button back_button,submit_button;
    RecyclerView recyclerView;
    ArrayList<Cursuri> cursuriArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_student);

        back_button();

        dateInitialize_courses();

        recyclerView = findViewById(R.id.cursuri_recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        CoursesAdapter coursesAdapter = new CoursesAdapter(getApplicationContext(),cursuriArrayList);
        recyclerView.setAdapter(coursesAdapter);
        coursesAdapter.notifyDataSetChanged();
        recyclerView.setHasFixedSize(true);

        submit_test();
    }

    private void back_button(){

        back_button = findViewById(R.id.courses_back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), HomeStudentActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void dateInitialize_courses()
    {
        cursuriArrayList.add(new Cursuri("Cursul 1"));
        cursuriArrayList.add(new Cursuri("Cursul 2"));
        cursuriArrayList.add(new Cursuri("Cursul 3"));
        cursuriArrayList.add(new Cursuri("Cursul 4"));
        cursuriArrayList.add(new Cursuri("Cursul 5"));
        cursuriArrayList.add(new Cursuri("Cursul 6"));



    }

    private void submit_test()
    {
        submit_button = findViewById(R.id.submit_test_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SubmitTestActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}