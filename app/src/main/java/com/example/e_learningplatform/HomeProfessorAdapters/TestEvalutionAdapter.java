package com.example.e_learningplatform.HomeProfessorAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_learningplatform.HomeProfessorClasses.ProfesorTestEvalution;
import com.example.e_learningplatform.HomeProfessorClasses.ProfessorChat;
import com.example.e_learningplatform.R;

import java.util.ArrayList;

public class TestEvalutionAdapter extends RecyclerView.Adapter<TestEvalutionAdapter.MyViewHolder> {

    Context context;
    ArrayList<ProfesorTestEvalution> professorTestEvalutionArrayList;

    public TestEvalutionAdapter(Context context, ArrayList<ProfesorTestEvalution> professorTestEvalutionArrayList) {
        this.context = context;
        this.professorTestEvalutionArrayList = professorTestEvalutionArrayList;
    }

    @NonNull
    @Override
    public TestEvalutionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.test_evalution_item,parent,false);



        return new TestEvalutionAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TestEvalutionAdapter.MyViewHolder holder, int position) {


        ProfesorTestEvalution profesorTestEvalution = professorTestEvalutionArrayList.get(position);
        holder.nume_elev_textView.setText(profesorTestEvalution.getNume_elev());
        holder.profile_imageView.setImageResource(profesorTestEvalution.getProfile_pic());

    }

    @Override
    public int getItemCount() {
        return professorTestEvalutionArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{



        TextView nume_elev_textView;
        ImageView profile_imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            nume_elev_textView = itemView.findViewById(R.id.nume_utilizator_test_evalution_textView);
            profile_imageView = itemView.findViewById(R.id.test_evalution_profile_pic_test_evalution);
        }
    }

}
