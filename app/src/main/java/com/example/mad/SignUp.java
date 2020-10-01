package com.example.mad;

import androidx.annotation.NonNull;
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

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class SignUp extends AppCompatActivity {

    Button button;
    EditText email, username, password;
    Spinner userType;
    ArrayAdapter AD;
    User user;
    Toast toast;
    DatabaseReference ref;
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
        ref = FirebaseDatabase.getInstance().getReference().child("User");
        OnclickButtonListener();
    }

    public void OnclickButtonListener() {
        button = findViewById(R.id.signupBtn);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        if(email.getText().toString().isEmpty() || username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                            Snackbar.make(view,"Please fill all fields",Snackbar.LENGTH_SHORT).show();
                        }else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                            username.setTextColor(Color.BLACK);
                            password.setTextColor(Color.BLACK);
                            email.setTextColor(Color.RED);
                            Snackbar.make(view,"Please enter your email correctly",Snackbar.LENGTH_SHORT).show();
                        }else if(username.getText().toString().length()<4){
                            password.setTextColor(Color.BLACK);
                            email.setTextColor(Color.BLACK);
                            username.setTextColor(Color.RED);
                            Snackbar.make(view,"Please enter at least four characters for the username",Snackbar.LENGTH_SHORT).show();
                        }else  if(password.getText().toString().length()<6){
                            email.setTextColor(Color.BLACK);
                            username.setTextColor(Color.BLACK);
                            password.setTextColor(Color.RED);
                            Snackbar.make(view,"Please enter at least six characters for the password",Snackbar.LENGTH_SHORT).show();
                        }else {

                            user.setEmail(email.getText().toString().trim());
                            user.setUsername(username.getText().toString().trim());
                            user.setPassword(password.getText().toString().trim());
                            user.setUserType(userList.get(userType.getSelectedItemPosition()));
                            ref.orderByChild("username").equalTo(user.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.getChildrenCount()>0){
                                        password.setTextColor(Color.BLACK);
                                        email.setTextColor(Color.BLACK);
                                        username.setTextColor(Color.RED);
                                        Snackbar.make(view,"Please fill all fields",Snackbar.LENGTH_SHORT).show();
                                        Toast.makeText(getApplicationContext(),"This username already exists",Toast.LENGTH_SHORT).show();
                                    }else {
                                        ref.push().setValue(user);
                                        Snackbar.make(view,"Please fill all fields",Snackbar.LENGTH_SHORT).show();
                                        toast.makeText(getApplicationContext(),"SIGN UP Successful",toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignUp.this, EmailVerification.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }


                    }
                }
        );
    }

}