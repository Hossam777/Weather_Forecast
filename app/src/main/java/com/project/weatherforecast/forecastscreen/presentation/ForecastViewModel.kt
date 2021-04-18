package com.project.weatherforecast.forecastscreen.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.weatherforecast.forecastscreen.data.entities.Main
import com.project.weatherforecast.forecastscreen.domain.ForecastDomain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ForecastViewModel (private val forecastDomain: ForecastDomain): ViewModel() {
    var capital:MutableLiveData<String> = MutableLiveData()
    var isLoading:MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value=false }
    var forecast:MutableLiveData<MutableList<Main>> = MutableLiveData()
    var onError:MutableLiveData<String> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun fetchForecast(){
        if(capital.value != null){
            isLoading.postValue(true)
            forecastDomain.fetchForecast(capital.value!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    forecast.postValue(it)
                    isLoading.postValue(false)
                }, {
                    onError.postValue(it.localizedMessage)
                    isLoading.postValue(false)
                })
        }
    }
}