package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {


    private EditText phonenumber;
    private EditText auth;
    private Button siginin;
    private TextView msg;
    private int btntype =0;

    private ProgressBar mainProgressbar;

    private FirebaseAuth mAuth;
    private  String mVerificationId;
    private  PhoneAuthProvider.ForceResendingToken mResendToken;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar input_notes_toolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(input_notes_toolbar);
        getSupportActionBar().setTitle("Journal it!");


        // Initializations
        mAuth = FirebaseAuth.getInstance();

        phonenumber=findViewById(R.id.sigininnumber);
        auth=findViewById(R.id.signinauth);
        siginin=findViewById(R.id.sigininbtn);
        mainProgressbar=findViewById(R.id.sigininprogressbar);
        msg = findViewById(R.id.siginmsg);



        siginin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btntype == 0) {
                    mainProgressbar.setVisibility(View.VISIBLE);
                    phonenumber.setEnabled(false);
                    siginin.setVisibility(View.INVISIBLE);
                    msg.setText("Please wait while we provide you with the verification code...");
                    String phnno = "+91"+ phonenumber.getText().toString();

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phnno,        // Phone number to verify
                            60,                 // Timeout duration
                            TimeUnit.SECONDS,   // Unit of timeout
                            LoginActivity.this,
                            mCallbacks // Activity (for callback binding)
                    );

                }else{
                    mainProgressbar.setVisibility(View.VISIBLE);
                    auth.setEnabled(false);
                    siginin.setVisibility(View.INVISIBLE);


                    String verificationcode = auth.getText().toString();
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, verificationcode);
                    signInWithPhoneAuthCredential(credential);

                }
            }
        });

        mCallbacks= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                signInWithPhoneAuthCredential(phoneAuthCredential);

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                msg.setText(e.getMessage());
                mainProgressbar.setEnabled(true);
                siginin.setEnabled(true);

            }


            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {


                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                btntype = 1;
                mainProgressbar.setVisibility(View.INVISIBLE);

                phonenumber.setVisibility(View.INVISIBLE);
                auth.setVisibility(View.VISIBLE);
                msg.setText("Please enter the verification code sent to your device...");
                siginin.setText("Verify");
                siginin.setEnabled(true);
                siginin.setVisibility(View.VISIBLE);




                // ...
            }


        };






    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                             FirebaseUser user = task.getResult().getUser();
                             Intent userIntent = new Intent(LoginActivity.this, SecondActivity.class);
                             startActivity(userIntent);
                             finish();

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                msg.setText(task.getException().getMessage());
                                mainProgressbar.setVisibility(View.INVISIBLE);

                                siginin.setEnabled(true);
                                siginin.setVisibility(View.VISIBLE);

                            }
                        }
                    }
                });
    }



}
