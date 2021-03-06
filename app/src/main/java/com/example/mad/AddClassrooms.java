package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddClassrooms extends AppCompatActivity {

    EditText txtcode, txtname, txtdescription;
    Button BtnCancel, btnCreate;
    TextView textViewDate;
    DatabaseReference dbRef,dbRef2;
    Classroom cls;
    private SimpleDateFormat dateformat;
    private String date;
    private String sessionID;
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
        sessionID=getIntent().getStringExtra("name");

        txtcode=findViewById(R.id.EtCode);
        txtname=findViewById(R.id.EtName);
        txtdescription=findViewById(R.id.EtDes);

        btnCreate=findViewById(R.id.btnCreate);
        BtnCancel=findViewById(R.id.btnCancel);

        Calendar calender=Calendar.getInstance();
        dateformat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateformat.format(calender.getTime());
        textViewDate = findViewById(R.id.txtdate);
        textViewDate.setText(date);


        //Classroom object
        cls=new Classroom();

        //method for create classroom
        btnCreate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                dbRef = FirebaseDatabase.getInstance().getReference().child("Classroom").child(sessionID);
                dbRef2 = FirebaseDatabase.getInstance().getReference().child("AvailableClz");

                // validate if there are empty fields . user must enter all fields

                try{
                    if(TextUtils.isEmpty(txtname.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Please Enter a classroom name", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(txtcode.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Please Enter a code", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(txtdescription.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Please Enter a description", Toast.LENGTH_SHORT).show();
                    }else{

                        //take user valid inputs
                        cls.setTeacherID(sessionID.toString().trim());
                        cls.setName(txtname.getText().toString().trim());
                        cls.setCode(Integer.parseInt(txtcode.getText().toString().trim()));
                        cls.setDescription(txtdescription.getText().toString().trim());

                        cls.setDate(date);

                        //inserting created classroom details in the database
                        dbRef.child(txtcode.getText().toString().trim()).setValue(cls);
                        dbRef2.child(txtcode.getText().toString().trim()).setValue(cls);
//                        dbRef.push().setValue(cls);
                        // dbRef.child(String.valueOf(maxid+1)).setValue(cls);

                        //Feedback through the toast message
                        Toast.makeText(getApplicationContext(), "Classroom created successfully", Toast.LENGTH_SHORT).show();
                        clearControls();

                    }

                }


                catch(NumberFormatException e){
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
                Intent intent=new Intent(AddClassrooms.this, addForum.class) ;
                startActivity(intent);
            }
        });


    }
}