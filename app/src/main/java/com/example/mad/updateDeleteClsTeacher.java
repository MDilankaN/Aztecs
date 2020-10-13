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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updateDeleteClsTeacher extends AppCompatActivity {

    private Button show,update,delete;
    DatabaseReference dbRef;
    private EditText txtname,txtdes,txtDate;
    private TextView txtcode;
   Classroom cls=new Classroom();
    String codename,username;


    //Method to clear all user inputs
    private void clearControls(){
        txtcode.setText("");
        txtname.setText("");
        txtdes.setText("");
        txtDate.setText("");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_cls_teacher);


        update=findViewById(R.id.btnUpdate);
        delete=findViewById(R.id.btnDelete);
        txtname=findViewById(R.id.EtName);
        txtcode=findViewById(R.id.EtCode);
        txtdes=findViewById(R.id.EtDes);
        txtDate=findViewById(R.id.txtdate);



        Intent i =getIntent();
        username = i.getStringExtra("name");
        codename = i.getStringExtra("code");

        dbRef = FirebaseDatabase.getInstance().getReference().child("Classroom").child(username).child(codename);

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    txtname.setText(snapshot.child("name").getValue().toString());
                    txtcode.setText(snapshot.child("code").getValue().toString());
                    txtdes.setText(snapshot.child("description").getValue().toString());
                    txtDate.setText(snapshot.child("date").getValue().toString());

                }
                else {

                    Toast.makeText(getApplicationContext(),"No source to display",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //update button
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef= FirebaseDatabase.getInstance().getReference().child("Classroom").child(username).child(codename);
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChildren()){
                            try{
                            cls.setName(txtname.getText().toString().trim());
                            cls.setCode(Integer.parseInt(txtcode.getText().toString().trim()));
                            cls.setDescription(txtdes.getText().toString().trim());
                            cls.setDate(txtDate.getText().toString().trim());
                            //write data to database reference
                            dbRef.setValue(cls);

                            clearControls();
                            Toast.makeText(getApplicationContext(),"Data updated successfully",Toast.LENGTH_SHORT).show();

                            }


                            catch (Exception e){

                            }

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"No source to update",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        //delete button
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef= FirebaseDatabase.getInstance().getReference().child("Classroom").child(username).child(codename);
                dbRef.addListenerForSingleValueEvent(new ValueEventListener(){


                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChildren()){
                            dbRef= FirebaseDatabase.getInstance().getReference().child("Classroom").child(username).child(codename);
                            dbRef.removeValue();
                            clearControls();
                            Toast.makeText(getApplicationContext(),"Classroom deleted successfully",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"No source to delete",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        });









    }
}