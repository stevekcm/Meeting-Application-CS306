package com.example.root.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This class simply for creating the table
 */
public class DBHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    private static final int DATABASE_VERSION = 11;

    // Database Name
    private static final String DATABASE_NAME = "crud.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This method create the database
     *
     * @param db database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables create will create here
        String CREATE_TABLE_MEETING = "CREATE TABLE " + Meeting.TABLE + "("
                + Meeting.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Meeting.KEY_name + " TEXT, "
                + Meeting.KEY_time + " TEXT, "
                + Meeting.KEY_date + " TEXT, "
                + Meeting.KEY_notes + " TEXT, "
                + Meeting.KEY_attendees + " TEXT, "
                + Meeting.KEY_location + " TEXT )";

        db.execSQL(CREATE_TABLE_MEETING);
    }

    /**
     * This method check if table exists delete it
     *
     * @param db         database
     * @param oldVersion old database
     * @param newVersion new database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Meeting.TABLE);
        // Create tables again
        onCreate(db);
    }
}