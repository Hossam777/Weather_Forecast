package com.project.weatherforecast.countriesscreen.data

import com.project.weatherforecast.countriesscreen.data.entities.Names
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryNameAPI {

    @GET("name/{name}?fullText=true&fields=name;flag;capital")
    fun loadCountries(
        @Path("name") name: String
    ) : Single<Names>

    companion object{
        operator fun invoke(): CountryNameAPI{
            return Retrofit.Builder()
                .baseUrl("https://restcountries.eu/rest/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(CountryNameAPI::class.java)
        }
    }
}