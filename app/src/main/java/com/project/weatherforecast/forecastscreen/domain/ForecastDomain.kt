package com.project.weatherforecast.forecastscreen.domain

import com.project.weatherforecast.forecastscreen.data.ForecastRepository
import com.project.weatherforecast.forecastscreen.data.entities.ForecastResponse
import com.project.weatherforecast.forecastscreen.data.entities.Main
import io.reactivex.Single

class ForecastDomain(private val forecastRepository: ForecastRepository) {

    fun fetchForecast(capital: String): Single<MutableList<Main>> {
        return forecastRepository.fetchForecast(capital)
            .map {
                handleResult(it)
            }
    }

    private fun handleResult(it: ForecastResponse): MutableList<Main> {
        var list:MutableList<Main> = mutableListOf()
        it.list.forEach { listItem ->
            list.add(listItem.main)
        }
        return list
    }
}