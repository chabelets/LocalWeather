package com.example.tom.localwheather.core.feature.places;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.tom.localwheather.R;
import com.example.tom.localwheather.core.base.mvp.BaseActivity;

import com.example.tom.localwheather.core.feature.places.PlacesContract;
import com.example.tom.localwheather.core.feature.places.PlacesPresenter;
import com.example.tom.localwheather.core.model.db.DBManager;
import com.example.tom.localwheather.core.util.Starter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlacesActivity extends BaseActivity<PlacesContract.View, PlacesContract.Presenter>
        implements PlacesContract.View {

    List<Address> city;
    @BindView(R.id.placeRecyclerView)
    RecyclerView placeRecyclerView;
    private LocationAdapter locationAdapter;

    void setLocationAdapter(List<String> address){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        placeRecyclerView.setHasFixedSize(true);
        placeRecyclerView.setLayoutManager(layoutManager);
        locationAdapter = new LocationAdapter(address);
        placeRecyclerView.setAdapter(locationAdapter);
        locationAdapter.notifyDataSetChanged();
    }
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
        DBManager dbManager = new DBManager(this);
        setLocationAdapter(dbManager.getPlaceListFromDB());
    }

    @Override
    protected void onResume() {
        super.onResume();
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

}
