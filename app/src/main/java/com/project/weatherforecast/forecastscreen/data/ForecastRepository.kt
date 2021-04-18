package com.project.weatherforecast.forecastscreen.data

import com.project.weatherforecast.forecastscreen.data.entities.ForecastResponse
import io.reactivex.Single

class ForecastRepository(private val forecastAPI: ForecastAPI) {
    fun fetchForecast(capital: String): Single<ForecastResponse> {
        return forecastAPI.fetchForecast(capital)
    }
}