package org.openweatherapp.viewmodels;

import androidx.lifecycle.ViewModel;

import org.openweatherapp.models.City;

import java.util.ArrayList;
import java.util.List;

public class CityViewModel extends ViewModel {

    private List<City> mCities;

    public CityViewModel() {
        mCities = new ArrayList<>();
        mCities.add(new City("Abidjan"));
        mCities.add(new City("Lyon"));
        mCities.add(new City("Londres"));
    }

    public List<City> getCities() { return mCities; }
}
