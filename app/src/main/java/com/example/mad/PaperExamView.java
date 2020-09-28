package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PaperExamView extends AppCompatActivity {

    private Button btnm1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paper_exam_view);
        onmbtnClick();
    }

    protected void onmbtnClick(){
        btnm1 = (Button) findViewById(R.id.Rbnm1);
        btnm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMarksview();
            }
        });

    }
    public void openMarksview(){
        Intent intx = new Intent(this, Marksview.class);
        startActivity(intx);
    }
}