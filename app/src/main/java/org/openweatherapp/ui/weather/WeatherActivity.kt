package org.openweatherapp.ui.weather

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import org.openweatherapp.R
import org.openweatherapp.databinding.ActivityWeatherBinding
import org.openweatherapp.ui.cities.EXTRA_CITY_NAME

class WeatherActivity: AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather)

        val cityName = intent.getStringExtra(EXTRA_CITY_NAME)

        val viewModel: WeatherViewModel by viewModels {
            WeatherViewModelFactory(cityName)
        }

        binding.cityName = cityName
        binding.weatherViewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.error.observe(this, Observer { error ->
            error?.let {
                Snackbar.make(binding.root, error, Snackbar.LENGTH_LONG).show()
                viewModel.errorShown()
            }
        })
    }
}