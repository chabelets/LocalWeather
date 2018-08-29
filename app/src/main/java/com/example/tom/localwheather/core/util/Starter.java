package com.example.tom.localwheather.core.util;

import android.content.Context;
import android.content.Intent;

import com.example.tom.localwheather.core.feature.map.MapActivity;
import com.example.tom.localwheather.core.feature.places.PlacesActivity;
import com.example.tom.localwheather.core.feature.weather.WeatherActivity;

public class Starter {
    public static void startPlacesActivity(Context context) {
        Intent intent = new Intent(context, PlacesActivity.class);
        context.startActivity(intent);
    }

    public static void startMapActivity(Context context) {
        Intent intent = new Intent(context, MapActivity.class);
        context.startActivity(intent);
    }

    public static void startWeatherActivity(Context context) {
        Intent intent = new Intent(context, WeatherActivity.class);
        context.startActivity(intent);
    }
}
