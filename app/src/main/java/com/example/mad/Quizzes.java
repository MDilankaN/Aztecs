package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
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
    ArrayList<String> QNAME;
    ImageView backbt;
    String Class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);
        final View view = findViewById(android.R.id.content);
        recyclerView = findViewById(R.id.quizzesRecyclerview);
        Intent intent = getIntent();
        Class = intent.getStringExtra("name");

        ref = FirebaseDatabase.getInstance().getReference().child("QuizzesDetails").child(Class);
            ref.addValueEventListener(new ValueEventListener(){
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                        quizLists = new ArrayList<>();
                        QNAME = new ArrayList<>();
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            QuizList ql = new QuizList();
                            quizLists.add(ql);
                            ql.setQuizName(ds.child("quizName").getValue().toString());
                            QNAME.add(ql.getQuizName());
                        }

                        recyclerAdapter = new RecyclerAdapter_QuizList(Quizzes.this,quizLists,QNAME,Class);
                        recyclerView.setAdapter(recyclerAdapter);

                       //Toast.makeText(Quizzes.this,"successful",Toast.LENGTH_SHORT).show();


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Quizzes.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

            backbt = findViewById(R.id.BackBT);
            backbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });

    }


    public void openNewActivity(View v){
        Intent intent = new Intent(this, QuizDetails.class);
        intent.putExtra("Class",Class);
        startActivity(intent);
    }
}