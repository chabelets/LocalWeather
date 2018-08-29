package com.example.tom.localwheather.core.feature.weather;

import com.example.tom.localwheather.core.base.mvp.MVPPresenter;
import com.example.tom.localwheather.core.base.mvp.MVPView;
import com.example.tom.localwheather.core.feature.places.PlacesContract;

public interface WeatherContract {

    interface View extends MVPView {

    }

    interface Presenter extends MVPPresenter<WeatherContract.View> {

    }
}
