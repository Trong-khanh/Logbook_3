package com.example.logbook3new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button saveDetailsButton = findViewById(R.id.saveDetailBtn);

        saveDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
            }
        });
    }

    private void saveDetails() {
        // Creates an object of our helper class
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Get references to the EditText views and read their content
        EditText nameTxt = findViewById(R.id.nameInput);
        EditText dobTxt = findViewById(R.id.dobInput);
        EditText emailTxt = findViewById(R.id.emailInput);
        RadioGroup radioGroupImages = findViewById(R.id.radioGroupImages);



        // get the input values
        String name = nameTxt.getText().toString();
        String dob = dobTxt.getText().toString();
        String email = emailTxt.getText().toString();
        int selectedImgId = radioGroupImages.getCheckedRadioButtonId();
        int imageResourceId = R.drawable.ic_launcher_foreground; // default image

        if (name.isEmpty() || dob.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedImgId == R.id.radioButton1) {
            imageResourceId = R.drawable.info_icon;
        } else if (selectedImgId == R.id.radioButton2) {
            imageResourceId = R.drawable.android_icon;
        } else if (selectedImgId == R.id.radioButton3) {
            imageResourceId = R.drawable.ios_icon;
        }

        // Calls the insertDetails method we created
        long personId = dbHelper.insertDetails(name, dob, email, imageResourceId);

        // Shows a toast with the automatically generated id
        Toast.makeText(this, "Person has been created with id: " + personId,
                Toast.LENGTH_LONG
        ).show();

        // Launch Details Activity
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }
}