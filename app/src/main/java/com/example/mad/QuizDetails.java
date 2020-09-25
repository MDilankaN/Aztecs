package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                         Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                         quizName.setTextColor(Color.BLACK);
                     } else if (quizName.getText().toString().length() < 4) {
                         Toast.makeText(getApplicationContext(), "Please enter at least four characters for the username", Toast.LENGTH_SHORT).show();
                         quizName.setTextColor(Color.RED);

                     } else {
                         QName = quizName.getText().toString().trim();
                         qd = new Quiz_Details();
                         ref.child(QName);
                         qd.setQuizName(quizName.getText().toString());
                         qd.setQuizTime(Integer.parseInt(time.getText().toString().trim()));
                         qd.setQuizDescription(description.getText().toString());
                         ref.child(QName).setValue(qd);
                         Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(QuizDetails.this, Entering_MCQs.class);
                         startActivity(intent);
                         finish();
                     }
                      }catch (NumberFormatException e){
                        Toast.makeText(getApplicationContext(),"Invalid Time deuration",Toast.LENGTH_SHORT).show();
                      }
                    }
                }
        );
    }
}