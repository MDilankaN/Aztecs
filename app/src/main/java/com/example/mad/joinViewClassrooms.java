package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class joinViewClassrooms extends AppCompatActivity implements View.OnClickListener {

    Button classrooms,btnaddforum,viewReply;
    private String sessionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_view_classrooms);
        classrooms=findViewById(R.id.btnview);
        classrooms.setOnClickListener(this);
        btnaddforum=findViewById(R.id.btnadd);
        btnaddforum.setOnClickListener(this);
        viewReply=findViewById(R.id.btnviewreply);
        viewReply.setOnClickListener(this);
        sessionID=getIntent().getStringExtra("sessionID");

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnadd:
                Intent intent= new Intent(joinViewClassrooms.this, addForum.class);
                intent.putExtra("sessionID",sessionID);
                startActivity(intent);
                break;
            case R.id.btnview:
                Intent intent2= new Intent(joinViewClassrooms.this, studentClassroom.class);
                startActivity(intent2);
                break;
            case R.id.btnviewreply:
                //Intent intent3= new Intent(joinViewClassrooms.this, addForum.class);
                //startActivity(intent3);
                break;
        }
    }
}