package com.example.mahmo.nanoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mahmo.nanoapp.Event.Add_Event_Fragment;
import com.example.mahmo.nanoapp.Event.Edit_Event_Fragment;
import com.example.mahmo.nanoapp.Task.Add_Task_Fragment;
import com.example.mahmo.nanoapp.Task.Edit_Task_Fragment;

public class ADDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);

        if(!MainActivity.editable) {
            if (MainActivity.fragment_id == R.id.nav_Events)
                toolbar.setTitle("Add event");
            else
                toolbar.setTitle("Add task");
        }
        else
        {
            if (MainActivity.fragment_id == R.id.nav_Events)
                toolbar.setTitle("Edit event");
            else
                toolbar.setTitle("Edit task");
        }
        setSupportActionBar(toolbar);
        if(!MainActivity.editable)
        {
            if(MainActivity.fragment_id==R.id.nav_Events)
        { android.app.FragmentManager fm= getFragmentManager();
        fm.beginTransaction().replace(R.id.Add_content,new Add_Event_Fragment()).commit();}
        else
        {
            android.app.FragmentManager fm= getFragmentManager();
            fm.beginTransaction().replace(R.id.Add_content,new Add_Task_Fragment()).commit();
        }

        }
        else
        {

            if(MainActivity.fragment_id==R.id.nav_Events)
            { android.app.FragmentManager fm= getFragmentManager();
                fm.beginTransaction().replace(R.id.Add_content,new Edit_Event_Fragment()).commit();

            }
            else
            {
                android.app.FragmentManager fm= getFragmentManager();
                fm.beginTransaction().replace(R.id.Add_content,new Edit_Task_Fragment()).commit();
            }
        }

    }

}
