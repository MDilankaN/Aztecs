package com.example.mad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    //Array
    ArrayList<News> newsLists;

    public RecyclerAdapter(ArrayList<News> newsLists){
        this.newsLists = newsLists;
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
        //Button btn1,btn2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.txtReadNews);
            //btn1 = itemView.findViewById(R.id.btnUpdate1);
            //btn2 = itemView.findViewById(R.id.btnDelete2);

        }
    }
}
