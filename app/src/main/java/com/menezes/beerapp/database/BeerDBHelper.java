package com.menezes.beerapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by cassiano.menezes on 04/06/2017.
 */

public class BeerDBHelper extends SQLiteOpenHelper {

    public static final String TABLE_BEERS = "beers_table";
    protected static final String ID = "_id";
    protected static final String CREATED_DATE = "created";
    protected static final String BEER_LABEL_URL = "beer_label_url";
    protected static final String BEER_NAME = "web_recent_page_url";
    protected static final String BEER_DESCRIPTION = "beer_description";
    private static final String DB_NAME = "beer";
    private static final String TAG = BeerDBHelper.class.getName();
    private static final int DB_VERSION = 1;

    private static final String CREATE_TABLE_BEERS = "CREATE TABLE " + TABLE_BEERS + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CREATED_DATE + " INTEGER, " +
            BEER_NAME + " TEXT, " +
            BEER_DESCRIPTION + " TEXT, " +
            BEER_LABEL_URL + " TEXT);";

    public BeerDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_BEERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.d(TAG, "Updating beers database from version " + oldVersion + " to " + newVersion);
    }

    public static String getID() {
        return ID;
    }

    public static void deleteDatabase(Context context) {
        Log.d(TAG, "Deleting beer database");
        context.deleteDatabase(DB_NAME);
    }
}
