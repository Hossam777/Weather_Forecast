package com.project.weatherforecast.countriesscreen.presentation

import android.annotation.SuppressLint
import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.weatherforecast.countriesscreen.data.entities.Names
import com.project.weatherforecast.countriesscreen.domain.CountriesDomain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.bindings.InstanceBinding

class CountriesViewModel(private val countriesDomain: CountriesDomain) : ViewModel() {
    var countryName: MutableLiveData<String> = MutableLiveData()
    var isLoading:MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value=false }
    var countriesData:MutableLiveData<Names> = MutableLiveData()
    var onError:MutableLiveData<String> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun searchForCountries() {
        if (countryName.value != null){
            isLoading.postValue(true)
            countriesDomain.loadCountries(countryName.value!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isLoading.postValue(false)
                    countriesData.postValue(it)
                }, {
                    isLoading.postValue(false)
                    onError.postValue(it.localizedMessage)
                })
        }
    }
}