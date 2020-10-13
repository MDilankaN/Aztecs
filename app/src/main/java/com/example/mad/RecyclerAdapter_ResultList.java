package com.example.mad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter_ResultList extends RecyclerView.Adapter<RecyclerAdapter_ResultList.ViewHolder> {
    ArrayList<Result> resultsList;
    ////get parameters
    public RecyclerAdapter_ResultList (ArrayList<Result> resultsList){
        this.resultsList = resultsList;
    }
    @NonNull
    @Override//for set card layout
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_rowview,parent,false);
        return   new ViewHolder(view);
    }

    @Override//set values in the row
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.sname.setText(resultsList.get(position).getSName());
        holder.sresult.setText(Integer.toString(resultsList.get(position).getSResult()));
    }

    @Override//get number of rows
    public int getItemCount() {
        return resultsList.size();
    }
    //handling component which in the card layout
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView sname,sresult;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //get component's ids
            sname = itemView.findViewById(R.id.SName);
            sresult = itemView.findViewById(R.id.SResult);
        }
    }


}
