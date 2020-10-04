package com.example.mad;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_classroom);

        /*Calendar calender=Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calender.getTime());
        TextView textViewDate= findViewById(R.id.txtdate);
        textViewDate.setText(currentDate);
        */


    }
}