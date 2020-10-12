package com.example.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class replyview_student extends AppCompatActivity {

    DatabaseReference ref;
    ArrayList<StudentForum> replyList;
    RecyclerView recyclerView;
    Intent intent;
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replyview_student);
        username=getIntent().getStringExtra("name");

        recyclerView =findViewById(R.id.rvReply);

        ref = FirebaseDatabase.getInstance().getReference().child("StudentForum").child(username);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                replyList = new ArrayList<>();
                //ArrayList<String> forum = new ArrayList<>();
                //ArrayList<String> StuName = new ArrayList<>();
                //for (DataSnapshot ds1 : snapshot.getChildren()) {
                    StudentForum s1 = new StudentForum();
                    replyList.add(s1);
                    s1.setQuection(snapshot.child("quection").getValue().toString().trim());
                    s1.setReply(snapshot.child("reply").getValue().toString().trim());
                    //System.out.println(s1.getMessage());
                    //forum.add(ds1.child("message").getValue().toString().trim());
                    //StuName.add(ds1.child("stdusername").getValue().toString().trim());
                //}

                AdupterReply Adupterreply = new AdupterReply(replyview_student.this, username, replyList);
                recyclerView.setAdapter(Adupterreply);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(TeacherNews.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }
}