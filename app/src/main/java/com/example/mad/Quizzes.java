package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Quizzes extends AppCompatActivity {

    public FloatingActionButton addQuiz;
    RecyclerView recyclerView;
    DatabaseReference ref;
    ArrayList<QuizList> quizLists;
    RecyclerAdapter_QuizList recyclerAdapter;
    DividerItemDecoration dividerItemDecoration;
    ArrayList<String> QNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);

        recyclerView = findViewById(R.id.quizzesRecyclerview);
        dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        ref = FirebaseDatabase.getInstance().getReference().child("QuizzesDetails").child("IT");

            ref.addValueEventListener(new ValueEventListener(){
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChildren()){
                        quizLists = new ArrayList<>();
                        QNAME = new ArrayList<>();
                        for(DataSnapshot ds : snapshot.getChildren()){

                             QuizList ql = new QuizList();
                             quizLists.add(ql);
                             ql.setQuizName(ds.child("quizName").getValue().toString());
                             QNAME.add(ql.getQuizName());
                        }
                        recyclerAdapter = new RecyclerAdapter_QuizList(Quizzes.this,quizLists,QNAME);
                        recyclerView.setAdapter(recyclerAdapter);

                       // Toast.makeText(Quizzes.this,"successful",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Quizzes.this,"can't find maths class",Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Quizzes.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

    }

    public void on(){
        Intent intent = new Intent(this, QuizDetails.class);
        startActivity(intent);
    }

    public void openNewActivity(View v){
        Intent intent = new Intent(this, QuizDetails.class);
        startActivity(intent);
    }
}