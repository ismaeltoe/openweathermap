package org.openweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.openweatherapp.adapters.CityListAdapter;
import org.openweatherapp.models.City;
import org.openweatherapp.viewmodels.CityViewModel;

public class MainActivity extends AppCompatActivity {

    private CityViewModel mCityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        final CityListAdapter adapter = new CityListAdapter(new CityListAdapter.CityDiff());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        mCityViewModel = new ViewModelProvider(this).get(CityViewModel.class);

        adapter.submitList(mCityViewModel.getCities());

        adapter.setOnItemClickListener(new CityListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                City city = adapter.getCurrentList().get(position);
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                intent.putExtra(WeatherActivity.KEY_CITY, city.getCity());
                startActivity(intent);
            }
        });
    }
}