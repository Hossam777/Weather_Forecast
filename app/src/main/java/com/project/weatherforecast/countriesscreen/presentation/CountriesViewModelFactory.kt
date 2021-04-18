package com.project.weatherforecast.countriesscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.weatherforecast.countriesscreen.domain.CountriesDomain

@Suppress("UNCHECKED_CAST")
class CountriesViewModelFactory (private val countriesDomain: CountriesDomain): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CountriesViewModel(countriesDomain) as T
    }
}