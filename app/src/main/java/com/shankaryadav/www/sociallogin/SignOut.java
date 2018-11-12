package com.shankaryadav.www.sociallogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SignOut extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_sign_out);

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
}
