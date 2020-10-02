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

public class RecyclerAdapterStudent extends RecyclerView.Adapter<RecyclerAdapterStudent.ViewHolder> {

    //Array
    ArrayList<News> StudentNewsLists;

    public RecyclerAdapterStudent(ArrayList<News> newsLists)
    {
        this.StudentNewsLists = newsLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.news_student_view_layout,parent,false);
        RecyclerAdapterStudent.ViewHolder viewHolder = new RecyclerAdapterStudent.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(StudentNewsLists.get(position).getNews());
    }

    @Override
    public int getItemCount() {
        return StudentNewsLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.txtNews1S);
        }


    }
}
