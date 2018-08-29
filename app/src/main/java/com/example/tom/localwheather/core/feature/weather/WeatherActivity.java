package com.example.tom.localwheather.core.feature.weather;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.tom.localwheather.R;
import com.example.tom.localwheather.core.base.mvp.BaseActivity;
import com.example.tom.localwheather.core.model.pojo.Place;
import com.example.tom.localwheather.core.model.network.WeatherManager;
import com.example.tom.localwheather.core.util.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherActivity extends BaseActivity<WeatherContract.View, WeatherContract.Presenter>
        implements WeatherContract.View {
    @BindView(R.id.weatherRecyclerView)
    RecyclerView weatherRecyclerView;

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
    }

    @OnClick(R.id.checkWeatherBtnWeatherActivity)
    public void onViewClicked() {
        Place place = new Place();
        WeatherManager weatherManager = new WeatherManager();
        weatherManager.receiveWeather(place)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(place1 ->{
                    Logger.v("temp" + place1.getMain().getTemp());
                    Logger.v("Coord place 1 " + place1.getCoord().getLat());
                    Logger.v("Coord place 1 " + place1.getName());

                });
    }
}
