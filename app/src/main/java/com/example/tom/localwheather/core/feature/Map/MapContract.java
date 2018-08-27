package com.example.tom.localwheather.core.feature.Map;

import com.example.tom.localwheather.core.base.mvp.MVPPresenter;
import com.example.tom.localwheather.core.base.mvp.MVPView;

public interface MapContract {

    interface View extends MVPView {
    }

    interface Presenter extends MVPPresenter<View> {
    }
}
