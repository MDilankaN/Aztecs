package com.example.mad;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class NotificationCounter {
    private TextView notificationNumber;

    private final int maxNumber = 99;
    private int notificationNumberCounter = 1;

    public NotificationCounter (View view){
        notificationNumber = view.findViewById(R.id.txtnoticationNos);
    }

    public void increaseNumber(){
        notificationNumberCounter++;

        if(notificationNumberCounter > maxNumber){
            Log.d("Counter","Maximum Number Reached");
        }else{
            notificationNumber.setText(String.valueOf(notificationNumberCounter));
        }
    }

}
