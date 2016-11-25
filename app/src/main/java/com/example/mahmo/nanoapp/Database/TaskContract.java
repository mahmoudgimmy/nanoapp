package com.example.mahmo.nanoapp.Database;

import android.provider.BaseColumns;

/**
 * Created by mahmo on 25/11/2016.
 */

public class TaskContract {
    public static final class TaskEntry implements BaseColumns {

        public static final String TABLE_NAME="Tasks";
        public static final String TASK_NAME="NAME";
        public static final String TASK_DESCRIPTION="DESCRIPTION";
        public static final String TASK_DATE="DATE";
        public static final String TASK_ID="ID";

    }



}
