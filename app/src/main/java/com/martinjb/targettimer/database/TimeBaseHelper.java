package com.martinjb.targettimer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by martinjb on 6/26/2017.
 */

public class TimeBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "timeBase.db";

    public TimeBaseHelper(Context context){ super(context, DATABASE_NAME, null, VERSION); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TimeDbSchema.TimeTable.NAME + "(" +
        "_id integer primary key autoincrement" + ", " +
                TimeDbSchema.TimeTable.Cols.UUID + ", " +
                TimeDbSchema.TimeTable.Cols.TITLE + ", " +
                TimeDbSchema.TimeTable.Cols.WORKED + ", " +
                TimeDbSchema.TimeTable.Cols.DATE + ", " +
                TimeDbSchema.TimeTable.Cols.INITHOUR + ", " +
                TimeDbSchema.TimeTable.Cols.INITMINUTE + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
