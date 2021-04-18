package com.project.weatherforecast.countriesscreen.data

import com.project.weatherforecast.countriesscreen.data.entities.Names
import io.reactivex.Single

class CountriesRepository(private val countryNameAPI: CountryNameAPI) {

    fun loadCountries(countryName: String): Single<Names> {
        return countryNameAPI.loadCountries(countryName)
            .doOnError {
                //return Specialized Exception
            }
    }

}