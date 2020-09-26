package com.example.mad;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerAdapter_QuizList extends RecyclerView.Adapter<RecyclerAdapter_QuizList.ViewHolder> {
    ArrayList<QuizList> quizLists;
    Context quizzes;
    DatabaseReference ref1,ref2;
    public RecyclerAdapter_QuizList(Context quizzes, ArrayList<QuizList> quizLists){
        this.quizLists = quizLists;
        this.quizzes = quizzes;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quizzes_rowview,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.QuizName.setText(quizLists.get(position).getQuizName());
    }

    @Override
    public int getItemCount() {
        return quizLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView QuizName;
        ImageView Edit,Delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            QuizName = itemView.findViewById(R.id.quizName);
            Edit = itemView.findViewById(R.id.QuizEdit);
            Delete = itemView.findViewById(R.id.QuizDelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(quizzes,Student_results.class);
                    quizzes.startActivity(intent);
                }
            });

            Edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(quizzes,Edit_quizzes.class);
                    quizzes.startActivity(intent);
                }
            });

            Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String QName = QuizName.getText().toString();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("ClassQuize").child("maths").child("q4");
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
