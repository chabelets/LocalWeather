package com.example.tom.localwheather.core.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<V extends MVPView, P extends MVPPresenter<V>>
        extends AppCompatActivity implements MVPView {
    protected V view;
    protected P presenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = createView();
        presenter = createPresenter();
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        presenter.onAttach(view);
    }

    protected abstract V createView();

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        presenter.onDetach(view);
        unbinder.unbind();
        super.onDestroy();
    }
}
