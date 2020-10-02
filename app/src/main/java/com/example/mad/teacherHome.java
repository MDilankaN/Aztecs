package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class teacherHome extends AppCompatActivity {

    Button navNews,navProfile,BtnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

    }

    @Override
    protected void onResume() {
        super.onResume();
        navNews = findViewById(R.id.btn_navi2);
        navProfile = findViewById(R.id.btn_navi3);

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



        BtnAdd= (Button) findViewById(R.id.btnadd);
        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(teacherHome.this,addForum.class) ;
                startActivity(intent);
            }
        });


    }
}