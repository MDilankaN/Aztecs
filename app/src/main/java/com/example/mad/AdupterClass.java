package com.example.mad;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdupterClass extends RecyclerView.Adapter<AdupterClass.MyViewHolder>{

    ArrayList<ClassroomView> list;

    private ListItemClickListener mOnClickListener;

    interface ListItemClickListener{
        void onListItemClick(int position);
    }

    public AdupterClass(ListItemClickListener onClickListener){
        this.mOnClickListener = onClickListener;
    }

    public AdupterClass(ArrayList<ClassroomView> list){

        this.list=list;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_hoder,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(list.get(position).getName().toString().trim());
        holder.code.setText(list.get(position).getCode().toString().trim());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        Button name;
        Button code;
        RecyclerView recyclerView;
        View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //today testing
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i =new Intent(view.getContext(), updateDeleteClsTeacher.class);
                   i.putExtra("code", code.getText().toString().trim());
                    view.getContext().startActivity(i);
                }
            });



            name=itemView.findViewById(R.id.clsName);
            recyclerView=itemView.findViewById(R.id.rv);
            code=itemView.findViewById(R.id.clsCode);
            view = itemView;


        }


    }



}
