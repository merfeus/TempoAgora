package com.tempoagora.presentation.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.tempoagora.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class WeatherViewModel(
    private val useCase: GetWeatherUseCase,
    private val context: Context,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val mutableState = MutableStateFlow<MainViewAction>(MainViewAction.NavigateToInsert)
    val state = mutableState.asStateFlow()

    private val _actions: MutableLiveData<MainViewAction> = MutableLiveData()
    val actions: LiveData<MainViewAction> = _actions

    fun getWeather(latitude: Double, longitude: Double, include: String) {
        viewModelScope.launch {
            useCase(latitude, longitude, include)
                .flowOn(dispatcher)
                .onStart {
                    mutableState.value = MainViewAction.LoadingState(true)
                }
                .catch {
                    println(it.message)
                    mutableState.value = MainViewAction.ErrorScreen(true)
                }
                .onCompletion {
                    mutableState.value = MainViewAction.LoadingState(false)
                }
                .collect {
                    mutableState.value = MainViewAction.Weather(it)
                }
        }
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    fun initLocationClient(context: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }
}