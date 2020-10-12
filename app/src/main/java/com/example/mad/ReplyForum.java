package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReplyForum extends AppCompatActivity {

    String uname,msg;
    Button msgreply,Back;
    EditText ReplyMSg;
    StudentForum studentForum = new StudentForum();
    private SimpleDateFormat dateformat;
    private String date;
    TextView textViewDate,forummsg,StudentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_forum);

        Calendar calender=Calendar.getInstance();
        dateformat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateformat.format(calender.getTime());
        textViewDate = findViewById(R.id.date);
        textViewDate.setText(date);

        uname=getIntent().getStringExtra("name");
        msg=getIntent().getStringExtra("msg");

        msgreply=findViewById(R.id.replysubmit);
        ReplyMSg = findViewById(R.id.reply1);
        forummsg = findViewById(R.id.Stdforum);
        StudentID = findViewById(R.id.stuid);
        Back = findViewById(R.id.backbtn);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReplyForum.this,forumviewTeacher.class);
                intent.putExtra("name",uname);
                startActivity(intent);

            }
        });

        forummsg.setText(msg);
        StudentID.setText(uname);


        msgreply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("StudentForum").child(uname);

                try{
                    if (TextUtils.isEmpty(ReplyMSg.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Please type your Reply...", Toast.LENGTH_SHORT).show();
                    }else {
                        //take user valid inputs
                        studentForum.setReply(ReplyMSg.getText().toString().trim());
                        studentForum.setQuection(forummsg.getText().toString().trim());
                        studentForum.setStdusername(uname.toString().trim());
                        ref.setValue(studentForum);
                        //Feedback through the toast message
                        Toast.makeText(getApplicationContext(), "Reply added successfully..", Toast.LENGTH_SHORT).show();
                        //clearControls();
                    }
                }catch(NumberFormatException e){


                }

            }
        });

    }
}