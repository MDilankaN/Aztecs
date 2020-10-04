package com.example.mad;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdupterForum extends RecyclerView.Adapter<AdupterForum.MyViewHolder> {

    ArrayList<Forum> forumList;


    public AdupterForum(ArrayList<Forum> forumList){

        this. forumList=forumList;


    }

    @NonNull
    @Override
    public AdupterForum.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_holder_forum,parent,false);
        return new AdupterForum.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdupterForum.MyViewHolder holder, int position) {
            holder.txtForum.setText(forumList.get(position).getMessage().toString().trim());

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
        }
    }
}
