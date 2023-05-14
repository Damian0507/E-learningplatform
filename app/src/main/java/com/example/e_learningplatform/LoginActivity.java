package com.example.e_learningplatform;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_learningplatform.Home.HomeStudentActivity;
import com.example.e_learningplatform.HomeProfessor.HomeProfessorActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    Button sing_in_btn;
    EditText username, password;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        sing_in();


    }


    public void sing_in(){

        sing_in_btn = findViewById(R.id.sing_in_button);
        username = findViewById(R.id.user_ID_editText);
        password = findViewById(R.id.password_editText);





        sing_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username_string;
                String password_string;

                username_string = username.getText().toString();
                password_string = password.getText().toString();

                Log.d(TAG, "sing_in: " +username.getText().toString());

               if(!username_string.isEmpty())
               {
                   readData(username_string,password_string);
               }else{

                   Toast.makeText(LoginActivity.this, "Please enter username",Toast.LENGTH_SHORT).show();

               }


            }
        });
}

    public void readData(String username, String password){

        reference = FirebaseDatabase.getInstance("https://e-learning-platform-3b8e7-default-rtdb.europe-west1.firebasedatabase.app").getReference("Users");
        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(task.isSuccessful()){

                    if(task.getResult().exists()){


                        DataSnapshot dataSnapshot = task.getResult();
                        String password_fb = String.valueOf(dataSnapshot.child("Password").getValue());
                        if(password_fb.equals(password)){

                            String role = String.valueOf(dataSnapshot.child("Role").getValue());
                            if(role.equals("student")){

                                Intent i = new Intent(getApplicationContext(),HomeStudentActivity.class);
                                startActivity(i);
                                finish();

                            }else{

                                Intent i = new Intent(getApplicationContext(),HomeProfessorActivity.class);
                                startActivity(i);
                                finish();

                            }

                        }else {

                            Toast.makeText(LoginActivity.this,"Password incorrect!",Toast.LENGTH_SHORT).show();

                        }


                        Toast.makeText(LoginActivity.this,"Login succsefully!",Toast.LENGTH_SHORT).show();



                    }else {

                        Toast.makeText(LoginActivity.this,"User doesn't exists!",Toast.LENGTH_SHORT).show();
                    }

                }else {

                    Toast.makeText(LoginActivity.this,"Failed to find the account!",Toast.LENGTH_SHORT).show();

                }


            }
        });


    }

}

