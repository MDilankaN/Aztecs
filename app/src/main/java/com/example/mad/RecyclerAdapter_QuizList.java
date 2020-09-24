package com.example.mad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter_QuizList extends RecyclerView.Adapter<RecyclerAdapter_QuizList.ViewHolder>{
    ArrayList<QuizList> quizLists;

    public RecyclerAdapter_QuizList(ArrayList<QuizList> quizLists){
        this.quizLists = quizLists;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quizzes_rowview,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.QuizName.setText(quizLists.get(position).getQuizName());
    }

    @Override
    public int getItemCount() {
        return quizLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView QuizName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            QuizName = itemView.findViewById(R.id.quizName);
        }
    }
}
