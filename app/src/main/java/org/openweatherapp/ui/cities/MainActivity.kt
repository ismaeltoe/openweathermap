package org.openweatherapp.ui.cities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import org.openweatherapp.R
import org.openweatherapp.databinding.ActivityMainBinding
import org.openweatherapp.ui.weather.WeatherActivity

const val EXTRA_CITY_NAME = "org.openweatherapp.CITY_NAME"

class MainActivity: AppCompatActivity() {

    private val viewModel: CityViewModel by viewModels {
        CityViewModelFactory()
    }

    // derived from the name of the layout file, that is, activity_main + Binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val adapter = CityAdapter(CityListener { cityName ->
            viewModel.onCityClicked(cityName)
        })

        binding.recyclerview.adapter = adapter

        adapter.submitList(viewModel.cities)

        binding.lifecycleOwner = this

        viewModel.navigateToCityDetail.observe(this, Observer { cityName ->
            cityName?.let {
                val intent = Intent(this, WeatherActivity::class.java).apply {
                    putExtra(EXTRA_CITY_NAME, cityName)
                }
                startActivity(intent)
                viewModel.onCityDetailNavigated()
            }
        })
    }
}