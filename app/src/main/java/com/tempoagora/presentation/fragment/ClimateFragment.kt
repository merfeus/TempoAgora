package com.tempoagora.presentation.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.tempoagora.R
import com.tempoagora.databinding.FragmentClimateBinding
import com.tempoagora.domain.entity.WeatherComponent
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentClimateBinding.bind(view)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fetchLocation()
        initViewModel()
        lifecycleScope.launch {
            weatherViewModel.state.collect { state ->
                handleState(state)
            }
        }
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
                Toast.makeText(requireContext(), "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", Toast.LENGTH_SHORT)
                    .show()
            }
        }
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
    @SuppressLint("MissingPermission")
    private fun fetchLocation() {
        if (isLocationPermissionGranted()) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    weatherViewModel.getWeather(latitude, longitude, include = "minutely")
                }
            }
        } else {
            requestLocationPermissions()
        }
    }

    private fun handleState(state: MainViewAction) {
        when (state) {
            is MainViewAction.LoadingState -> {
                binding.progressCircular.isVisible = state.isLoading
            }

            is MainViewAction.ErrorScreen -> {
                Toast.makeText(requireContext(), "Erro no Servidor", Toast.LENGTH_SHORT)
                    .show()
            }

            is MainViewAction.Weather -> {
                setupComponents(state.list)
            }

            is MainViewAction.DeniedPermission -> {
                Toast.makeText(
                    requireContext(),
                    "Permissão Para acessar logalização negada",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

            else -> {

            }
        }
    }

    private fun setupComponents(list: WeatherComponent) {
        list.let {
            it.data.map { data ->
                binding.cityName.text = data.cityName
                binding.tvWeather.text = data.temp.toString() + "°C"
                binding.tvWeatherStatus.text = data.weather.description
                binding.btnIqa.text = data.aqi.toString()

            }
        }
    }

    private fun initViewModel() {
        weatherViewModel.initLocationClient(requireContext())
    }
}