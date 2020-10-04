package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class Marksview extends AppCompatActivity {

    TextView total,questionNo,questionMark,header;
    LinearLayout tblrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.markview);

        //hooks
        tblrow = findViewById(R.id.rowsoftbl);
        total = findViewById(R.id.TotalMark);
        header = findViewById(R.id.hedaertxt);
        View v = findViewById(android.R.id.content);

        try{
            //getting values from bundel
            Intent data = getIntent();
            Bundle bundle = data.getExtras();
            int TotalMark = bundle.getInt("TotalMarks");
            int Marks[] = bundle.getIntArray("Marks");
            String headerx = bundle.getString("QuizNo");

            header.setText(headerx+" Results");

            total.setText(""+TotalMark);//setting values

            for(int i = 0;i<Marks.length;i++){

                final View itemView1 = getLayoutInflater().inflate(R.layout.marktable_row,null,false);//

                //hooks
                questionNo = itemView1.findViewById(R.id.QuestionNo);
                questionMark = itemView1.findViewById(R.id.QuestionMark);

                tblrow.addView(itemView1);

                //setting Values
                questionNo.setText("Question No "+i);
                questionMark.setText(""+Marks[i]);
            }

        }catch (Exception e){
            Snackbar.make(v,"Error occurred",Snackbar.LENGTH_SHORT).show();
        }




    }
}