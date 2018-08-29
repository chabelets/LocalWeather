package com.example.tom.localwheather.core.feature.places;

import android.location.Address;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tom.localwheather.R;
import com.example.tom.localwheather.core.base.adapter.BaseAdapter;
import com.example.tom.localwheather.core.model.db.DBManager;
import com.example.tom.localwheather.core.model.pojo.Place;
import com.example.tom.localwheather.core.util.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationAdapter extends BaseAdapter<String, LocationAdapter.LocationViewHolder> {

    LocationAdapter(@NonNull List<String> items) {
        super(items);
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocationViewHolder(inflate(parent, R.layout.item_place));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
          holder.placeItem.setText(getItems().get(position));
    }

    static class LocationViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.placeItemTextView)
        TextView placeItem;

        LocationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
