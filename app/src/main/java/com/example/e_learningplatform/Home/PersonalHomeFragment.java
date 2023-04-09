package com.example.e_learningplatform.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_learningplatform.HomeAdapters.HomeAdapter;
import com.example.e_learningplatform.HomeAdapters.PersonalAdapter;
import com.example.e_learningplatform.HomeClasses.Materii;
import com.example.e_learningplatform.HomeClasses.Personal;
import com.example.e_learningplatform.R;

import java.util.ArrayList;

public class PersonalHomeFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Personal> personalArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dateInitialize_personal();

        recyclerView = view.findViewById(R.id.personal_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PersonalAdapter personalAdapter = new PersonalAdapter(getContext(),personalArrayList);
        recyclerView.setAdapter(personalAdapter);
        personalAdapter.notifyDataSetChanged();
        recyclerView.setHasFixedSize(true);
    }

    private void dateInitialize_personal() {



        personalArrayList.add(new Personal("Procesare paralela si distribuita","70%",70));
        personalArrayList.add(new Personal("Electronica de putere","50%",50));
        personalArrayList.add(new Personal("Prelucrarea semnalelor","30%",30));
        personalArrayList.add(new Personal("Condicerea structurilor flexibile de fabricatie","80%",80));
        personalArrayList.add(new Personal("Programare orientata pe obiece","76%",76));
        personalArrayList.add(new Personal("Sisteme de conducere a procesor continue","95%",95));


    }
}