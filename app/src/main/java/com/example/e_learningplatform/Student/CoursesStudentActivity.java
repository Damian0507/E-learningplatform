package com.example.e_learningplatform.Student;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.e_learningplatform.Data;
import com.example.e_learningplatform.DatabaseHelper;
import com.example.e_learningplatform.DateProfesor;
import com.example.e_learningplatform.Home.HomeStudentActivity;
import com.example.e_learningplatform.HomeAdapters.HomeAdapter;
import com.example.e_learningplatform.R;
import com.example.e_learningplatform.StudentAdapters.CoursesAdapter;
import com.example.e_learningplatform.StudentClasses.Cursuri;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CoursesStudentActivity extends AppCompatActivity {

    Button back_button,submit_button;
    RecyclerView recyclerView;
    ArrayList<Cursuri> cursuriArrayList = new ArrayList<>();
    DatabaseHelper databaseHelper,databaseHelper2;
    TextView nume_materie_txt, nume_profesor_txt;
    DatabaseReference reference,database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_student);

        back_button();




        String materie = getIntent().getStringExtra("materie");

        if(materie != null) {
            reference = FirebaseDatabase.getInstance("https://e-learning-platform-3b8e7-default-rtdb.europe-west1.firebasedatabase.app")
                    .getReference("Users").child("Materii");
            reference.child(materie).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    DataSnapshot dataSnapshot = task.getResult();
                    String nume_profesor = String.valueOf(dataSnapshot.child("Profesor").getValue());
                    String nume_materie = getIntent().getStringExtra("materie");

                    databaseHelper = new DatabaseHelper(getApplicationContext());
                    String nume_student = databaseHelper.fetchAllData();
                    databaseHelper.insertDataProfessor(new DateProfesor(nume_student, nume_materie, nume_profesor));
                    Log.d(TAG, "onComplete: " + " is aici");
                    Log.d(TAG, "onCreate: " + nume_materie + nume_profesor + nume_student);

                    set_texts();

                    dateInitialize_courses(materie);

                }
            });

        }
        else
        {
            set_texts();

            databaseHelper = new DatabaseHelper(getApplicationContext());

            String nm_materie = databaseHelper.fetchAllDataMaterie();

            dateInitialize_courses(nm_materie);
        }








        submit_test();
    }

    private void set_texts(){

        nume_materie_txt = findViewById(R.id.nume_curs_textView);
        nume_profesor_txt = findViewById(R.id.nume_profesor_textView);

        databaseHelper2 = new DatabaseHelper(getApplicationContext());
        String nume_materie = databaseHelper2.fetchAllDataMaterie();
        String nume_profesor = databaseHelper2.fetchAllDataProfessor();
        String nume_student = databaseHelper2.fetchAllData();

        nume_materie_txt.setText(nume_materie);
        nume_profesor_txt.setText(nume_profesor);

        Log.d(TAG, "onCreate: "+ nume_materie + nume_profesor + nume_student);
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

    private void dateInitialize_courses(String materie)
    {

        recyclerView = findViewById(R.id.cursuri_recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        CoursesAdapter coursesAdapter = new CoursesAdapter(getApplicationContext(),cursuriArrayList);
        recyclerView.setAdapter(coursesAdapter);

        recyclerView.setHasFixedSize(true);

        database = FirebaseDatabase.getInstance("https://e-learning-platform-3b8e7-default-rtdb.europe-west1.firebasedatabase.app").
                getReference("Users").child("Materii").child(materie);
        database.child("Cursuri").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                cursuriArrayList.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Cursuri cursuri = dataSnapshot.getValue(Cursuri.class);
                    cursuriArrayList.add(cursuri);

                }
                coursesAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



//        cursuriArrayList.add(new Cursuri("Cursul 1"));
//        cursuriArrayList.add(new Cursuri("Cursul 2"));
//        cursuriArrayList.add(new Cursuri("Cursul 3"));
//        cursuriArrayList.add(new Cursuri("Cursul 4"));
//        cursuriArrayList.add(new Cursuri("Cursul 5"));
//        cursuriArrayList.add(new Cursuri("Cursul 6"));



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