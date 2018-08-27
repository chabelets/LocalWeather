package com.example.tom.localwheather.core.model.pojo;

import com.google.gson.annotations.SerializedName;

public class PlaceD {

    @SerializedName("coord")
    private Coord coord;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }
}
