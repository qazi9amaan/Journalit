package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SecondActivity extends AppCompatActivity {
    FloatingActionButton addNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        addNote=findViewById(R.id.addnote);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newNote();
            }
        });
    }

    private void newNote()
    {
        Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
        startActivity(intent);
    }


}
