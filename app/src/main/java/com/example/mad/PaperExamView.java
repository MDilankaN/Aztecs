package com.example.mad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PaperExamView extends AppCompatActivity {

    private DatabaseReference dref ;
    private Button btnm1 ;
    private RecyclerView recycler;
    private ArrayList<MCQ> mcq;
    private RecyclerAdapeter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paper_exam_view);
        onmbtnClick();//submit btn click

        //datebase
        dref = FirebaseDatabase.getInstance().getReference().child("MCQ").child("IT").child("Quiz 01");
        dref.keepSynced(true);

        //arraylist
        mcq = new ArrayList<>();

        //recycle view
        recycler = findViewById(R.id.stuRecyc);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recycler.setLayoutManager(llm);
        recycler.setHasFixedSize(true);

        //getting data
        getQuizMcqs();

    }

    private void getQuizMcqs() {

        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    MCQ qs = new MCQ();
                    qs.setQuestion(snapshot.child("question").getValue().toString());
                    qs.setAns1(snapshot.child("ans1").getValue().toString());
                    qs.setAns2(snapshot.child("ans2").getValue().toString());
                    qs.setAns3(snapshot.child("ans3").getValue().toString());
                    qs.setAns4(snapshot.child("ans4").getValue().toString());
                    qs.setCorrectAns(Integer.parseInt((snapshot.child("correctAns").getValue().toString())));

                    mcq.add(qs);
                }

                recyclerAdapter = new RecyclerAdapeter(getApplicationContext(),mcq);
                recycler.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    //submit btn click
    protected void onmbtnClick(){
        btnm1 = findViewById(R.id.Rbnm1);
        btnm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMarksview();
            }
        });

    }
    public void openMarksview(){
        Intent intx = new Intent(this, Marksview.class);
        startActivity(intx);
    }
}
