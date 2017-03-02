package com.cmpickle.cs3270a8.courseDatabase;

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

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase database;

    public static final String DATABASE_NAME = "courses.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
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

    public void deleteCourse(long _id) {
        String query = String.format("DELETE FROM %s WHERE %s = ?",
                CourseListTable.TABLE_COURSE_LIST,
                CourseListTable.COLUMN_ID_KEY);
        database.execSQL(query, new String[] {String.valueOf(_id)});
    }

    public void updateCourse(String id, String name, String courseCode, String startAt, String endAt, long _id) {
        String query = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
                CourseListTable.TABLE_COURSE_LIST,
                CourseListTable.COLUMN_ID,
                CourseListTable.COLUMN_NAME,
                CourseListTable.COLUMN_COURSE_CODE,
                CourseListTable.COLUMN_START_AT,
                CourseListTable.COLUMN_END_AT,
                CourseListTable.COLUMN_ID_KEY);
        database.execSQL(query,
                new String[] { id, name, courseCode, startAt, endAt, String.valueOf(_id)});
    }

    public Cursor getAllCourses() {
        Cursor cursor = null;
        if(open() != null) {
            Log.d(DatabaseHelper.class.getName(), "Querying all courses");
            String query = String.format("SELECT * FROM %s",
                    CourseListTable.TABLE_COURSE_LIST);
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor getCourseById(long id) {
        Cursor cursor = null;
        if(open() != null) {
            Log.d(DatabaseHelper.class.getName(), "Querying course where _id = " + id);
            String query = String.format("SELECT * FROM %s WHERE %s = ?",
                    CourseListTable.TABLE_COURSE_LIST,
                    CourseListTable.COLUMN_ID_KEY);
            cursor = database.rawQuery(query, new String[] {String.valueOf(id)});
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
