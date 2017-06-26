package com.martinjb.targettimer.database;

/**
 * Created by martinjb on 6/26/2017.
 */

public class TimeDbSchema {
    public static final class TimeTable{
        public static final String NAME = "timers";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String WORKED = "worked";
            public static final String DATE = "date";
            public static final String INITHOUR = "inithour";
            public static final String INITMINUTE = "initminute";
        }
    }
}
