package com.example.tom.localwheather.core.model.db;

class DBScripts {

    public static final String OnCreate = "CREATE TABLE " + DBConstants.DB_TABLE_NAME +
            " (" + DBConstants.DB_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DBConstants.DB_FIELD_LOCALITY + " TEXT NOT NULL, " +
            DBConstants.DB_FIELD_LATITUDE + " TEXT NOT NULL, " +
            DBConstants.DB_FIELD_LONGITUDE + " TEXT NOT NULL);";

    public static final String OnUpgrade = "DROP TABLE IF EXISTS " + DBConstants.DB_TABLE_NAME;
}
