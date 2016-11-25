package com.example.mahmo.nanoapp.Event;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahmo.nanoapp.Database.EventContract;
import com.example.mahmo.nanoapp.Database.EventHelper;
import com.example.mahmo.nanoapp.Database.TaskContract;
import com.example.mahmo.nanoapp.Database.TaskHelper;
import com.example.mahmo.nanoapp.Dialogs.DateDialog;
import com.example.mahmo.nanoapp.MainActivity;
import com.example.mahmo.nanoapp.R;
import com.example.mahmo.nanoapp.Dialogs.TimeDialog;

/**
 * Created by mahmo on 22/11/2016.
 */

public class Add_Event_Fragment extends Fragment {
    EventHelper eventHelper;
    ContentValues contentValues;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview=inflater.inflate(R.layout.add_event_fragment,container,false);
        eventHelper = new EventHelper(getContext());
        contentValues = new ContentValues();
        EditText date=(EditText) rootview.findViewById(R.id.add_event_date);
        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    DateDialog dialog =new DateDialog(view);
                    android.app.FragmentTransaction ft=getFragmentManager().beginTransaction();
                    dialog.show(ft,"DataPicker");

                }
            }
        });



        EditText start=(EditText) rootview.findViewById(R.id.add_event_startsTime);
        start.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    TimeDialog dialog = new TimeDialog(view);
                    android.app.FragmentTransaction ft=getFragmentManager().beginTransaction();
                    dialog.show(ft,"DataPicker");

                }
            }
        });

        EditText end=(EditText) rootview.findViewById(R.id.add_event_endsTime);
        end.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    TimeDialog dialog = new TimeDialog(view);
                    android.app.FragmentTransaction ft=getFragmentManager().beginTransaction();
                    dialog.show(ft,"DataPicker");

                }
            }
        });

        Button save=(Button)rootview.findViewById(R.id.button4);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText EventName=(EditText) rootview.findViewById(R.id.add_event_name);
                String eventname=(EventName.getText()).toString();
                if(eventname.isEmpty())
                    eventname="Event";

                EditText EventDate=(EditText) rootview.findViewById(R.id.add_event_date);
                String eventdate=(EventDate.getText()).toString();

                EditText Description=(EditText) rootview.findViewById(R.id.add_event_description);
                String description=(Description.getText()).toString();

                EditText EventPlace=(EditText) rootview.findViewById(R.id.add_event_place);
                String eventplace=(EventPlace.getText()).toString();

                EditText EventStartTime=(EditText) rootview.findViewById(R.id.add_event_startsTime);
                String eventstarttime=(EventStartTime.getText()).toString();

                EditText EventEndTime=(EditText) rootview.findViewById(R.id.add_event_endsTime);
                String eventendtime=(EventEndTime.getText()).toString();

                MainActivity.events.add(new Event(eventname,description,eventdate,eventstarttime,eventendtime,eventplace,MainActivity.EventId));

                contentValues.put(EventContract.EventEntry.EVENT_NAME, eventname);
                contentValues.put(EventContract.EventEntry.EVENT_DESCRIPTION, description);
                contentValues.put(EventContract.EventEntry.EVENT_DATE, eventdate);
                contentValues.put(EventContract.EventEntry.EVENT_START_TIME, eventstarttime);
                contentValues.put(EventContract.EventEntry.EVENT_END_TIME, eventendtime);
                contentValues.put(EventContract.EventEntry.EVENT_PLACE, eventplace);
                contentValues.put(EventContract.EventEntry.EVENT_ID, MainActivity.EventId);
                eventHelper.insert(contentValues);


                MainActivity.EventId++;
                Toast toast=Toast.makeText(getContext(),"Saved",Toast.LENGTH_SHORT);
                toast.show();
                Intent move=new Intent(getContext(),MainActivity.class);
                move.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(move);
            }
        });

        Button cancel=(Button)rootview.findViewById(R.id.button3);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move=new Intent(getContext(),MainActivity.class);
                move.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(move);
            }
        });


        return rootview;
    }
}
