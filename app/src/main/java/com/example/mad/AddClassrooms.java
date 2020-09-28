package com.example.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddClassrooms extends AppCompatActivity {

    EditText txtcode, txtname, txtdescription;
    Button BtnCancel, btnCreate;
    DatabaseReference dbRef;
    Classroom cls;
    //long maxid=0;



    //Method to clear all user inputs
    private void clearControls(){
        txtcode.setText("");
        txtname.setText("");
        txtdescription.setText("");

    }

    //method to check classroom joining code that whether it is already exists or not



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_classroom);
        //Create classroom interface
        txtcode=findViewById(R.id.EtCode);
        txtname=findViewById(R.id.EtName);
        txtdescription=findViewById(R.id.EtDes);

        btnCreate=findViewById(R.id.btnCreate);
        BtnCancel=findViewById(R.id.btnCancel);

        //Classroom object
        cls=new Classroom();










        //method for create classroom
        btnCreate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                dbRef = FirebaseDatabase.getInstance().getReference().child("Classroom");
                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists());
                        //maxid=(dataSnapshot.getChildrenCount());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                try{
                    if(TextUtils.isEmpty(txtname.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Please Enter a classroom name", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(txtcode.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Please Enter a code", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(txtdescription.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Please Enter a description", Toast.LENGTH_SHORT).show();
                    }else{

                        //take user valid inputs
                        cls.setName(txtname.getText().toString().trim());
                        cls.setCode(Integer.parseInt(txtcode.getText().toString().trim()));
                        cls.setDescription(txtdescription.getText().toString().trim());

                        //inserting created classroom details in the database
                        dbRef.push().setValue(cls);
                        // dbRef.child(String.valueOf(maxid+1)).setValue(cls);

                        //Feedback through the toast message
                        Toast.makeText(getApplicationContext(), "Classroom created successfully", Toast.LENGTH_SHORT).show();
                        clearControls();

                    }

                }


                catch(java.lang.NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Invalid code syntax", Toast.LENGTH_SHORT).show();
                }



            }

        });




    }

    //navigate button


    @Override
    protected void onResume() {
        super.onResume();
        BtnCancel= (Button) findViewById(R.id.btnCancel);
        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddClassrooms.this,addForum.class) ;
                startActivity(intent);
            }
        });


    }
}