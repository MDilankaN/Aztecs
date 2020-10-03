package com.example.mad;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
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

import java.util.HashMap;

public class ActivityTeacherProfile extends AppCompatActivity {

    String userName;
    EditText Password,Email;
    TextView Password1,Email1;
    Button btnProflieupdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);

        Password = findViewById(R.id.editTextTeacherName);
        Password1 = findViewById(R.id.NamePrintT);
        Email = findViewById(R.id.editTeacherEmailAddress);
        Email1 = findViewById(R.id.EmailPrintT);
        btnProflieupdate = findViewById(R.id.btnProflieUpdate);
        Intent intent = getIntent();
         userName = intent.getStringExtra("name");

        final User user1 = new User();


        retriveData();


        btnProflieupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //final DatabaseReference updDBref = FirebaseDatabase.getInstance().getReference().child("User");
                final DatabaseReference updDBref = FirebaseDatabase.getInstance().getReference();
                updDBref.child("User").child(userName).child("password").setValue(Password.getText().toString().trim());
                updDBref.child("User").child(userName).child("email").setValue(Email.getText().toString().trim());
                Toast.makeText(getApplicationContext(), "Data update successfully", Toast.LENGTH_SHORT).show();
                clearControls();
                retriveData();

            }


               /* updDBref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(userName) ){

                            user1.setPassword(Password.getText().toString().trim());
                            user1.setEmail(Email.getText().toString().trim());


                            try{
                                //insert data to data base
                                DatabaseReference DBrefInsert = FirebaseDatabase.getInstance().getReference().child("User").child(userName);

                                if(!Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString()).matches()) {
                                    Email.setTextColor(Color.RED);
                                    Toast.makeText(getApplicationContext(), "Please enter your email correctly", Toast.LENGTH_SHORT).show();
                                }
                                else  if(Password.getText().toString().length()<6){
                                    Password.setTextColor(Color.RED);
                                    Toast.makeText(getApplicationContext(),"Please enter at least six characters for the password",Toast.LENGTH_SHORT).show();
                                }

                                user1.setPassword(Password.getText().toString().trim());
                                user1.setEmail(Email.getText().toString().trim());

                                DBrefInsert.setValue(user1);

                                clearControls();

                                Toast.makeText(getApplicationContext(),"Data update successfully",Toast.LENGTH_SHORT).show();

                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"Exception error",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Invalid User",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }*/


    private void clearControls(){
        Password.setText("");
        Email.setText("");
    }
        });
    }

    private void retriveData() {
        //Retrive data from database
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("User").child(userName);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    Password.setText(dataSnapshot.child("password").getValue().toString());
                    Password1.setText(dataSnapshot.child("password").getValue().toString());
                    Email.setText(dataSnapshot.child("email").getValue().toString());
                    Email1.setText(dataSnapshot.child("email").getValue().toString());
                }
                else
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}