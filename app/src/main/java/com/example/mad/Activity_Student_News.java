package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Activity_Student_News extends AppCompatActivity {

    DatabaseReference dbRef;
    ArrayList<News> studentNewsLists;
    RecyclerView recyclerView;
    RecyclerAdapterStudent recyclerAdapterStudent;
    String uname;
    Button navhome, navprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__student__news);
        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        uname = name;

        //find recyclerView id
        recyclerView = findViewById(R.id.Recycleview2);
        dbRef = FirebaseDatabase.getInstance().getReference().child("StuNews");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    studentNewsLists = new ArrayList<>();
                    for(DataSnapshot ds : snapshot.getChildren()){
                        News N1 = new News();
                        studentNewsLists.add(N1);
                        N1.setNews(ds.child("news").getValue().toString());
                    }
                    recyclerAdapterStudent = new RecyclerAdapterStudent(studentNewsLists);
                    recyclerView.setAdapter(recyclerAdapterStudent);
                }else{
                    Toast.makeText(Activity_Student_News.this,"can't find news class",Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        navhome = findViewById(R.id.btn_navi1);
        navhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Student_News.this,studentClassroom.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        navprofile = findViewById(R.id.btn_navi3);
        navprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Student_News.this,Student_profile.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

    }


}