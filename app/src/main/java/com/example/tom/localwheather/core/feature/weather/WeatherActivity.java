package com.example.tom.localwheather.core.feature.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.tom.localwheather.R;
import com.example.tom.localwheather.core.base.mvp.BaseActivity;
import com.example.tom.localwheather.core.model.db.DBManager;
import com.example.tom.localwheather.core.model.network.WeatherManager;
import com.example.tom.localwheather.core.model.pojo.City;
import com.example.tom.localwheather.core.util.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WeatherActivity extends BaseActivity<WeatherContract.View, WeatherContract.Presenter>
        implements WeatherContract.View {
    @BindView(R.id.weatherRecyclerView)
    RecyclerView weatherRecyclerView;
    @BindView(R.id.cityItem)
    TextView cityItem;
    String city;

    @Override
    protected WeatherContract.View createView() {
        return this;
    }

    @Override
    protected WeatherContract.Presenter createPresenter() {
        return new WeatherPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_weather;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        cityItem.setText("Do you want to know the weather in " + city + "?");
    }

    @OnClick(R.id.checkWeatherBtnWeatherActivity)
    public void onViewClicked() {
        DBManager dbManager = new DBManager(this);
        City place = dbManager.getCity(city);
//        Logger.v("receiveWeather(place) temp" + place.getWeather());
        Logger.v("receiveWeather(place) city" + place.getCity());
        Logger.v("receiveWeather(place) Coord place 1 " + place.getLatitude());
        Logger.v("receiveWeather(place) Coord place 1 " + place.getLongitude());
        WeatherManager weatherManager = new WeatherManager();
        weatherManager.receiveWeather(place)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<City>() {
                    @Override
                    public void accept(City place1) throws Exception {
//                        Logger.v("receiveWeather(place1) temp" + place1.getWeather());
                        Logger.v("receiveWeather(place1) city" + place1.getCity());
                        Logger.v("receiveWeather(place1) Coord place 1 " + place1.getLatitude());
                        Logger.v("receiveWeather(place1) Coord place 1 " + place1.getLongitude());

                    }
                });
    }
}
