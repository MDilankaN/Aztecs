package com.example.mad;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forumview_teacher);

        ref = FirebaseDatabase.getInstance().getReference().child("Forum");
        recyclerView =findViewById(R.id.rvForum);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(ref!=null){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        forumList=new ArrayList<>();
                        for(DataSnapshot ds: snapshot.getChildren()){

                            forumList.add(ds.getValue(Forum.class));

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


        }
    }
}