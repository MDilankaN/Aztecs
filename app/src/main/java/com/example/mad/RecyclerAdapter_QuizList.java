package com.example.mad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter_QuizList extends RecyclerView.Adapter<RecyclerAdapter_QuizList.ViewHolder>{

    ArrayList<QuizNameList> quizNameLists;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView QuizName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            QuizName = itemView.findViewById(R.id.quizName);
        }
    }
}
