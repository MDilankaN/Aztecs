package com.example.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.xml.namespace.QName;

public class Edit_quizzes extends AppCompatActivity {
    EditText QName,QTime,QDescription;
    DatabaseReference ref;
    Button update;
    Quiz_Details QD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quizzes);

        QName = findViewById(R.id.UpdateQname);
        QTime = findViewById(R.id.UpdateQtime);
        QDescription = findViewById(R.id.UpdateQdescription);

        Intent intent = getIntent();
        final String qname = intent.getStringExtra("Extar");

        ref = FirebaseDatabase.getInstance().getReference().child("QuizzesDetails").child("IT");
        ref.child(qname).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    QName.setText(snapshot.child("quizName").getValue().toString());
                    QTime.setText(snapshot.child("quizTime").getValue().toString());
                    QDescription.setText(snapshot.child("quizDescription").getValue().toString());

                }else{
                    Toast.makeText(getApplicationContext(),"can't find "+qname,Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        OnButtonClickListener();
    }

    private void OnButtonClickListener() {
        update = findViewById(R.id.Update_BT);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                try {
                    if (QName.getText().toString().isEmpty() | QTime.getText().toString().trim().isEmpty() | QDescription.getText().toString().isEmpty()) {
                        Snackbar.make(view,"Please fill all fields",Snackbar.LENGTH_SHORT).show();
                    } else {
                                    QD = new Quiz_Details();
                                    QD.setQuizName(QName.getText().toString());
                                    QD.setQuizTime(Integer.parseInt(QTime.getText().toString().trim()));
                                    QD.setQuizDescription(QDescription.getText().toString());
                            ref.orderByChild("quizName").equalTo(QD.getQuizName()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.hasChildren()){
                                        ref.child(QD.getQuizName()).setValue(QD);
                                        Snackbar.make(view,"Update Successful",Snackbar.LENGTH_SHORT).show();

                                    }else{
                                        Snackbar.make(view,"quiz name does not exist",Snackbar.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                    }
                }catch (NumberFormatException e){
                    Snackbar.make(view,"Invalid Time Duration",Snackbar.LENGTH_SHORT).show();

                }

            }
        });
    }




}