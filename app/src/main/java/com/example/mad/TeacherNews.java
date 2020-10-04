package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeacherNews extends AppCompatActivity {

    //initialize all the variables
    EditText  NewsDescription;
    //TextView News1;
    Button btnAddNewsK,btn_navi2;
    DatabaseReference dbRef;
    News news;
    //long maxId = 0;
    ArrayList<News> newsLists;

    //create recyclerView object
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_news);

        //assign insert values
        NewsDescription = (EditText) findViewById(R.id.MultitxtK);

        //assign button
        btnAddNewsK = (Button) findViewById(R.id.btnAddK);

        //create new News object
        news = new News();



        btnAddNewsK.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //create db connection
                dbRef = FirebaseDatabase.getInstance().getReference().child("News");
                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists());
                        //maxId =  (dataSnapshot.getChildrenCount());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                try{
                    if (TextUtils.isEmpty(NewsDescription.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Description", Toast.LENGTH_SHORT);
                    else{
                        //Take input from the user and assigning them to this instance (std) of the Student...
                        news.setNews(NewsDescription.getText().toString().trim());

                        //insert in to the Database..
                        //dbRef.child(String.valueOf(maxId+1)).setValue(news);
                        dbRef.push().setValue(news);
                        //dbRef.child("newsID").setValue(news);

                        //feedback to the user via a Toast
                        Toast.makeText(getApplicationContext(),"Data Saved Successfully",Toast.LENGTH_SHORT);
                        clearControls();
                    }
                }
                catch(Exception e){
                    Toast.makeText(getApplicationContext(),"Empty fields", Toast.LENGTH_SHORT);
                }
            }
        });

        //find recyclerView id
        recyclerView = findViewById(R.id.Recycleview1);
        dbRef = FirebaseDatabase.getInstance().getReference().child("News");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    newsLists = new ArrayList<>();
                    for(DataSnapshot ds : snapshot.getChildren()){
                        News N1 = new News();
                        newsLists.add(N1);
                        N1.setNews(ds.child("news").getValue().toString());
                    }
                    recyclerAdapter = new RecyclerAdapter(newsLists);
                    recyclerView.setAdapter(recyclerAdapter);
                }else{
                    Toast.makeText(TeacherNews.this,"can't find news class",Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });



        //dbRef = FirebaseDatabase.getInstance().getReference().child("News");
        //recyclerView = findViewById(R.id.Recycleview1);

        //private FirebaseRecyclerOptions<News> options;
        //private FirebaseRecyclerAdapter<News,MyViewHolder> adapter;
        //private

        //create db connection
        //dbRef = FirebaseDatabase.getInstance().getReference().child("News");
        //view id on recycler view

        /*recyclerView.setHasFixedSize(true);

        options =  new FirebaseRecyclerOptions.Builder<News>().setQuery(dbRef,News.class).build();
        adapter = new FirebaseRecyclerAdapter<News, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull News model) {
                holder.textViewNews.setText(model.getNews());
                String title = ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.txtReadNews)).getText().toString();
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_view_layout,parent,false);

                return new MyViewHolder(v);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);*/


        //calling Show news method in the interface
        /*btn_navi2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("News").child("1");
                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name =dataSnapshot.child("news").getValue().toString();
                        News1.setText(name);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });*/

    }

    /*@Override
    protected void onStart() {
        super.onStart();
        if(dbRef != null){
            dbRef = FirebaseDatabase.getInstance().getReference().child("News").child("MI4uAqQDbNL0bUZ_WuF");
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        newsLists = new ArrayList<>();
                        for(DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            newsLists.add(ds.getValue(News.class));
                        }
                        AdapterClass adapterClass = new AdapterClass(newsLists);
                        recyclerView.setAdapter(adapterClass);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(MainActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }*/

    //method to clear all the user inputs
    private void clearControls(){
        NewsDescription.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_navi2 =(Button)findViewById(R.id.btn_navi3);
        btn_navi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherNews.this, ActivityTeacherProfile.class);
                startActivity(intent);
            }
        });

    }
}
