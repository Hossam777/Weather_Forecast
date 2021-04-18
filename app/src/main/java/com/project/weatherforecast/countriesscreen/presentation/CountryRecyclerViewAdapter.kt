package com.project.weatherforecast.countriesscreen.presentation

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.project.weatherforecast.countriesscreen.data.entities.Names
import com.project.weatherforecast.databinding.RecyclerCountriesBinding

class CountryRecyclerViewAdapter(private val activity:Activity, val onItemClicked:(String)->Unit): RecyclerView.Adapter<CountryRecyclerViewAdapter.CountriesViewHolder>() {
    private var countries : Names = Names()
    fun setCountries(countries : Names){
        this.countries = countries
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = RecyclerCountriesBinding.inflate(inflate, parent, false)
        return CountriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.binding.countryName.text = countries[position].name
        GlideToVectorYou.justLoadImage(activity, Uri.parse(countries[position].flag), holder.binding.countryFlag)
        holder.binding.root.setOnClickListener(View.OnClickListener {
            onItemClicked(countries[position].capital)
        })
    }

    override fun getItemCount(): Int {
        return countries.size
    }
    inner class CountriesViewHolder(
        val binding: RecyclerCountriesBinding
    ) : RecyclerView.ViewHolder(binding.root){
    }
}