package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
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
    private  int marks,givenTime,noofquestions;
    private int Mark[];
    private String Quizname,clzname,username,TeacherName;
    private Bundle bundle = new Bundle();
    private Long time;

    private TextView question,title,paperid,counter,descr;
    RadioButton r1,r2,r3,r4;
    RadioGroup rbngroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paper_exam_view);
        //getting values
        Intent intx = getIntent();
        Quizname = intx.getStringExtra("QuizName");
        clzname = intx.getStringExtra("Class");
        username = intx.getStringExtra("userName");
        TeacherName = intx.getStringExtra("teacherID");

        //arraylist
        correctAns = new ArrayList<>();
        //LinerLayout view
        listx = findViewById(R.id.mcqviewline);
        View v = findViewById(android.R.id.content) ;//creating view for SnackBar

        //Functions
        setquizHead();
        getQuizMcqs(v);
        onmbtnClick();

    }

    private void setquizHead() {
        //hooks
        paperid = findViewById(R.id.paperID);
        descr = findViewById(R.id.paperDes);
        counter = findViewById(R.id.countdown);
        dref = FirebaseDatabase.getInstance().getReference().child("QuizzesDetails").child(clzname).child(Quizname);


        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            Quiz_Details qd = new Quiz_Details();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{

                    qd.setQuizName((snapshot.child("quizName").getValue().toString()));
                    qd.setQuizTime(Integer.parseInt(snapshot.child("quizTime").getValue().toString()));
                    qd.setQuizDescription(snapshot.child("quizDescription").getValue().toString());

                    //System.out.println(qd.getQuizTime() +" , "+qd.getQuizName());

                    paperid.setText(qd.getQuizName());
                    descr.setText(qd.getQuizDescription());


                }catch (Exception e){
                    e.printStackTrace();
                }

                givenTime = qd.getQuizTime();
                startTimer(givenTime);






            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
            });

    }

    private void getQuizMcqs(final View v) {

        dref = FirebaseDatabase.getInstance().getReference().child("MCQ").child(clzname).child(Quizname);
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                int i = 0;
                listx.removeAllViews();
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
                        Snackbar.make(v,"Error occurred", Snackbar.LENGTH_SHORT).show();
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
                marks = chechQuiz();
                openMarksview();
            }


        });

    }
    public void openMarksview(){
        Intent intx = new Intent(this, Marksview.class);

        bundle.putIntArray("Marks",Mark);
        bundle.putInt("TotalMarks",marks);
        bundle.putString("QuizNo",Quizname);
        bundle.putString("ClassName",clzname);
        bundle.putString("UserName",username);
        bundle.putString("TeacherName",TeacherName);
        bundle.putInt("noOfQuestions",noofquestions);
        intx.putExtras(bundle);
        startActivity(intx);
        finish();
    }

    public int chechQuiz() {
        Mark = new int[listx.getChildCount()];
        int mcqAns = 0,k ;
        int totalmarks = 0;
        noofquestions = listx.getChildCount();
        for(k = 0;k < listx.getChildCount();k++){
            View itemView = listx.getChildAt(k);
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
            if(correctAns.get(k) == mcqAns){
                totalmarks++;
                Mark[k] = 1;
            } else{
                Mark[k] = 0;
            }
        }
        System.out.println("total  "+totalmarks);
        for(int j =0; j<Mark.length;j++){
            System.out.println("Question "+j+" / "+Mark[j]);
        }

        MarkData md = new MarkData(username,totalmarks);
        addtoStudentDatabase(md);
        return totalmarks;
    }

    private void addtoStudentDatabase(MarkData md) {
        dref = FirebaseDatabase.getInstance().getReference().child("Quiz_Marks").child("IT").child(Quizname);
        dref.push().setValue(md);

    }

    private void startTimer(int noOfMinutes) {
        Long TimeDue = Long.valueOf(60 * noOfMinutes * 1000) ;
        CountDownTimer  countDownTimer = new CountDownTimer(TimeDue, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                //Convert milliseconds into hour,minute and seconds
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                counter.setText(hms);//set text
            }
            public void onFinish() {
                counter.setText("TIME'S UP!!"); //On finish change timer text
                marks = chechQuiz();
                openMarksview();

            }
        }.start();
}
}
