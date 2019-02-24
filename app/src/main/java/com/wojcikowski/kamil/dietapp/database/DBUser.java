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

    private String usernameColumn = "username";
    private String passColumn = "password";
    private String emailColumn = "email";
    private String userIdColumn = "userid";

    public long insertUser(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(usernameColumn, user.getUsername());
        contentValues.put(passColumn, user.getPassword());
        contentValues.put(emailColumn, user.getEmail());

        return db.insert(DB_USER, null, contentValues);
    }

    public boolean updateUser(User user) {
        String where = userIdColumn+"=" + user.getUserid();
        ContentValues contentValues = new ContentValues();
        contentValues.put(emailColumn, user.getEmail());
        return db.update(DB_USER, contentValues, where, null) > 0;
    }

    public boolean deleteUser(long id) {
        String where = userIdColumn+"=" + id;
        return db.delete(DB_USER, where, null) > 0;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String[] columns = {userIdColumn, usernameColumn, passColumn, emailColumn};
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

    public User findUserById(long id) {
        String[] columns = {userIdColumn, usernameColumn, passColumn, emailColumn};
        String where = userIdColumn+"=" + id;
        Cursor cursor = db.query(DB_USER, columns, where, null, null, null, null);
        User user = null;
        if(cursor != null && cursor.moveToFirst()) {
            user = new User(id, cursor.getString(1), cursor.getString(2), cursor.getString(3));
            cursor.close();
        }
        return user;
    }

    public User findUserByUsername (String username) {
        String[] columns = {userIdColumn, usernameColumn, passColumn, emailColumn};
        String where = "username='" + username + "'";
        Cursor cursor = db.query(DB_USER, columns, where, null, null, null, null);
        User user = null;
        if(cursor != null && cursor.moveToFirst()) {
            user = new User(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            cursor.close();
        }
        return user;
    }

    public User findUserByEmail (String email) {
        String[] columns = {userIdColumn, usernameColumn, passColumn, emailColumn};
        String where = "email='" + email + "'";
        Cursor cursor = db.query(DB_USER, columns, where, null, null, null, null);
        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            user = new User(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            cursor.close();
        }
        return user;
    }


}
