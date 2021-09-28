package org.openweatherapp.models;

import androidx.annotation.NonNull;

public class City {

    private String mCity;

    public City(@NonNull String city) {
        this.mCity = city;
    }

    public String getCity() {
        return mCity;
    }
}
