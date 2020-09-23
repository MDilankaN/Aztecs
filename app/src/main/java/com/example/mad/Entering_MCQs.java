package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Entering_MCQs extends AppCompatActivity implements View.OnClickListener {

    LinearLayout mcqLayout;
    Button add;
    EditText question, Ans1, Ans2, Ans3, Ans4, Ans5;
    Spinner correctAns;
    ImageView deleteMCQ;
    ArrayAdapter AD;
    List<Integer> correctAnsList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entering__m_c_qs);

        mcqLayout = findViewById(R.id.mcq_Layout);
        add = findViewById(R.id.mcq_add);
        add.setOnClickListener(this);

        correctAnsList.add(1);
        correctAnsList.add(2);
        correctAnsList.add(3);
        correctAnsList.add(4);
    }

    @Override
    public void onClick(View view) {
        addView();
    }

    private void addView() {
        final View mcqView = getLayoutInflater().inflate(R.layout.mcq_raowview,null,false);
        question = mcqView.findViewById(R.id.quetion);
        Ans1 = mcqView.findViewById(R.id.Ans1);
        Ans2 = mcqView.findViewById(R.id.Ans2);
        Ans3 = mcqView.findViewById(R.id.Ans3);
        Ans4 = mcqView.findViewById(R.id.Ans4);
        Ans2 = mcqView.findViewById(R.id.Ans2);
        correctAns = mcqView.findViewById(R.id.correctAns);
        deleteMCQ = mcqView.findViewById(R.id.delete_mcq);

        AD = new ArrayAdapter(this,android.R.layout.simple_spinner_item,correctAnsList);
        correctAns.setAdapter(AD);

        deleteMCQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView(mcqView);
            }
        });

        mcqLayout.addView(mcqView);
    }

    private void removeView(View view) {
        mcqLayout.removeView(view);
    }

}