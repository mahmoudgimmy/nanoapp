package com.example.mahmo.nanoapp.Task;

import java.io.Serializable;

/**
 * Created by mahmo on 20/11/2016.
 */

public class Task implements Serializable {
    public Task(String x, String y, String z, int b)
    {
        name=x;
        description=y;
        date=z;
        id=b;
    }
    public String  name;
    public String description;
    public String date;
    public int id;

}
