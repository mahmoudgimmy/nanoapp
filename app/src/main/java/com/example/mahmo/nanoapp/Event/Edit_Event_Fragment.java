package com.example.mahmo.nanoapp.Event;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.DialogInterface;
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
 * Created by mahmo on 25/11/2016.
 */

public class Edit_Event_Fragment extends Fragment {
    EventHelper eventHelper;
    ContentValues contentValues;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.edit_event_fragment, container, false);

        eventHelper = new EventHelper(getContext());
        contentValues = new ContentValues();

        ((EditText) rootview.findViewById(R.id.edit_event_name))
                .setText(MainActivity.events.get(MainActivity.editableEventPosition).name);

        ((EditText) rootview.findViewById(R.id.edit_event_description))
                .setText(MainActivity.events.get(MainActivity.editableEventPosition).description);

        ((EditText) rootview.findViewById(R.id.edit_event_date))
                .setText(MainActivity.events.get(MainActivity.editableEventPosition).date);

        ((EditText) rootview.findViewById(R.id.edit_event_place))
                .setText(MainActivity.events.get(MainActivity.editableEventPosition).place);

        ((EditText) rootview.findViewById(R.id.edit_event_startsTime))
                .setText(MainActivity.events.get(MainActivity.editableEventPosition).start_time);

        ((EditText) rootview.findViewById(R.id.edit_event_endsTime))
                .setText(MainActivity.events.get(MainActivity.editableEventPosition).end_time);



        EditText date=(EditText) rootview.findViewById(R.id.edit_event_date);
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


        EditText start=(EditText) rootview.findViewById(R.id.edit_event_startsTime);
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

        EditText end=(EditText) rootview.findViewById(R.id.edit_event_endsTime);
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


        Button delete=(Button)rootview.findViewById(R.id.button10);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder alert = new AlertDialog.Builder(
                        getContext());
                alert.setTitle("Alert!!");
                alert.setMessage("Are you sure to delete?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        MainActivity.editable=false;
                        eventHelper.delete(MainActivity.events.get(MainActivity.editableEventPosition).id);
                        MainActivity.events.remove(MainActivity.editableEventPosition);
                        Intent move=new Intent(getContext(),MainActivity.class);
                        move.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(move);
                        Toast toast=Toast.makeText(getContext(),"Deleted",Toast.LENGTH_SHORT);
                        toast.show();


                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();


            }
        });


        Button save=(Button)rootview.findViewById(R.id.button8);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.editable=false;

                EditText EventName=(EditText) rootview.findViewById(R.id.edit_event_name);
                MainActivity.events.get(MainActivity.editableEventPosition).name=(EventName.getText()).toString();
                if(MainActivity.events.get(MainActivity.editableEventPosition).name.isEmpty())
                    MainActivity.events.get(MainActivity.editableEventPosition).name="Event";

                EditText EventDate=(EditText) rootview.findViewById(R.id.edit_event_date);
                MainActivity.events.get(MainActivity.editableEventPosition).date=(EventDate.getText()).toString();

                EditText Description=(EditText) rootview.findViewById(R.id.edit_event_description);
                MainActivity.events.get(MainActivity.editableEventPosition).description=(Description.getText()).toString();

                EditText EventPlace=(EditText) rootview.findViewById(R.id.edit_event_place);
                MainActivity.events.get(MainActivity.editableEventPosition).place=(EventPlace.getText()).toString();

                EditText EventStartTime=(EditText) rootview.findViewById(R.id.edit_event_startsTime);
                MainActivity.events.get(MainActivity.editableEventPosition).start_time=(EventStartTime.getText()).toString();

                EditText EventEndTime=(EditText) rootview.findViewById(R.id.edit_event_endsTime);
                MainActivity.events.get(MainActivity.editableEventPosition).end_time=(EventEndTime.getText()).toString();



                contentValues.put(EventContract.EventEntry.EVENT_NAME, MainActivity.events.get(MainActivity.editableEventPosition).name);
                contentValues.put(EventContract.EventEntry.EVENT_DESCRIPTION, MainActivity.events.get(MainActivity.editableEventPosition).description);
                contentValues.put(EventContract.EventEntry.EVENT_DATE, MainActivity.events.get(MainActivity.editableEventPosition).date);
                contentValues.put(EventContract.EventEntry.EVENT_START_TIME, MainActivity.events.get(MainActivity.editableEventPosition).start_time);
                contentValues.put(EventContract.EventEntry.EVENT_END_TIME, MainActivity.events.get(MainActivity.editableEventPosition).end_time);
                contentValues.put(EventContract.EventEntry.EVENT_PLACE, MainActivity.events.get(MainActivity.editableEventPosition).place);
                contentValues.put(EventContract.EventEntry.EVENT_ID, MainActivity.events.get(MainActivity.editableEventPosition).id);
                eventHelper.update(MainActivity.events.get(MainActivity.editableEventPosition).id,contentValues);



                Toast toast=Toast.makeText(getContext(),"Saved",Toast.LENGTH_SHORT);
                toast.show();
                Intent move=new Intent(getContext(),MainActivity.class);
                move.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(move);
            }
        });

        Button cancel=(Button)rootview.findViewById(R.id.button9);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.editable=false;
                Intent move=new Intent(getContext(),MainActivity.class);
                move.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(move);
            }
        });


        return  rootview;
    }
}
