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

public class ActivityTeacherProfile extends AppCompatActivity {

    String userName = "T1";
    EditText Username,Email;
    TextView Username1,Email1;
    Button btnProflieupdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);

        Username = findViewById(R.id.editTextTeacherName);
        Username1 = findViewById(R.id.NamePrintT);
        Email = findViewById(R.id.editTeacherEmailAddress);
        Email1 = findViewById(R.id.EmailPrintT);
        btnProflieupdate = findViewById(R.id.btnProflieUpdate);

        final User user1 = new User();

        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("user").child(userName);
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

        btnProflieupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference updDBref = FirebaseDatabase.getInstance().getReference().child("user");
                updDBref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(userName) ){

                            user1.setUsername(Username.getText().toString().trim());
                            user1.setEmail(Email.getText().toString().trim());

                            //insert data to data base
                            // = FirebaseDatabase.getInstance().getReference().child("user").child(userName);


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}