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

import com.example.login.Adapters.NoteListAdapter;
import com.example.login.Helper.DatabaseHelper;
import com.example.login.Model.Note;
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

        ArrayList<Note> noteList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        if(data.getCount() == 0){
            Toast.makeText(this, "No notes!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                Note note = new Note(data.getString(1),data.getString(2));
                noteList.add(note);

                // Need to create personalised adapter
                NoteListAdapter adapter = new NoteListAdapter(this,R.layout.list_view_items,noteList);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Note selectednote = (Note)parent.getItemAtPosition(position);
                        Intent intent=new Intent(SecondActivity.this,ShowNoteActivity.class);
                        intent.putExtra("note_title",selectednote.getTitle());
                        intent.putExtra("note_desc",selectednote.getDesc());
                        startActivity(intent);
                        }
                });


                listView.setAdapter(adapter);
            }
        }
    }



    private void newNote()
    {
        Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
        startActivity(intent);
    }


}
