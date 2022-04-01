package org.openweatherapp.ui.cities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import org.openweatherapp.R

class MainActivity: AppCompatActivity() {

    private val viewModel: CityViewModel by viewModels {
        CityViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        val adapter = CityAdapter()

        recyclerView.adapter = adapter

        adapter.submitList(viewModel.cities)
    }
}