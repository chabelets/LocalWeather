package com.example.tom.localwheather.core.feature.Places;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tom.localwheather.R;
import com.example.tom.localwheather.core.base.mvp.BaseActivity;
import com.example.tom.localwheather.core.util.Logger;
import com.example.tom.localwheather.core.util.Starter;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlacesActivity extends BaseActivity<PlacesContract.View, PlacesContract.Presenter>
        implements PlacesContract.View {

    private static final int PLACE_PICKER_REQUEST = 13;

    @Override
    protected PlacesContract.View createView() {
        return this;
    }

    @Override
    protected PlacesContract.Presenter createPresenter() {
        return new PlacesPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_places;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.addPlaceBtnPlaceActivity)
    public void onAddPlaceBtnClicked() {
        Starter.startMapActivity(this);
//        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//        try {
//            startActivityForResult(builder.build(PlacesActivity.this), PLACE_PICKER_REQUEST);
//        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
//            e.printStackTrace();
//            Logger.v("GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException"+ e);
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == PLACE_PICKER_REQUEST){
            Place place = PlacePicker.getPlace(this, data);
            String toastMsg = String.format("Place: %s", place.getName());
            Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            place.getLatLng();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
