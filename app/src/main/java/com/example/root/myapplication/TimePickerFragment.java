package com.example.root.myapplication;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;


/**
 * This fragment initialize the time picker function
 */
public class TimePickerFragment extends DialogFragment {
    /**
     * This method initialize the time picker as hour and minutes
     *
     * @param savedInstanceState It's a object containing the activity previously saved state
     * @return the time in time picker which is in 24hours format
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int mins = calendar.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, mins, android.text.format.DateFormat.is24HourFormat(getActivity()));
    }
}
