package com.example.mahmo.nanoapp.Database;

import android.provider.BaseColumns;

/**
 * Created by mahmo on 26/11/2016.
 */

public class IDContract {
    public static final class IDEntry implements BaseColumns {

        public static final String TABLE_NAME="ids";
        public static final String ID="ID";
        public static final String TASK_ID="TASK_ID";
        public static final String EVENT_ID="EVENT_ID";
}

}
