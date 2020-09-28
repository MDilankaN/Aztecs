package com.example.mad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdupterClass extends RecyclerView.Adapter<AdupterClass.MyViewHolder>{

    ArrayList<ClassroomView> list;
    public AdupterClass(ArrayList<ClassroomView> list){
        this.list=list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_hoder,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.clsName.setText(list.get(position).getName());
        holder.btnAdd.setText("ADD");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView clsName;
        Button btnAdd;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            clsName=itemView.findViewById(R.id.clsName);
            btnAdd=itemView.findViewById(R.id.btnadd);

        }
    }


}
