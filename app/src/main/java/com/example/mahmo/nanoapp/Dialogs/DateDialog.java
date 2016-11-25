package com.example.mahmo.nanoapp.Dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

/**
 * Created by mahmo on 22/11/2016.
 */

public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    EditText datetext;
    public DateDialog(View view)
    {
        datetext=(EditText) view;
    }
    public Dialog onCreateDialog (Bundle savedInstanceState){
        final Calendar c=Calendar.getInstance();
        int year =c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getContext(),this,year,month,day);

    }
    public void onDateSet(DatePicker view,int year,int month,int day)
    {
        String date=day+"/"+(month+1)+"/"+year;
        datetext.setText(date);

    }

}
