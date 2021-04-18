package com.project.weatherforecast.app

import android.app.Application
import com.project.weatherforecast.countriesscreen.di.countriesModule
import com.project.weatherforecast.forecastscreen.di.forecastModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class MyApp : Application(), KodeinAware {
    override val kodein: Kodein = Kodein {
        import(androidXModule(this@MyApp))
        import(countriesModule)
        import(forecastModule)
    }
}