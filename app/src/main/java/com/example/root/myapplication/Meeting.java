package com.example.root.myapplication;


import com.google.android.gms.maps.model.LatLng;

/**
 * The items that store in database
 */
public class Meeting {
    // Labels table name
    public static final String TABLE = "Meeting";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_name = "name";
    public static final String KEY_time = "time";
    public static final String KEY_date = "date";
    public static final String KEY_location = "location";
    public static final String KEY_attendees = "attendees";
    public static final String KEY_notes = "notes";

    // property help us to keep data
    public int meeting_ID;
    public String name;
    public String time;
    public String date;
    public String location;
    public String attendees;
    public String notes;
}
