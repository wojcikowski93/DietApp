package com.wojcikowski.kamil.dietapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wojcikowski.kamil.dietapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    private static final String DEBUG_TAG = "DatabaseHandler";

    protected SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "dietapp.db";
    protected static final String DB_USER = "user";
    private static final String DB_USER_DETAILS = "user_details";
    private static final String DB_MEASUREMENTS = "measurements";

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE_USER_TABLE);

            Log.d(DEBUG_TAG, "Database creating...");
            Log.d(DEBUG_TAG, "Table " + DB_USER + " ver." + DB_VERSION + " created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_USER_TABLE);

            Log.d(DEBUG_TAG, "Database updating...");
            Log.d(DEBUG_TAG, "Table " + DB_USER + " updated from ver." + oldVersion + " to ver." + newVersion);
            Log.d(DEBUG_TAG, "All data is lost.");

            onCreate(db);
        }
    }

    public DatabaseHandler(Context context) {
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
            "CREATE TABLE " + DB_USER + " (user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " username TEXT NOT NULL," +
                    " password TEXT NOT NULL," +
                    " email TEXT);";

    private static final String DROP_USER_TABLE =
            "DROP TABLE IF EXISTS " + DB_USER;


    /*

    private static final String DB_CREATE_USER_DETAILS_TABLE =
            "CREATE TABLE " + DB_USER_DETAILS + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " gender TEXT NOT NULL, " +
                    " birthday TEXT NOT NULL, " +
                    " height INTEGER NOT NULL, " +
                    " targetweight DOUBLE NOT NULL);";

    private static final String DROP_USER_DETAILS_TABLE =
            "DROP TABLE IF EXISTS " + DB_USER_DETAILS;

    private static final String DB_CREATE_WEIGHTS_TABLE =
            "CREATE TABLE " + DB_WEIGHTS + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " user_details_id INTEGER, " +
                    " date TEXT NOT NULL, " +
                    " weight DOUBLE NOT NULL, " +
                    "FOREIGN KEY(user_details_id) REFERENCES user_details(id));";

    private static final String DROP_WEIGHTS_TABLE =
            "DROP TABLE IF EXISTS " + DB_WEIGHTS;
*/

}
