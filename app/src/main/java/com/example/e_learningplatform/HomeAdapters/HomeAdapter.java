package com.example.e_learningplatform.HomeAdapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_learningplatform.Student.CoursesStudentActivity;
import com.example.e_learningplatform.HomeClasses.Materii;
import com.example.e_learningplatform.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    Context context;
    ArrayList<Materii> materiiArrayList;

    public HomeAdapter(Context context, ArrayList<Materii> materiiArrayList) {

        this.context = context;
        this.materiiArrayList = materiiArrayList;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.materii_item,parent,false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Materii materii = materiiArrayList.get(position);
        holder.nume_materie_textView.setText(materii.getTitlu());
        Glide.with(context).load(materiiArrayList.get(position).getImagine()).into(holder.materie_imageView);

        holder.materie_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, CoursesStudentActivity.class);
                context.startActivity(i);
                ((Activity)context).finish();

            }
        });

        holder.nume_materie_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, CoursesStudentActivity.class);
                context.startActivity(i);
                ((Activity)context).finish();

            }
        });

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
