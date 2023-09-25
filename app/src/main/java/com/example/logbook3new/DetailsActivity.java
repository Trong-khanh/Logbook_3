package com.example.logbook3new;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ListView listView = findViewById(R.id.infoListView);

        // Retrieve the list of persons from the database and create an adapter
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<Person> personList = dbHelper.getAllPersons(); // You need to implement getAllPersons()

        PersonAdapter adapter = new PersonAdapter(this, personList);
        listView.setAdapter(adapter);
    }
}

