package com.example.mad;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdupterReply extends RecyclerView.Adapter<AdupterReply.MyViewHolder> {
    ArrayList<StudentForum> replyList;
    Context replyview_student;
    String username;

    public AdupterReply(replyview_student replyview_student, String username, ArrayList<StudentForum> replyList) {
        this.replyList = replyList;
        this.replyview_student= replyview_student;
        this.username = username;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.replyview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.quection.setText(replyList.get(position).getQuection().toString());
        holder.Reply.setText(replyList.get(position).getReply().toString());
    }

    @Override
    public int getItemCount() {
        return replyList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView quection,Reply;
        RecyclerView recyclerView;
        View view;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //identify elements in XML
            quection=itemView.findViewById(R.id.forumreply);
            Reply=itemView.findViewById(R.id.reply);

            view=itemView;

        }
    }

}
