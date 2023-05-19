package com.tempoagora.presentation.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.tempoagora.R
import com.tempoagora.databinding.FragmentClimateBinding
import com.tempoagora.domain.entity.DataComponent
import com.tempoagora.domain.entity.DataForecastComponent
import com.tempoagora.domain.entity.WeatherComponent
import com.tempoagora.domain.entity.WeatherLocalComponent
import com.tempoagora.presentation.adapter.WeatherForecastAdapter
import com.tempoagora.presentation.viewmodel.MainViewAction
import com.tempoagora.presentation.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClimateFragment : Fragment(R.layout.fragment_climate) {
    companion object {
        private const val REQUEST_LOCATION_PERMISSIONS = 0
    }

    private val weatherViewModel: WeatherViewModel by viewModel()
    private lateinit var binding: FragmentClimateBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var adapter: WeatherForecastAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentClimateBinding.bind(view)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fetchLocation()
        lifecycleScope.launch {
            weatherViewModel.state.collect { state ->
                handleState(state)
            }
        }
        setupRecyclerView()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocation()
            } else {
                Toast.makeText(requireContext(), "Permissão Negada", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun fetchLocation() {
        if (isLocationPermissionGranted()) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    weatherViewModel.getWeather(latitude, longitude, include = "minutely")
                    weatherViewModel.getWeatherForecast(latitude, longitude)
                }
            }
        } else {
            requestLocationPermissions()
        }
    }

    private fun setupRecyclerView() {
        adapter = WeatherForecastAdapter()
        binding.rvForecast.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvForecast.adapter = adapter

    }

    private fun requestLocationPermissions() {
        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            REQUEST_LOCATION_PERMISSIONS
        )
    }

    private fun isLocationPermissionGranted(): Boolean {
        return (ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun handleState(state: MainViewAction) {
        when (state) {
            is MainViewAction.LoadingState -> {
                binding.progressCircular.isVisible = state.isLoading
                binding.scrollView2.isVisible = state.isLoading.not()
            }

            is MainViewAction.ErrorScreen -> {
                Toast.makeText(requireContext(), "Erro no Servidor", Toast.LENGTH_SHORT)
                    .show()
                binding.scrollView2.isVisible = false
            }

            is MainViewAction.Weather -> {
                setupComponents(state.list)
                setupBackgroundColor(state.list.data[0].weather)
            }

            is MainViewAction.DeniedPermission -> {
                Toast.makeText(
                    requireContext(),
                    "Permissão Para acessar logalização negada",
                    Toast.LENGTH_SHORT
                ).show()
            }

            is MainViewAction.WeatherForecast -> {
                setupForecast(state.list)
            }

            else -> {

            }
        }
    }

    private fun setupForecast(list: List<DataForecastComponent>) {
        adapter?.submitList(list)
    }

    private fun setupComponents(list: WeatherComponent) {
        list.let {
            it.data.map { data ->
                binding.cityName.text = data.cityName
                binding.tvWeather.text = data.temp.toInt().toString() + "°C"
                binding.tvWeatherStatus.text = data.weather.description
                binding.btnIqa.text = "IQA " + data.aqi.toString()
                binding.includeSunrise.tvSunrise.text = "Nascer do Sol: " + data.sunrise
                binding.includeSunrise.tvSunset.text = "Por do Sol: " + data.sunset

            }
        }
    }

    private fun setupBackgroundColor(temp: WeatherLocalComponent?) = with(binding) {
        temp?.let {
            when (temp.code) {
                in 200..201, 202 -> {
                    constraintMain.setBackgroundResource(R.drawable.gradient_200)
                    setupStatusBarColor(R.color.sky_200)
                    animation.setAnimation(R.raw.rain)
                }

                in 230..231, 233 -> {
                    constraintMain.setBackgroundResource(R.drawable.gradient_230)
                    setupStatusBarColor(R.color.sky_230)
                    animation.setAnimation(R.raw.rain)
                }

                in 300..301, 302 -> {
                    constraintMain.setBackgroundResource(R.drawable.gradient_300)
                    setupStatusBarColor(R.color.sky_300)
                    animation.setAnimation(R.raw.rain)
                }

                in 500..501, 522 -> {
                    constraintMain.setBackgroundResource(R.drawable.gradient_500)
                    setupStatusBarColor(R.color.sky_500)
                    animation.setAnimation(R.raw.rain)
                }

                in 600..601, 610 -> {
                    constraintMain.setBackgroundResource(R.drawable.gradient_600)
                    setupStatusBarColor(R.color.sky_600)
                }

                in 611..612, 751 -> {
                    constraintMain.setBackgroundResource(R.drawable.gradient_611)
                    setupStatusBarColor(R.color.sky_611)
                }

                800 -> {
                    constraintMain.setBackgroundResource(R.drawable.gradient_blue_orenge)
                    setupStatusBarColor(R.color.sky_800)
                }

                in 801..802 -> {
                    constraintMain.setBackgroundResource(R.drawable.gradient_801)
                    setupStatusBarColor(R.color.sky_801)
                    animation.setAnimation(R.raw.clouds)
                }

                803 -> {
                    constraintMain.setBackgroundResource(R.drawable.gradient_801)
                    setupStatusBarColor(R.color.sky_803)
                    animation.setAnimation(R.raw.clouds)
                }

                804 -> {
                    constraintMain.setBackgroundResource(R.drawable.gradient_804)
                    setupStatusBarColor(R.color.sky_804)
                    animation.setAnimation(R.raw.clouds)
                }

                900 -> {
                    constraintMain.setBackgroundResource(R.drawable.gradient_blue_blue)
                    setupStatusBarColor(R.color.blue)
                }

                else -> {

                }

            }
        }
    }
//    private fun setupBackgroundColor(temp: WeatherLocalComponent?) = with(binding) {
//        temp?.let {
//            val drawableResId = when (temp.code) {
//                in 200..202 -> {
//                    setupStatusBarColor(R.color.sky_200)
//                    R.drawable.gradient_200
//                    animation.setAnimation(R.raw.rain)
//                }
//
//                in 230..231, 233 -> {
//                    setupStatusBarColor(R.color.sky_230)
//                    R.drawable.gradient_230
//                    animation.setAnimation(R.raw.rain)
//                }
//
//                in 300..302 -> {
//                    setupStatusBarColor(R.color.sky_300)
//                    R.drawable.gradient_300
//                    animation.setAnimation(R.raw.rain)
//                }
//
//                in 500..501, 522 -> {
//                    setupStatusBarColor(R.color.sky_500)
//                    R.drawable.gradient_500
//                }
//
//                in 600..601, 610 -> {
//                    setupStatusBarColor(R.color.sky_600)
//                    R.drawable.gradient_600
//                }
//
//                in 611..612, 751 -> {
//                    setupStatusBarColor(R.color.sky_611)
//                    R.drawable.gradient_611
//                }
//
//                800 -> {
//                    setupStatusBarColor(R.color.sky_800)
//                    R.drawable.gradient_blue_orenge
//                    animation.setAnimation(R.raw.sun)
//
//                }
//
//                in 801..802 -> {
//                    setupStatusBarColor(R.color.sky_801)
//                    R.drawable.gradient_801
//                    animation.setAnimation(R.raw.clouds)
//                }
//
//                803 -> {
//                    setupStatusBarColor(R.color.sky_803)
//                    R.drawable.gradient_801
//                    animation.setAnimation(R.raw.clouds)
//                }
//
//                804 -> {
//                    setupStatusBarColor(R.color.sky_804)
//                    R.drawable.gradient_804
//                    animation.setAnimation(R.raw.clouds)
//                }
//
//                900 -> {
//                    setupStatusBarColor(R.color.blue)
//                    R.drawable.gradient_blue_blue
//                }
//
//                else -> null
//            }
//
//            drawableResId?.let {
//                constraintMain.setBackgroundResource(drawableResId)
//            }
//        }
//    }

    private fun setupStatusBarColor(color: Int) {
        activity?.window?.statusBarColor = resources.getColor(color)
    }
}