package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Quizzes extends AppCompatActivity {

    public FloatingActionButton addQuiz;
    TextView result,Q1edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);

        OnclickButtonListener();


    }

    public void openNewActivity(View v){
        Intent intent = new Intent(this, QuizDetails.class);
        startActivity(intent);


    }
    public void OnclickButtonListener() {

        result = (TextView)findViewById(R.id.Q1Rbtn);
        Q1edit = (TextView)findViewById(R.id.Q1btn);


        result.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         Intent intent = new Intent(Quizzes.this, Student_results.class);
                        startActivity(intent);
                    }
                }
        );

        Q1edit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Quizzes.this, Edit_quizzes.class);
                        startActivity(intent);
                    }
                }
        );
    }
}