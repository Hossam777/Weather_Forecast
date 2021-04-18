package com.project.weatherforecast.countriesscreen.di

import android.app.Activity
import com.project.weatherforecast.countriesscreen.data.CountriesRepository
import com.project.weatherforecast.countriesscreen.data.CountryNameAPI
import com.project.weatherforecast.countriesscreen.domain.CountriesDomain
import com.project.weatherforecast.countriesscreen.flow.CountriesFlowController
import com.project.weatherforecast.countriesscreen.presentation.CountriesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.*

val countriesModule = Kodein.Module(name = "CountriesModule") {
    bind<CountryNameAPI>() with singleton { CountryNameAPI() }
    bind<CountriesRepository>() with singleton { CountriesRepository(instance()) }
    bind<CountriesDomain>() with singleton { CountriesDomain(instance()) }
    bind<CountriesViewModelFactory>() with provider { CountriesViewModelFactory(instance()) }
    bind() from factory { activity: Activity -> CountriesFlowController(activity) }
}