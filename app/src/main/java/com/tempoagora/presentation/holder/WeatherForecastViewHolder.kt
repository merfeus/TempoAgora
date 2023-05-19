package com.tempoagora.presentation.holder

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.tempoagora.R
import com.tempoagora.databinding.ItemWeatherForecastBinding
import com.tempoagora.domain.entity.DataForecastComponent
import com.tempoagora.presentation.extensions.convertDateFormat
import com.tempoagora.presentation.extensions.convertMsToKmh

internal class WeatherForecastViewHolder(
    private val binding: ItemWeatherForecastBinding
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(item: DataForecastComponent) = with(binding) {
        val context = itemView.context

        tvMaxTemp.text = "${item.maxTemp.toInt()}${context.getString(R.string.degree)}"
        tvMinTemp.text = "${item.minTemp.toInt()}${context.getString(R.string.degree)}"
        tvDay.text = item.datetime.convertDateFormat("yyyy-MM-dd", "dd/MM")
        tvSpeedWind.text = "${item.windSpd.convertMsToKmh()} ${context.getString(R.string.km)}"
    }
}