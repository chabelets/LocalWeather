package com.example.tom.localwheather.core.feature.places;

import com.example.tom.localwheather.core.base.mvp.MVPPresenter;
import com.example.tom.localwheather.core.base.mvp.MVPView;

public interface PlacesContract {

    interface View extends MVPView{

    }

    interface Presenter extends MVPPresenter<View>{

    }

}
