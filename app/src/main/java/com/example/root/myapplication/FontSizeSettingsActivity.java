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
 * This class is the general font settings
 */
public class FontSizeSettingsActivity extends AppCompatActivity implements android.view.View.OnClickListener {

    Button btnLarge;
    Button btnNormal;
    Button btnSmall;
    Button btnClose;

    public static final int LARGE_SIZE = 30;
    public static final int NORMAL_SIZE = 20;
    public static final int SMALL_SIZE = 10;

    /**
     * This method initialize the font settings activity
     *
     * @param savedInstanceState It's a object containing the activity previously saved state
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_settings);

        btnLarge = findViewById(R.id.btnLarge);
        btnLarge.setOnClickListener(this);
        btnLarge.setTextSize(LARGE_SIZE);

        btnNormal = findViewById(R.id.btnNormal);
        btnNormal.setOnClickListener(this);
        btnNormal.setTextSize(NORMAL_SIZE);

        btnSmall = findViewById(R.id.btnSmall);
        btnSmall.setOnClickListener(this);
        btnSmall.setTextSize(SMALL_SIZE);

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
        // If user presses the Large button
        if (view == findViewById(R.id.btnLarge)) {
            if (getSize(this) == NORMAL_SIZE || getSize(this) == SMALL_SIZE) {
                // it stores the large size of the font
                storefloat(LARGE_SIZE, this);
                Intent intent = new Intent();
                // pass the result to intent
                setResult(RESULT_OK, intent);
                finish();
                Toast.makeText(this, "You successfully setup as large size", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You already setup as large size", Toast.LENGTH_SHORT).show();
            }
        }

        // If user presses the Normal button
        if (view == findViewById(R.id.btnNormal)) {
            if (getSize(this) == LARGE_SIZE || getSize(this) == SMALL_SIZE) {
                storefloat(NORMAL_SIZE, this);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                Toast.makeText(this, "You successfully setup as normal size", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You already setup as normal size", Toast.LENGTH_SHORT).show();
            }
        }

        // If user presses the Small button
        if (view == findViewById(R.id.btnSmall)) {
            if (getSize(this) == NORMAL_SIZE || getSize(this) == LARGE_SIZE) {
                storefloat(SMALL_SIZE, this);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                Toast.makeText(this, "You successfully setup as small size", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You already setup as small size", Toast.LENGTH_SHORT).show();
            }
        }

        // If user press the close button
        if (view == findViewById(R.id.btnClose)) {
            // Close the activity
            finish();
        }

    }

    /**
     * This method simply store the font size
     *
     * @param fontSize The font size
     * @param context  The state of object
     */
    public static void storefloat(int fontSize, Context context) {
        SharedPreferences m = context.getSharedPreferences("Setting", MODE_PRIVATE);
        m.edit().putFloat("size", fontSize).apply();
    }

    /**
     * This method simply get the font size
     *
     * @param context the context
     * @return size
     */
    public static float getSize(Context context) {
        SharedPreferences m = context.getSharedPreferences("Setting", MODE_PRIVATE);
        // default as normal size
        float selector = m.getFloat("size", NORMAL_SIZE);
        return selector;
    }
}
