package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=2000;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mAuth = FirebaseAuth.getInstance();


    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if( currentUser == null){
            boolean b = new Handler().postDelayed(new Runnable() {
                                                      @Override
                                                      public void run() {
                                                          Intent homeintent = new Intent(SplashScreen.this,LoginActivity.class);
                                                          startActivity(homeintent);
                                                          finish();
                                                      }
                                                  }, SPLASH_TIME_OUT
            );
        }else{
            boolean b = new Handler().postDelayed(new Runnable() {
                                                      @Override
                                                      public void run() {
                                                          Intent homeintent = new Intent(SplashScreen.this,SecondActivity.class);
                                                          startActivity(homeintent);
                                                          finish();
                                                      }
                                                  }, SPLASH_TIME_OUT
            );
        }

    }


}
