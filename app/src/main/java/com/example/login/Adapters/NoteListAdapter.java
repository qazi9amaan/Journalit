package com.example.login.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Model.Note;
import com.example.login.R;
import com.example.login.ShowNoteActivity;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.MyViewHolder> {

    Context context;
    ArrayList<Note> notes;





    public NoteListAdapter(Context c, ArrayList<Note> n  )
    {
       this.context=c;
        this.notes=n;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_view_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Note mnote = notes.get(position);
        holder.text_title.setText(notes.get(position).getTitle());
        holder.text_desc.setText(notes.get(position).getDesc());
        holder.text_date.setText(notes.get(position).getDate());
        holder.notecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ShowNoteActivity.class);
                intent.putExtra("note_title",mnote.getTitle());
                intent.putExtra("note_desc",mnote.getDesc());
                intent.putExtra("note_date",mnote.getDate());
                intent.putExtra("note_key",mnote.getKey());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder   {

        TextView text_title ;
        TextView text_desc ;
        TextView text_date ;
        CardView notecard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

             text_title = itemView.findViewById(R.id.view_note_title);
             text_desc = itemView.findViewById(R.id.view_note_desc);
             text_date = itemView.findViewById(R.id.view_note_birthday);
             notecard = itemView.findViewById(R.id.carditem);

        }


    }




}


//    TextView text_title = convertView.findViewById(R.id.view_note_title);
//    TextView text_desc = convertView.findViewById(R.id.view_note_desc);
//        text_title.setText(title);
//                text_desc.setText(desc);