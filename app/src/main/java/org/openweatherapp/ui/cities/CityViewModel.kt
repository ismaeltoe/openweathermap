package org.openweatherapp.ui.cities

import androidx.lifecycle.ViewModel

class CityViewModel : ViewModel() {

    val cities = listOf(
        City("Abidjan"),
        City("Lyon"),
        City("Londres")
    )
}