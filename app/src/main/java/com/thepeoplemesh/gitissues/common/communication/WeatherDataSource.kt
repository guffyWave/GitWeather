package com.thepeoplemesh.gitissues.common.communication

import androidx.lifecycle.LiveData
import com.thepeoplemesh.gitissues.data.db.network.response.CurrentWeatherResponse

interface WeatherDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String
    )

}