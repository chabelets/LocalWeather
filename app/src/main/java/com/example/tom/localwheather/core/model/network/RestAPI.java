package com.example.tom.localwheather.core.model.network;

import com.example.tom.localwheather.core.model.pojo.City;
import com.example.tom.localwheather.core.model.pojo.Place;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestAPI {

//    api.openweathermap.org/data/2.5/weather?lat=35&lon=139&APPID=addc4a37960ab6c1aa199a82e36249d8
    @GET("weather")
    Single<City> getWeather(@Query("lat") double latitude,
                            @Query("lon") double longitude,
                            @Query("appid") String apiKey,
                            @Query("units") String unit);

//    @POST("users/login")
//    Single<LoginEntity> getLoginUser(@Body LoginEntity user,
//                                     @Header("Authorization") String userToken);
//
//    https://api.backendless.com/D0C2329C-1475-7BEC-FF56-E36FFAC2D600/1C02B61E-F38A-0219-FFA7-760BB45A7000/data/photos_v1
//    @GET("data/{table_name}")
//    Single <List<DataGetResponse>> getPhoto(@Path(value = "table_name", encoded = true) String tableName,
//                                            @Header("Authorization") String userToken,
//                                            @Query(value = "pageSize", encoded = true) int pageSize,
//                                            @Query(value = "offset", encoded = true) int offset);
//
//
//    @DELETE("data/{table_name}/{object-id}")
//    void deleteItem(@Path(value = "table_name", encoded = true) String tableName,
//                    @Header("Authorization") String userToken);
}

