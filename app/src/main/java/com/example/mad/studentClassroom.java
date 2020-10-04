package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class studentClassroom extends AppCompatActivity {

    Button navPro,navNews;

    String uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_classroom);
        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        uname = name;

    }

    @Override
    protected void onResume() {
        super.onResume();
        navPro = findViewById(R.id.btn_navi3);
        navNews = findViewById(R.id.btn_navi2);

        navNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(studentClassroom.this, Activity_Student_News.class);
                intent.putExtra("name",uname);
                startActivity(intent);

            }
        });
        navPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(studentClassroom.this, Student_profile.class);
                intent.putExtra("name",uname);
                startActivity(intent);

            }
        });

    }
}