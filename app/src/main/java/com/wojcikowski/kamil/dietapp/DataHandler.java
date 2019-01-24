package com.wojcikowski.kamil.dietapp;

public class DataHandler {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "dietapp.db";
    private static final String DB_USER = "user";
    private static final String DB_USER_DETAILS = "user_details";
    private static final String DB_WEIGHTS = "weights";

    private static final String DB_CREATE_USER_TABLE =
            "CREATE TABLE " + DB_USER + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " username TEXT NOT NULL," +
                    " password TEXT NOT NULL," +
                    " email TEXT," +
                    " user_details_id INTEGER," +
                    " FOREIGN KEY(user_details_id) REFERENCES user_details(id));";

    private static final String DROP_USER_TABLE =
            "DROP TABLE IF EXISTS " + DB_USER;

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

}
