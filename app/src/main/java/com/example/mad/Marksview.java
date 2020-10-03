package com.example.mad;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Marksview extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.markview);

        Intent data = getIntent();
        Bundle bundle = data.getExtras();
        int Arraysize = bundle.getInt("ArraySize");
        int TotalMark = bundle.getInt("TotalMarks");
        int Marks[] = bundle.getIntArray("Marks");




            System.out.println(Arraysize);
            System.out.println(TotalMark);
            System.out.println(Marks[0]);
            System.out.println(Marks[1]);
            System.out.println(Marks[2]);
            System.out.println(Marks[3]);

    }
}