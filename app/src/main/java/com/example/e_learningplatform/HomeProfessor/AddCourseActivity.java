package com.example.e_learningplatform.HomeProfessor;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
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

public class AddCourseActivity extends AppCompatActivity {
    Button back_button;
    DatabaseReference databaseReference;
    StorageReference storageRefrence;
    ProfessorAddVideo professorAddVideo;
    Button add_course_Button,add_video_Button;
    EditText nume_curs;
    TextView nume_video,nume_pdf;
    private Uri videoUri,pdfUri;
    MediaController mediaController;
    PlayerView playerView;
    ExoPlayer player;
    private static final int PICK_VIDEO = 1;
    private static final int PICK_PDF = 2;
    ProgressBar progressBar,progressBar2;
    UploadTask uploadTask,uploadPDF;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        add_course_Button = findViewById(R.id.add_course_button);
        add_video_Button = findViewById(R.id.add_clip_button);
        nume_curs = findViewById(R.id.nume_curs_editText);
        nume_video = findViewById(R.id.nume_video_mp4_textView);
        nume_pdf = findViewById(R.id.nume_curs_PDF_textView);

        playerView = findViewById(R.id.player_view_add_course_professor);
        progressBar = findViewById(R.id.progressBar_add_course);
        progressBar2 = findViewById(R.id.progressBar_add_course2);

        player = new ExoPlayer.Builder(getApplicationContext()).build();
        playerView.setPlayer(player);
        player.prepare();
        player.pause();

        Intent i = getIntent();

        String nume_materie = i.getStringExtra("Materie");

        Log.d(TAG, "uploadData: " + nume_materie);






        add_course_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                uploadData(nume_materie);
                uploadPDF(nume_materie);





            }
        });






        back_button();
    }

    private void uploadData(String nume_materie) {


        professorAddVideo = new ProfessorAddVideo();

        storageRefrence = FirebaseStorage.getInstance().getReference("Video");


        databaseReference = FirebaseDatabase.getInstance("https://e-learning-platform-2-default-rtdb.europe-west1.firebasedatabase.app").
                getReference("Users").child("Materii").child(nume_materie).child("Cursuri");

        String numeCurs = nume_curs.getText().toString();
        String numeVideo = nume_video.getText().toString();
        String numePDF = nume_pdf.getText().toString();

        if(!TextUtils.isEmpty(numeCurs) && !numeVideo.equals("video.mp4") && !numePDF.equals("curs.pdf")){

            progressBar.setVisibility(View.VISIBLE);
            StorageReference reference =  storageRefrence.child(numeVideo);
            uploadTask = reference.putFile(videoUri);
            Log.d(TAG, "uploadData: " + videoUri);

            Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return reference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {

                    if(task.isSuccessful()){

                        String video;
                        String nume_curs;

                        Uri downloadUrl = task.getResult();
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(AddCourseActivity.this,"Video saved", Toast.LENGTH_SHORT).show();


                        video = downloadUrl.toString();

                        nume_curs = numeCurs;
                        //professorAddVideo.setVideo(downloadUrl.toString());
                        //professorAddVideo.setCurs(pdfUri.toString());
                        //professorAddVideo.setNume_curs(numeCurs);
                        //String i = databaseReference.push().getKey();
                        databaseReference.child(numeCurs).child("nume_curs").setValue(nume_curs);
                        databaseReference.child(numeCurs).child("video").setValue(video);

                        Intent i = new Intent(getApplicationContext(),HomeProfessorActivity.class);
                        startActivity(i);
                        finish();


                    }else {
                        Toast.makeText(AddCourseActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }else{
            Toast.makeText(AddCourseActivity.this,"All fields are required",Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadPDF(String nume_materie) {


        professorAddVideo = new ProfessorAddVideo();

        storageRefrence = FirebaseStorage.getInstance().getReference("Cursuri");
        databaseReference = FirebaseDatabase.getInstance("https://e-learning-platform-2-default-rtdb.europe-west1.firebasedatabase.app").
                getReference("Users").child("Materii").child(nume_materie).child("Cursuri");

        String numeCurs = nume_curs.getText().toString();
        String numeVideo = nume_video.getText().toString();
        String numePDF = nume_pdf.getText().toString();

        if(!TextUtils.isEmpty(numeCurs) && !numeVideo.equals("video.mp4") && !numePDF.equals("curs.pdf")){

            progressBar2.setVisibility(View.VISIBLE);
            StorageReference referencePDF =  storageRefrence.child(numePDF);
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
                        progressBar2.setVisibility(View.INVISIBLE);
                        Toast.makeText(AddCourseActivity.this,"Course saved", Toast.LENGTH_SHORT).show();

                        curs = downloadUrl.toString();
                        Log.d(TAG, "onComplete: "+ curs);
                        //professorAddVideo.setCurs(downloadUrl.toString());

                        databaseReference.child(numeCurs).child("curs").setValue(curs);

                    }else{
                        Toast.makeText(AddCourseActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }else{
            Toast.makeText(AddCourseActivity.this,"All fields are required",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_VIDEO && (resultCode == RESULT_OK && data != null && data.getData() != null))
        {
            videoUri = data.getData();
            String nume_clip = null;
            nume_clip = videoUri.getPath();
            int cut = nume_clip.lastIndexOf("/");

            if(cut != -1)
            {
                nume_clip = nume_clip.substring(cut + 1);
            }

            MediaItem mediaItem = MediaItem.fromUri(videoUri);
            player.setMediaItem(mediaItem);

            nume_video.setText(nume_clip);

        }

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

    public void ChooseVideo(View view){

        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_VIDEO);

        player.pause();


    }

    public void ChoosePDF(View view){

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_PDF);

        player.pause();

    }


    private String getExt(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void back_button() {

        back_button = findViewById(R.id.add_course_back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), HomeProfessorActivity.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //i.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(i);
                finish();
            }
        });


    }
}