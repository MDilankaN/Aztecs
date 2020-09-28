package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcomePage extends AppCompatActivity {

    private Button btnSend ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        onButtonClick();

    }
    public void onButtonClick(){
        btnSend = (Button) findViewById(R.id.btnstrt);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });
    }
    public void openActivity3(){
        Intent intx = new Intent(this, LoginPage.class);
        startActivity(intx);
    }
}