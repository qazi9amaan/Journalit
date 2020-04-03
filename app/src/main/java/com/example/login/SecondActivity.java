package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.login.Adapters.NoteListAdapter;
import com.example.login.Helper.DatabaseHelper;
import com.example.login.Model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SecondActivity extends AppCompatActivity   {
    FloatingActionButton addNote;

     RecyclerView recyclerview;

    FirebaseDatabase mdatabase ;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    String CurrentUserID;
    ArrayList<Note> noteList ;
    NoteListAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar input_notes_toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(input_notes_toolbar);
        getSupportActionBar().setTitle("Journal It!");
        
        // Initiliaztions
        addNote=findViewById(R.id.addnote);
        recyclerview = findViewById(R.id.listView);

        mAuth = FirebaseAuth.getInstance();
        CurrentUserID = mAuth.getUid();
        mdatabase = FirebaseDatabase.getInstance();
        myRef= mdatabase.getReference().child("users").child(CurrentUserID);

         noteList = new ArrayList<Note>();


        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newNote();
            }
        });
        recyclerview.setLayoutManager(new LinearLayoutManager(this));


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                    {
                        Note note = dataSnapshot1.getValue(Note.class);
                        noteList.add(note);

                    }
                    adapter = new NoteListAdapter(SecondActivity.this,noteList );
                    recyclerview.setAdapter(adapter);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//



        

        


//        recyclerview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });



    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if( currentUser == null){
            Intent signinintent = new Intent(SecondActivity.this, LoginActivity.class);
            finish();
        }

    }




    private void newNote()
    {
        Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainpagetoolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout_btn:
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(SecondActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    @Override
//    public void OnNoteClick(int position) {
//                        Note selectednote = noteList.get(position);
//
//        Intent intent=new Intent(SecondActivity.this,ShowNoteActivity.class);
//                intent.putExtra("note_title",selectednote.getTitle());
//                intent.putExtra("note_desc",selectednote.getDesc());
//                startActivity(intent);
//    }
}
