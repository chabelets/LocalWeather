package com.example.tom.localwheather.core.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;

import com.example.tom.localwheather.core.util.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DBManager {

    private SQLiteDatabase sqLiteDatabase;

    public DBManager(Context context) {
        AppDBHelper dbHelper = new AppDBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void addPlace(String city, double latitude, double longitude){
        ContentValues cv = new ContentValues();
        cv.put(DBConstants.DB_FIELD_LOCALITY, city);
        cv.put(DBConstants.DB_FIELD_LATITUDE, latitude);
        cv.put(DBConstants.DB_FIELD_LONGITUDE, longitude);

        Logger.v("addPlace() address.getLocality() " + city);
        sqLiteDatabase.insert(DBConstants.DB_TABLE_NAME, null, cv);
    }

    public List<String> getPlaceListFromDB(){
        Cursor cursor = sqLiteDatabase.query(
                DBConstants.DB_TABLE_NAME,
                new String[]{
                        DBConstants.DB_FIELD_LOCALITY,
                        DBConstants.DB_FIELD_LATITUDE,
                        DBConstants.DB_FIELD_LONGITUDE
                },
                null,
                null,
                null,
                null,
                null,
                null
        );

        List<String> addresses = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                String address = String.valueOf(new Address(Locale.getDefault()));
//                address.(cursor.getString(cursor.getColumnIndex(DBConstants.DB_FIELD_LOCALITY)));
                addresses.add(cursor.getString(cursor.getColumnIndex(DBConstants.DB_FIELD_LOCALITY)));

                Logger.v("getPlaceListFromDB() addresses.add(address) " + address);
            } while (cursor.moveToNext());
        }

        for (String addres: addresses) {
            Logger.v("Address addres: addresses " + addres);

        }
        return addresses;
    }

}
