package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class teacherHome extends AppCompatActivity {

    Button navNews,navProfile,viewClass,addclass,  checkForum;
    Button tempory;
    private  String sessionID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        sessionID=getIntent().getStringExtra("sessionID");









    }

    @Override
    protected void onResume() {
        super.onResume();
        navNews = findViewById(R.id.btn_navi2);
        navProfile = findViewById(R.id.btn_navi3);
        viewClass=findViewById(R.id.btnview);
        addclass=findViewById(R.id.btnadd);
        checkForum=findViewById(R.id.btnform);
       //tempory=findViewById(R.id.tempory);

        //navigation for teacher news
        navNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(teacherHome.this, TeacherNews.class);
                startActivity(intent);

            }
        });

        //navigation for teacher profile
        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(teacherHome.this, ActivityTeacherProfile.class);
                startActivity(intent);
            }
        });

        //navigation for view current classrooms
        viewClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(teacherHome.this, viewUpdateTeacher.class);
                intent.putExtra("sessionID",sessionID);
                startActivity(intent);
            }
        });

        //navigation for add new  classrooms
        addclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(teacherHome.this, AddClassrooms.class);
                intent.putExtra("sessionID",sessionID);
                startActivity(intent);
            }
        });


        //tempory navigate button testing update,delete
        checkForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(teacherHome.this, forumviewTeacher.class);
                startActivity(intent);
            }
        });

       /* //tempory navigate add forum
        *//*tempory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(teacherHome.this,addForum.class);
                startActivity(intent);
            }*//*
        });*/




    }
}