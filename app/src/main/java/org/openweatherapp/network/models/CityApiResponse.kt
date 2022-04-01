package org.openweatherapp.network.models

data class CityApiResponse (
    val weather: List<Weather>,
    val main: Main
)