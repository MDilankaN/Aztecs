package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class TeacherNews extends AppCompatActivity {

    //initialize all the variables
    EditText  NewsDescription,newsID;
    //TextView News1;
    Button btnAddNewsK,btn_navi2;
    DatabaseReference dbRef;
    News news;
    //long maxId = 0;
    ArrayList<News> newsLists;

    //create recyclerView object
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_news);

        //assign insert values
        NewsDescription = (EditText) findViewById(R.id.MultitxtK);
        newsID = (EditText)findViewById(R.id.NewsID);

        //assign button
        btnAddNewsK = (Button) findViewById(R.id.btnAddK);

        //create new News object
        news = new News();
        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        dbRef = FirebaseDatabase.getInstance().getReference().child("News").child(name);

        btnAddNewsK.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //create db connection
                String NID = newsID.getText().toString().trim();
                try{
                    if (TextUtils.isEmpty(NewsDescription.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Description", Toast.LENGTH_SHORT);
                    else{
                        //Take input from the user and assigning them to this instance (std) of the Student...
                        news.setNews(NewsDescription.getText().toString().trim());
                        news.setId(newsID.getText().toString().trim());

                        //insert in to the Database..
                        //dbRef.child(String.valueOf(maxId+1)).setValue(news);
                        System.out.println(NID +" 123");
                        dbRef.child(NID).setValue(news);

                        //dbRef.child("newsID").setValue(news);

                        //feedback to the user via a Toast
                        Toast.makeText(getApplicationContext(),"Data Saved Successfully",Toast.LENGTH_SHORT);
                        clearControls();
                    }
                }
                catch(Exception e){
                    Toast.makeText(getApplicationContext(),"Empty fields", Toast.LENGTH_SHORT);
                }
            }
        });

        //find recyclerView id
        recyclerView = findViewById(R.id.Recycleview1);
        //retrieve data from data base
        dbRef = FirebaseDatabase.getInstance().getReference().child("News").child(name);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    newsLists = new ArrayList<>();
                    ArrayList<String> news = new  ArrayList<>();
                    for(DataSnapshot ds : snapshot.getChildren()){
                        News N1 = new News();
                        newsLists.add(N1);
                        N1.setNews(ds.child("news").getValue().toString());
                        news.add(ds.child("id").getValue().toString());
                    }
                    recyclerAdapter = new RecyclerAdapter(newsLists,TeacherNews.this,news,name);
                    recyclerView.setAdapter(recyclerAdapter);
                }else{
                    Toast.makeText(TeacherNews.this,"can't find news class",Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(TeacherNews.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    //method to clear all the user inputs
    private void clearControls(){
        NewsDescription.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_navi2 =(Button)findViewById(R.id.btn_navi3);
        btn_navi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherNews.this,ActivityTeacherProfile.class);
                startActivity(intent);
            }
        });

    }
}
