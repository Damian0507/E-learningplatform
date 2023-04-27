package com.example.e_learningplatform.HomeProfessor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_learningplatform.HomeAdapters.HomeAdapter;
import com.example.e_learningplatform.HomeClasses.Materii;
import com.example.e_learningplatform.HomeProfessorAdapters.HomeProfessorAdapter;
import com.example.e_learningplatform.HomeProfessorClasses.ProfessorMaterii;
import com.example.e_learningplatform.R;

import java.util.ArrayList;


public class HomeProfessorFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ProfessorMaterii> materiiArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_professor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dateInitialize_home();

        recyclerView = view.findViewById(R.id.materii_professor_recycleView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        HomeProfessorAdapter homeProfessorAdapter = new HomeProfessorAdapter(getContext(),materiiArrayList);
        recyclerView.setAdapter(homeProfessorAdapter);
        homeProfessorAdapter.notifyDataSetChanged();
        recyclerView.setHasFixedSize(true);
    }
    private void dateInitialize_home() {

        materiiArrayList.add(new ProfessorMaterii("Procesare paralela si distribuita",R.drawable.ppsd_image));
        materiiArrayList.add(new ProfessorMaterii("Electronica de putere",R.drawable.edp_image));
        materiiArrayList.add(new ProfessorMaterii("Prelucrarea semnalelor",R.drawable.pc_image));
        materiiArrayList.add(new ProfessorMaterii("Condicerea structurilor flexibile de fabricatie",R.drawable.cpc_image));
        materiiArrayList.add(new ProfessorMaterii("Programare orientata pe obiece",R.drawable.opo_image));
        materiiArrayList.add(new ProfessorMaterii("Sisteme de conducere a procesor continue",R.drawable.sfdf_image));


    }
}