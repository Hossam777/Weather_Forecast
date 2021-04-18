package com.project.weatherforecast.forecastscreen.data.entities

data class ForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ListItem>,
    val message: Int
)