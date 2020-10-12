package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
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

public class forumviewTeacher extends AppCompatActivity {


    DatabaseReference ref;
    ArrayList<Forum> forumList;
    RecyclerView recyclerView;
    String sessionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forumview_teacher);
        sessionID=getIntent().getStringExtra("name");
        System.out.println(sessionID);

        recyclerView =findViewById(R.id.rvForum);

        ref = FirebaseDatabase.getInstance().getReference().child("Forum").child(sessionID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                forumList = new ArrayList<>();
                ArrayList<String> forum = new ArrayList<>();
                ArrayList<String> StuName = new ArrayList<>();
                    for (DataSnapshot ds1 : snapshot.getChildren()) {
                        Forum f1 = new Forum();
                        forumList.add(f1);
                        f1.setMessage(ds1.child("message").getValue().toString().trim());
                        System.out.println(f1.getMessage());
                        forum.add(ds1.child("message").getValue().toString().trim());
                        StuName.add(ds1.child("stdusername").getValue().toString().trim());
                    }

                    AdupterForum AdupterForum = new AdupterForum(forumviewTeacher.this, forum, sessionID, forumList,StuName);
                    recyclerView.setAdapter(AdupterForum);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(TeacherNews.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

 /*   @Override
    protected void onStart() {
        super.onStart();*/
        /*if(ref!=null){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        forumList=new ArrayList<>();
                        ArrayList<String> forum = new  ArrayList<>();
                        for(DataSnapshot ds: snapshot.getChildren()){
                            Forum N1 = new Forum();
                            forumList.add(N1);
                            N1.setMessage(ds.child("message").getValue().toString());
                            forum.add(ds.getValue().toString());
                            //news.add(ds.child("id").getValue().toString());

                        }
                        AdupterForum adupterForumClass =new AdupterForum(forumList);
                        recyclerView.setAdapter(adupterForumClass);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(forumviewTeacher.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });


        }*/
        //recyclerView = findViewById(R.id.Recycleview1);
        //retrieve data from data base
        //ref = FirebaseDatabase.getInstance().getReference().child("News").child(name);

    //}
}