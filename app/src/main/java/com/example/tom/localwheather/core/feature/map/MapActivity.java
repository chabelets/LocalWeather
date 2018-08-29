package com.example.tom.localwheather.core.feature.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.tom.localwheather.R;
import com.example.tom.localwheather.core.base.mvp.BaseActivity;
import com.example.tom.localwheather.core.model.db.DBManager;
import com.example.tom.localwheather.core.model.locationManager.WeatherLocationManager;
import com.example.tom.localwheather.core.model.pojo.Coord;
import com.example.tom.localwheather.core.model.pojo.Place;
import com.example.tom.localwheather.core.model.network.WeatherManager;
import com.example.tom.localwheather.core.util.Logger;
import com.example.tom.localwheather.core.util.Starter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MapActivity extends BaseActivity<MapContract.View, MapContract.Presenter>
        implements MapContract.View {
    @BindView(R.id.mapView)
    MapView mapView;
    private GoogleMap map;
    private WeatherLocationManager locationManager;
    private Place place = new Place();
    private MarkerOptions markerOptions;
    private Geocoder geocoder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeviceLocation();
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                MapActivity.this.map = mMap;
                if (Build.VERSION.SDK_INT >= 23) {
                    if (MapActivity.this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                            MapActivity.this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    mMap.setMyLocationEnabled(true);
                }

                MapActivity.this.onViewClicked();
                //TODO permission class
            }
        });
    }

    @OnClick(R.id.mapView)
    public void onViewClicked() {
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                locationManager.stopLocationUpdates();
                map.clear();
                Coord coord = new Coord();
                coord.setLat(latLng.latitude);
                place.setCoord(coord);
                coord.setLon(latLng.longitude);
                place.setCoord(coord);
                MapActivity.this.createMarker(place);
                Logger.v("Coord onMapClick latLng " + place.getCoord().getLat() + " " + place.getCoord().getLon());
            }
        });
    }

    private void getDeviceLocation() {
        locationManager = new WeatherLocationManager();
        locationManager.startLocationUpdates(this)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(location -> {
                    Logger.v("Coord getDeviceLocation " + location.getLongitude());
                    Coord coord = new Coord();
                    coord.setLat(location.getLatitude());
                    place.setCoord(coord);
                    coord.setLon(location.getLongitude());
                    place.setCoord(coord);
                    createMarker(place);
                });
    }

    private void createMarker(@NonNull final Place place) {
        double markerLatitude = place.getCoord().getLat();
        double markerLongitude = place.getCoord().getLon();
        LatLng myCoordinate = new LatLng(markerLatitude, markerLongitude);
        markerOptions = new MarkerOptions()
                .position(myCoordinate);

        map.addMarker(markerOptions);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    public void onPause() {
        if (mapView != null) {
            mapView.onPause();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (mapView != null) {
            try {
                mapView.onDestroy();
            } catch (NullPointerException e) {
                Logger.v("Error while attempting MapView.onDestroy(), ignoring exception" + e);
            }
        }
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mapView != null) {
            mapView.onLowMemory();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mapView != null) {
            mapView.onSaveInstanceState(outState);
        }
    }

    @Override
    protected MapContract.View createView() {
        return this;
    }

    @Override
    protected MapContract.Presenter createPresenter() {
        return new MapPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_map;
    }


    @OnClick({R.id.okButton, R.id.cancelButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.okButton:
                WeatherManager weatherManager = new WeatherManager();
                weatherManager.receiveWeather(place)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(place1 ->{
                            Logger.v("temp" + place1.getMain().getTemp());
                        Logger.v("Coord place 1 " + place1.getCoord().getLat());
                            Logger.v("Coord place 1 " + place1.getName());

                        });

                getCityFromLocation();
                Starter.startPlacesActivity(this);
                break;
            case R.id.cancelButton:
                Starter.startWeatherActivity(this);
                break;
        }
    }

    void getCityFromLocation(){
        geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder
                        .getFromLocation(
                                place.getCoord().getLat(),
                                place.getCoord().getLon(),
                                1);
        } catch (IOException e) {
            e.printStackTrace();
            Logger.v(e.getMessage());
        }
        Address obj = addresses.get(0);
//        DBManager dbManager = App.getApp(this).getDBManager();
        DBManager dbManager = new DBManager(this);
        Logger.v("getLocality " + obj.getLocality());
        dbManager.addPlace(obj.getLocality(), obj.getLatitude(), obj.getLongitude());
        dbManager.getPlaceListFromDB();
        Starter.startPlacesActivity(this);
    }
    @Override
    public void onWeatherFetchSuccessful() {

    }
}
