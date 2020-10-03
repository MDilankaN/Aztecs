package com.example.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Pass_Student extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference ref;
    ArrayList<Result> resultsList;
    RecyclerAdapter_ResultList recyclerAdapter;
    TextView percentage;
    int n=0;
    ArrayList<String> a;
    double AllResult;
    private  static DecimalFormat df = new DecimalFormat("0.00");
    ImageView backbt;
    String Class,qname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass__student);
        percentage = findViewById(R.id.PassResult);
        recyclerView = findViewById(R.id.passRecyclerview);
        Intent intent = getIntent();
        AllResult = intent.getIntExtra("AllResult",0);
        int mark = intent.getIntExtra("mark",0);
        Class = intent.getStringExtra("Class");
        qname = intent.getStringExtra("qname");

        ref = FirebaseDatabase.getInstance().getReference().child("Result").child(Class).child(qname);
        ref.orderByChild("result").startAt(mark).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    resultsList = new ArrayList<>();

                    for(DataSnapshot DS : snapshot.getChildren()){
                        Result R= new Result();
                        resultsList.add(R);
                        R.setSName(DS.child("name").getValue().toString());
                        R.setSResult(Integer.parseInt((DS.child("result").getValue().toString())));
                        n++;
                    }
                             recyclerAdapter = new RecyclerAdapter_ResultList(resultsList);
                             recyclerView.setAdapter(recyclerAdapter);
                             double result = calculation(AllResult,n);
                             percentage.setText(df.format(result)+"%");

                }else{
                    Toast.makeText(getApplicationContext(),"can't find any child",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();
            }
        });

        backbt = findViewById(R.id.BackBT);
        backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });
    }

    public double calculation(double allResult, int n) {

            double result =(n/allResult*100);
            return result;
    }
}