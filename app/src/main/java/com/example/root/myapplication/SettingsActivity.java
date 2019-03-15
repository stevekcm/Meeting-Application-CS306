package com.example.root.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * This class is settings activity, which give options of font size and change colour
 */
public class SettingsActivity extends AppCompatActivity implements android.view.View.OnClickListener {

    Button btnDefault;
    Button btnFontSize;
    Button btnTextColour;
    Button btnClose;
    ColourSettingsActivity colourSettings;
    FontSizeSettingsActivity fontSettings;

    private final int COLOUR_SETTINGS_REQUEST = 3;
    private final int FONT_SETTINGS_REQUEST = 2;

    /**
     * This method initialize the settings option buttons
     *
     * @param savedInstanceState It's a object containing the activity previously saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnDefault = findViewById(R.id.btnDefault);
        btnDefault.setOnClickListener(this);
        btnDefault.setTextColor(colourSettings.getColor(this));
        btnDefault.setTextSize(fontSettings.getSize(this));

        btnFontSize = findViewById(R.id.btnFontSize);
        btnFontSize.setOnClickListener(this);
        btnFontSize.setTextColor(colourSettings.getColor(this));
        btnFontSize.setTextSize(fontSettings.getSize(this));

        btnTextColour = findViewById(R.id.btnTextColour);
        btnTextColour.setOnClickListener(this);
        btnTextColour.setTextColor(colourSettings.getColor(this));
        btnTextColour.setTextSize(fontSettings.getSize(this));

        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);
    }

    /**
     * This method is when a view has been clicked
     *
     * @param view the view that abel to click
     */
    @Override
    public void onClick(View view) {
        // if user clicked default, set colour and font to default colour which is Dark gray
        if (view == findViewById(R.id.btnDefault)) {
            colourSettings.store(ColourSettingsActivity.DKGRAY, this);
            fontSettings.storefloat(fontSettings.NORMAL_SIZE, this);
            Toast.makeText(this, "You successfully reset everything as default", Toast.LENGTH_SHORT).show();
            finish();
            // if user press front button switch page to font size settings activity
        } else if (view == findViewById(R.id.btnFontSize)) {
            Intent intent = new Intent(this, FontSizeSettingsActivity.class);
            startActivityForResult(intent, FONT_SETTINGS_REQUEST);
            // if user press colour button switch page to colour settings activity
        } else if (view == findViewById(R.id.btnTextColour)) {
            Intent intent = new Intent(this, ColourSettingsActivity.class);
            startActivityForResult(intent, COLOUR_SETTINGS_REQUEST);
            // if user press close button , close the page
        } else if (view == findViewById(R.id.btnClose)) {
            finish();
        }

    }

    /** This method is mainly authenticate and then set the font or colour settings
     * @param requestCode the request code that authenticate the activity
     * @param resultCode the result code to authenticate the result
     * @param data  the data colour or size
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == FONT_SETTINGS_REQUEST && resultCode == RESULT_OK) {
            btnTextColour.setTextSize(fontSettings.getSize(this));
            btnFontSize.setTextSize(fontSettings.getSize(this));
            btnDefault.setTextSize(fontSettings.getSize(this));
        } else if (requestCode == COLOUR_SETTINGS_REQUEST && resultCode == RESULT_OK) {
            btnTextColour.setTextSize(colourSettings.getColor(this));
            btnFontSize.setTextSize(colourSettings.getColor(this));
            btnDefault.setTextSize(colourSettings.getColor(this));
        }
    }
}
