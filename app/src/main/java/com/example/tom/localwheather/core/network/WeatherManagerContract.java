package com.example.tom.localwheather.core.network;

import android.support.annotation.NonNull;

import com.example.tom.localwheather.core.model.pojo.Place;
import com.example.tom.localwheather.core.model.pojo.PlaceD;

import io.reactivex.Single;

public interface WeatherManagerContract {

    Single<Place> receiveWeather(@NonNull Place place);
}
