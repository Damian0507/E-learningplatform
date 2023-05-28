package com.example.e_learningplatform.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.e_learningplatform.R;
import com.github.barteksc.pdfviewer.PDFView;

public class CourseActivity extends AppCompatActivity {

    Button back_button;
    PlayerView playerView;
    ExoPlayer player;
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        play_video();

        set_PDF();

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

    private void play_video(){


        playerView = findViewById(R.id.player_view);

        player = new ExoPlayer.Builder(this).build();

        playerView.setPlayer(player);

        MediaItem mediaItem = MediaItem.fromUri("https://firebasestorage.googleapis.com/v0/b/e-learning-platform-3b8e7.appspot.com/o/Video%2FVideo?alt=media&token=805c400e-b037-44f2-96a2-620b794638ae");

        player.setMediaItem(mediaItem);

        player.prepare();

        player.pause();

    }

    private void set_PDF(){

        pdfView = findViewById(R.id.curs_PDFView);

        pdfView.fromAsset("curs1.pdf").load();



    }
}