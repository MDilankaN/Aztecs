package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {
    private Button btnSend ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpwd);
        onButtonClick();
    }
    public void onButtonClick(){
        btnSend = (Button) findViewById(R.id.btnm3);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });
}
    public void openActivity3(){
        Intent intx = new Intent(this, MainActivity4.class);
        startActivity(intx);
    }
}