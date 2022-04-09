package org.openweatherapp

import org.openweatherapp.network.models.CityApiResponse
import org.openweatherapp.ui.weather.WeatherUiState

fun CityApiResponse.toWeatherUiState(): WeatherUiState {
    return WeatherUiState(
        main.temp,
        weather[0].icon,
        weather[0].description
    )
}