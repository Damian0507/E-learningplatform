package com.example.e_learningplatform.HomeAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_learningplatform.HomeClasses.Materii;
import com.example.e_learningplatform.HomeClasses.Personal;
import com.example.e_learningplatform.R;

import java.util.ArrayList;

public class PersonalAdapter extends RecyclerView.Adapter<PersonalAdapter.MyViewHolder> {

    Context context;
    ArrayList<Personal> personalArrayList;

    public PersonalAdapter(Context context, ArrayList<Personal> personalArrayList) {
        this.context = context;
        this.personalArrayList = personalArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.personal_item,parent,false);


        return new PersonalAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Personal personal = personalArrayList.get(position);
        holder.personal_progres_bar.setProgress(Integer.parseInt(personal.getPunctaj()));
        holder.percent_textView.setText(personal.getPunctaj());
        holder.materie_textView.setText(personal.getMaterie());

    }

    @Override
    public int getItemCount() {
        return personalArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        ProgressBar personal_progres_bar;
        TextView percent_textView,materie_textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            personal_progres_bar = itemView.findViewById(R.id.personal_progres_bar);
            percent_textView = itemView.findViewById(R.id.percent_textView);
            materie_textView = itemView.findViewById(R.id.personal_materie_textView);
        }
    }

}
