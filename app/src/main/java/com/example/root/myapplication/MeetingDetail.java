package com.example.root.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * This class is mainly for the meeting detail activity included time, date, and google map picker
 * Also implement the history of attendees and setting colours and text size
 */
public class MeetingDetail extends AppCompatActivity implements android.view.View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    Button btnSave, btnDelete;
    Button btnClose;
    Button btnMap;
    Button btnDate;
    Button btnTime;

    TextView textViewDate;
    TextView textViewTime;
    TextView textViewLocation;

    EditText editTextName;
    EditText editTextNotes;
    AutoCompleteTextView AutoCompleteAttendees;

    ColourSettingsActivity colourSettings;
    FontSizeSettingsActivity fontSizeSettings;

    private int _Meeting_Id = 0;
    private final int PLACE_PICKER_REQUEST = 1;
    private ArrayList<HashMap<String, String>> attendees;

    /**
     * This method is storing the date and set on text view
     * @param view to set calendar view automatically synchronized
     * @param year the year
     * @param month the month
     * @param dayOfMonth the day of month
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        textViewDate.setText(currentDateString);
    }

    /**
     * This method is storing the time and set on text view
     * @param view to set calendar view automatically synchronized
     * @param hourOfDay hour of day in 24hours format
     * @param minute minute
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        String currentTimeString = DateFormat.getTimeInstance(DateFormat.FULL).format(calendar.getTime());
        textViewTime.setText(currentTimeString);
    }

    /**
     * This method initialize the meeting detail activity
     * @param savedInstanceState It's a object containing the activity previously saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_detail);

        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        btnClose = findViewById(R.id.btnClose);
        btnMap = findViewById(R.id.btnMap);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);

        editTextName = findViewById(R.id.editTextName);
        editTextName.setTextSize(fontSizeSettings.getSize(this));
        editTextName.setTextColor(colourSettings.getColor(this));

        AutoCompleteAttendees = findViewById(R.id.AutoCompleteAttendees);

        // Setting up the attendees history for future use
        MeetingRepo meetingRepo = new MeetingRepo(this);
        attendees = meetingRepo.getMeetingList();
        int attendeesSize = attendees.size();
        String[] attendeesHistory = new String[attendeesSize];
        for (int i = 0; i < attendeesSize; i++) {
            attendeesHistory[i] = attendees.get(i).get("attendees");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, attendeesHistory);

        AutoCompleteAttendees.setAdapter(adapter);

        AutoCompleteAttendees.setTextSize(fontSizeSettings.getSize(this));
        AutoCompleteAttendees.setTextColor(colourSettings.getColor(this));

        editTextNotes = findViewById(R.id.editTextNotes);
        editTextNotes.setTextSize(fontSizeSettings.getSize(this));
        editTextNotes.setTextColor(colourSettings.getColor(this));

        textViewDate = findViewById(R.id.textViewDate);
        textViewDate.setTextSize(fontSizeSettings.getSize(this));
        textViewDate.setTextColor(colourSettings.getColor(this));

        textViewTime = findViewById(R.id.textViewTime);
        textViewTime.setTextSize(fontSizeSettings.getSize(this));
        textViewTime.setTextColor(colourSettings.getColor(this));

        textViewLocation = findViewById(R.id.textViewLocation);
        textViewLocation.setTextSize(fontSizeSettings.getSize(this));
        textViewLocation.setTextColor(colourSettings.getColor(this));

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnMap.setOnClickListener(this);

        _Meeting_Id = 0;

        Intent intent = getIntent();
        _Meeting_Id = intent.getIntExtra("meeting_Id", 0);
        MeetingRepo repo = new MeetingRepo(this);
        Meeting meeting = new Meeting();
        meeting = repo.getMeetingById(_Meeting_Id);

        editTextName.setText(meeting.name);
        editTextNotes.setText(meeting.notes);
        AutoCompleteAttendees.setText(meeting.attendees);

        textViewTime.setText(meeting.time);
        textViewDate.setText(meeting.date);
        textViewLocation.setText(meeting.location);
    }

    /**
     * This method is when a view has been clicked
     * @param view the view that abel to click
     */
    public void onClick(View view) {
        // if user press the save button
        if (view == findViewById(R.id.btnSave)) {
            MeetingRepo repo = new MeetingRepo(this);
            Meeting meeting = new Meeting();

            // storing the data
            meeting.date = textViewDate.getText().toString();
            meeting.notes = editTextNotes.getText().toString();
            meeting.time = textViewTime.getText().toString();
            meeting.name = editTextName.getText().toString();
            meeting.location = textViewLocation.getText().toString();
            meeting.attendees = AutoCompleteAttendees.getText().toString();

            meeting.meeting_ID = _Meeting_Id;

            // if there is no meeting id
            if (_Meeting_Id == 0) {
                _Meeting_Id = repo.insert(meeting);
                Toast.makeText(this, "New Meeting Insert", Toast.LENGTH_SHORT).show();
                finish();
            } else { // update the meeting details
                repo.update(meeting);
                Toast.makeText(this, "Meeting Record updated", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else if (view == findViewById(R.id.btnDelete)) { // if pressing the delete , delete the meeting detail
            MeetingRepo repo = new MeetingRepo(this);
            repo.delete(_Meeting_Id);
            Toast.makeText(this, "Meeting Record Deleted", Toast.LENGTH_SHORT);
            finish();
        } else if (view == findViewById(R.id.btnClose)) { // if pressing the close button, close the current activity
            finish();
        } else if (view == findViewById(R.id.btnDate)) { // if pressing date button, generate date picker
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date picker");
        } else if (view == findViewById(R.id.btnTime)) { // if pressing time button, generate time picker
            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "time picker");
        } else if (view == findViewById(R.id.btnMap)) { // if pressing map button, generate google map picker
            startPlacePickerActivity();
        }
    }

    /**
     *  This method start the google map picker activity
     */
    private void startPlacePickerActivity() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            Intent intent = builder.build(this);
            startActivityForResult(intent, PLACE_PICKER_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is mainly authenticate and then set the location
     * @param requestCode the request code that authenticate the activity
     * @param resultCode the result code to authenticate the result
     * @param data the data of location
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK) {
            Place placeSelected = PlacePicker.getPlace(data, this);
            String name = placeSelected.getName().toString();
            String address = placeSelected.getAddress().toString();
            textViewLocation.setText(address + ", " + name);
        }
    }
}






