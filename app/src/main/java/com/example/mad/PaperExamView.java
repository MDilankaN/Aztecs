package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.os.IResultReceiver;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PaperExamView extends AppCompatActivity {

    private DatabaseReference dref ;
    private Button btnm1 ;
    private LinearLayout listx;
    private ArrayList<Integer> correctAns;
    private CountDownTimer countDownTimer;
    private  int marks;

    private TextView question,title,paperid,counter,descr;
    RadioButton r1,r2,r3,r4;
    RadioGroup rbngroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paper_exam_view);
        //arraylist
        correctAns = new ArrayList<>();
        //recycle view
        listx = findViewById(R.id.mcqviewline);
        //getting data
        setquizHead();
        getQuizMcqs();

        onmbtnClick();




    }

    private void setquizHead() {
        //hooks
        paperid = findViewById(R.id.paperID);
        descr = findViewById(R.id.paperDes);
        counter = findViewById(R.id.countdown);
        dref = FirebaseDatabase.getInstance().getReference().child("QuizzesDetails").child("IT").child("Quiz 01");
        int time;

        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    Quiz_Details qd = new Quiz_Details();
                    qd.setQuizName((snapshot.child("quizName").getValue().toString()));
                    qd.setQuizTime(Integer.parseInt(snapshot.child("quizTime").getValue().toString()));
                    qd.setQuizDescription(snapshot.child("quizDescription").getValue().toString());

                    System.out.println(qd.getQuizTime() +" , "+qd.getQuizName());

                    paperid.setText(qd.getQuizName());
                    descr.setText(qd.getQuizDescription());

                    Long hours =    TimeUnit.MILLISECONDS.toMinutes(qd.getQuizTime());
                    Long minutes =  (TimeUnit.MILLISECONDS.toMinutes(qd.getQuizTime()) % TimeUnit.HOURS.toMinutes(1));
                    Long seconds =  (TimeUnit.MILLISECONDS.toSeconds(qd.getQuizTime()) % TimeUnit.MINUTES.toSeconds(1));

                    String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
                    System.out.println(timeLeftFormatted);
                    counter.setText(timeLeftFormatted);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
            });

    }

    private void getQuizMcqs() {

        dref = FirebaseDatabase.getInstance().getReference().child("MCQ").child("IT").child("Quiz 01");
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                int i = 0;
                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    //MCQ qs = new MCQ();
                    try{
                        i++;// question number counter
                        final View itemView = getLayoutInflater().inflate(R.layout.mcqviewx,null,false);//

                        //hooks
                        question = itemView.findViewById(R.id.txtqus);
                        title = itemView.findViewById(R.id.txtqn);
                        r1 = itemView.findViewById(R.id.Rbnm1);
                        r2 = itemView.findViewById(R.id.Rbnm2);
                        r3 = itemView.findViewById(R.id.Rbnm3);
                        r4 = itemView.findViewById(R.id.Rbnm4);

                        listx.addView(itemView);//adding to layout

                        //setting values
                        title.setText("Question No : "+ i);
                        System.out.println(i);
                        question.setText(snapshot.child("question").getValue().toString());
                        r1.setText(snapshot.child("ans1").getValue().toString());
                        r2.setText(snapshot.child("ans2").getValue().toString());
                        r3.setText(snapshot.child("ans3").getValue().toString());
                        r4.setText(snapshot.child("ans4").getValue().toString());
                        correctAns.add(Integer.parseInt(snapshot.child("correctAns").getValue().toString()));

                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    //submit btn click
    protected void onmbtnClick(){
        btnm1 = findViewById(R.id.submitbtn);
        btnm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMarksview();
                marks = chechQuiz();
            }


        });

    }
    public void openMarksview(){
        Intent intx = new Intent(this, Marksview.class);
        startActivity(intx);
    }

    public int chechQuiz() {
        int mcqAns = 0 ;
        int totalmarks = 0;
        for(int i = 0;i < listx.getChildCount();i++){
            View itemView = listx.getChildAt(i);
            r1 = itemView.findViewById(R.id.Rbnm1);
            r2 = itemView.findViewById(R.id.Rbnm2);
            r3 = itemView.findViewById(R.id.Rbnm3);
            r4 = itemView.findViewById(R.id.Rbnm4);
            r4 = findViewById(R.id.Rbnm4);

            if(r1.isChecked()){
                mcqAns = 1;
            }else if(r2.isChecked()){
                mcqAns = 2;
            }else if(r3.isChecked()){
                mcqAns = 3;
            }else if(r4.isChecked()){
                mcqAns = 4;
            }else {
                mcqAns = 0;
            }

            System.out.println(mcqAns);
            if(correctAns.get(i) == mcqAns){
                totalmarks++;
            }

        }
        System.out.println("total  "+totalmarks);
        return totalmarks;
    }
}
