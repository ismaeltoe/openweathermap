package org.openweatherapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.openweatherapp.network.models.CityApiResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): CityApiResponse
}

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level =  HttpLoggingInterceptor.Level.BODY
}

private val okHttpClient = OkHttpClient.Builder()
    .addNetworkInterceptor(httpLoggingInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

object CityApi {
    val retrofitService: CityApiService by lazy {
        retrofit.create(CityApiService::class.java)
    }
}