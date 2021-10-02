package org.openweatherapp.services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {

    String URL = "https://api.openweathermap.org/data/2.5/";

    @GET("weather")
    Call<ResponseBody> getWeather(
            @Query("q") String city,
            @Query("appid") String api_key,
            @Query("units") String units
    );
}
