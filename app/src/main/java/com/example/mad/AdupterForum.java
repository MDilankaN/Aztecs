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

import java.io.SyncFailedException;
import java.util.ArrayList;

public class AdupterForum extends RecyclerView.Adapter<AdupterForum.MyViewHolder> {

    ArrayList<Forum> forumList;
    Context teacherviewforum;
    ArrayList<String> forum;
    ArrayList<String> stuname;
    String sessionID;


    public AdupterForum(forumviewTeacher forumviewTeacher, ArrayList<String> forum, String sessionID, ArrayList<Forum> forumList, ArrayList<String> stuName){

        this. forumList=forumList;
        this.teacherviewforum= forumviewTeacher;
        this.forum =  forum;
        this.sessionID = sessionID;
        this.stuname = stuName;

    }



    @NonNull
    @Override
    public AdupterForum.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_holder_forum,parent,false);
        return new AdupterForum.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdupterForum.MyViewHolder holder, int position) {
            holder.txtForum.setText(forumList.get(position).getMessage().toString());
        //System.out.println(forumList.get(position).toString());

    }

    @Override
    public int getItemCount() {

        return forumList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        private  TextView txtForum;
        private Button btnReply;
        private RecyclerView recyclerView;
        private  View view;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i =new Intent(view.getContext(), updateDeleteClsTeacher.class);
                     
                    view.getContext().startActivity(i);
                }
            });


            txtForum=itemView.findViewById(R.id.forum);
            btnReply=itemView.findViewById(R.id.btnreply);
            view=itemView;

            btnReply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i =new Intent(teacherviewforum,ReplyForum.class);
                    i.putExtra("name",stuname.get(getAdapterPosition()));
                    i.putExtra("msg",forum.get(getAdapterPosition()));
                    teacherviewforum.startActivity(i);

                }
            });

        }
    }
}
