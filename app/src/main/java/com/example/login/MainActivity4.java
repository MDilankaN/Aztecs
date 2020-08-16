package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity4 extends AppCompatActivity {

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
        Intent intx = new Intent(this, MainActivity5.class);
        startActivity(intx);
    }
}