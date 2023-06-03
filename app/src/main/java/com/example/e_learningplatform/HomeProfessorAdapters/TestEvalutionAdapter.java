package com.example.e_learningplatform.HomeProfessorAdapters;

import static android.content.Context.DOWNLOAD_SERVICE;


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_learningplatform.DatabaseHelper;
import com.example.e_learningplatform.HomeProfessor.TestEvaluationActivity;
import com.example.e_learningplatform.HomeProfessorClasses.ProfesorTestEvalution;
import com.example.e_learningplatform.HomeProfessorClasses.ProfessorChat;
import com.example.e_learningplatform.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
        holder.nume_elev_textView.setText(profesorTestEvalution.getNume());
        if(profesorTestEvalution.getStatus().equals("Neevaluat!")){
            holder.status_checkBox.setText("Neevaluat!");
            holder.status_checkBox.setChecked(false);
        }
        else if(profesorTestEvalution.getStatus().equals("Evaluat!")){
            holder.status_checkBox.setText("Evaluat!");
            holder.status_checkBox.setChecked(true);
        }

        holder.download_test_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            //context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(profesorTestEvalution.getTEST2())));
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(profesorTestEvalution.getTEST2()));
                Log.d(TAG, "onClick: " + profesorTestEvalution.getTEST2());
            request.setTitle("Test_" + profesorTestEvalution.getNume());
            request.setDescription("Downloading the test");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.allowScanningByMediaScanner();
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"pdf_file.pdf");
            DownloadManager manager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
            manager.enqueue(request);

                Toast.makeText(context, "Test downloaded successfuly, please check your storage!",Toast.LENGTH_SHORT).show();

            }
        });

        holder.send_note_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference database;

                database = FirebaseDatabase.getInstance("https://e-learning-platform-2-default-rtdb.europe-west1.firebasedatabase.app").
                        getReference("Users").child("Materii").child(profesorTestEvalution.getMaterie()).child("Teste").child(profesorTestEvalution.getID());

                //Log.d(TAG, "onClick: " + Integer.parseInt(holder.punctaj_editText.getText().toString()));

                if(!holder.punctaj_editText.getText().toString().equals("")) {
                    if ((Integer.parseInt(holder.punctaj_editText.getText().toString()) > 100 || Integer.parseInt(holder.punctaj_editText.getText().toString()) < 0)) {

                        Toast.makeText(context, "Points should be beetwen 0 and 100 points!", Toast.LENGTH_SHORT).show();


                    } else {
                        database.child("punctaj").setValue(holder.punctaj_editText.getText().toString());
                        database.child("status").setValue("Evaluat!");
                        holder.status_checkBox.setText("Evaluat!");
                        holder.status_checkBox.setChecked(true);
                    }
                } else {
                    Toast.makeText(context, "Points should be beetwen 0 and 100 points!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return professorTestEvalutionArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{



        TextView nume_elev_textView;
        CheckBox status_checkBox;
        EditText punctaj_editText;
        Button download_test_Btn,send_note_Btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            send_note_Btn = itemView.findViewById(R.id.trimite_nota_button);
            download_test_Btn = itemView.findViewById(R.id.download_test_button);
            punctaj_editText = itemView.findViewById(R.id.nota_test_editText);
            status_checkBox = itemView.findViewById(R.id.stare_evaluare_checkBox);
            nume_elev_textView = itemView.findViewById(R.id.nume_utilizator_test_evalution_textView);
        }
    }

}
