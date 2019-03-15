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
 * This class is for the font colour settings
 */
public class ColourSettingsActivity extends AppCompatActivity implements android.view.View.OnClickListener {

    public static final int DKGRAY = 0xFF444444;
    public static final int LTGRAY = 0xFFCCCCCC;
    public static final int RED = 0xFFFF0000;
    public static final int GREEN = 0xFF00FF00;
    public static final int BLUE = 0xFF0000FF;
    public static final int YELLOW = 0xFFFFFF00;
    public static final int CYAN = 0xFF00FFFF;
    public static final int MAGENTA = 0xFFFF00FF;

    Button btnLightGray;
    Button btnRed;
    Button btnGreen;
    Button btnBlue;
    Button btnYellow;
    Button btnCyan;
    Button btnMagenta;
    Button btnClose;

    /**
     * This method initialize the colour  buttons
     *
     * @param savedInstanceState It's a object containing the activity previously saved state
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_settings);

        btnLightGray = findViewById(R.id.btnLightGray);
        btnLightGray.setTextColor(LTGRAY);
        btnLightGray.setOnClickListener(this);

        btnRed = findViewById(R.id.btnRed);
        btnRed.setTextColor(RED);
        btnRed.setOnClickListener(this);

        btnGreen = findViewById(R.id.btnGreen);
        btnGreen.setTextColor(GREEN);
        btnGreen.setOnClickListener(this);

        btnBlue = findViewById(R.id.btnBlue);
        btnBlue.setTextColor(BLUE);
        btnBlue.setOnClickListener(this);

        btnYellow = findViewById(R.id.btnYellow);
        btnYellow.setTextColor(YELLOW);
        btnYellow.setOnClickListener(this);

        btnCyan = findViewById(R.id.btnCyan);
        btnCyan.setTextColor(CYAN);
        btnCyan.setOnClickListener(this);

        btnMagenta = findViewById(R.id.btnMagenta);
        btnMagenta.setTextColor(MAGENTA);
        btnMagenta.setOnClickListener(this);

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
        // if user press the light gray button, change light gray colour, else remind the user it's already changed
        if (view == findViewById(R.id.btnLightGray)) {
            if (getColor(this) == RED || getColor(this) == GREEN || getColor(this) == BLUE || getColor(this) == YELLOW || getColor(this) == CYAN || getColor(this) == MAGENTA || getColor(this) == DKGRAY) {
                store(LTGRAY, this);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                Toast.makeText(this, "You successfully setup as Light Gray", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You already setup as Light Gray", Toast.LENGTH_SHORT).show();
            }
        } else if (view == findViewById(R.id.btnRed)) { // if user press the red button, change red colour, else remind the user it's already changed
            if (getColor(this) == LTGRAY || getColor(this) == GREEN || getColor(this) == BLUE || getColor(this) == YELLOW || getColor(this) == CYAN || getColor(this) == MAGENTA || getColor(this) == DKGRAY) {
                store(RED, this);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                Toast.makeText(this, "You successfully setup as Red", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You already setup as Red", Toast.LENGTH_SHORT).show();
            }
        } else if (view == findViewById(R.id.btnGreen)) { // // if user press the green button, change green colour, else remind the user it's already changed
            if (getColor(this) == RED || getColor(this) == LTGRAY || getColor(this) == BLUE || getColor(this) == YELLOW || getColor(this) == CYAN || getColor(this) == MAGENTA || getColor(this) == DKGRAY) {
                store(GREEN, this);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                Toast.makeText(this, "You successfully setup as Green", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You already setup as Green", Toast.LENGTH_SHORT).show();
            }
        } else if (view == findViewById(R.id.btnBlue)) { // if user press the blue button, change blue colour, else remind the user it's already changed
            if (getColor(this) == RED || getColor(this) == GREEN || getColor(this) == LTGRAY || getColor(this) == YELLOW || getColor(this) == CYAN || getColor(this) == MAGENTA || getColor(this) == DKGRAY) {
                store(BLUE, this);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                Toast.makeText(this, "You successfully setup as Blue", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You already setup as Blue", Toast.LENGTH_SHORT).show();
            }
        } else if (view == findViewById(R.id.btnYellow)) { // if user press the yellow button, change yellow colour, else remind the user it's already changed
            if (getColor(this) == RED || getColor(this) == GREEN || getColor(this) == BLUE || getColor(this) == LTGRAY || getColor(this) == CYAN || getColor(this) == MAGENTA || getColor(this) == DKGRAY) {
                store(YELLOW, this);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                Toast.makeText(this, "You successfully setup as Yellow", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You already setup as Yellow", Toast.LENGTH_SHORT).show();
            }
        } else if (view == findViewById(R.id.btnCyan)) { // if user press the cyan button, change cyan colour, else remind the user it's already changed
            if (getColor(this) == RED || getColor(this) == GREEN || getColor(this) == BLUE || getColor(this) == YELLOW || getColor(this) == LTGRAY || getColor(this) == MAGENTA || getColor(this) == DKGRAY) {
                store(CYAN, this);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                Toast.makeText(this, "You successfully setup as Cyan", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You already setup as Cyan", Toast.LENGTH_SHORT).show();
            }
        } else if (view == findViewById(R.id.btnMagenta)) { // if user press the magenta button, change magenta colour, else remind the user it's already changed
            if (getColor(this) == RED || getColor(this) == GREEN || getColor(this) == BLUE || getColor(this) == YELLOW || getColor(this) == CYAN || getColor(this) == LTGRAY || getColor(this) == DKGRAY) {
                store(MAGENTA, this);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                Toast.makeText(this, "You successfully setup as Magenta", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You already setup as Magenta", Toast.LENGTH_SHORT).show();
            }
        } else if (view == findViewById(R.id.btnClose)) { // if user press the close button, close the current activity
            finish();
        }


    }

    /**
     * The method store the colour int
     *
     * @param color
     * @param context
     */
    public static void store(int color, Context context) {
        SharedPreferences m = context.getSharedPreferences("Setting", MODE_PRIVATE);
        SharedPreferences.Editor editor = m.edit();
        editor.putInt("color", color);
        editor.apply();
    }

    /**
     * This method is for getting the colours
     *
     * @param context the context
     * @return colour integer
     */
    public static int getColor(Context context) {
        SharedPreferences m = context.getSharedPreferences("Setting", MODE_PRIVATE);
        return m.getInt("color", LTGRAY);
    }

}
