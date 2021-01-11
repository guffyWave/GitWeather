package com.thepeoplemesh.gitissues.common.communication

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.thepeoplemesh.gitissues.data.db.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "769599463154e26bf1d67d9b5fcb22e1"

//endpoint http://api.weatherstack.com/current?access_key=769599463154e26bf1d67d9b5fcb22e1&query=New%20Delhi
interface WeatherService {

    @GET("current")
    fun getCurrentWeather(
        @Query("query") location: String
    ): Deferred<CurrentWeatherResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): WeatherService {
            val requestInterceptor = Interceptor { chain ->
                var url =
                    chain.request().url().newBuilder().addQueryParameter(
                        "access_key",
                        API_KEY
                    )
                        .build()
                val request = chain.request().newBuilder().url(url).build()
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder().client(okHttpClient).baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherService::class.java)
        }
    }
}