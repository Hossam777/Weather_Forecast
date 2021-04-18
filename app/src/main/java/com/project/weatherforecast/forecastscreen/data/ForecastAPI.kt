package com.project.weatherforecast.forecastscreen.data

import com.project.weatherforecast.forecastscreen.data.entities.ForecastResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastAPI {

    @GET("forecast?appid=0fbf44b349ff113e3291e54c8acef773&units=metric")
    fun fetchForecast(
        @Query("q") capital: String
    ): Single<ForecastResponse>

    companion object{
        operator fun invoke(): ForecastAPI {
            return Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ForecastAPI::class.java)
        }
    }
}