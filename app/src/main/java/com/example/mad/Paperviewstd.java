package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Paperviewstd extends AppCompatActivity {

    private Button btnp1 ;
    private Button btnm1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paperviewstd);
        onpbtnClick();
        onmbtnClick();
    }

    protected void onpbtnClick(){
        btnp1 = (Button) findViewById(R.id.btnp1);
        btnp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPaperview();
            }
        });
    }
    protected void onmbtnClick(){
        btnm1 = (Button) findViewById(R.id.btnm1);
        btnm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMarksview();
            }
        });

    }
    public void openPaperview(){
       Intent intx = new Intent(this, PaperExamView.class);
       startActivity(intx);
    }

    public void openMarksview(){
        Intent intx = new Intent(this, Marksview.class);
        startActivity(intx);
    }
}