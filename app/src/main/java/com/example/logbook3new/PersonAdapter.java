package com.example.logbook3new;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {
    public PersonAdapter(Context context, List<Person> personList) {
        super(context, 0, personList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Person person = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_person, parent, false);
        }

        // Lookup views within item layout
        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewDob = convertView.findViewById(R.id.textViewDob);
        TextView textViewEmail = convertView.findViewById(R.id.textViewEmail);

        // Populate the data into the template view using the data object
        imageView.setImageResource(person.getImageResourceId());
        textViewName.setText("Name: " + person.getName());
        textViewDob.setText("DOB: " + person.getDob());
        textViewEmail.setText("Email: " + person.getEmail());

        return convertView;
    }
}

