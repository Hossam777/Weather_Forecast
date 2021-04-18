package com.project.weatherforecast.countriesscreen.data.entities

class NetworkError(var error: String)
    : Throwable(error) {

}