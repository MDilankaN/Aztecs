package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
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
    EditText ED;
    public static final int putextra = 0;
    int n=0;
    double mark = 0;
    ImageView backbt;
    String Class,qname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_results);
        OnclickButtonListener();

        recyclerView = findViewById(R.id.resultRecyclerview);

        Intent intent = getIntent();
         qname = intent.getStringExtra("Extar");
         Class = intent.getStringExtra("Class");
        System.out.println(qname);

        ref = FirebaseDatabase.getInstance().getReference().child("Result").child(Class).child(qname);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    resultsList = new ArrayList<>();
                    for (DataSnapshot DS : snapshot.getChildren()) {
                        Result R = new Result();
                        resultsList.add(R);
                        R.setSName(DS.child("name").getValue().toString());
                        R.setSResult(Integer.parseInt(DS.child("result").getValue().toString()));
                        n++;
                    }
                    recyclerAdapter = new RecyclerAdapter_ResultList(resultsList);
                    recyclerView.setAdapter(recyclerAdapter);
                }else {

                    Toast.makeText(getApplicationContext(),"No one is attempt for this quiz yet",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void OnclickButtonListener() {
        ED = (EditText) findViewById(R.id.edPassMark);
        button = findViewById(R.id.calculateBtn);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       try {
                               mark = Double.parseDouble(ED.getText().toString());
                               Intent intent = new Intent(Student_results.this, Pass_Student.class);
                               intent.putExtra("AllResult", n);
                               intent.putExtra("mark", mark);
                               intent.putExtra("qname", qname);
                               intent.putExtra("Class", Class);
                               startActivity(intent);
                           } catch (NumberFormatException e) {
                            Snackbar.make(view,"Invalid mark", Snackbar.LENGTH_SHORT).show();
                       }
                    }
                }
        );

        backbt = findViewById(R.id.BackBT);
        backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}