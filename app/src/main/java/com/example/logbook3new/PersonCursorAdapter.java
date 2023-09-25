package com.example.logbook3new;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class PersonCursorAdapter extends CursorAdapter {

    public PersonCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Create a new TextView to display the person name
        TextView textView = new TextView(context);
        return textView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Get the person name from the cursor
        @SuppressLint("Range") String personName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME_COLUMN));

        // Set the person name on the TextView
        TextView textView = (TextView) view;
        textView.setText(personName);
    }
}

