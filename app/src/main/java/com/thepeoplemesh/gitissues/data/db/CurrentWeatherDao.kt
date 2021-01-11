package com.thepeoplemesh.gitissues.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thepeoplemesh.gitissues.data.db.entity.CURRENT_WEATHER_ID
import com.thepeoplemesh.gitissues.data.db.entity.CurrentWeather

@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currentWeather: CurrentWeather)

    @Query("select * from current_weather where id=$CURRENT_WEATHER_ID")
    fun getCurrentWeather(): LiveData<CurrentWeather>
}