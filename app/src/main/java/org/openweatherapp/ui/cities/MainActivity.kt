package org.openweatherapp.ui.cities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.openweatherapp.R
import org.openweatherapp.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private val viewModel: CityViewModel by viewModels {
        CityViewModelFactory()
    }

    // derived from the name of the layout file, that is, activity_main + Binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val adapter = CityAdapter()

        binding.recyclerview.adapter = adapter

        adapter.submitList(viewModel.cities)
    }
}