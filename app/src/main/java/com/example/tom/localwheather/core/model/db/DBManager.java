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

    public void addPlace(Address address){
        ContentValues cv = new ContentValues();
        cv.put(DBConstants.DB_FIELD_LOCALITY, address.getLocality());

        Logger.v("addPlace() address.getLocality() " + address.getLocality());
        sqLiteDatabase.insert(DBConstants.DB_TABLE_NAME, null, cv);
    }

    public List<Address> getPlaceListFromDB(){
        Cursor cursor = sqLiteDatabase.query(
                DBConstants.DB_TABLE_NAME,
                new String[]{
                        DBConstants.DB_FIELD_LOCALITY
                },
                null,
                null,
                null,
                null,
                null,
                null
        );

        List<Address> addresses = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                Address address = new Address(new Locale(Locale.getDefault(), Locale.));
                address.setLocality(cursor.getString(cursor.getColumnIndex(DBConstants.DB_FIELD_LOCALITY)));
                addresses.add(address);
                Logger.v("getPlaceListFromDB() addresses.add(address) " + address);
            } while (cursor.moveToNext());
        }
        return addresses;
    }

}
