package com.example.mad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapeter extends RecyclerView.Adapter<RecyclerAdapeter.ViewMCQHolder> {
    public static final String tag = "RecyclerView";
    private Context context;
    private ArrayList<MCQ> mcqx;

    public RecyclerAdapeter(Context context, ArrayList<MCQ> mcqx) {
        this.context = context;
        this.mcqx = mcqx;
    }

    @NonNull
    @Override
    public ViewMCQHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mcqviewx,parent,false);
        return new ViewMCQHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewMCQHolder holder, int position) {
        holder.question.setText(mcqx.get(position).getQuestion());
        holder.r1.setText(mcqx.get(position).getAns1());
        holder.r2.setText(mcqx.get(position).getAns2());
        holder.r3.setText(mcqx.get(position).getAns3());
        holder.r4.setText(mcqx.get(position).getAns4());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewMCQHolder extends RecyclerView.ViewHolder{

        TextView question;
        RadioButton r1,r2,r3,r4;

        public ViewMCQHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.txtqus);
            r1 = itemView.findViewById(R.id.Rbnm1);
            r2 = itemView.findViewById(R.id.Rbnm2);
            r3 = itemView.findViewById(R.id.Rbnm3);
            r4 = itemView.findViewById(R.id.Rbnm4);
        }
    }
}
