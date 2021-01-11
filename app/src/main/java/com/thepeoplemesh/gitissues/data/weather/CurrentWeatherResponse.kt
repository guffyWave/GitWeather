package com.thepeoplemesh.gitissues.data.weather


import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("current")
    val current: Current,
    @SerializedName("location")
    val location: Location,
    @SerializedName("request")
    val request: Request
)