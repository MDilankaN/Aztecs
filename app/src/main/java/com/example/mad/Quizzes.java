package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Quizzes extends AppCompatActivity {

    public FloatingActionButton addQuiz;
    TextView result,Q1edit;
    RecyclerView recyclerView;
    RecyclerAdapter_QuizList recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);

        recyclerView = findViewById(R.id.quizzesRecyclerview);
        recyclerAdapter = new RecyclerAdapter_QuizList();

        recyclerView.setAdapter(recyclerAdapter);
    }

    public void openNewActivity(View v){
        Intent intent = new Intent(this, QuizDetails.class);
        startActivity(intent);
    }

}