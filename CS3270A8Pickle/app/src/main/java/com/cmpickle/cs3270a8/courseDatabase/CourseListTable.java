package com.cmpickle.cs3270a8.courseDatabase;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 2/24/2017.
 */

public class CourseListTable {

    public static final String TABLE_COURSE_LIST = "course_list";
    public static final String COLUMN_ID_KEY = "_id";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_COURSE_CODE = "course_code";
    public static final String COLUMN_START_AT = "start_at";
    public static final String COLUMN_END_AT = "end_at";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_COURSE_LIST + "("
            + COLUMN_ID_KEY + " integer primary key autoincrement, "
            + COLUMN_ID + ", "
            + COLUMN_NAME + " text not null, "
            + COLUMN_COURSE_CODE + ", "
            + COLUMN_START_AT + ", "
            + COLUMN_END_AT + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(CourseListTable.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE_LIST);
        onCreate(database);
    }
}
