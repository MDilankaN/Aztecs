package com.example.mad;

import android.content.Context;
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
    Context context;
    String name;
    ArrayList<String> codeList;
    ArrayList<String> nameList;

    private ListItemClickListener mOnClickListener;

    interface ListItemClickListener{
        void onListItemClick(int position);
    }

    public AdupterClass(ListItemClickListener onClickListener){
        this.mOnClickListener = onClickListener;

    }
    public AdupterClass(ArrayList<ClassroomView> myList){
        list =myList;
    }
    public AdupterClass(ArrayList<ClassroomView> list, viewUpdateTeacher viewUpdateTeacher, String name, ArrayList<String> codeList,ArrayList<String> nameList){

        this.list=list;
        this.name=name;
        this.codeList=codeList;
        this.nameList=nameList;
        context=viewUpdateTeacher;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_hoder,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.btnname.setText(list.get(position).getName().toString().trim());
        holder.code.setText(list.get(position).getCode().toString().trim());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        Button btnname;
        Button code;
        RecyclerView recyclerView;
        View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnname=itemView.findViewById(R.id.clsName);
            recyclerView=itemView.findViewById(R.id.rv);
            code=itemView.findViewById(R.id.clsCode);
            view = itemView;

            // testing
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i =new Intent(view.getContext(), updateDeleteClsTeacher.class);
                    i.putExtra("name",name);
                    i.putExtra("code", code.getText().toString().trim());
                    view.getContext().startActivity(i);
                }
            });

                code.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i =new Intent(context,updateDeleteClsTeacher.class);
                        i.putExtra("name",name);
                        i.putExtra("code",codeList.get(getAdapterPosition()));
                        context.startActivity(i);

                    }
                });

                btnname.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i =new Intent(context,Quizzes.class);

                        //i.putExtra("name",name);
                        i.putExtra("name",nameList.get(getAdapterPosition()));
                        context.startActivity(i);
                    }
                });



        }

    }



}
