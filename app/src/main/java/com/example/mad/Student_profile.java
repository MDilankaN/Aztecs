package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Student_profile extends AppCompatActivity {

    String userName;
    EditText Password,Email;
    TextView Password1,Email1,NoClasses,NoForum,NoPapers;
    Button btnProflieupdate,BtnForum,Logout;
    int countClass,countForum;
    String uname;
    //NotificationCounter notificationCounter;//IT19804316

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        //find text and edit view
        Password = findViewById(R.id.editTextStudentName);
        Password1 = findViewById(R.id.NamePrintS);
        Email = findViewById(R.id.editTextEmailAddress);
        Email1 = findViewById(R.id.EmailPrintS);
        NoClasses = findViewById(R.id.Card1SubS);
        NoForum = findViewById(R.id.Card2SubS);
        Intent intent = getIntent();
        userName = intent.getStringExtra("name");
        Button navNews,navHome;
        final User user1 = new User();
        int count;

        //find button id
        btnProflieupdate = findViewById(R.id.btnProflieUpdate);

        //notification Counter - IT19804316
        //notificationCounter = new NotificationCounter(findViewById(R.id.notification));

        //hooks
        Logout = findViewById(R.id.btnLogout);
        //onclick Listener
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Student_profile.this, welcomePage.class);
                startActivity(intent);
                finish();
            }
        });

        //call the retrive function
        retriveData();

        //calculation data base
        DatabaseReference classref = FirebaseDatabase.getInstance().getReference().child("StuEnrollClasses").child(userName);
        classref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    countClass = (int) dataSnapshot.getChildrenCount();
                    NoClasses.setText(Integer.toString(countClass));

                }
                else
                {
                    NoClasses.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //calculation data base
       DatabaseReference forumref = FirebaseDatabase.getInstance().getReference().child("Forumcount").child(userName);
        classref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    countForum = (int) dataSnapshot.getChildrenCount();
                    NoForum.setText(Integer.toString(countClass));

                }
                else
                {
                    NoForum.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //update user details
        btnProflieupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //final DatabaseReference updDBref = FirebaseDatabase.getInstance().getReference().child("User");
                final DatabaseReference updDBref = FirebaseDatabase.getInstance().getReference();
                updDBref.child("User").child(userName).child("password").setValue(Password.getText().toString().trim());
                updDBref.child("User").child(userName).child("email").setValue(Email.getText().toString().trim());
                Snackbar.make(view, "Data update successfully", Snackbar.LENGTH_SHORT).show();
                clearControls();

                retriveData();

            }

            private void clearControls(){
                Password.setText("");
                Email.setText("");
            }
        });

        navHome = findViewById(R.id.btn_navi1);
        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Student_profile.this,studentClassroom.class);
                intent.putExtra("name",userName);
                startActivity(intent);
            }
        });
        navNews = findViewById(R.id.btn_navi2);
        navNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Student_profile.this,Activity_Student_News.class);
                intent.putExtra("name",userName);
                startActivity(intent);
            }
        });

        BtnForum = findViewById(R.id.btnForum);
        BtnForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Student_profile.this,replyview_student.class);
                intent.putExtra("name",userName);
                startActivity(intent);
            }
        });
    }



    //retrive the data from the data base
    private void retriveData() {
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