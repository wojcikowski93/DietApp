package com.wojcikowski.kamil.dietapp.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHandler {

    private static final String DEBUG_TAG = "DatabaseHandler";
    private static final String TABLE = "Table ";

    SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "dietapp.db";
    static final String DB_USER = "user";
    static final String DB_DETAILS = "details";

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DB_CREATE_USER_TABLE);
            db.execSQL(DB_CREATE_USER_DETAILS);

            Log.d(DEBUG_TAG, "Database creating...");
            Log.d(DEBUG_TAG, TABLE + DB_USER + " ver." + DB_VERSION + " created.");
            Log.d(DEBUG_TAG, TABLE + DB_DETAILS + " ver." + DB_VERSION + " created.");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_USER_TABLE);
            db.execSQL(DROP_USER_DETAILS_TABLE);

            Log.d(DEBUG_TAG, "Database updating...");
            Log.d(DEBUG_TAG, TABLE + DB_USER + " updated from ver." + oldVersion + " to ver." + newVersion);
            Log.d(DEBUG_TAG, TABLE + DB_DETAILS + " updated from ver." + oldVersion + " to ver." + newVersion);
            Log.d(DEBUG_TAG, "All data is lost.");

            onCreate(db);
        }
    }

    DatabaseHandler(Context context) {
        this.context = context;
    }

    public DatabaseHandler open(){

        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            db = dbHelper.getReadableDatabase();
        }
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private static final String DB_CREATE_USER_TABLE =
            "CREATE TABLE " + DB_USER + " (userid INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " username TEXT NOT NULL," +
                    " password TEXT NOT NULL," +
                    " email TEXT);";

    private static final String DROP_USER_TABLE =
            "DROP TABLE IF EXISTS " + DB_USER;

    private static final String DB_CREATE_USER_DETAILS =
            "CREATE TABLE " + DB_DETAILS + " (detailsid INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " userid INTEGER NOT NULL," +
                    " gender TEXT," +
                    " birthday DATE," +
                    " height INTEGER," +
                    " targetweight DOUBLE);";

    private static final String DROP_USER_DETAILS_TABLE =
            "DROP TABLE IF EXISTS " + DB_DETAILS;
}
