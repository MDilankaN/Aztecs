package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginPage extends AppCompatActivity {


    SessionManager s = new SessionManager();
    String uname,pwd;
    private TextView txv;
    private Button logbtn,signbtn;
    private EditText unedtxt,pwdtxt;

    DatabaseReference dref ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //hooks
        signbtn =(Button) findViewById(R.id.btnm1);
        ontextClick();
        onloginbtnclick();

    }

    //login btn click
    private void onloginbtnclick() {
        //hooks
        logbtn =(Button) findViewById(R.id.btnm2);
        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(view);
            }
        });
    }

    //Forgot password Txt field click
    public void ontextClick(){
        //hooks
        txv = (TextView) findViewById(R.id.txtfpwm);
        txv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();//calling open activity2 function
            }
        });
    }

    // open activity2 function
    public void openActivity2(){
        Intent intx = new Intent(this, Forgotpwd.class);
        startActivity(intx);
    }

    //check & validates user inputs
    public void validate(final View view){
        //hooks
        unedtxt  =(EditText) findViewById(R.id.unminput);
        pwdtxt  =(EditText) findViewById(R.id.pwdminput);

        try{
            if(TextUtils.isEmpty(unedtxt.getText())){
                Snackbar.make(view,"Username Field id empty", Snackbar.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(pwdtxt.getText())){
                Snackbar.make(view,"Password Field id empty", Snackbar.LENGTH_SHORT).show();
            }else {
                uname = unedtxt.getText().toString();
                pwd = pwdtxt.getText().toString();
                dref = FirebaseDatabase.getInstance().getReference().child("User").child(uname);
//                dref.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if(snapshot.hasChildren()){
//                            //if data was found
//                            s.setUname(snapshot.child("username").getValue().toString());
//                            s.setPwd(snapshot.child("password").getValue().toString());
//                            s.setE_mail(snapshot.child("email").getValue().toString());
//                            s.setType(snapshot.child("userType").getValue().toString());
//                        }else{
//                            //if data not found
//                            Snackbar.make(view,"Invalid User", Snackbar.LENGTH_SHORT).show();
//                        }
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                    }
//                });

                s.setUname("Akash");
                s.setPwd("pwd");
                s.setType("Teacher");

                //check the user inputs and database values are equal
                if(s.getPwd().equals(pwd)){
                    if(s.getType().equals("teacher") ||s.getType().equals("Teacher")){
                        Snackbar.make(view,"Logging n", Snackbar.LENGTH_SHORT).show();
                        //Teacher's Homepage
                        onteacherVali();

                    }else if(s.getType().equals("student") ||s.getType().equals("Student")){
                        Snackbar.make(view,"Logging in", Snackbar.LENGTH_SHORT).show();
                        //Students's Homepage
                        onstudentVali();
                    }
                }else{
                    Snackbar.make(view,"Invalid password", Snackbar.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void onstudentVali() {
        Intent intx = new Intent(this, studentClassroom.class);
        startActivity(intx);
    }

    private void onteacherVali() {
        Intent intx = new Intent(this, teacherHome.class);
        startActivity(intx);
    }

    //Students's Homepage
    //Teacher's Homepage
    //sign in page
}


