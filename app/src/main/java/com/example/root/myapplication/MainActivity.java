package com.example.root.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This method is the main page activity
 */
public class MainActivity extends ListActivity implements android.view.View.OnClickListener {

    Button btnAdd, btnGetAll;
    Button btnSettings;
    TextView meeting_Id;


    /**
     * This method initialize the main page settings activity
     *
     * @param savedInstanceState It's a object containing the activity previously saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);

        btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(this);
    }

    /**
     * This method is when a view has been clicked
     *
     * @param view the view that abel to click
     */
    @Override
    public void onClick(View view) {
        // if user clicked the button add
        if (view == findViewById(R.id.btnAdd)) {
            Intent intent = new Intent(this, MeetingDetail.class);
            // put the meeting value to 0
            intent.putExtra("meeting_Id", 0);
            startActivity(intent);
        }
        // if user clicked the history
        else if (view == findViewById(R.id.btnGetAll)) {
            MeetingRepo repo = new MeetingRepo(this);
            // take the values from meeting repo
            ArrayList<HashMap<String, String>> meetingList = repo.getMeetingList();
            if (meetingList.size() != 0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        meeting_Id = view.findViewById(R.id.meeting_Id);
                        String meetingId = meeting_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(), MeetingDetail.class);
                        objIndent.putExtra("meeting_Id", Integer.parseInt(meetingId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter(MainActivity.this, meetingList, R.layout.view_meeting_entry, new String[]{"id", "name", "location", "attendees"}, new int[]{R.id.meeting_Id, R.id.meeting_name, R.id.meeting_location, R.id.meeting_attendees});
                setListAdapter(adapter);
                ((SimpleAdapter) adapter).notifyDataSetChanged();
            } else {
                Toast.makeText(this, "No Meeting!", Toast.LENGTH_SHORT).show();
                ListAdapter adapter = new SimpleAdapter(MainActivity.this, meetingList, R.layout.view_meeting_entry, new String[]{"id", "name", "location", "attendees"}, new int[]{R.id.meeting_Id, R.id.meeting_name, R.id.meeting_location, R.id.meeting_attendees});
                setListAdapter(adapter);
                ((SimpleAdapter) adapter).notifyDataSetChanged();
            }
            // if user clicked the settings button
        } else if (view == findViewById(R.id.btnSettings)) {
            Intent intent = new Intent(this, SettingsActivity.class);
            // start the settings activity
            startActivity(intent);
        }
    }
}