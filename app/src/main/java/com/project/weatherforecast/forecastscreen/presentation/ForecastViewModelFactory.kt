package com.project.weatherforecast.forecastscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.weatherforecast.forecastscreen.domain.ForecastDomain

@Suppress("UNCHECKED_CAST")
class ForecastViewModelFactory (private val forecastDomain: ForecastDomain): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ForecastViewModel(forecastDomain) as T
    }
}