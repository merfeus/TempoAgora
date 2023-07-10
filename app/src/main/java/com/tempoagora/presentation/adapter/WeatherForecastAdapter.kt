package com.tempoagora.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.tempoagora.databinding.ItemWeatherForecastBinding
import com.tempoagora.domain.entity.DataForecastComponent
import com.tempoagora.presentation.holder.WeatherForecastViewHolder

internal class WeatherForecastAdapter() :
    ListAdapter<DataForecastComponent, WeatherForecastViewHolder>(DefaultDiffCallback<DataForecastComponent>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastViewHolder {
        return WeatherForecastViewHolder(
            ItemWeatherForecastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}