package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Forgotpwd extends AppCompatActivity {
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
        Intent intx = new Intent(this, Veriyfypage.class);
        startActivity(intx);
    }
}