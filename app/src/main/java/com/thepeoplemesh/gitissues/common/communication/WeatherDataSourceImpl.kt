package com.thepeoplemesh.gitissues.common.communication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thepeoplemesh.gitissues.common.utils.TAG_CONNECTIVITY
import com.thepeoplemesh.gitissues.data.db.network.response.CurrentWeatherResponse

class WeatherDataSourceImpl(
    private val weatherService: WeatherService
) : WeatherDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()

    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String) {
        try {
            val fetchCurrentWeather = weatherService.getCurrentWeather(location).await()
            _downloadedCurrentWeather.postValue(fetchCurrentWeather)
        } catch (nce: NoConnectivityException) {
            Log.e(TAG_CONNECTIVITY, "No internet connection.", nce)
        }
    }
}