package com.example.tom.localwheather.core.base.mvp;

import android.support.annotation.NonNull;
import android.view.View;

public interface MVPPresenter<V extends MVPView> {
    void onAttach(@NonNull V view);

    void onDetach(@NonNull V view);

    V getView();
}
