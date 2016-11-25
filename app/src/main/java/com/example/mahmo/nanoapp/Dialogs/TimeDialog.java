package com.example.mahmo.nanoapp.Dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

/**
 * Created by mahmo on 23/11/2016.
 */

public class TimeDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    EditText TimeText;

    public TimeDialog(View view) {
        TimeText = (EditText) view;

    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getContext(), this, hours, minutes, false);

    }
    @Override
    public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
        String AM_PM;
        String date;
        if(hours<=12)
            AM_PM="AM";
        else {
            hours -= 12;
            AM_PM = "PM";
        }
         date = hours + ":" + minutes+" "+AM_PM;

        TimeText.setText(date);
    }
}