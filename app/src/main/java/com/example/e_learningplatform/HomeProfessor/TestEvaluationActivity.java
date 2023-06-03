package com.example.e_learningplatform.HomeProfessor;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.e_learningplatform.DatabaseHelper;
import com.example.e_learningplatform.HomeProfessorAdapters.ChatProfessorAdapter;
import com.example.e_learningplatform.HomeProfessorAdapters.TestEvalutionAdapter;
import com.example.e_learningplatform.HomeProfessorClasses.ProfesorTestEvalution;
import com.example.e_learningplatform.HomeProfessorClasses.ProfessorChat;
import com.example.e_learningplatform.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TestEvaluationActivity extends AppCompatActivity {
    Button back_button;
    RecyclerView recyclerView;
    ArrayList<ProfesorTestEvalution> profesorTestEvalutionsArrayList = new ArrayList<>();
    DatabaseReference database;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_evaluation);

        back_button();


        Log.d(TAG, "onCreate: " + "am intrat in activitate");
        dateInitialize_testEvaluation();


    }

    private void back_button() {

        back_button = findViewById(R.id.test_evalution_back_button);

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

    private void dateInitialize_testEvaluation() {


        recyclerView = findViewById(R.id.checkExams_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TestEvalutionAdapter testEvalutionAdapter = new TestEvalutionAdapter(this,profesorTestEvalutionsArrayList);
        recyclerView.setAdapter(testEvalutionAdapter);
        recyclerView.setHasFixedSize(true);

        String materie = getIntent().getStringExtra("Materie");
        Log.d(TAG, "dateInitialize_testEvaluation: " + materie);

        database = FirebaseDatabase.getInstance("https://e-learning-platform-2-default-rtdb.europe-west1.firebasedatabase.app").
                getReference("Users").child("Materii").child(materie).child("Teste");

        Log.d(TAG, "dateInitialize_testEvaluation: " + "am intrat in functie");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                profesorTestEvalutionsArrayList.clear();

                Log.d(TAG, "onDataChange: " + "is aici");

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Log.d(TAG, "onDataChange: " + snapshot.getValue());

                    ProfesorTestEvalution profesorTestEvalution = dataSnapshot.getValue(ProfesorTestEvalution.class);
                    profesorTestEvalutionsArrayList.add(profesorTestEvalution);



                }
                testEvalutionAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}