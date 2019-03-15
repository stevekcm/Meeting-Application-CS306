package com.example.root.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is the repository for models
 */
public class MeetingRepo {
    private DBHelper dbHelper;

    public MeetingRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    /**
     * This method is for inserting data
     *
     * @param meeting items from meeting class
     * @return meeting id
     */
    public int insert(Meeting meeting) {
        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Meeting.KEY_time, meeting.time);
        values.put(Meeting.KEY_date, meeting.date);
        values.put(Meeting.KEY_name, meeting.name);
        values.put(Meeting.KEY_notes, meeting.notes);
        values.put(Meeting.KEY_attendees, meeting.attendees);
        values.put(Meeting.KEY_location, meeting.location);

        // Inserting Row
        long meeting_Id = db.insert(Meeting.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) meeting_Id;
    }

    /**
     * This method is for deleting the database
     *
     * @param meeting_Id meeting id
     */
    public void delete(int meeting_Id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Meeting.TABLE, Meeting.KEY_ID + "= ?", new String[]{String.valueOf(meeting_Id)});
        db.close(); // Closing database connection
    }

    /**
     * This method is for updating the database
     *
     * @param meeting meeting id
     */
    public void update(Meeting meeting) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Meeting.KEY_time, meeting.time);
        values.put(Meeting.KEY_date, meeting.date);
        values.put(Meeting.KEY_name, meeting.name);
        values.put(Meeting.KEY_notes, meeting.notes);
        values.put(Meeting.KEY_attendees, meeting.attendees);
        values.put(Meeting.KEY_location, meeting.location);

        db.update(Meeting.TABLE, values, Meeting.KEY_ID + "= ?", new String[]{String.valueOf(meeting.meeting_ID)});
        db.close(); // Closing database connection
    }

    /**
     * This method is for getting the meeting list
     *
     * @return meeting list
     */
    public ArrayList<HashMap<String, String>> getMeetingList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Meeting.KEY_ID + "," +
                Meeting.KEY_name + "," +
                Meeting.KEY_time + "," +
                Meeting.KEY_date + "," +
                Meeting.KEY_notes + "," +
                Meeting.KEY_attendees + "," +
                Meeting.KEY_location +
                " FROM " + Meeting.TABLE;

        ArrayList<HashMap<String, String>> meetingList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> meeting = new HashMap<>();
                meeting.put("id", cursor.getString(cursor.getColumnIndex(Meeting.KEY_ID)));
                meeting.put("name", cursor.getString(cursor.getColumnIndex(Meeting.KEY_name)));
                meeting.put("location", cursor.getString(cursor.getColumnIndex(Meeting.KEY_location)));
                meeting.put("attendees", cursor.getString(cursor.getColumnIndex(Meeting.KEY_attendees)));
                meetingList.add(meeting);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return meetingList;
    }

    /**
     * This method is for main activity which getting the meeting by id
     *
     * @param Id meeting id
     * @return meeting elements
     */
    public Meeting getMeetingById(int Id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Meeting.KEY_ID + "," +
                Meeting.KEY_name + "," +
                Meeting.KEY_time + "," +
                Meeting.KEY_date + "," +
                Meeting.KEY_notes + "," +
                Meeting.KEY_attendees + "," +
                Meeting.KEY_location +
                " FROM " + Meeting.TABLE
                + " WHERE " +
                Meeting.KEY_ID + "=?";

        int iCount = 0;
        Meeting meeting = new Meeting();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(Id)});

        if (cursor.moveToFirst()) {
            do {
                meeting.meeting_ID = cursor.getInt(cursor.getColumnIndex(Meeting.KEY_ID));
                meeting.name = cursor.getString(cursor.getColumnIndex(Meeting.KEY_name));
                meeting.time = cursor.getString(cursor.getColumnIndex(Meeting.KEY_time));
                meeting.date = cursor.getString(cursor.getColumnIndex(Meeting.KEY_date));
                meeting.notes = cursor.getString(cursor.getColumnIndex(Meeting.KEY_notes));
                meeting.attendees = cursor.getString(cursor.getColumnIndex(Meeting.KEY_attendees));
                meeting.location = cursor.getString(cursor.getColumnIndex(Meeting.KEY_location));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return meeting;
    }
}
