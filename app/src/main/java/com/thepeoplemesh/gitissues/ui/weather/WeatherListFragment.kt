package com.thepeoplemesh.gitissues.ui.weather

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thepeoplemesh.gitissues.R
import com.thepeoplemesh.gitissues.data.WeatherService
import kotlinx.android.synthetic.main.weather_list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherListFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherListFragment()
    }

    private lateinit var viewModel: WeatherListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeatherListViewModel::class.java)
        // TODO: Use the ViewModel

        val apiService = WeatherService()

        GlobalScope.launch(Dispatchers.Main) {
            val currentWeatherResponse = apiService.getCurrentWeather("Shimla").await()//IO Thread
            weather_text_view.text = currentWeatherResponse.current.toString()
        }
    }

}