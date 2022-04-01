package org.openweatherapp.ui.cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CityViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CityViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}