package com.example.e_learningplatform.HomeProfessor;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_learningplatform.HomeProfessorClasses.ProfessorAddVideo;
import com.example.e_learningplatform.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddTestActivity extends AppCompatActivity {

    Button back_button,import_test_btn,submit_test_pdf;
    private static final int PICK_PDF = 2;
    ProgressBar progressBar;
    UploadTask uploadPDF;
    private Uri pdfUri;
    TextView nume_pdf;
    DatabaseReference databaseReference;
    StorageReference storageRefrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);

        import_test_btn = findViewById(R.id.import_PDF_button2);
        submit_test_pdf = findViewById(R.id.add_course_button2);
        nume_pdf = findViewById(R.id.nume_curs_PDF_textView2);
        progressBar = findViewById(R.id.progressBar_add_course4);

        Intent i = getIntent();

        String nume_materie = i.getStringExtra("Materie");

        Log.d(TAG, "uploadData: " + nume_materie);

        submit_test_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPDF(nume_materie);



            }
        });

        back_button();
    }
    private void uploadPDF(String nume_materie) {




        storageRefrence = FirebaseStorage.getInstance().getReference("Test_profesori");
        databaseReference = FirebaseDatabase.getInstance("https://e-learning-platform-2-default-rtdb.europe-west1.firebasedatabase.app").
                getReference("Users").child("Materii").child(nume_materie).child("Cursuri");


        String numePDF = nume_pdf.getText().toString();

        if(!numePDF.equals("test.pdf")){

            progressBar.setVisibility(View.VISIBLE);
            StorageReference referencePDF =  storageRefrence.child("test");
            uploadPDF = referencePDF.putFile(pdfUri);
            Log.d(TAG, "uploadPDF: "+ pdfUri);

            Task<Uri> uriTaskPDF = uploadPDF.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return referencePDF.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful())
                    {
                        String curs;

                        Uri downloadUrl = task.getResult();
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(AddTestActivity.this,"Course saved", Toast.LENGTH_SHORT).show();

                        curs = downloadUrl.toString();
                        Log.d(TAG, "onComplete: "+ curs);
                        //professorAddVideo.setCurs(downloadUrl.toString());
                        databaseReference.child("Test").child("nume_curs").setValue("Test");
                        databaseReference.child("Test").child("test2").setValue(curs);

                        Intent i = new Intent(getApplicationContext(),HomeProfessorActivity.class);
                        startActivity(i);
                        finish();

                    }else{
                        Toast.makeText(AddTestActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }else{
            Toast.makeText(AddTestActivity.this,"All fields are required",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_PDF && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            pdfUri = data.getData();
            String nume_curs = null;
            nume_curs = pdfUri.getPath();
            int cut = nume_curs.lastIndexOf("/");

            if(cut != -1)
            {
                nume_curs = nume_curs.substring(cut + 1);
            }
            nume_pdf.setText(nume_curs);


        }
    }

    public void ChooseTestPDF(View view){

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_PDF);


    }

    private void back_button() {

        back_button = findViewById(R.id.option_back_button2);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), OptionsActivity.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //i.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(i);
                finish();
            }
        });


    }



}