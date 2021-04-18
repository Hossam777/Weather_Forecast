package com.project.weatherforecast.forecastscreen.presentation

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.weatherforecast.R
import com.project.weatherforecast.countriesscreen.flow.CountriesFlowController
import com.project.weatherforecast.databinding.ActivityForecastBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class ForecastActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val forecastViewModelFactory: ForecastViewModelFactory by instance()
    private val flowController: CountriesFlowController by instance(arg = this)

    private lateinit var viewModel: ForecastViewModel
    private lateinit var binding: ActivityForecastBinding
    private var forecastAdapter = ForecastRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forecast)

        viewModel = ViewModelProvider(this, forecastViewModelFactory).get(ForecastViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        binding.forecastRecycler.layoutManager = LinearLayoutManager(this)
        binding.forecastRecycler.adapter = forecastAdapter

        viewModel.capital.postValue(intent.getStringExtra("capital").toString())
        subscribeOnData()
        subscribeOnError()
        subscribeOnCapital()
    }

    private fun subscribeOnCapital() {
        viewModel.capital.observe(this, {
            viewModel.fetchForecast()
        })
    }

    private fun subscribeOnError() {
        viewModel.onError.observe(this, {
            Toast.makeText(this, "Error: " + it, Toast.LENGTH_SHORT).show()
            finish()
        })
    }

    private fun subscribeOnData() {
        viewModel.forecast.observe(this,
            {
                forecastAdapter.setForecast(it)
            })
    }
}