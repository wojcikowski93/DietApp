package com.wojcikowski.kamil.dietapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.wojcikowski.kamil.dietapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class DBUser extends  DatabaseHandler {

    public DBUser(Context context) {
        super(context);
    }

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

}
