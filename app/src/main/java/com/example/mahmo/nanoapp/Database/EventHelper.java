package com.example.mahmo.nanoapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.mahmo.nanoapp.Database.EventContract.EventEntry;

import static com.example.mahmo.nanoapp.Database.TaskContract.TaskEntry.TASK_DATE;
import static com.example.mahmo.nanoapp.Database.TaskContract.TaskEntry.TASK_DESCRIPTION;

/**
 * Created by mahmo on 25/11/2016.
 */

public class EventHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=2;
    static final String DATABASE_NAME="events.db";

    public EventHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_TASK_TABLE="CREATE TABLE "+ EventEntry.TABLE_NAME +" ("+
                EventEntry.EVENT_NAME +" TEXT,"+
                EventEntry.EVENT_DESCRIPTION +" TEXT,"+
                EventEntry.EVENT_DATE+" TEXT,"+
                EventEntry.EVENT_START_TIME+" TEXT,"+
                EventEntry.EVENT_END_TIME+" TEXT,"+
                EventEntry.EVENT_PLACE+" TEXT,"+
                  EventEntry.EVENT_ID+" INTEGER"+");";
        sqLiteDatabase.execSQL(SQL_CREATE_TASK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EventEntry.TABLE_NAME);
    }


    public void insert(ContentValues contentValues){
        SQLiteDatabase obj= getWritableDatabase();
        obj.insert(EventEntry.TABLE_NAME,null,contentValues);
        obj.close();
    }

    public void delete(int id){
        SQLiteDatabase obj= getWritableDatabase();
        obj.delete(EventEntry.TABLE_NAME,EventEntry.EVENT_ID+" ="+id,null);
        obj.close();

    }
    public void update(int id,ContentValues contentValues){
        SQLiteDatabase obj= getWritableDatabase();
        obj.update(EventEntry.TABLE_NAME,contentValues,EventEntry.EVENT_ID+" ="+id,null);
        obj.close();

    }

    public Cursor getData(){
        SQLiteDatabase obj= getReadableDatabase();
        Cursor cursor = obj.rawQuery("SELECT* FROM "+EventEntry.TABLE_NAME, null);
        return cursor;
    }



}
