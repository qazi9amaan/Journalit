package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShowNoteActivity extends AppCompatActivity {
    private EditText noteDesc;
    private FloatingActionButton updatebtn;
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
        if(bundle != null)
        {
            getSupportActionBar().setTitle(bundle.getString("note_title"));
            noteDesc.setText(bundle.getString("note_desc"));
            
        }
        
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShowNoteActivity.this, "Method under Construction", Toast.LENGTH_SHORT).show();
            }
        });



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
                Toast.makeText(ShowNoteActivity.this, "Method under Construction", Toast.LENGTH_SHORT).show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
