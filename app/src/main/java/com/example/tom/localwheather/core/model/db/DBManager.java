package com.example.tom.localwheather.core.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.tom.localwheather.core.model.pojo.City;
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

    public void addPlace(String cityName, double latitude, double longitude) {
        String selection = "DBConstants.DB_FIELD_LOCALITY + \"=?\",\n" +
                "                DBConstants.DB_FIELD_LOCALITY + \"=?\",\n" +
                "                DBConstants.DB_FIELD_LOCALITY + \"=?\"";
        Cursor cursor = sqLiteDatabase.query(
                DBConstants.DB_TABLE_NAME,
                new String[] {
                        DBConstants.DB_FIELD_LOCALITY,
                        DBConstants.DB_FIELD_LATITUDE,
                        DBConstants.DB_FIELD_LONGITUDE
                },
                DBConstants.DB_FIELD_LOCALITY + "=?",
                null,
                null,
                null,
                null,
                null
        );

        if (!cursor.moveToFirst()) {
//        if (cursor.moveToFirst()
//                && TextUtils.equals(cursor.getString(cursor.getColumnIndex(DBConstants.DB_FIELD_LOCALITY)), cityName)) {
            ContentValues cv = new ContentValues();
            cv.put(DBConstants.DB_FIELD_LOCALITY, cityName);
            cv.put(DBConstants.DB_FIELD_LATITUDE, latitude);
            cv.put(DBConstants.DB_FIELD_LONGITUDE, longitude);

            Logger.v("addPlace() address.getLocality() " + cityName);
            Logger.v("addPlace() address.getLocality() " + latitude);
            Logger.v("addPlace() address.getLocality() " + longitude);

            sqLiteDatabase.insert(DBConstants.DB_TABLE_NAME, null, cv);
        }
        cursor.close();
    }

//    public List<String> getCities() {}

    public City getCity(@NonNull String cityName) {
        Cursor cursor = sqLiteDatabase.query(
                DBConstants.DB_TABLE_NAME,
                new String[] {
                        DBConstants.DB_FIELD_LOCALITY,
                        DBConstants.DB_FIELD_LATITUDE,
                        DBConstants.DB_FIELD_LONGITUDE
//                        DBConstants.DB_FIELD_WEATHER
                },
                null,
                null,
                null,
                null,
                null,
                null
        );

        City city = new City();
        if (cursor.moveToFirst()) {
            double cityLat = Double.parseDouble(cursor.getString(cursor.getColumnIndex(DBConstants.DB_FIELD_LATITUDE)));
            double cityLng = Double.parseDouble(cursor.getString(cursor.getColumnIndex(DBConstants.DB_FIELD_LONGITUDE)));
//            String cityWeather = cursor.getString(cursor.getColumnIndex(DBConstants.DB_FIELD_WEATHER));

            city.setCity(cityName);
            city.setLatitude(cityLat);
            city.setLongitude(cityLng);
//            city.setWeather(cityWeather);
        }
        cursor.close();
        return city;
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

//        if (selection != null) {
//            getCityLocation(cursor);
//        } else {
//            getFullDatabase(cursor);
//        }
        List<String> addresses = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                String address = String.valueOf(new Address(Locale.getDefault()));
//                address.(cursor.getString(cursor.getColumnIndex(DBConstants.DB_FIELD_LOCALITY)));
                addresses.add(cursor.getString(cursor.getColumnIndex(DBConstants.DB_FIELD_LOCALITY)));
            } while (cursor.moveToNext());
        }

        for (String addres: addresses) {
            Logger.v("Address addres: addresses " + addres);

        }
        return addresses;
    }

    private void getFullDatabase(@NonNull Cursor c) {}

    private void getCityLocation(@NonNull Cursor c) {}



}
