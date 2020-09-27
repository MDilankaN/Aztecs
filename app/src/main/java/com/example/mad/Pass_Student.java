package com.example.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pass_Student extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference ref;
    ArrayList<Result> resultsList;
    RecyclerAdapter_ResultList recyclerAdapter;
    DividerItemDecoration dividerItemDecoration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass__student);

        recyclerView = findViewById(R.id.passRecyclerview);
        Intent intent = getIntent();

        dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        ref = FirebaseDatabase.getInstance().getReference().child("Result").child("IT").child("Quiz 01");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    resultsList = new ArrayList<>();
                    for(DataSnapshot DS : snapshot.getChildren()){
                        Result R = new Result();
                        resultsList.add(R);
                        R.setSName(DS.child("name").getValue().toString());
                        R.setSResult(Integer.parseInt((DS.child("result").getValue().toString())));
                    }
                    recyclerAdapter = new RecyclerAdapter_ResultList(resultsList);
                    recyclerView.setAdapter(recyclerAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}