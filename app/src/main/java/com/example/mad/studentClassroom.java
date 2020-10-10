package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    DatabaseReference ref,ref1,ref2;
    LinearLayout clzroom;
    Button clzname,code,navNews,navPro,idbt,join;
    String Name, TeachName,clzid,TecherName;
    EditText codefield,namefield;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_classroom);
        Intent intent = getIntent();
        Name = intent.getStringExtra("name");

        clzroom = findViewById(R.id.Classrooms);

       setScroll();
        btnEnrollClick();

    }

    private void btnEnrollClick() {
        join = findViewById(R.id.btnjoin);
        codefield = findViewById(R.id.codeField);
        namefield = findViewById(R.id.TechName);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clzid = codefield.getText().toString();
                TecherName = namefield.getText().toString();
                ref2 = FirebaseDatabase.getInstance().getReference().child("Classroom").child(TecherName);
                ref2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String clzName = snapshot.child("name").getValue().toString();
                        ///continue
                        /*
                        timer ----
                        Enrollment User Friendlyness  =need to copy or
                        forum reply

                         */
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
    }

    public void setScroll() {
        try {

            ref = FirebaseDatabase.getInstance().getReference().child("Student").child(Name);

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot ds: snapshot.getChildren()){
                        View Container =getLayoutInflater().inflate(R.layout.card_hoder,null,false);

                        clzname = Container.findViewById(R.id.clsName);
                        code = Container.findViewById(R.id.clsCode);

                        clzroom.addView(Container);


                        String cname =ds.child("name").getValue().toString();
                        clzname.setText(cname);
                        code.setText(ds.child("code").getValue().toString());
                        TeachName = ds.child("teacherID").getValue().toString();
                        //TeachName = "chamiya";

                        openclzroon(Container,cname);
                        ref1=FirebaseDatabase.getInstance().getReference().child("Classroom").child(Name);



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

    private void openclzroon(View container, final String clzname) {
        idbt = container.findViewById(R.id.clsName);
        idbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(studentClassroom.this,Paperviewstd.class);
                intent.putExtra("ClassName",clzname);
                intent.putExtra("name",Name);
                intent.putExtra("teacherID",TeachName);

               // System.out.println(clzname+" , "+Name);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Name = intent.getStringExtra("name");

        navNews = findViewById(R.id.btn_navi2);
        navPro = findViewById(R.id.btn_navi3);


        navNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(studentClassroom.this,Activity_Student_News.class);
                intent.putExtra("name",Name);
                startActivity(intent);
            }
        });

        navPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(studentClassroom.this,Student_profile.class);
                intent.putExtra("name",Name);
                startActivity(intent);
            }
        });

    }
}