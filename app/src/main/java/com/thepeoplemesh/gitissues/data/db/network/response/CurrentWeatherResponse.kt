package com.thepeoplemesh.gitissues.data.db.network.response


import com.google.gson.annotations.SerializedName
import com.thepeoplemesh.gitissues.data.db.entity.CurrentWeather
import com.thepeoplemesh.gitissues.data.db.entity.Location
import com.thepeoplemesh.gitissues.data.db.entity.Request

data class CurrentWeatherResponse(
    @SerializedName("current")
    val current: CurrentWeather,
    @SerializedName("location")
    val location: Location,
    @SerializedName("request")
    val request: Request
)