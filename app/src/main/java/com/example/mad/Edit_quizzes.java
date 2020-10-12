package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//for update Quiz details
public class Edit_quizzes extends AppCompatActivity {
    EditText QName,QTime,QDescription;
    DatabaseReference ref;
    Button update;
    Quiz_Details QD;
    ImageView backbt;
    String Class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quizzes);
        //create view for call snackbar
        final View view = findViewById(android.R.id.content);
        //set id for get to input text
        QName = findViewById(R.id.UpdateQname);
        QTime = findViewById(R.id.UpdateQtime);
        QDescription = findViewById(R.id.UpdateQdescription);
        //get extra massages
        Intent intent = getIntent();
        final String qname = intent.getStringExtra("Extar");
         Class = intent.getStringExtra("Class");
        //create database path to retrieve current quiz details
        ref = FirebaseDatabase.getInstance().getReference().child("QuizzesDetails").child(Class);
        ref.child(qname).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){//check whether there has any child
                    //get current quiz details and set to that inputs.
                    QName.setText(snapshot.child("quizName").getValue().toString());
                    QTime.setText(snapshot.child("quizTime").getValue().toString());
                    QDescription.setText(snapshot.child("quizDescription").getValue().toString());

                }else{
                    //display error massage
                    Snackbar.make(view,"can't find "+qname, Snackbar.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        OnButtonClickListener();//for update btn click
    }
    //for update btn click
    private void OnButtonClickListener() {
        update = findViewById(R.id.Update_BT);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                try {
                    //empty fields validation
                    if (QName.getText().toString().isEmpty() | QTime.getText().toString().trim().isEmpty() | QDescription.getText().toString().isEmpty()) {
                        Snackbar.make(view,"Please fill all fields", Snackbar.LENGTH_SHORT).show();
                    } else {
                                    //send current data to Quiz_detail java class with newly create object
                                    QD = new Quiz_Details();
                                    QD.setQuizName(QName.getText().toString());
                                    QD.setQuizTime(Integer.parseInt(QTime.getText().toString().trim()));
                                    QD.setQuizDescription(QDescription.getText().toString());
                                    //create firebase path for update
                                    ref.orderByChild("quizName").equalTo(QD.getQuizName()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.hasChildren()){
                                        ref.child(QD.getQuizName()).setValue(QD);//send that object

                                        Snackbar.make(view,"Update Successful", Snackbar.LENGTH_SHORT).show();
                                        //create intent for go back to quiz home
                                        Intent intent = new Intent(Edit_quizzes.this,Quizzes.class);
                                        intent.putExtra("name",Class);//send extra msg to quiz home
                                        startActivity(intent);

                                    }else{
                                        Snackbar.make(view,"quiz name does not exist", Snackbar.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                    }
                }catch (NumberFormatException e){
                    Snackbar.make(view,"Invalid Time Duration", Snackbar.LENGTH_SHORT).show();

                }

            }
        });
        //for back button
        backbt = findViewById(R.id.BackBT);
        backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();//go back to previous activity
            }
        });
    }




}