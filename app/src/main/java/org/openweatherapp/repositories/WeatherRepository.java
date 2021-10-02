package org.openweatherapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.openweatherapp.Utils;
import org.openweatherapp.services.WebService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    private WebService mService;

    public WeatherRepository(WebService service) {
        this.mService = service;
    }

    public LiveData<Response<ResponseBody>> getWeather(String city) {

        final MutableLiveData<Response<ResponseBody>> data = new MutableLiveData<>();

        mService.getWeather(city, Utils.API_KEY, "metric").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                data.setValue(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // TODO
            }
        });

        return data;
    }
}
