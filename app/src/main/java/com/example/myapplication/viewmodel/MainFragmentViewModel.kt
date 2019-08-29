package com.example.myapplication.viewmodel

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.myapplication.BR
import com.example.myapplication.Callback
import com.example.myapplication.data.Model
import com.example.myapplication.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers

class MainFragmentViewModel : BaseObservable() {

    init {
        Repository.observeResponse().subscribeOn(AndroidSchedulers.mainThread()).subscribe { weather: Model.Weather? ->
            if (weather != null) {
                cityName = "${weather.name}, ${weather.sys!!.country}"
                temperature = weather.main?.temp.toString()
            }
        }
    }

    @Bindable
    var temperature:String? = null
    set(value) {
        field = value
        notifyPropertyChanged(BR.temperature)
    }

    @Bindable
    var cityName:String? = null
    set(value) {
        field = value
        notifyPropertyChanged(BR.cityName)
    }

    @Bindable
    var text:String = ""
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.text)
        }

    var buttonClicked = object: Callback {
        override fun onButtonClicked() {
           Log.d("..","Button Clicked   "+text)
            Repository.getWeather(text)
        }
    }


}