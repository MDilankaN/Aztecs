package com.example.mad;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewUpdateTeacher extends AppCompatActivity {

    DatabaseReference ref;
    ArrayList<ClassroomView> list;
    RecyclerView recyclerView;
    SearchView searchView;
    Button btn_classroom_retrieve_name;
    Button btn_classroom_retrieve_code;
    AdupterClass mRepositoryAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_update_teacher);
        ref = FirebaseDatabase.getInstance().getReference().child("Classroom");
        recyclerView =findViewById(R.id.rv);
        searchView =findViewById(R.id.searchView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ref!=null){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        list=new ArrayList<>();
                        for(DataSnapshot ds: snapshot.getChildren()){

                            list.add(ds.getValue(ClassroomView.class));

                        }

                        AdupterClass adupterClass =new AdupterClass(list);
                        recyclerView.setAdapter(adupterClass);



                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Toast.makeText(viewUpdateTeacher.this,error.getMessage(),Toast.LENGTH_SHORT).show();


                }
            });



        }

        if(searchView!=null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });


        }





    }

    private void search(String str){
        ArrayList<ClassroomView> myList=new ArrayList<>();
        for(ClassroomView object : list){
            if(object.getCode().toString().trim().toLowerCase().contains(str.toLowerCase())){
                myList.add(object);
            }

         /*   if(object.getName().toString().trim().toLowerCase().contains(str.toLowerCase())){
                myList.add(object);
            }*/


        }

        AdupterClass adupterClass=new AdupterClass(myList);
        recyclerView.setAdapter(adupterClass);


    }




}