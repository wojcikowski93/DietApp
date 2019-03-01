package com.wojcikowski.kamil.dietapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.wojcikowski.kamil.dietapp.model.UserDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBDetails extends DatabaseHandler {

    private static final String DEBUG_TAG = "DBDetails";

    public DBDetails(Context context) {
        super(context);
    }

    private String detailsIDColumn = "detailsid";
    private String userIDColumn = "userid";
    private String genderColumn = "gender";
    private String heightColumn = "height";
    private String targetWeightColumn = "targetweight";
    private String birthdayColumn = "birthday";

    public long insertDetails(UserDetails userDetails) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(userIDColumn, userDetails.getUserID());
        contentValues.put(genderColumn, userDetails.getGender());
        contentValues.put(birthdayColumn, userDetails.getBirthday());
        contentValues.put(heightColumn, userDetails.getHeight());
        contentValues.put(targetWeightColumn, userDetails.getTargetWeight());

        return db.insert(DB_DETAILS, null, contentValues);
    }

    public boolean updateDetails(UserDetails userDetails) {
        String where = detailsIDColumn+"=" + userDetails.getDetailsid();
        ContentValues contentValues = new ContentValues();
        contentValues.put(genderColumn, userDetails.getGender());
        contentValues.put(birthdayColumn, userDetails.getBirthday());
        contentValues.put(heightColumn, userDetails.getHeight());
        contentValues.put(targetWeightColumn, userDetails.getTargetWeight());

        return db.update(DB_DETAILS, contentValues, where, null) > 0;
    }

    public boolean deleteDetails(long id) {
        String where = detailsIDColumn+"=" + id;
        return db.delete(DB_DETAILS, where, null) > 0;
    }

    public UserDetails findDetailsByUserID(long userID) {
        String[] columns = {detailsIDColumn, userIDColumn, genderColumn, birthdayColumn, heightColumn, targetWeightColumn};
        String where = userIDColumn+"=" + userID;
        Cursor cursor = db.query(DB_DETAILS, columns, where, null, null, null, null);
        UserDetails userDetails = null;
        if(cursor != null && cursor.moveToFirst()) {
            userDetails = new UserDetails(cursor.getLong(0),
                    userID,
                    cursor.getString(2),
                    getBirthdayDate(cursor),
                    cursor.getInt(4),
                    cursor.getDouble(5));
            cursor.close();
        }
        return userDetails;
    }

    private Date getBirthdayDate(Cursor cursor) {
        Date birthday = null;
        try {
            birthday = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(cursor.getString(3));
        } catch (ParseException e) {
            Log.e(DEBUG_TAG, e.getMessage());
        }
        return birthday;
    }

}
