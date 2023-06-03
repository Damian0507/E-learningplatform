package com.example.e_learningplatform.Home;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

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
import com.example.e_learningplatform.HomeAdapters.PersonalAdapter;
import com.example.e_learningplatform.HomeClasses.Materii;
import com.example.e_learningplatform.HomeClasses.Personal;
import com.example.e_learningplatform.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PersonalHomeFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Personal> personalArrayList = new ArrayList<>();
    DatabaseHelper databaseHelper;
    DatabaseReference databaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        databaseHelper = new DatabaseHelper(getContext());
        String username = databaseHelper.fetchAllData();




        dateInitialize_personal(username);


    }

    private void dateInitialize_personal(String username) {

        recyclerView = getView().findViewById(R.id.personal_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PersonalAdapter personalAdapter = new PersonalAdapter(getContext(),personalArrayList);
        recyclerView.setAdapter(personalAdapter);
        recyclerView.setHasFixedSize(true);

        databaseReference = FirebaseDatabase.getInstance("https://e-learning-platform-2-default-rtdb.europe-west1.firebasedatabase.app").
                getReference("Users").child("Materii");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                personalArrayList.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){


                    Personal personal = dataSnapshot.child("Teste").child(username).getValue(Personal.class);
                    personalArrayList.add(personal);
                    Log.d(TAG, "onDataChange: is aici" + dataSnapshot.child("Teste").child(username).getValue());

                }
                personalAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
}