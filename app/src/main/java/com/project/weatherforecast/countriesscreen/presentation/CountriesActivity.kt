package com.project.weatherforecast.countriesscreen.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.weatherforecast.R
import com.project.weatherforecast.countriesscreen.data.entities.Names
import com.project.weatherforecast.countriesscreen.flow.CountriesFlowController
import com.project.weatherforecast.databinding.ActivityCountriesBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class CountriesActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val viewModelFactory: CountriesViewModelFactory by instance()
    private val flowController: CountriesFlowController by instance(arg = this)

    private lateinit var viewModel: CountriesViewModel
    private lateinit var binding: ActivityCountriesBinding
    private var countriesAdapter = CountryRecyclerViewAdapter(this) {
        flowController.openCountryDetails(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_countries)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CountriesViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        binding.countryRecycler.layoutManager = LinearLayoutManager(this)
        binding.countryRecycler.adapter = countriesAdapter

        subscribeOnData()
        subscribeOnError()
    }

    fun searchCountries(view: View) {
        viewModel.searchForCountries()
    }
    private fun subscribeOnData(){
        viewModel.countriesData.observe(this,
            { t ->
                if(t != null)
                    countriesAdapter.setCountries(t)
            })
    }
    private fun subscribeOnError(){
        viewModel.onError.observe(this,
            { t ->
                if (t != null) {
                    Toast.makeText(this, "Error: " + t, Toast.LENGTH_SHORT).show()
                    countriesAdapter.setCountries(Names())
                }
            })
    }


}