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
import com.example.e_learningplatform.HomeClasses.Materii;
import com.example.e_learningplatform.R;

import java.util.ArrayList;


public class HomeStudentFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Materii> materiiArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_student, container, false);




    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        dateInitialize();

        recyclerView = view.findViewById(R.id.materii_recycleView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        HomeAdapter homeAdapter = new HomeAdapter(getContext(),materiiArrayList);
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
        recyclerView.setHasFixedSize(true);
    }

    private void dateInitialize() {

        materiiArrayList.add(new Materii("Procesare paralela si distribuita",R.drawable.ppsd_image));
        materiiArrayList.add(new Materii("Electronica de putere",R.drawable.edp_image));
        materiiArrayList.add(new Materii("Prelucrarea semnalelor",R.drawable.pc_image));
        materiiArrayList.add(new Materii("Condicerea structurilor flexibile de fabricatie",R.drawable.cpc_image));
        materiiArrayList.add(new Materii("Programare orientata pe obiece",R.drawable.opo_image));
        materiiArrayList.add(new Materii("Sisteme de conducere a procesor continue",R.drawable.sfdf_image));


    }
}