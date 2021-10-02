package org.openweatherapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import org.openweatherapp.repositories.WeatherRepository;
import org.openweatherapp.services.WebService;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherViewModel extends ViewModel {

    private WeatherRepository mRepository;

    private final LiveData<Response<ResponseBody>> mWeather;

    public WeatherViewModel(String city) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        WebService service = new Retrofit.Builder()
                .baseUrl(WebService.URL)
                .client(client)
                .build()
                .create(WebService.class);

        mRepository = new WeatherRepository(service);

        mWeather = mRepository.getWeather(city);
    }

    public LiveData<Response<ResponseBody>> getWeather() { return mWeather; }
}
