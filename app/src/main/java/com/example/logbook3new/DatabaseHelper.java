package com.example.logbook3new;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CONTACTS";
    private static final String TABLE_NAME = "persons";
    public static final String ID_COLUMN = "person_id";
    public static final String NAME_COLUMN = "name";
    public static final String DOB_COLUMN = "dob";
    public static final String EMAIL_COLUMN = "email";

    private static final String IMAGE_COLUMN = "image";

    private SQLiteDatabase database;

    private static final String TABLE_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s INTEGER)",
            TABLE_NAME, ID_COLUMN, NAME_COLUMN, DOB_COLUMN, EMAIL_COLUMN, IMAGE_COLUMN
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        database = getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        Log.v(this.getClass().getName(), TABLE_NAME +
                "upgrade to version" + newVersion + " - old data lost"
        );
        onCreate(db);
    }

    public String getDetails() {
        Cursor results = database.query(TABLE_NAME,
                // Defines the query to execute
                new String[]{ID_COLUMN, NAME_COLUMN, DOB_COLUMN, EMAIL_COLUMN, IMAGE_COLUMN},
                null, null, null, null, NAME_COLUMN
        );
        String resultText = "";

        // Moves to the first position of the result set
        results.moveToFirst();

        // Checks whether there are more rows in the result set
        while (!results.isAfterLast()) {

            // Extracts the values from the row
            int id = results.getInt(0);
            String name = results.getString(1);
            String dob = results.getString(2);
            String email = results.getString(3);
            String image = results.getString(4);

            // Concatenates the text values
            resultText += id + " " + name + " " + dob + " " + email + " " + image + "\n";

            // Moves to the next row in the result set
            results.moveToNext();
        }

        // Returns a long string of all results
        return resultText;
    }

    public long insertDetails(String name, String dob, String email, int imageResourceId) {
        // ContentValues represents a single table row as a key/value map
        ContentValues rowValues = new ContentValues();

        rowValues.put(NAME_COLUMN, name);
        rowValues.put(DOB_COLUMN, dob);
        rowValues.put(EMAIL_COLUMN, email);
        rowValues.put(IMAGE_COLUMN, imageResourceId);

        return database.insertOrThrow(
                TABLE_NAME,
                // nullColumnHack specifies a column that will be set to null if the ContentValues argument contains no data
                null,
                // Inserts ContentValues into the database
                rowValues
        );
    }
}
