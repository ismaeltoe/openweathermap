package org.openweatherapp.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.openweatherapp.network.CityApi
import org.openweatherapp.network.models.CityApiResponse
import java.lang.Exception

class WeatherViewModel : ViewModel() {

    private val _weather = MutableLiveData<CityApiResponse>()
    val weather: LiveData<CityApiResponse> = _weather

    init {
        getWeather()
    }

    private fun getWeather() {
        viewModelScope.launch {
            try {
                val response = CityApi.retrofitService.getWeather("Abidjan", "84192d4a1e089b56f61245cf8b67d7ae", "metric")
                _weather.value = response
            } catch (e: Exception) {
                TODO()
            }
        }
    }
}