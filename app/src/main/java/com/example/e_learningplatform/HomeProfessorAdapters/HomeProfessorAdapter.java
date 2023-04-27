package com.example.e_learningplatform.HomeProfessorAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_learningplatform.HomeAdapters.HomeAdapter;
import com.example.e_learningplatform.HomeClasses.Materii;
import com.example.e_learningplatform.HomeProfessorClasses.ProfessorMaterii;
import com.example.e_learningplatform.R;

import java.util.ArrayList;

public class HomeProfessorAdapter extends RecyclerView.Adapter<HomeProfessorAdapter.MyViewHolder> {


    Context context;
    ArrayList<ProfessorMaterii> materiiArrayList;

    public HomeProfessorAdapter(Context context, ArrayList<ProfessorMaterii> materiiArrayList) {

        this.context = context;
        this.materiiArrayList = materiiArrayList;


    }

    @NonNull
    @Override
    public HomeProfessorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.materii_item,parent,false);


        return new HomeProfessorAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProfessorAdapter.MyViewHolder holder, int position) {

        ProfessorMaterii materii = materiiArrayList.get(position);
        holder.nume_materie_textView.setText(materii.getTitle());
        holder.materie_imageView.setImageResource(materii.getImage());

    }

    @Override
    public int getItemCount() {
        return materiiArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        ImageView materie_imageView;
        TextView nume_materie_textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            materie_imageView = itemView.findViewById(R.id.materie_imageView);
            nume_materie_textView = itemView.findViewById(R.id.nume_materie_textView);
        }
    }
}
