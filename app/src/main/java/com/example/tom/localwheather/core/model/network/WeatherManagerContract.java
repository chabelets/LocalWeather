package com.example.tom.localwheather.core.model.network;

import android.support.annotation.NonNull;

import com.example.tom.localwheather.core.model.pojo.Place;

import io.reactivex.Single;

public interface WeatherManagerContract {

    Single<Place> receiveWeather(@NonNull Place place);
}
