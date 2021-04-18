package com.project.weatherforecast.countriesscreen.domain

import com.project.weatherforecast.countriesscreen.data.CountriesRepository
import com.project.weatherforecast.countriesscreen.data.entities.Names
import io.reactivex.Single

class CountriesDomain (private val countriesRepository: CountriesRepository) {
    fun loadCountries(countryName: String): Single<Names> {
        return countriesRepository.loadCountries(countryName)
            .map {
                handleResult(it)
            }
    }

    private fun handleResult(it: Names): Names {
        return it
    }
}