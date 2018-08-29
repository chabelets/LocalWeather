package com.example.tom.localwheather.core.feature.map;

import com.example.tom.localwheather.core.base.mvp.MVPPresenter;
import com.example.tom.localwheather.core.base.mvp.MVPView;

public interface MapContract {

    interface View extends MVPView {
        void onWeatherFetchSuccessful();
    }

    interface Presenter extends MVPPresenter<View> {
        void onWeatherFetchCall();
    }
}
