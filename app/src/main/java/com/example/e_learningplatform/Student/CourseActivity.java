package com.example.e_learningplatform.Student;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CourseActivity extends AppCompatActivity {

    Button back_button;
    PlayerView playerView;
    ExoPlayer player;
    PDFView pdfView;
    DatabaseHelper databaseHelper;
    DatabaseReference databaseReference;
    Uri pdfUri,videoUri;
    TextView curs_txt;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        constraintLayout = findViewById(R.id.parent_layout);

        pdfView = findViewById(R.id.curs_PDFView);

        playerView = findViewById(R.id.player_view);

        curs_txt = findViewById(R.id.nr_curs_textView);

        String nume_curs = getIntent().getStringExtra("nume_curs");

        Log.d(TAG, "onCreate: " + nume_curs);

        if(!nume_curs.equals("Test")) {
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
        }
        else
        {
            databaseHelper = new DatabaseHelper(getApplicationContext());
            String nume_materie = databaseHelper.fetchAllDataMaterie();
            Log.d(TAG, "onCreate: " + nume_materie);
            databaseReference = FirebaseDatabase.getInstance("https://e-learning-platform-2-default-rtdb.europe-west1.firebasedatabase.app")
                    .getReference("Users").child("Materii");
            databaseReference.child(nume_materie).child("Cursuri").child(nume_curs).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    DataSnapshot dataSnapshot = task.getResult();
                    pdfUri = Uri.parse(dataSnapshot.child("test2").getValue().toString());
                    //videoUri = Uri.parse(dataSnapshot.child("video").getValue().toString());
                    curs_txt.setText(dataSnapshot.child("nume_curs").getValue().toString());

                    //play_video(videoUri);
                    playerView.setVisibility(View.INVISIBLE);

                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(R.id.curs_PDFView,ConstraintSet.TOP,R.id.nr_curs_textView,ConstraintSet.BOTTOM,0);
                    constraintSet.applyTo(constraintLayout);

                    set_PDF(pdfUri);
                }

            });
        }





        back_button();
    }

    private void back_button() {

        back_button = findViewById(R.id.course_back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(videoUri != null) {
                    player.pause();
                }
                Intent i = new Intent(getApplicationContext(), CoursesStudentActivity.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(i);
                finish();
            }
        });


    }

    private void play_video(Uri uri){




        player = new ExoPlayer.Builder(this).build();

        playerView.setPlayer(player);

        MediaItem mediaItem = MediaItem.fromUri(uri);

        player.setMediaItem(mediaItem);

        player.prepare();

        player.pause();

    }

    private void set_PDF(Uri uri){



        new RetrievePDFfromUrl().execute(String.valueOf(uri));

    }

    // create an async task class for loading pdf file from URL.
    class RetrievePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            // we are using inputstream
            // for getting out PDF.
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                // below is the step where we are
                // creating our connection.
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    // response is success.
                    // we are getting input stream from url
                    // and storing it in our variable.
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                // this is the method
                // to handle errors.
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            // after the execution of our async
            // task we are loading our pdf in our pdf view.
            pdfView.fromStream(inputStream).load();
        }
    }
}