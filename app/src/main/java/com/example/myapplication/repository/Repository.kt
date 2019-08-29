package com.example.myapplication.repository

import android.util.Log
import com.example.myapplication.WeatherAPIService
import com.example.myapplication.data.Model
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repository {
    val appid ="492001dae2f929087a4383812c861c79"
    val responsePublishSubject = PublishSubject.create<Model.Weather>()

    fun getWeather(cityName: String) {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build()

        val weatherAPIService = retrofit.create(WeatherAPIService::class.java)
        weatherAPIService.getWeatherForCity(cityName, appid).enqueue(object : Callback<Model.Weather> {
            override fun onFailure(call: Call<Model.Weather>, t: Throwable) {
                Log.e("##", t.toString())
            }

            override fun onResponse(call: Call<Model.Weather>, response: Response<Model.Weather>) {
                Log.e("###", response.body().toString())
                responsePublishSubject.onNext(response.body())
            }

        })

    }


    fun observeResponse():Observable<Model.Weather>{
        return responsePublishSubject;
    }

}