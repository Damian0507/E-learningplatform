package com.example.e_learningplatform.HomeProfessor;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.e_learningplatform.HomeAdapters.HomeAdapter;
import com.example.e_learningplatform.HomeClasses.Materii;
import com.example.e_learningplatform.HomeProfessorAdapters.HomeProfessorAdapter;
import com.example.e_learningplatform.HomeProfessorClasses.ProfessorMaterii;
import com.example.e_learningplatform.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeProfessorFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ProfessorMaterii> materiiArrayList = new ArrayList<>();
    TextView nume_profesor_textView;
    DatabaseReference database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_professor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //get data from homeprofessoractivity
        String username = getArguments().getString("Username");
        Log.d(TAG, "onViewCreated: " + username);

        //set welcome message
        nume_profesor_textView = view.findViewById(R.id.nume_profesor_textView);
        nume_profesor_textView.setText("Bună ziua, " +  username);


        dateInitialize_home(username);

        recyclerView = view.findViewById(R.id.materii_professor_recycleView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        HomeProfessorAdapter homeProfessorAdapter = new HomeProfessorAdapter(getContext(),materiiArrayList);
        recyclerView.setAdapter(homeProfessorAdapter);
        homeProfessorAdapter.notifyDataSetChanged();
        recyclerView.setHasFixedSize(true);


    }
    private void dateInitialize_home(String username) {

        materiiArrayList.add(new ProfessorMaterii("Procesare paralela si distribuita",R.drawable.ppsd_image));
        materiiArrayList.add(new ProfessorMaterii("Electronica de putere",R.drawable.edp_image));
        materiiArrayList.add(new ProfessorMaterii("Prelucrarea semnalelor",R.drawable.pc_image));
        materiiArrayList.add(new ProfessorMaterii("Condicerea structurilor flexibile de fabricatie",R.drawable.cpc_image));
        materiiArrayList.add(new ProfessorMaterii("Programare orientata pe obiece",R.drawable.opo_image));
        materiiArrayList.add(new ProfessorMaterii("Sisteme de conducere a procesor continue",R.drawable.sfdf_image));

//        database = FirebaseDatabase.getInstance("https://e-learning-platform-3b8e7-default-rtdb.europe-west1.firebasedatabase.app").getReference("Users").child(username).child("Materii").child("Logica computationala");
//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                String username_DB = username;
//
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//
//
//
//
//
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


    }
}