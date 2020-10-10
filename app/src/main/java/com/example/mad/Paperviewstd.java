package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Paperviewstd extends AppCompatActivity {

    private DatabaseReference dref ;
    private Button btnp1 ;
    private Button btnm1 ;
    private LinearLayout ly;
    private String username;
    private String Clzname,TeacherID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paperviewstd);
        Intent intent = getIntent();
        username = intent.getStringExtra("name");
        Clzname = intent.getStringExtra("ClassName");
        TeacherID = intent.getStringExtra("teacherID");



        ly = findViewById(R.id.stshowlay);
        final View v = findViewById(android.R.id.content) ;


        dref = FirebaseDatabase.getInstance().getReference().child("QuizzesDetails").child(Clzname);
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    try{
                        Quiz_Details qdd = new Quiz_Details();
                        final View itemView3 = getLayoutInflater().inflate(R.layout.quizbtnlayout,null,false);//

                        ly.addView(itemView3);

                        qdd.setQuizName(snapshot.child("quizName").getValue().toString());

                        btnp1 = itemView3.findViewById(R.id.btnp1);
                        btnp1.setText(qdd.getQuizName());

                        onpbtnClick(itemView3,qdd.getQuizName());
                       // onmbtnClick(itemView3,username);


                    }catch (Exception e){
                        Snackbar.make(v,"Error occurred", Snackbar.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    protected void onpbtnClick(View viewx, final String Clzname){
        btnp1 = (Button) viewx.findViewById(R.id.btnp1);
        btnp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPaperview(Clzname);
            }
        });
    }

    public void openPaperview(String qname){
       Intent intx = new Intent(this, PaperExamView.class);
        intx.putExtra("QuizName",qname);
        intx.putExtra("Class",Clzname);
        intx.putExtra("userName",username);
        intx.putExtra("teacherID",TeacherID);

       startActivity(intx);
    }

//    protected void onmbtnClick(View viewx,final String uname){
//        btnm1 = (Button) findViewById(R.id.btnm1);
//        btnm1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openMarksview(uname);
//            }
//        });
//
//    }

//    public void openMarksview(String uname){
//        Intent intx = new Intent(this, Marksview.class);
//        intx.putExtra("Username",uname);
//        startActivity(intx);
//    }
}