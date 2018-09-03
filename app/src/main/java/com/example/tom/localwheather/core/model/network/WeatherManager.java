package com.example.tom.localwheather.core.model.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.tom.localwheather.core.model.pojo.City;
import com.example.tom.localwheather.core.model.pojo.Place;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherManager implements WeatherManagerContract{

    private static final String WEATHER_FULL_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "addc4a37960ab6c1aa199a82e36249d8";
    private RestAPI restAPI;
    private static final String UNIT = "metric";

    public WeatherManager() {
        retrofitBuilder();
    }

    private void retrofitBuilder() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Log.v("LOG", "interceptor " + interceptor.toString());


        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WEATHER_FULL_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        restAPI = retrofit.create(RestAPI.class);

    }

    @Override
    public Single<Place> receiveWeather(@NonNull City place) {
        return restAPI.getWeather(place.getLatitude(), place.getLongitude(), API_KEY, UNIT);
    }
}
