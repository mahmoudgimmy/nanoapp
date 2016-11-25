package com.example.mahmo.nanoapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.mahmo.nanoapp.Event.Event;
import com.example.mahmo.nanoapp.Event.Event_Fragment;
import com.example.mahmo.nanoapp.Task.Task;
import com.example.mahmo.nanoapp.Task.Task_Fragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static int fragment_id;
    public static int TaskId=1 ;
    public static int EventId=1 ;
    static boolean first = true;
    public static boolean editable = false;
    public static int editableTaskPosition;
    public static int editableEventPosition;
    public static ArrayList<Task> tasks;
    public static ArrayList<Event> events;

Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Focuse");
        setSupportActionBar(toolbar);
        editableTaskPosition = 0;
        editableEventPosition = 0;

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (first) {
            events = new ArrayList<Event>();
            tasks = new ArrayList<Task>();

            fragment_id = R.id.nav_Tasks;
            first = false;
        }

        navigationView.setCheckedItem(fragment_id);

        if (fragment_id == R.id.nav_Tasks) {
            android.app.FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.content_fragment, new Task_Fragment()).commit();

        } else if (fragment_id == R.id.nav_Events) {
            android.app.FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.content_fragment, new Event_Fragment()).commit();
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(getBaseContext(), ADDActivity.class);
                startActivity(move);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Tasks) {
            fragment_id = R.id.nav_Tasks;
            android.app.FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.content_fragment, new Task_Fragment()).commit();
        } else if (id == R.id.nav_Events) {
            fragment_id = R.id.nav_Events;
            android.app.FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.content_fragment, new Event_Fragment()).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
