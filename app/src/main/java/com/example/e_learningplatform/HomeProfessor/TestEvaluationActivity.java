package com.example.e_learningplatform.HomeProfessor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.e_learningplatform.HomeProfessorAdapters.ChatProfessorAdapter;
import com.example.e_learningplatform.HomeProfessorAdapters.TestEvalutionAdapter;
import com.example.e_learningplatform.HomeProfessorClasses.ProfesorTestEvalution;
import com.example.e_learningplatform.HomeProfessorClasses.ProfessorChat;
import com.example.e_learningplatform.R;

import java.util.ArrayList;

public class TestEvaluationActivity extends AppCompatActivity {
    Button back_button;
    RecyclerView recyclerView;
    ArrayList<ProfesorTestEvalution> profesorTestEvalutionsArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_evaluation);

        back_button();

        dateInitialize_testEvaluation();

        recyclerView = findViewById(R.id.checkExams_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TestEvalutionAdapter testEvalutionAdapter = new TestEvalutionAdapter(this,profesorTestEvalutionsArrayList);
        recyclerView.setAdapter(testEvalutionAdapter);
        testEvalutionAdapter.notifyDataSetChanged();
        recyclerView.setHasFixedSize(true);
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


        profesorTestEvalutionsArrayList.add(new ProfesorTestEvalution("Duma Virgil",R.drawable.chat_profile_pic));
        profesorTestEvalutionsArrayList.add(new ProfesorTestEvalution("Barna Cornel",R.drawable.chat_profile_pic));
        profesorTestEvalutionsArrayList.add(new ProfesorTestEvalution("Dragu Daniel",R.drawable.chat_profile_pic));
        profesorTestEvalutionsArrayList.add(new ProfesorTestEvalution("Mnerie Corina",R.drawable.chat_profile_pic));
        profesorTestEvalutionsArrayList.add(new ProfesorTestEvalution("Balas Marius",R.drawable.chat_profile_pic));


    }
}