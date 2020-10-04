package com.example.mad;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QuizDetails extends AppCompatActivity {

    Button button;
    EditText quizName,time,description;
    DatabaseReference ref;
    Quiz_Details qd;
    String Class,QName;
    session s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizdetails);
        quizName = findViewById(R.id.ETQuizName);
        time = findViewById(R.id.ETQuizTime);
        description = findViewById(R.id.ETQuizDescription);
        OnclickButtonListener();
        s = new session();
        s.setCLASS("IT");
        Class = s.getCLASS();
        ref = FirebaseDatabase.getInstance().getReference().child("QuizzesDetails").child(Class);
    }

    public void OnclickButtonListener() {
        button = findViewById(R.id.QuizNextBt);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                 try {
                     if (quizName.getText().toString().isEmpty() | time.getText().toString().trim().isEmpty() | description.getText().toString().isEmpty()) {
                         Snackbar.make(view,"Please fill all fields",Snackbar.LENGTH_SHORT).show();
                         quizName.setTextColor(Color.BLACK);
                     } else if (quizName.getText().toString().length() < 4) {
                         Snackbar.make(view,"Please enter at least four characters for the username",Snackbar.LENGTH_SHORT).show();
                         quizName.setTextColor(Color.RED);

                     } else {
                         qd = new Quiz_Details();
                         qd.setQuizName(quizName.getText().toString());
                         qd.setQuizTime(Integer.parseInt(time.getText().toString().trim()));
                         qd.setQuizDescription(description.getText().toString());
                         ref.child(qd.getQuizName()).setValue(qd);
                         Snackbar.make(view,"Successful",Snackbar.LENGTH_SHORT).show();
                         Intent intent = new Intent(QuizDetails.this, Entering_MCQs.class);
                         startActivity(intent);
                         finish();
                     }
                      }catch (NumberFormatException e){
                        Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
                         Snackbar.make(view,"Invalid Time Duration",Snackbar.LENGTH_SHORT).show();
                      }
                    }
                }
        );
    }
}