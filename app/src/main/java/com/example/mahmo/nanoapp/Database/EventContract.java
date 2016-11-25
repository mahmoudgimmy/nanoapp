package com.example.mahmo.nanoapp.Database;

import android.provider.BaseColumns;

/**
 * Created by mahmo on 25/11/2016.
 */

public class EventContract {

    public static final class EventEntry implements BaseColumns {

        public static final String TABLE_NAME="Events";
        public static final String EVENT_NAME="NAME";
        public static final String EVENT_DESCRIPTION="DESCRIPTION";
        public static final String EVENT_DATE="DATE";
        public static final String EVENT_PLACE="PLACE";
        public static final String EVENT_START_TIME="START_TIME";
        public static final String EVENT_END_TIME="END_TIME";
        public static final String EVENT_ID="ID";

    }
}
