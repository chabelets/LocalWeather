package com.example.tom.localwheather.core.feature.weather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.tom.localwheather.core.base.adapter.BaseAdapter;
import com.example.tom.localwheather.core.feature.places.LocationAdapter;

public class WeatherAdapter extends BaseAdapter<String, WeatherAdapter.WeatherViewHolder> {

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {

    }

    static class WeatherViewHolder extends RecyclerView.ViewHolder {
        public WeatherViewHolder(View itemView) {
            super(itemView);
        }
    }

}
