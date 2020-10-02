package com.example.mad;

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
        Button update,delete;
        String newsName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.txtReadNews);
            update = itemView.findViewById(R.id.btnUpdate1);
            delete = itemView.findViewById(R.id.btnDelete2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //add child for delete function
                    newsName = textView.getText().toString();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("News").child("-MIcMu662d5-WKfHT3rK");
                    ref.removeValue();
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
