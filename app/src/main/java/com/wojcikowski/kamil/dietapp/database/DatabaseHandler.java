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

    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "dietapp.db";
    private static final String DB_USER = "user";
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

    public long insertUser(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getUsername());
        contentValues.put("password", user.getPassword());
        contentValues.put("email", user.getEmail());

        return db.insert(DB_USER, null, contentValues);
    }

    public boolean updateUser(User user) {
        long id = user.getUser_id();
        String email = user.getEmail();
        return updateUser(id, email);
    }

    public boolean updateUser(long id, String email) {
        String where = "user_id=" + id;
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        return db.update(DB_USER, contentValues, where, null) > 0;
    }

    public boolean deleteUser(long id) {
        String where = "user_id=" + id;
        return db.delete(DB_USER, where, null) > 0;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String[] columns = {"user_id", "username", "password", "email"};
        Cursor cursor = db.query(DB_USER, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            userList.add(new User(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            while(cursor.moveToNext()){
                userList.add(new User(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            }
            cursor.close();
        }
        return userList;
    }

    public User getUser(long id) {
        String[] columns = {"user_id", "username", "password", "email"};
        String where = "user_id=" + id;
        Cursor cursor = db.query(DB_USER, columns, where, null, null, null, null);
        User user = null;
        if(cursor != null && cursor.moveToFirst()) {
            user = new User(id, cursor.getString(1), cursor.getString(2), cursor.getString(3));
            cursor.close();
        }

        return user;
    }

    public boolean usernameExists (String username) {
        String[] columns = {"user_id", "username", "password", "email"};
        String where = "username='" + username + "'";
        Cursor cursor = db.query(DB_USER, columns, where, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            cursor.close();
            return true;
        } else
            return false;
    }

    public boolean emailExists (String email) {
        String[] columns = {"user_id", "username", "password", "email"};
        String where = "email='" + email +"'";
        Cursor cursor = db.query(DB_USER, columns, where, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            cursor.close();
            return true;
        } else
            return false;
    }






















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
