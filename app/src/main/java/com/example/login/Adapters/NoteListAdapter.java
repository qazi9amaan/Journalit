package com.example.login.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.login.Model.Note;
import com.example.login.R;

import java.util.ArrayList;
import java.util.List;

public class NoteListAdapter extends ArrayAdapter<Note> {
    private Context mContext;
    int mResource;

    public NoteListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Note> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       // Information about note
        String title = getItem(position).getTitle();
        String desc = getItem(position).getDesc();

        // creating object with this info

        Note note = new Note(title,desc);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView text_title = convertView.findViewById(R.id.view_note_title);
        TextView text_desc = convertView.findViewById(R.id.view_note_desc);
        text_title.setText(title);
        text_desc.setText(desc);

        return convertView;
    }
}
