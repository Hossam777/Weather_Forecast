package com.project.weatherforecast.countriesscreen.flow

import android.app.Activity
import android.content.Intent
import com.project.weatherforecast.forecastscreen.presentation.ForecastActivity

open class CountriesFlowController(var activity: Activity)
    : CountriesFlow {
    override fun openCountryDetails(countryCapital: String) {
        val i = Intent(activity, ForecastActivity::class.java).apply {
            putExtra("capital", countryCapital)
        }
        activity.startActivity(i)
    }
}