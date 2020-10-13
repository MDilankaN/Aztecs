package com.example.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class EditNews extends AppCompatActivity {
    EditText updateet;
    Button update;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_news);
        updateet = findViewById(R.id.updateET);
        update = findViewById(R.id.updateBT);

        Intent intent = getIntent();
       String id =  intent.getStringExtra("id");
       String name =  intent.getStringExtra("name");
       //System.out.println(id + "" +name);

        ref = FirebaseDatabase.getInstance().getReference().child("News").child(name).child(id);

        //update data
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ref.child("news").setValue(updateet.getText().toString().trim());
                        Snackbar.make(view,"Successful...!!!",Snackbar.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Snackbar.make(view,"Error...!!!",Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}