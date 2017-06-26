package com.martinjb.targettimer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.martinjb.targettimer.database.TimeCursorWrapper;
import com.martinjb.targettimer.database.TimeDbSchema.TimeTable;
import com.martinjb.targettimer.database.TimeBaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinjb on 6/26/2017.
 */

public class TimeLab {
    private static TimeLab sTimeLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private TimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TimeBaseHelper(mContext).getWritableDatabase();
    }

    public void addTimer(myTimer t) {
        ContentValues values = getContentValues(t);
        mDatabase.insert(TimeTable.NAME, null, values);
    }

    /**
     * Used in TimeListFragment.updateUI();
     * @param context
     * @return
     */
    public static TimeLab get(Context context) {
        if (sTimeLab == null) {
            sTimeLab = new TimeLab(context);
        }
        return sTimeLab;
    }
    /**
     *  Pull out info about a myTimer from our Database.
     * @param timer
     * @return
     */
    private static ContentValues getContentValues(myTimer timer) {
        ContentValues values = new ContentValues();
        values.put(TimeTable.Cols.UUID, timer.getID().toString());
        values.put(TimeTable.Cols.TITLE, timer.getTitle());
        values.put(TimeTable.Cols.WORKED, timer.getWorked());
        values.put(TimeTable.Cols.DATE, timer.getDate().getTime());
        values.put(TimeTable.Cols.INITHOUR, timer.getMinutes());
        values.put(TimeTable.Cols.INITMINUTE, timer.getHours());
        return values;
    }

    /**
     * queryTimers is used by getTimers();
     * @param whereClause
     * @param whereArgs
     * @return
     */

    private TimeCursorWrapper queryTimers(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(TimeTable.NAME,
                null, //columns - null selects all columns
                whereClause,
                whereArgs,
                null, //groupBy
                null, //having
                null //orderBy
        );
        return new TimeCursorWrapper(cursor);
    }

    /**
     * Uses queryTimers
     * @return
     */

    public List<myTimer> getTimers() {
        List<myTimer> timers = new ArrayList<>();
        TimeCursorWrapper cursor = queryTimers(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                timers.add(cursor.getTimer());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return timers;
    }
}
