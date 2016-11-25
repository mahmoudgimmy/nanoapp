package com.example.mahmo.nanoapp.Event;

import java.io.Serializable;

/**
 * Created by mahmo on 20/11/2016.
 */

public class Event implements Serializable{
    Event(String x,String y,String z,String a,String b,String c,int d )
    {
        name=x;
        description=y;
        date=z;
        start_time=a;
        end_time=b;
        place=c;
        id=d;


    }
    public String  name;
    public String description;
    public String date;
    public String start_time;
    public String end_time;
    public String place;
    public int id;
}
