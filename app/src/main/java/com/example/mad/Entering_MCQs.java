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

    LinearLayout mcqLayout;//for add card View
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
        //get extra msg
        Intent intent = getIntent();
        Class = intent.getStringExtra("Class");
        qname = intent.getStringExtra("qname");

        //for add numbers inside Spinner
        correctAnsList.add(1);
        correctAnsList.add(2);
        correctAnsList.add(3);
        correctAnsList.add(4);

        //set firebase path for add mcqs
        ref = FirebaseDatabase.getInstance().getReference().child("MCQ").child(Class).child(qname);
        OnclickButtonListener();
    }

    private void OnclickButtonListener() {
        //get button add id
        add = findViewById(R.id.mcq_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView();//for add card view in inside a linearlayout
                McqNumberRecovery();//for set correct order to mcq numbers
            }
        });
        submit = findViewById(R.id.mcqSubmitBT);//submit the quiz
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nullValidation(view)){
                    for(int i=0; i<mcqLayout.getChildCount(); i++) {//for get one view by one in the linearlayout

                        View mcqView = mcqLayout.getChildAt(i);//get the view
                        //get relevant vies's edit texts id
                        question = mcqView.findViewById(R.id.quetion);
                        Ans1 = mcqView.findViewById(R.id.Ans1);
                        Ans2 = mcqView.findViewById(R.id.Ans2);
                        Ans3 = mcqView.findViewById(R.id.Ans3);
                        Ans4 = mcqView.findViewById(R.id.Ans4);
                        correctAns = mcqView.findViewById(R.id.correctAns);

                        // set that mcq details in the MCQ java class
                        mcq = new MCQ();
                        mcq.setQuestion(question.getText().toString());
                        mcq.setAns1(Ans1.getText().toString());
                        mcq.setAns2(Ans2.getText().toString());
                        mcq.setAns3(Ans3.getText().toString());
                        mcq.setAns4(Ans4.getText().toString());
                        mcq.setCorrectAns(correctAnsList.get(correctAns.getSelectedItemPosition()));

                        int n = i+1;//get mcq count for create child name

                        ref.child("MCQ "+n).setValue(mcq);
                    }
                    //notify msg
                    Snackbar.make(view,"Quiz successfully created", Snackbar.LENGTH_SHORT).show();

                    Intent intent = new Intent(Entering_MCQs.this, Quizzes.class);//for go to quiz home
                    intent.putExtra("name",Class);//send extra msg
                    startActivity(intent);
                    finish();//to not able to return this activity
                }
            }
        });

    }

    //for add card view in inside a linearlayout
    private void addView() {
        //get mcq card view
        final View mcqView = getLayoutInflater().inflate(R.layout.mcq_raowview,null,false);
        //set edit texts ids and button id from the mcq card view
        question = mcqView.findViewById(R.id.quetion);
        Ans1 = mcqView.findViewById(R.id.Ans1);
        Ans2 = mcqView.findViewById(R.id.Ans2);
        Ans3 = mcqView.findViewById(R.id.Ans3);
        Ans4 = mcqView.findViewById(R.id.Ans4);
        correctAns = mcqView.findViewById(R.id.correctAns);
        deleteMCQ = mcqView.findViewById(R.id.delete_mcq);

        AD = new ArrayAdapter(this,android.R.layout.simple_spinner_item,correctAnsList);//create array adapter for set correct answer list
        correctAns.setAdapter(AD);//

        //for delete mcq card view
        deleteMCQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                removeView(mcqView);
                McqNumberRecovery();
            }
        });

        mcqLayout.addView(mcqView);//add card view  inside in a linearlayout
    }

    private void removeView(View view) {
        mcqLayout.removeView(view);//add card view  inside in a linearlayout
    }

    //for check empty fields are there
    private boolean nullValidation(View view) {
        boolean value = true;
        if(mcqLayout.getChildCount() == 0){//check there has only one mcq card view
            Snackbar.make(view,"Please first add MCQs", Snackbar.LENGTH_SHORT).show();
            value = false;
        }else {
          for(int i=0; i<mcqLayout.getChildCount(); i++){//check one mcq view by one

                View mcqView = mcqLayout.getChildAt(i);

              question = mcqView.findViewById(R.id.quetion);
              Ans1 = mcqView.findViewById(R.id.Ans1);
              Ans2 = mcqView.findViewById(R.id.Ans2);
              Ans3 = mcqView.findViewById(R.id.Ans3);
              Ans4 = mcqView.findViewById(R.id.Ans4);

              //check there has any empty fields
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

            for(int i=0; i<mcqLayout.getChildCount();i++){//set mcq number in ascending order
                    View mcqView = mcqLayout.getChildAt(i);
                    QNumber=mcqView.findViewById(R.id.QNumber);
                    int n = i + 1;
                    QNumber.setText("MCQ "+n);

            }
        }
    }
}



