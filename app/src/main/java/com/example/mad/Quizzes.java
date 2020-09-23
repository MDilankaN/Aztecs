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


    }

    public void openNewActivity(View v){
        Intent intent = new Intent(this, QuizDetails.class);
        startActivity(intent);
    }

}