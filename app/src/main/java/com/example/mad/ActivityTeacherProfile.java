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


    EditText Password,Email;
    TextView Password1,Email1,NoClasses,NoPapers;
    Button btnProflieupdate;
    int countClass,countPaper;
    Button navClassHome,navNews,BtnAdd,Logout;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String uname = bundle.getString("name");
        name = uname;

        Password = findViewById(R.id.editTextTeacherName);
        Password1 = findViewById(R.id.NamePrintT);
        Email = findViewById(R.id.editTeacherEmailAddress);
        Email1 = findViewById(R.id.EmailPrintT);
        NoClasses = findViewById(R.id.Card1SubS);
        NoPapers = findViewById(R.id.Card3SubS);
        btnProflieupdate = findViewById(R.id.btnProflieUpdate);

        //name = intent.getStringExtra("name");

        final User user1 = new User();

        retriveData();


        DatabaseReference classref = FirebaseDatabase.getInstance().getReference().child("Classroom").child(name);
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

        //hooks
        Logout = findViewById(R.id.btnLogout);
        //onclick Listener
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityTeacherProfile.this, welcomePage.class);
                startActivity(intent);
                finish();
            }
        });

        Classroom cr = new Classroom();

        DatabaseReference paperRef = FirebaseDatabase.getInstance().getReference().child("QuizzesDetails").child("IT");
        paperRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    countPaper = (int) dataSnapshot.getChildrenCount();
                    NoPapers.setText(Integer.toString(countPaper));

                }
                else
                {
                    NoPapers.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btnProflieupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //final DatabaseReference updDBref = FirebaseDatabase.getInstance().getReference().child("User");
                final DatabaseReference updDBref = FirebaseDatabase.getInstance().getReference();
                updDBref.child("User").child(name).child("password").setValue(Password.getText().toString().trim());
                updDBref.child("User").child(name).child("email").setValue(Email.getText().toString().trim());
                Toast.makeText(getApplicationContext(), "Data update successfully", Toast.LENGTH_SHORT).show();
                clearControls();
                retriveData();

            }


    private void clearControls(){
        Password.setText("");
        Email.setText("");
    }
        });

    }


    private void retriveData() {
        //Retrive data from database
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("User").child(name);
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


    @Override
    protected void onResume() {
        super.onResume();
        navClassHome = findViewById(R.id.btn_navi1);
        navNews = findViewById(R.id.btn_navi2);

        //navigation for teacher news
        navClassHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(ActivityTeacherProfile.this, teacherHome.class);
                intent.putExtra("name",name);
                startActivity(intent);

            }
        });

        //navigation for teacher profile
        navNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ActivityTeacherProfile.this, TeacherNews.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });



    }




}