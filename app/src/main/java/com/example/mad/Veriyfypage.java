package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Veriyfypage extends AppCompatActivity {

    private Button check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verificode);
        onConButtonClick();
    }

    public void onConButtonClick(){
        check = (Button) findViewById(R.id.btnmcon);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity4();
            }
        });
    }
    public void openActivity4(){
        Intent intx = new Intent(this, ResetPwdPage.class);
        startActivity(intx);
    }
}