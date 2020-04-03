package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.login.Helper.ExampleDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowNoteActivity extends AppCompatActivity implements ExampleDialog.ExampleDialogListener  {
    private EditText noteDesc;
    private FloatingActionButton updatebtn;
    FirebaseDatabase mdatabase ;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    String CurrentUserID;
    private  String currentNoteKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);
        Toolbar input_notes_toolbar = (Toolbar) findViewById(R.id.edit_notes_toolbar);
        setSupportActionBar(input_notes_toolbar);
        getSupportActionBar().setTitle("Edit your note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    
        // Initializations
        noteDesc = findViewById(R.id.edit_note_desc);
        updatebtn= findViewById(R.id.edit_save_note_btn);
        Bundle bundle = getIntent().getExtras();

        mdatabase = FirebaseDatabase.getInstance();
        myRef= mdatabase.getReference("users");
        mAuth = FirebaseAuth.getInstance();
        CurrentUserID = mAuth.getUid();


        currentNoteKey = bundle.getString("note_key");

        if(bundle != null)
        {
            getSupportActionBar().setTitle(bundle.getString("note_title"));
            noteDesc.setText(bundle.getString("note_desc"));

        }
        
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateUser(noteDesc.getText().toString());
                gotoMain();
            }
        });



    }


    public void updateUser(String desc) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(desc)) {
            myRef.child(CurrentUserID).child(currentNoteKey).child("desc").setValue(desc);
            Toast.makeText(this, "Successfully updated ", Toast.LENGTH_SHORT).show();
        } else
        {
            Toast.makeText(this, "Unable to update user", Toast.LENGTH_SHORT).show();
        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.update_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.delete_note:
                myRef.child(CurrentUserID).child(currentNoteKey).removeValue();
                Toast.makeText(this, "Successfully deleted!", Toast.LENGTH_SHORT).show();
                gotoMain();
                return true;
            case R.id.edit_title:
                updatetitle();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void updatetitle() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }



    private void gotoMain() {
        Intent intent=new Intent(ShowNoteActivity.this,SecondActivity.class);
        startActivity(intent);
    }



    @Override
    public void applyTexts(String title) {
        getSupportActionBar().setTitle(title);
        myRef.child(CurrentUserID).child(currentNoteKey).child("title").setValue(title);
        Toast.makeText(this, "Successfully updated ", Toast.LENGTH_SHORT).show();

    }
}
