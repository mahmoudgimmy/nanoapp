package com.example.mahmo.nanoapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.mahmo.nanoapp.Database.IDContract.IDEntry;
/**
 * Created by mahmo on 26/11/2016.
 */

public class IDHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=2;
    static final String DATABASE_NAME="ids.db";

    public IDHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_ID_TABLE="CREATE TABLE "+ IDEntry.TABLE_NAME +" ("+
                IDEntry.ID +" INTEGER,"+
                IDEntry.TASK_ID +" INTEGER,"+
                IDEntry.EVENT_ID +" INTEGER"+");";
        sqLiteDatabase.execSQL(SQL_CREATE_ID_TABLE);
        ContentValues contentValues=new ContentValues();
        contentValues.put(IDEntry.ID,1);
        contentValues.put(IDEntry.TASK_ID,1);
        contentValues.put(IDEntry.EVENT_ID,1);
        sqLiteDatabase.insert(IDEntry.TABLE_NAME,null,contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + IDEntry.TABLE_NAME);
    }

    public void update(int id,ContentValues contentValues){
        SQLiteDatabase obj= getWritableDatabase();
        obj.update(IDEntry.TABLE_NAME,contentValues, IDEntry.ID+" ="+id,null);
        obj.close();

    }

    public Cursor getData(){
        SQLiteDatabase obj= getReadableDatabase();
        Cursor cursor = obj.rawQuery("SELECT* FROM "+ IDEntry.TABLE_NAME, null);
        return cursor;
    }
}
