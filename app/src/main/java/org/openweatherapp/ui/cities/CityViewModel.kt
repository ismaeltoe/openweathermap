package org.openweatherapp.ui.cities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CityViewModel : ViewModel() {

    val cities = listOf(
        City("Abidjan"),
        City("Lyon"),
        City("Londres")
    )

    private val _navigateToCityDetail = MutableLiveData<String>()
    val navigateToCityDetail: LiveData<String> = _navigateToCityDetail

    fun onCityClicked(name: String) {
        _navigateToCityDetail.value = name
    }

    fun onCityDetailNavigated() {
        _navigateToCityDetail.value = null
    }
}