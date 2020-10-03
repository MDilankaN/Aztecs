package com.example.mad;

import android.os.Bundle;
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

public class Student_profile extends AppCompatActivity {

    String userName = "S2";
    EditText Password,Email;
    TextView Password1,Email1;
    Button btnProflieupdate;
    NotificationCounter notificationCounter;//IT19804316

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        //find text and edit view
        Password = findViewById(R.id.editTextStudentName);
        Password1 = findViewById(R.id.NamePrintS);
        Email = findViewById(R.id.editTextEmailAddress);
        Email1 = findViewById(R.id.EmailPrintS);

        //find button id
        btnProflieupdate = findViewById(R.id.btnProflieUpdate);

        //notification Counter - IT19804316
        notificationCounter = new NotificationCounter(findViewById(R.id.notification));

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("user").child(userName);
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

        btnProflieupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //final DatabaseReference updDBref = FirebaseDatabase.getInstance().getReference().child("User");
                final DatabaseReference updDBref = FirebaseDatabase.getInstance().getReference();
                updDBref.child("User").child("-MII2LaVlw57IDD3Brqg").child("password").setValue(Password.getText().toString().trim());
                updDBref.child("User").child("-MII2LaVlw57IDD3Brqg").child("email").setValue(Email.getText().toString().trim());
                Toast.makeText(getApplicationContext(), "Data update successfully", Toast.LENGTH_SHORT).show();
                clearControls();

            }

            private void clearControls(){
                Password.setText("");
                Email.setText("");
            }
        });
    }

}