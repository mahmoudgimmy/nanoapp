package com.example.mahmo.nanoapp.Event;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahmo.nanoapp.Adapters.Events_Adapter;
import com.example.mahmo.nanoapp.Database.EventHelper;
import com.example.mahmo.nanoapp.Database.TaskHelper;
import com.example.mahmo.nanoapp.Event.Empty_event;
import com.example.mahmo.nanoapp.MainActivity;
import com.example.mahmo.nanoapp.R;
import com.example.mahmo.nanoapp.Task.Task;

/**
 * Created by mahmo on 18/11/2016.
 */

public class Event_Fragment extends Fragment {
    public EventHelper eventHelper;
    public Cursor cursor;
     static Events_Adapter EventAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview =inflater.inflate(R.layout.event_fragment,container,false);
        RecyclerView recyclerView=(RecyclerView) rootview.findViewById(R.id.recycleView_events);
        eventHelper=new EventHelper(getContext());
        cursor=eventHelper.getData();

        if(!cursor.equals(null)) {
            cursor.moveToFirst();
            MainActivity.events.clear();
            while (cursor.isAfterLast() == false) {
                MainActivity.events.add(new Event(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6)));
                cursor.moveToNext();
            }
        }


        if(MainActivity.events.isEmpty())
        {
            android.app.FragmentManager fm= getFragmentManager();
            fm.beginTransaction().replace(R.id.content_fragment,new Empty_event()).commit();

        }

        else
        {EventAdapter=new Events_Adapter(getContext());
        recyclerView.setAdapter(EventAdapter);
        LinearLayoutManager lm=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(lm);}
        return rootview;
    }

}
