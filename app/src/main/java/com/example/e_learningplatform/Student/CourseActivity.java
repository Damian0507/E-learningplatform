package com.example.e_learningplatform.Student;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.e_learningplatform.DatabaseHelper;
import com.example.e_learningplatform.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CourseActivity extends AppCompatActivity {

    Button back_button;
    PlayerView playerView;
    ExoPlayer player;
    PDFView pdfView;
    DatabaseHelper databaseHelper;
    DatabaseReference databaseReference;
    Uri pdfUri,videoUri;
    TextView curs_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        curs_txt = findViewById(R.id.nr_curs_textView);

        String nume_curs = getIntent().getStringExtra("nume_curs");

        Log.d(TAG, "onCreate: " + nume_curs);


        databaseHelper = new DatabaseHelper(getApplicationContext());
        String nume_materie = databaseHelper.fetchAllDataMaterie();
        Log.d(TAG, "onCreate: " + nume_materie);
        databaseReference = FirebaseDatabase.getInstance("https://e-learning-platform-2-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("Users").child("Materii");
        databaseReference.child(nume_materie).child("Cursuri").child(nume_curs).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();
                pdfUri = Uri.parse(dataSnapshot.child("curs").getValue().toString());
                videoUri = Uri.parse(dataSnapshot.child("video").getValue().toString());
                curs_txt.setText(dataSnapshot.child("nume_curs").getValue().toString());

                play_video(videoUri);

                set_PDF(pdfUri);
            }
        });




        back_button();
    }

    private void back_button() {

        back_button = findViewById(R.id.course_back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.pause();
                Intent i = new Intent(getApplicationContext(), CoursesStudentActivity.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(i);
                finish();
            }
        });


    }

    private void play_video(Uri uri){


        playerView = findViewById(R.id.player_view);

        player = new ExoPlayer.Builder(this).build();

        playerView.setPlayer(player);

        MediaItem mediaItem = MediaItem.fromUri(uri);

        player.setMediaItem(mediaItem);

        player.prepare();

        player.pause();

    }

    private void set_PDF(Uri uri){

        pdfView = findViewById(R.id.curs_PDFView);

        pdfView.fromUri(uri).load();



    }
}