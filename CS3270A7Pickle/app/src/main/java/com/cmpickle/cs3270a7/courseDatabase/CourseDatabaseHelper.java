package com.cmpickle.cs3270a7.courseDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 2/24/2017.
 */

public class CourseDatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase database;

    public static final String DATABASE_NAME = "courses.db";
    public static final int DATABASE_VERSION = 1;

    public CourseDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteDatabase open() {
        database = getWritableDatabase();
        return database;
    }

    public void close() {
        if(database != null) {
            database.close();
        }
    }

    public long insertCourse(String id, String name, String courseCode, String startAt, String endAt) {
        long rowId = -1;

        ContentValues newCourse = new ContentValues();
        newCourse.put(CourseListTable.COLUMN_ID, id);
        newCourse.put(CourseListTable.COLUMN_NAME, name);
        newCourse.put(CourseListTable.COLUMN_COURSE_CODE, courseCode);
        newCourse.put(CourseListTable.COLUMN_START_AT, startAt);
        newCourse.put(CourseListTable.COLUMN_END_AT, endAt);
        if(open() != null) {
            rowId = database.insert(CourseListTable.TABLE_COURSE_LIST, null, newCourse);
        }
        return rowId;
    }

    public Cursor getAllCourses() {
        Cursor cursor = null;
        if(open() != null) {
            Log.d(CourseDatabaseHelper.class.getName(), "Querying all courses");
            cursor = database.rawQuery("SELECT * FROM ?", new String[] {CourseListTable.TABLE_COURSE_LIST});
        }
        return cursor;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CourseListTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        CourseListTable.onUpgrade(db, oldVersion, newVersion);
    }
}
