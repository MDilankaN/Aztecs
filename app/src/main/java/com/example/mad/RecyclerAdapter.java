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

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    //Array
    ArrayList<News> newsLists;
    Context teacherNews;
    ArrayList<String> news;
    String name;

    public RecyclerAdapter(ArrayList<News> newsLists, TeacherNews teacherNews, ArrayList<String> news, String name){
        this.newsLists = newsLists;
        this.teacherNews = teacherNews;
        this.news = news;
        this.name = name;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.news_view_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view );
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(newsLists.get(position).getNews());
    }

    @Override
    public int getItemCount()
    {
        return newsLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        Button update,delete;
        String newsName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.txtReadNews);
            update = itemView.findViewById(R.id.btnUpdate1);
            delete = itemView.findViewById(R.id.btnDelete2);

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String News = news.get(getAdapterPosition());
                    Intent intent = new Intent(teacherNews,EditNews.class);
                    intent.putExtra("id",News);
                    intent.putExtra("name",name);
                    teacherNews.startActivity(intent);


                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //add child for delete function
                    String News = news.get(getAdapterPosition());
                    DatabaseReference ref2 , ref;
                    ref = FirebaseDatabase.getInstance().getReference().child("News").child(name).child(News);
                    ref2 = FirebaseDatabase.getInstance().getReference().child("StuNews").child(News);

                    ref.removeValue();
                    ref2.removeValue();
                    Snackbar snackbar = Snackbar.make(view,"successfully deleted",Snackbar.LENGTH_SHORT);
                    snackbar.setAction("OKAY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
                    snackbar.show();
                }
            });

        }
    }
}
