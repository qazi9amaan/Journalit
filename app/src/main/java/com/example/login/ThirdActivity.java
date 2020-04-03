package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.login.Helper.DatabaseHelper;
import com.example.login.Model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ThirdActivity extends AppCompatActivity {

    private EditText note_title;
    private EditText note_desc;
    private FloatingActionButton save_btn;
    DatabaseHelper myDB;



    FirebaseDatabase mdatabase ;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    String CurrentUserID;
    Date date;





    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Toolbar input_notes_toolbar = (Toolbar) findViewById(R.id.input_notes_toolbar);
        setSupportActionBar(input_notes_toolbar);
        getSupportActionBar().setTitle("Create a note now");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Initializations
        note_title = findViewById(R.id.input_note_title);
        note_desc= findViewById(R.id.input_note_desc);
        save_btn = findViewById(R.id.input_save_note_btn);
        myDB = new DatabaseHelper(this);


        mdatabase = FirebaseDatabase.getInstance();
        myRef= mdatabase.getReference("users");
        mAuth = FirebaseAuth.getInstance();
        CurrentUserID = mAuth.getUid();

        date = new Date(System.currentTimeMillis());


        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = note_title.getText().toString();
                String desc = note_desc.getText().toString();
                if(note_title.length()!= 0){
                    AddData(title,desc);
                    note_title.setText("");
                    note_desc.setText("");
                    toastMessage("Successfully added!");
                    gotoMain();
                }else{
                    toastMessage("Insert a title");
                }
            }
        });
    }

    private void AddData(String title,String desc) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm aa", Locale.ENGLISH);
        String c_date = dateFormat.format(date);

        String key = myRef.child(CurrentUserID).push().getKey();

//        Note note = new Note(title,desc,c_date);
        HashMap<Object,String> hashMap = new HashMap<>();
        hashMap.put("key",key);
        hashMap.put("title",title);
        hashMap.put("desc",desc);
        hashMap.put("date",c_date);


        myRef.child(CurrentUserID).child(key).setValue(hashMap);
        }

    private void gotoMain() {
        Intent intent=new Intent(ThirdActivity.this,SecondActivity.class);
        startActivity(intent);
    }



    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
