package com.shankaryadav.www.sociallogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class TaskActivity extends AppCompatActivity {
   private EditText subject;
  private  EditText mName;
   FirebaseDatabase database = FirebaseDatabase.getInstance ();
   DatabaseReference myRef = database.getReference ();
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_task);

        subject = (EditText) findViewById (R.id.subject);
        mName = (EditText) findViewById (R.id.topic);
    }

    public void addTask(View view) {

        String mySubject = subject.getText ().toString ();
        String myTopic = mName.getText ().toString ();

        Map myMap  = new HashMap ();

         myMap.put ("subject",mySubject);
         myMap.put ("topic",myTopic);

        myRef.child ("tasklist").push ().setValue (myMap);

        startActivity (new Intent (TaskActivity.this,SignOut.class));
        finish ();

    }
}
