package com.menezes.beerapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.menezes.beerapp.model.BeerObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cassiano.menezes on 04/06/2017.
 */

public class BeersDataSource {

    private static final String TAG = BeersDataSource.class.getName();
    private BeerDBHelper dbHelper;
    private SQLiteDatabase database;
    private Context context;
    private String[] allColumns = {BeerDBHelper.ID, BeerDBHelper.CREATED_DATE, BeerDBHelper.BEER_NAME, BeerDBHelper.BEER_DESCRIPTION,
            BeerDBHelper.BEER_LABEL_URL};

    public BeersDataSource(Context context) {
        this.context = context;
        dbHelper = new BeerDBHelper(context);
        open();
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void deleteDb() {
        close();
        dbHelper.deleteDatabase(context);
    }

    public void close() {
        dbHelper.close();
    }

    public void createBeerEntry(BeerObject beerObject) {
        Log.d(TAG, "Adding new beer entry " + beerObject.getName());
        ContentValues values = new ContentValues();

        values.put(BeerDBHelper.BEER_NAME, beerObject.getName());
        values.put(BeerDBHelper.BEER_DESCRIPTION, beerObject.getDescription());
        values.put(BeerDBHelper.BEER_LABEL_URL, beerObject.getLabelUrl());
        values.put(BeerDBHelper.CREATED_DATE, System.currentTimeMillis());

        database.insert(BeerDBHelper.TABLE_BEERS, null, values);

    }

    public List<BeerObject> getFavedBeers() {
        BeerObject beerObject;
        List<BeerObject> list = new ArrayList<>();
        Cursor cursor = database
                .query(BeerDBHelper.TABLE_BEERS, allColumns, null, null, null, null,
                        BeerDBHelper.CREATED_DATE + " DESC");
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            beerObject = new BeerObject();
            beerObject.setId(cursor.getLong(0));
            beerObject.setName(cursor.getString(2));
            beerObject.setDescription(cursor.getString(3));
            beerObject.setLabelUrl(cursor.getString(4));
            list.add(beerObject);
            cursor.moveToNext();
        }
        cursor.close();

        return list;
    }

    public void deleteBeerRow(String name) {
        String[] args = {name};
        Cursor cursor = database.query(BeerDBHelper.TABLE_BEERS, allColumns,
                BeerDBHelper.BEER_NAME + "=?", args, null, null, null);
        cursor.moveToFirst();
        database.delete(BeerDBHelper.TABLE_BEERS, "_id=" + cursor.getLong(0), null);
        cursor.close();
    }
}
