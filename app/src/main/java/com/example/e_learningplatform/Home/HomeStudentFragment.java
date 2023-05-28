package com.example.e_learningplatform.Home;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_learningplatform.DatabaseHelper;
import com.example.e_learningplatform.HomeAdapters.HomeAdapter;
import com.example.e_learningplatform.HomeClasses.Materii;
import com.example.e_learningplatform.HomeProfessorClasses.ProfessorMaterii;
import com.example.e_learningplatform.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeStudentFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Materii> materiiArrayList = new ArrayList<>();
    DatabaseReference database,database_an;
    DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_student, container, false);




    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseHelper = new DatabaseHelper(getContext());


        //get data from homeprofessoractivity
        String username = databaseHelper.fetchAllData();
        Log.d(TAG, "onViewCreated: " + username);
        
        dateInitialize_home(username);


    }

    private void dateInitialize_home(String username) {

        recyclerView = getView().findViewById(R.id.materii_recycleView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        HomeAdapter homeAdapter = new HomeAdapter(getContext(),materiiArrayList);
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
        recyclerView.setHasFixedSize(true);

        database = FirebaseDatabase.getInstance("https://e-learning-platform-3b8e7-default-rtdb.europe-west1.firebasedatabase.app").
                getReference("Users").child("Materii");

        database_an = FirebaseDatabase.getInstance("https://e-learning-platform-3b8e7-default-rtdb.europe-west1.firebasedatabase.app").
                getReference("Users").child(username);




        database_an.child("Specializare_an").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {


                DataSnapshot dataSnapshot = task.getResult();
                String an = String.valueOf(dataSnapshot.getValue());
                Log.d(TAG, "onComplete: " + an);

                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        materiiArrayList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Materii studentMaterii = dataSnapshot.getValue(Materii.class);
                            Log.d(TAG, "onDataChange: " + studentMaterii.getAn());
                            Log.d(TAG, "onDataChange: " + studentMaterii.getTitlu());
                            Log.d(TAG, "onDataChange: " + studentMaterii.getImagine());

                            if(studentMaterii.getAn().equals(an)) {

                                materiiArrayList.add(studentMaterii);


                            }



                        }
                        homeAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });



//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                materiiArrayList.clear();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//
//
//                        Materii studentMaterii = dataSnapshot.getValue(Materii.class);
//                        materiiArrayList.add(studentMaterii);
//
//
//
//
//                }
//                homeAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


    }
}