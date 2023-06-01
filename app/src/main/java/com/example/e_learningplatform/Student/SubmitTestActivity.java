 package com.example.e_learningplatform.Student;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_learningplatform.DatabaseHelper;
import com.example.e_learningplatform.Home.HomeStudentActivity;
import com.example.e_learningplatform.HomeProfessor.AddCourseActivity;
import com.example.e_learningplatform.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

 public class SubmitTestActivity extends AppCompatActivity {

     Button back_button, import_PDF_btn, submit_test_btn;
     TextView nume_materie, nume_test;
     EditText nume_student, prenume_student;
     private Uri pdfUri;
     private static final int PICK_PDF = 1;
     ProgressBar progressBar;
     UploadTask uploadPDF;
     DatabaseReference databaseReference;
     StorageReference storageRefrence;
     DatabaseHelper databaseHelper;

     @SuppressLint("MissingInflatedId")
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_submit_test);

         nume_materie = findViewById(R.id.nume_materie_submit_test_textView);
         nume_student = findViewById(R.id.nume_editText);
         prenume_student = findViewById(R.id.prenume_editText);
         progressBar = findViewById(R.id.progressBar_add_course3);
         import_PDF_btn = findViewById(R.id.import_PDF_button);
         submit_test_btn = findViewById(R.id.submit_test_student_button);
         nume_test = findViewById(R.id.nume_test_textView);


         String materie = getIntent().getStringExtra("materie");
         nume_materie.setText(materie);


         submit_test_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 uploadTest(materie);

             }
         });


         back_button();

     }

     private void back_button() {

         back_button = findViewById(R.id.submit_test_back_button);

         back_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i = new Intent(getApplicationContext(), CoursesStudentActivity.class);
                 startActivity(i);
                 finish();
             }
         });


     }

     public void uploadTest(String materie) {


         String nume = nume_student.getText().toString();
         String prenume = prenume_student.getText().toString();
         String numePDF = nume_test.getText().toString();

         String nume_complet = nume + " " + prenume;

         databaseHelper = new DatabaseHelper(getApplicationContext());
         String id = databaseHelper.fetchAllData();

         storageRefrence = FirebaseStorage.getInstance().getReference("Test_elevi");
         databaseReference = FirebaseDatabase.getInstance("https://e-learning-platform-2-default-rtdb.europe-west1.firebasedatabase.app").
                 getReference("Users").child("Materii").child(materie).child(id);

         Log.d(TAG, "uploadTest: " + nume + prenume + numePDF);

         if (!TextUtils.isEmpty(nume) && !TextUtils.isEmpty(prenume) && !numePDF.equals("procesare_paralela_si_distribuita.pdf")) {

             Log.d(TAG, "uploadTest: " + "is aici?");
             progressBar.setVisibility(View.VISIBLE);
             StorageReference referencePDF = storageRefrence.child("Test");
             uploadPDF = referencePDF.putFile(pdfUri);
             Log.d(ContentValues.TAG, "uploadPDF: " + pdfUri);

             Task<Uri> uriTask = uploadPDF.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                 @Override
                 public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                     if (!task.isSuccessful()) {
                         throw task.getException();
                     }
                     return referencePDF.getDownloadUrl();
                 }
             }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                 @Override
                 public void onComplete(@NonNull Task<Uri> task) {
                     if (task.isSuccessful()) {
                         String test;
                         String status = "Neevaluat!";

                         Uri downloadUrl = task.getResult();
                         progressBar.setVisibility(View.INVISIBLE);
                         test = downloadUrl.toString();
                         databaseReference.child("TEST2").setValue(test);
                         databaseReference.child("nume").setValue(nume_complet);
                         databaseReference.child("status").setValue(status);

                         Toast.makeText(SubmitTestActivity.this,"Submit succesful!",Toast.LENGTH_SHORT).show();

                     }else {
                         Toast.makeText(SubmitTestActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                     }

                 }
             });

         }else{
             Toast.makeText(SubmitTestActivity.this,"All fields are required",Toast.LENGTH_SHORT).show();
         }


     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
         super.onActivityResult(requestCode, resultCode, data);

         if (requestCode == PICK_PDF && resultCode == RESULT_OK && data != null && data.getData() != null) {
             pdfUri = data.getData();
             String nume_curs = null;
             nume_curs = pdfUri.getPath();
             int cut = nume_curs.lastIndexOf("/");

             if (cut != -1) {
                 nume_curs = nume_curs.substring(cut + 1);
             }
             nume_test.setText(nume_curs);


         }

     }
     public void StudentChoosePDF(View view){

         Intent intent = new Intent();
         intent.setType("application/pdf");
         intent.setAction(Intent.ACTION_GET_CONTENT);
         startActivityForResult(intent,PICK_PDF);


     }
 }