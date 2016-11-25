package com.example.mahmo.nanoapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.mahmo.nanoapp.Database.TaskContract.TaskEntry;

/**
 * Created by mahmo on 25/11/2016.
 */

public class TaskHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=2;
    static final String DATABASE_NAME="tasks.db";

    public TaskHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_TASK_TABLE="CREATE TABLE "+ TaskEntry.TABLE_NAME +" ("+
                TaskEntry.TASK_NAME +" TEXT,"+
                TaskEntry.TASK_DESCRIPTION +" TEXT,"+
                TaskEntry.TASK_DATE+" TEXT,"+
                TaskEntry.TASK_ID+" INTEGER"+");";
        sqLiteDatabase.execSQL(SQL_CREATE_TASK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TaskEntry.TABLE_NAME);
    }

    public void insert(ContentValues contentValues){
        SQLiteDatabase obj= getWritableDatabase();
        obj.insert(TaskEntry.TABLE_NAME,null,contentValues);
        obj.close();
    }

    public void delete(int id){
        SQLiteDatabase obj= getWritableDatabase();
        obj.delete(TaskEntry.TABLE_NAME,TaskEntry.TASK_ID+" ="+id,null);
        obj.close();

    }
    public void update(int id,ContentValues contentValues){
        SQLiteDatabase obj= getWritableDatabase();
        obj.update(TaskEntry.TABLE_NAME,contentValues,TaskEntry.TASK_ID+" ="+id,null);
        obj.close();

    }

    public Cursor getData(){
        SQLiteDatabase obj= getReadableDatabase();
        Cursor cursor = obj.rawQuery("SELECT* FROM "+TaskEntry.TABLE_NAME, null);
        return cursor;
    }


}


