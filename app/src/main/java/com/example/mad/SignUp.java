package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity {

    Button button;
    EditText email, username, password;
    Spinner userType;
    ArrayAdapter AD;
    User user;
    Toast toast;

    List<String> userList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userType = findViewById(R.id.UserType);
        email = findViewById(R.id.ETEmail);
        username = findViewById(R.id.ETUsername);
        password = findViewById(R.id.ETPassword);

        userList.add("Teacher");
        userList.add("Student");

        AD = new ArrayAdapter(this,android.R.layout.simple_spinner_item,userList);
        userType.setAdapter(AD);

        user = new User();

        OnclickButtonListener();
    }

    public void OnclickButtonListener() {
        button = findViewById(R.id.signupBtn);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(email.getText().toString().isEmpty() || username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                          toast.makeText(getApplicationContext(),"Please fill all fields",toast.LENGTH_SHORT).show();
                        }else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                            username.setTextColor(Color.BLACK);
                            password.setTextColor(Color.BLACK);
                            email.setTextColor(Color.RED);
                            toast.makeText(getApplicationContext(),"Please enter your email correctly",toast.LENGTH_SHORT).show();
                        }else if(username.getText().toString().length()<4){
                            password.setTextColor(Color.BLACK);
                            email.setTextColor(Color.BLACK);
                            username.setTextColor(Color.RED);
                            toast.makeText(getApplicationContext(),"Please enter at least four characters for the username ",toast.LENGTH_SHORT).show();
                        }else  if(password.getText().toString().length()<6){
                            email.setTextColor(Color.BLACK);
                            username.setTextColor(Color.BLACK);
                            password.setTextColor(Color.RED);
                            toast.makeText(getApplicationContext(),"Please enter at least six characters for the password",toast.LENGTH_SHORT).show();
                        }else {
                            user.setEmail(email.getText().toString().trim());
                            user.setUsername(username.getText().toString().trim());
                            user.setPassword(password.getText().toString().trim());
                            user.setUserType(userList.get(userType.getSelectedItemPosition()));

                            toast.makeText(getApplicationContext(),"SIGN UP Successful",toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this, EmailVerification.class);
                            startActivity(intent);
                        }


                    }
                }
        );
    }

}