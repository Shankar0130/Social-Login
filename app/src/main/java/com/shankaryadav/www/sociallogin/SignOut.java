package com.shankaryadav.www.sociallogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SignOut extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    private List<TaskObject> tasklist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_sign_out);

        recyclerView = findViewById (R.id.rv);

        tasklist = new ArrayList<> ();
        initialiseRecyclerView();


            getTask();
    }

    private void getTask() {

         FirebaseDatabase myDatabase = FirebaseDatabase.getInstance ();
         myDatabase.setPersistenceEnabled (true);

        myDatabase.getReference ().child ("tasklist")
                .addValueEventListener (new ValueEventListener () {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String sub = "";
                        String top = "";

                        if (dataSnapshot.exists ()) {

                            for (DataSnapshot taskSnapshot : dataSnapshot.getChildren ()) {
                                if (taskSnapshot.exists ()) {

                                    if (taskSnapshot.child ("subject").getValue () != null){

                                        sub = taskSnapshot.child ("subject").getValue ().toString ().trim ();
                                    }

                                    if (taskSnapshot.child ("topic").getValue () != null){

                                        top = taskSnapshot.child ("topic").getValue ().toString ().trim ();
                                    }
                                    TaskObject taskObject = new TaskObject (sub,top);
                                    tasklist.add (taskObject);
                                }
                            }
                            adapter.notifyDataSetChanged ();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.i ("Error ----", "is ----" + databaseError.toException ().toString ());
                        Toast.makeText (SignOut.this, "----Error----", Toast.LENGTH_SHORT).show ();
                    }
                });

    }

    private void initialiseRecyclerView() {
        adapter = new RecyclerViewAdapter (getApplicationContext (),tasklist);
        recyclerView.setAdapter (adapter);
        recyclerView.setLayoutManager (new LinearLayoutManager (getApplicationContext (),LinearLayoutManager.VERTICAL,false));
    }

    public void signOut(View view) {
        AuthUI.getInstance ().signOut (this)
                .addOnCompleteListener (this, new OnCompleteListener<Void> () {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                   startActivity (new Intent (SignOut.this,LoginActivity.class));
                   finish ();
                    }
                });
    }

    public void gotoTaskActivity(View view) {

        startActivity (new Intent (SignOut.this,TaskActivity.class));
        finish ();
    }

    public void deleteTask(View view) {
        
    }
}
