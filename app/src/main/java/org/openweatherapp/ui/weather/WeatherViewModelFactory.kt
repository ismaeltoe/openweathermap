package org.openweatherapp.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WeatherViewModelFactory(private val cityName: String?): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(cityName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}