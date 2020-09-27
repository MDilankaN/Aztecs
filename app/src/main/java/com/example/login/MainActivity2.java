package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity2 extends AppCompatActivity {


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
                validate();
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
        Intent intx = new Intent(this, MainActivity3.class);
        startActivity(intx);
    }

    //check & validates user inputs
    public void validate(){
        //hooks
        unedtxt  =(EditText) findViewById(R.id.unminput);
        pwdtxt  =(EditText) findViewById(R.id.pwdminput);

        try{
            if(TextUtils.isEmpty(unedtxt.getText())){
                Toast.makeText(getApplicationContext(),"Username Field id empty",Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(pwdtxt.getText())){
                Toast.makeText(getApplicationContext(),"Password Field id empty",Toast.LENGTH_SHORT).show();
            }else {
                uname = unedtxt.getText().toString();
                pwd = pwdtxt.getText().toString();
                dref = FirebaseDatabase.getInstance().getReference().child("User").child(uname);
                dref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChildren()){
                            //if data was found
                            s.setUname(snapshot.child("username").getValue().toString());
                            s.setPwd(snapshot.child("password").getValue().toString());
                            s.setE_mail(snapshot.child("email").getValue().toString());
                            s.setType(snapshot.child("userType").getValue().toString());
                        }else{
                            //if data not found
                            Toast.makeText(getApplicationContext(),"Invalid User",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

                //check the user inputs and database values are equal
                if(s.getPwd().equals(pwd)){
                    if(s.getType().equals("teacher")){
                        Toast.makeText(getApplicationContext(),"Loging in",Toast.LENGTH_SHORT).show();
                        //Teacher's Homepage
                    }else{
                        Toast.makeText(getApplicationContext(),"Loging in",Toast.LENGTH_SHORT).show();
                        //Students's Homepage
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Invalid password",Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Students's Homepage
    //Teacher's Homepage
    //sign in page
}


