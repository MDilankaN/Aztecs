package com.example.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Student_results extends AppCompatActivity {
    Button button;
    RecyclerView recyclerView;
    DatabaseReference ref;
    ArrayList<Result> resultsList;
    RecyclerAdapter_ResultList recyclerAdapter;
    DividerItemDecoration dividerItemDecoration;
    public static final int putextra = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_results);
        OnclickButtonListener();

        recyclerView = findViewById(R.id.resultRecyclerview);
        dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        ref = FirebaseDatabase.getInstance().getReference().child("Result").child("IT").child("Quiz 01");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                resultsList = new ArrayList<>();
                for(DataSnapshot DS : snapshot.getChildren()){
                    Result R = new Result();
                    resultsList.add(R);
                    R.setSName(DS.child("name").getValue().toString());
                    R.setSResult(Integer.parseInt(DS.child("result").getValue().toString()));
                }
                recyclerAdapter = new RecyclerAdapter_ResultList(resultsList);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void OnclickButtonListener() {
        button = findViewById(R.id.calculateBtn);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int AllResult = resultsList.size();

                        Intent intent = new Intent(Student_results.this, Pass_Student.class);
                        intent.putExtra(String.valueOf(putextra),AllResult);
                        startActivity(intent);
                    }
                }
        );
    }
}