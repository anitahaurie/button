package com.example.anatoly.assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anatoly on 10/10/16.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RunsDB.db";
    private static final String TABLE_RUNS = "Runs";

    public static final String KEY_ID = "id";               //TODO: change to date and time-stamp // column names
    public static final String KEY_DISTANCE = "Distance";
    public static final String KEY_DURATION = "Duration";
    public static final String KEY_AVG_SPEED = "AverageSpeed";
    public static final String KEY_CALORIES = "Calories";

    public DBHandler(Context context /*, String name, SQLiteDatabase.CursorFactory factory, int version */) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RUNS_TABLE = "CREATE TABLE " + TABLE_RUNS + "(" + KEY_ID + " INTEGER,"
                + KEY_DISTANCE + " FLOAT," + KEY_DURATION + " FLOAT,"
                + KEY_AVG_SPEED + " FLOAT," + KEY_CALORIES + " INTEGER" + ")";
        db.execSQL(CREATE_RUNS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RUNS);   // drop older table if it existed
        onCreate(db);                                       // creating tables again
    }

    public void addRunLog(RunLog log) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, log.getID());
        values.put(KEY_DISTANCE, log.getDistance());
        values.put(KEY_DURATION, log.getDuration());
        values.put(KEY_AVG_SPEED, log.getAvgSpeed());
        values.put(KEY_CALORIES, log.getCalories());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_RUNS, null, values);    // insert rows
        db.close();
    }

    // get RunLog based on id
    public RunLog getRunLog(int id) {
        String query = "SELECT * FROM " + TABLE_RUNS + " WHERE " + KEY_ID + " = \"" + Integer.toString(id) + "\"";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        RunLog log = new RunLog();

        if (cursor != null) {
            cursor.moveToFirst();
            log.setID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            log.setDistance(cursor.getFloat(cursor.getColumnIndex(KEY_DISTANCE)));
            log.setDuration(cursor.getFloat(cursor.getColumnIndex(KEY_DURATION)));
            log.setAvgSpeed();  // computed without arguments (using 'this')
            log.setCalories();
            cursor.close();
        }
        else {
            log = null;
        }

        db.close();

        return log;
    }


    // TODO wrong implementation: change it (getting floats and ints)
    public List<RunLog> getAllRunLogs() {
        List<RunLog> logList = new ArrayList<RunLog>();
        String selectQuery = "SELECT * FROM " + TABLE_RUNS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                RunLog log = new RunLog();
                log.setID(cursor.getInt(0));
                log.setDistance(cursor.getFloat(1));
                log.setDuration(cursor.getFloat(2));
                log.setAvgSpeed();
                log.setCalories();

                logList.add(log);
            } while (cursor.moveToNext());
        }
        return logList;
    }

    public List<String> getAllRunLogIDs() {
        List<String> idList= new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + TABLE_RUNS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String id = new String();
                id = cursor.getString(0);
                idList.add(id);
            } while (cursor.moveToNext());
        }
        return idList;
    }

    public int getRunLogCount() {
        String countQuery = "SELECT * FROM " + TABLE_RUNS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateRunLog(RunLog log) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DISTANCE, log.getDistance());
        values.put(KEY_DURATION, log.getDuration());
        values.put(KEY_AVG_SPEED, log.getAvgSpeed());
        values.put(KEY_CALORIES, log.getCalories());

        return db.update(TABLE_RUNS, values, KEY_ID + " = ?", new String[] {String.valueOf(log.getID())});
    }

    public void deleteRunLog(RunLog log) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RUNS, KEY_ID + " = ?", new String[] {String.valueOf(log.getID())});
        db.close();
    }
}






