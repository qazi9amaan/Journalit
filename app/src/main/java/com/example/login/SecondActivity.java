package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.login.Helper.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity {
    FloatingActionButton addNote;
    DatabaseHelper myDB;
    private ListView listView;


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar input_notes_toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(input_notes_toolbar);
        getSupportActionBar().setTitle("Journal It!");
        
        // Initiliaztions
        addNote=findViewById(R.id.addnote);
        myDB = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);
        
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newNote();
            }
        });
        
        populateList();
    }

    private void populateList() {
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        if(data.getCount() == 0){
            Toast.makeText(this, "No notes!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent=new Intent(SecondActivity.this,ShowNoteActivity.class);
                        String selectedItem = (String)parent.getItemAtPosition(position);
                        intent.putExtra("note_title",selectedItem);
                        startActivity(intent);



                    }
                });
                listView.setAdapter(listAdapter);

            }
        }
    }



    private void newNote()
    {
        Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
        startActivity(intent);
    }


}
