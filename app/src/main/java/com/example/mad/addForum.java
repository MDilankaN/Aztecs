package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addForum extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String sessionID;
    EditText addForum;
    Button btnsubmit;
    Button btnback;

    DatabaseReference dbRef;
    Forum forum;
    long maxid=0;



    //Method to clear all user inputs
    private void clearControls(){
        addForum.setText("");


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newforum);

        sessionID=getIntent().getStringExtra("sessionID");

        //Add forum interface
        addForum=findViewById(R.id.forum);

        btnsubmit=findViewById(R.id.btn_sub);
        btnback=findViewById(R.id.btn_back);

        //retrieve classrooms to spinner
        Spinner spinner=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.classrooms,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.child("Classroom").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //forum object
        forum= new Forum();

        //method for adding a new forum
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Forum");
                dbRef.addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists());
                        maxid=(dataSnapshot.getChildrenCount());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }




                });

                try{
                    Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
                    String text = mySpinner.getSelectedItem().toString();

                    if (TextUtils.isEmpty(addForum.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Please type your question...", Toast.LENGTH_SHORT).show();
                    }else {
                        //take user valid inputs
                        forum.setMessage(addForum.getText().toString().trim());
                        forum.setCode(Integer.parseInt(text));
                        forum.setStdusername(sessionID.toString().trim());
                        //inserting new forum  details in the database
                        //dbRef.push().setValue(forum);
                        dbRef.child(String.valueOf(maxid+1)).setValue(forum);
                        //Feedback through the toast message
                        Toast.makeText(getApplicationContext(), "Forum added successfully..", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                }catch(NumberFormatException e){


                }




            }
        });




    }


    //nevigate button
    @Override
    protected void onResume() {
        super.onResume();
        btnback= (Button) findViewById(R.id.btn_back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(com.example.mad.addForum.this, viewUpdateTeacher.class) ;
                startActivity(intent);
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text =adapterView.getItemAtPosition(i).toString();
       // Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}