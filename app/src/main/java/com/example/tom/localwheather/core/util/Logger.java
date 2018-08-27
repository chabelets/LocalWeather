package com.example.tom.localwheather.core.util;

import android.util.Log;

import com.example.tom.localwheather.BuildConfig;


public class Logger {

    public static void v(String message) {
        if (BuildConfig.DEBUG){
            Log.v("myLogs", message);
        }
    }
}
