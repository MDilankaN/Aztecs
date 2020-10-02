package com.example.mad;

import android.os.Bundle;
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

public class Student_profile extends AppCompatActivity {

    String userName = "S2";
    EditText Username,Email;
    TextView Username1,Email1;
    NotificationCounter notificationCounter;//IT19804316

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        Username = findViewById(R.id.editTextStudentName);
        Username1 = findViewById(R.id.NamePrintS);
        Email = findViewById(R.id.editTextEmailAddress);
        Email1 = findViewById(R.id.EmailPrintS);

        //notification Counter - IT19804316
        notificationCounter = new NotificationCounter(findViewById(R.id.notification));

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("user").child(userName);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    Username.setText(dataSnapshot.child("username").getValue().toString());
                    Username1.setText(dataSnapshot.child("username").getValue().toString());
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