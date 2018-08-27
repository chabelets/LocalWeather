package com.example.tom.localwheather.core.model.locationManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.Observable;


public class WeatherLocationManager {

    private FusedLocationProviderClient locationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private static final int UPDATE_INTERVAL_NORMAL = 2000;
    private static final int UPDATE_INTERVAL_FAST = 1000;

    @SuppressLint("MissingPermission")
    public Observable<Location> startLocationUpdates(@NonNull Context context) {
        return Observable.create(emitter -> {
            locationClient = LocationServices.getFusedLocationProviderClient(context);

            locationRequest = new LocationRequest()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(UPDATE_INTERVAL_NORMAL)
                    .setFastestInterval(UPDATE_INTERVAL_FAST);

            locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    emitter.onNext(locationResult.getLastLocation());
                }
            };

            locationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
        });
    }

    public void stopLocationUpdates() {
        locationClient.removeLocationUpdates(locationCallback);
    }



}
