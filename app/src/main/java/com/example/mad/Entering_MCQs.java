package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Entering_MCQs extends AppCompatActivity {

    LinearLayout mcqLayout;
    Button add,submit;
    EditText question, Ans1, Ans2, Ans3, Ans4;
    Spinner correctAns;
    ImageView deleteMCQ;
    ArrayAdapter AD;
    MCQ mcq;
    TextView QNumber;
    List<Integer> correctAnsList = new ArrayList<Integer>();
    DatabaseReference ref;
    String Class,qname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entering__m_c_qs);

        mcqLayout = findViewById(R.id.mcq_Layout);
        Intent intent = getIntent();
        Class = intent.getStringExtra("Class");
        qname = intent.getStringExtra("qname");

        correctAnsList.add(1);
        correctAnsList.add(2);
        correctAnsList.add(3);
        correctAnsList.add(4);

        ref = FirebaseDatabase.getInstance().getReference().child("MCQ").child(Class).child(qname);
        OnclickButtonListener();
    }

    private void OnclickButtonListener() {
        add = findViewById(R.id.mcq_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView();
                McqNumberRecovery();
            }
        });
        submit = findViewById(R.id.mcqSubmitBT);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nullValidation(view)){
                    for(int i=0; i<mcqLayout.getChildCount(); i++) {

                        View mcqView = mcqLayout.getChildAt(i);

                        question = mcqView.findViewById(R.id.quetion);
                        Ans1 = mcqView.findViewById(R.id.Ans1);
                        Ans2 = mcqView.findViewById(R.id.Ans2);
                        Ans3 = mcqView.findViewById(R.id.Ans3);
                        Ans4 = mcqView.findViewById(R.id.Ans4);
                        correctAns = mcqView.findViewById(R.id.correctAns);

                        mcq = new MCQ();
                        mcq.setQuestion(question.getText().toString());
                        mcq.setAns1(Ans1.getText().toString());
                        mcq.setAns2(Ans2.getText().toString());
                        mcq.setAns3(Ans3.getText().toString());
                        mcq.setAns4(Ans4.getText().toString());
                        mcq.setCorrectAns(correctAnsList.get(correctAns.getSelectedItemPosition()));

                        int n = i+1;

                        ref.child("MCQ "+n).setValue(mcq);
                    }

                    Snackbar.make(view,"Quiz successfully created", Snackbar.LENGTH_SHORT).show();
                    Intent intent = new Intent(Entering_MCQs.this, Quizzes.class);
                   // intent.putExtra("Class",C3lass);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }



    private void addView() {
        final View mcqView = getLayoutInflater().inflate(R.layout.mcq_raowview,null,false);
        question = mcqView.findViewById(R.id.quetion);
        Ans1 = mcqView.findViewById(R.id.Ans1);
        Ans2 = mcqView.findViewById(R.id.Ans2);
        Ans3 = mcqView.findViewById(R.id.Ans3);
        Ans4 = mcqView.findViewById(R.id.Ans4);
        correctAns = mcqView.findViewById(R.id.correctAns);
        deleteMCQ = mcqView.findViewById(R.id.delete_mcq);

        AD = new ArrayAdapter(this,android.R.layout.simple_spinner_item,correctAnsList);
        correctAns.setAdapter(AD);

        deleteMCQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                removeView(mcqView);
                McqNumberRecovery();
            }
        });

        mcqLayout.addView(mcqView);
    }

    private void removeView(View view) {
        mcqLayout.removeView(view);
    }

    private boolean nullValidation(View view) {
        boolean value = true;
        if(mcqLayout.getChildCount() == 0){
            Snackbar.make(view,"Please first add MCQs", Snackbar.LENGTH_SHORT).show();
            value = false;
        }else {
          for(int i=0; i<mcqLayout.getChildCount(); i++){

                View mcqView = mcqLayout.getChildAt(i);

              question = mcqView.findViewById(R.id.quetion);
              Ans1 = mcqView.findViewById(R.id.Ans1);
              Ans2 = mcqView.findViewById(R.id.Ans2);
              Ans3 = mcqView.findViewById(R.id.Ans3);
              Ans4 = mcqView.findViewById(R.id.Ans4);


            if(question.getText().toString().isEmpty() | Ans1.getText().toString().isEmpty() | Ans2.getText().toString().isEmpty() | Ans3.getText().toString().isEmpty() | Ans4.getText().toString().isEmpty() ){
                value = false;
                Snackbar.make(view,"Please fill all empty fields", Snackbar.LENGTH_SHORT).show();
                break;
            }
          }
        }
        return value ;
    }

    private void McqNumberRecovery() {
        if(mcqLayout.getChildCount()>0){

            for(int i=0; i<mcqLayout.getChildCount();i++){
                    View mcqView = mcqLayout.getChildAt(i);
                    QNumber=mcqView.findViewById(R.id.QNumber);
                    int n = i + 1;
                    QNumber.setText("MCQ "+n);

            }
        }
    }
}



