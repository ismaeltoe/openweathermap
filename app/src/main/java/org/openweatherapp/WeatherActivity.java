package org.openweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.openweatherapp.viewmodels.WeatherViewModel;

import java.io.IOException;


public class WeatherActivity extends AppCompatActivity {

    private WeatherViewModel mWeatherViewModel;

    private TextView mCityView;
    private TextView mTempView;
    private TextView mWeatherView;
    private ImageView mIconView;

    public static final String KEY_CITY = "city";

    private String mCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mCityView = findViewById(R.id.city);
        mTempView = findViewById(R.id.temp);
        mWeatherView = findViewById(R.id.weather);
        mIconView = findViewById(R.id.icon);

        if (getIntent().getExtras() != null) {
            mCity = getIntent().getStringExtra(KEY_CITY);
        }

        mWeatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        mWeatherViewModel.getWeather(mCity).observe(this, response -> {

            if (response.isSuccessful() && response.body() != null) {

                try {
                    JSONObject json = new JSONObject(response.body().string());

                    JSONObject weatherObj = json.getJSONArray("weather").getJSONObject(0);

                    String description = weatherObj.getString("description");
                    String icon = weatherObj.getString("icon");

                    JSONObject mainObj = json.getJSONObject("main");

                    double temperature = mainObj.getDouble("temp");

                    mCityView.setText(mCity);
                    mTempView.setText(String.format(getString(R.string.format_temperature), temperature));
                    mWeatherView.setText(description);
                    Picasso.get().load("http://openweathermap.org/img/wn/" + icon + "@2x.png")
                            .into(mIconView);

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}