package com.example.mad;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerAdapter_QuizList extends RecyclerView.Adapter<RecyclerAdapter_QuizList.ViewHolder> {
    ArrayList<QuizList> quizLists;
    Context quizzes;
    ArrayList<String> QNAME;
    String q;
    String Class;
    //get parameters
    public RecyclerAdapter_QuizList(Context quizzes, ArrayList<QuizList> quizLists, ArrayList<String> QNAME, String Class){
        this.quizLists = quizLists;
        this.quizzes = quizzes;
        this.QNAME = QNAME;
        this.Class = Class;
    }
    @NonNull
    @Override//for set card layout
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quizzes_rowview,parent,false);
        return  new ViewHolder(view);
    }

    @Override//set values in the row
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.QuizName.setText(quizLists.get(position).getQuizName());

    }

    @Override//get number of rows
    public int getItemCount() {
        return quizLists.size();
    }
    //handling component which in the card layout
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView QuizName;
        ImageView Edit,Delete;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            //get component's ids
            QuizName = itemView.findViewById(R.id.quizName);
            Edit = itemView.findViewById(R.id.QuizEdit);
            Delete = itemView.findViewById(R.id.QuizDelete);

            //for go to see student results
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    q = QNAME.get(getAdapterPosition());
                    Intent intent = new Intent(quizzes, Student_results.class);
                    intent.putExtra("Extar",q);
                    intent.putExtra("Class",Class);
                    quizzes.startActivity(intent);
                }
            });
            //for go to update Quiz details
           Edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    q = QNAME.get(getAdapterPosition());
                    Intent intent = new Intent(quizzes, Edit_quizzes.class);
                    intent.putExtra("Extar",q);
                    intent.putExtra("Class",Class);
                    quizzes.startActivity(intent);
                }
            });
            //for go to delete Quizes
            Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    q = QNAME.get(getAdapterPosition());
                    //set path to delete Quizes
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("QuizzesDetails").child(Class).child(q);
                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference().child("MCQ").child(Class).child(q);
                    ref.removeValue();
                   ref2.removeValue();

                    Snackbar snackbar = Snackbar.make(view,"successfully deleted", Snackbar.LENGTH_SHORT);
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
