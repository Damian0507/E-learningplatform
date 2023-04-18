package com.example.e_learningplatform.StudentAdapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_learningplatform.HomeAdapters.HomeAdapter;
import com.example.e_learningplatform.R;
import com.example.e_learningplatform.Student.CourseActivity;
import com.example.e_learningplatform.Student.CoursesStudentActivity;
import com.example.e_learningplatform.StudentClasses.Cursuri;

import java.util.ArrayList;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.MyViewHolder> {

    Context context;
    ArrayList<Cursuri> cursuriArrayList;

    public CoursesAdapter(Context context, ArrayList<Cursuri> cursuriArrayList) {
        this.context = context;
        this.cursuriArrayList = cursuriArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cursuri_item,parent,false);


        return new CoursesAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Cursuri cursuri = cursuriArrayList.get(position);
        holder.curs_button.setText(cursuri.getNume_curs());

        holder.curs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(context, CourseActivity.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //context.startActivity(i);
               //((Activity)context).finish();

                /*TO DO: Check why app gets crash when jump backward to courses and trying to open course again*/
                Intent i = new Intent(context, CourseActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
               ((Activity)context).finish();
            }
        });


    }

    @Override
    public int getItemCount() {
        return cursuriArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        Button curs_button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            curs_button = itemView.findViewById(R.id.curs_button);

        }
    }
}
