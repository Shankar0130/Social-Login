package com.shankaryadav.www.sociallogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        // Firebase instance
        mAuth = FirebaseAuth.getInstance ();
        
        if (mAuth.getCurrentUser () !=  null){
            startActivity (new Intent (LoginActivity.this,SignOut.class));
            Toast.makeText (this, "user is signed in", Toast.LENGTH_SHORT).show ();
        }else{
            Toast.makeText (this, "user is not signed in please click on the button", Toast.LENGTH_SHORT).show ();
        }
    }

    private void signin() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.GoogleBuilder().build(),
                                new AuthUI.IdpConfig.FacebookBuilder().build(),
                                new AuthUI.IdpConfig.TwitterBuilder().build(),
                                new AuthUI.IdpConfig.EmailBuilder().build(),
                                new AuthUI.IdpConfig.PhoneBuilder().build(),
                                new AuthUI.IdpConfig.AnonymousBuilder().build()))
                        .build(),
                RC_SIGN_IN);
    }

    public void login(View view) {
        signin();
    }
}
