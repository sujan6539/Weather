package com.example.myapplication

import retrofit2.http.GET
import com.example.myapplication.data.Model
import retrofit2.Call
import retrofit2.http.Query

interface WeatherAPIService {

    @GET("weather")
    fun getWeatherForCity(@Query("q") cityname: String, @Query("APPID") appid:String): Call<Model.Weather>
}