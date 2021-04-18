package com.project.weatherforecast.forecastscreen.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.weatherforecast.databinding.RecyclerForecastBinding
import com.project.weatherforecast.forecastscreen.data.entities.Main
import java.text.DateFormatSymbols
import java.util.*

class ForecastRecyclerViewAdapter: RecyclerView.Adapter<ForecastRecyclerViewAdapter.ForecastViewHolder>() {
    private var forecast : MutableList<Main> = mutableListOf()
    val symbols: DateFormatSymbols = DateFormatSymbols()
    val days = symbols.weekdays
    val months = symbols.months
    fun setForecast(forecast: MutableList<Main>){
        this.forecast = forecast
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = RecyclerForecastBinding.inflate(inflate, parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val gregorianCalendar = GregorianCalendar()
        gregorianCalendar.add(Calendar.DATE, position)
        holder.binding.day.text = days.get(gregorianCalendar.get(Calendar.DAY_OF_WEEK)).subSequence(0, 4).toString() + ", " +
                                    gregorianCalendar.get(Calendar.DAY_OF_MONTH).toString() + " " +
                                    months.get(gregorianCalendar.get(Calendar.MONTH))
        holder.binding.maxTemp.text = forecast[position].temp_max.toString()
        holder.binding.minTemp.text = forecast[position].temp_min.toString().subSequence(0, 4).toString()
    }

    override fun getItemCount(): Int {
        return forecast.size
    }
    inner class ForecastViewHolder(
        val binding: RecyclerForecastBinding
    ) : RecyclerView.ViewHolder(binding.root){
    }
}

