package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class new_teacher_profile extends AppCompatActivity {

    Button btn;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_teacher_profile);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn = (Button) findViewById(R.id.btn_navi2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new_teacher_profile.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }



}