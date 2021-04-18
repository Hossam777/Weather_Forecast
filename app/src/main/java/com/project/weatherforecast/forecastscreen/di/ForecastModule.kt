package com.project.weatherforecast.forecastscreen.di

import android.app.Activity
import com.project.weatherforecast.forecastscreen.data.ForecastAPI
import com.project.weatherforecast.forecastscreen.data.ForecastRepository
import com.project.weatherforecast.forecastscreen.domain.ForecastDomain
import com.project.weatherforecast.forecastscreen.flow.ForecastFlowController
import com.project.weatherforecast.forecastscreen.presentation.ForecastViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.*

val forecastModule = Kodein.Module(name="ForecastModule"){
    bind<ForecastAPI>() with singleton { ForecastAPI() }
    bind<ForecastRepository>() with singleton { ForecastRepository(instance()) }
    bind<ForecastDomain>() with singleton { ForecastDomain(instance()) }
    bind<ForecastViewModelFactory>() with provider { ForecastViewModelFactory(instance()) }
    bind() from factory { activity: Activity -> ForecastFlowController(activity) }
}