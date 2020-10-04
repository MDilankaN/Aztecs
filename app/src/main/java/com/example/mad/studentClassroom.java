package com.example.mad;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class studentClassroom extends AppCompatActivity {

    DatabaseReference ref;
    LinearLayout clzroom;
    Button clzname,code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_classroom);

        clzroom = findViewById(R.id.Classrooms);

       setScroll();

    }

    public void setScroll() {
        try {
            ref = FirebaseDatabase.getInstance().getReference().child("Classroom");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot ds: snapshot.getChildren()){
                        View Container =getLayoutInflater().inflate(R.layout.card_hoder,null,false);

                        clzname = Container.findViewById(R.id.clsName);
                        code = Container.findViewById(R.id.clsCode);

                        clzroom.addView(Container);

                        clzname.setText(ds.child("name").getValue().toString());
                        code.setText(ds.child("code").getValue().toString());

                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Toast.makeText(studentClassroom.this,error.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}