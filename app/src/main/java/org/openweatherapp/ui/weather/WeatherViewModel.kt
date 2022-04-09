package org.openweatherapp.ui.weather

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.openweatherapp.network.CityApi
import org.openweatherapp.toWeatherUiState
import java.lang.Exception

class WeatherViewModel(private val cityName: String?) : ViewModel() {

    private val _weather = MutableLiveData<WeatherUiState>()
    val weather: LiveData<WeatherUiState> = _weather

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        cityName?.let {
            getWeather(cityName)
        }
    }

    private fun getWeather(cityName: String) {
        viewModelScope.launch {
            try {
                val response = CityApi.retrofitService.getWeather(
                    cityName,
                    "84192d4a1e089b56f61245cf8b67d7ae",
                    "metric"
                )
                _weather.value = response.toWeatherUiState()
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            }
        }
    }

    fun errorShown() {
        _error.value = null
    }
}