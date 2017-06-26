package com.martinjb.targettimer.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.martinjb.targettimer.database.TimeDbSchema.TimeTable;
import com.martinjb.targettimer.myTimer;
import java.util.Date;
import java.util.UUID;

/**
 * Created by martinjb on 6/26/2017.
 */

public class TimeCursorWrapper extends CursorWrapper {

    public TimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    public myTimer getTimer(){
        String uuidString = getString( getColumnIndex(TimeTable.Cols.UUID));
        String title = getString( getColumnIndex(TimeTable.Cols.TITLE));
        String worked = getString( getColumnIndex(TimeTable.Cols.WORKED));

        /**
         ---These are FINAL values set at substantiation---
        long date = getLong(getColumnIndex(TimeTable.Cols.DATE));
        int minutes = getInt(getColumnIndex(TimeTable.Cols.INITMINUTE));
        int hours = getInt(getColumnIndex(TimeTable.Cols.INITHOUR));
        **/

        myTimer timer = new myTimer(UUID.fromString(uuidString));
        timer.setID(UUID.fromString(uuidString));
        timer.setWorked(worked);
        timer.setTitle(title);
        return timer;
    }
}
