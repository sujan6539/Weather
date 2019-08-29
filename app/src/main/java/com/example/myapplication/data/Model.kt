package com.example.myapplication.data

object Model {

    data class Weather(

        var coord: CoordBean? = null,
        var base: String? = null,
        var main: MainBean? = null,
        var visibility: Int = 0,
        var wind: WindBean? = null,
        var clouds: CloudsBean? = null,
        var dt: Int = 0,
        var sys: SysBean? = null,
        var timezone: Int = 0,
        var id: Int = 0,
        var name: String? = null,
        var cod: Int = 0,
        var weather: List<WeatherBean>? = null
    )


    class CoordBean(

        var lon: Double = 0.toDouble(),
        var lat: Double = 0.toDouble()
    )

    class MainBean(

        var temp: Double = 0.toDouble(),
        var pressure: Int = 0,
        var humidity: Int = 0,
        var temp_min: Double = 0.toDouble(),
        var temp_max: Double = 0.toDouble()
    )

    class WindBean(

        var speed: Double = 0.toDouble(),
        var deg: Int = 0
    )

    class CloudsBean(

        var all: Int = 0
    )

    class SysBean(

        var type: Int = 0,
        var id: Int = 0,
        var message: Double?,
        var country: String? = null,
        var sunrise: Int = 0,
        var sunset: Int = 0
    )

    data class WeatherBean(

        var id: Int = 0,
        var main: String? = null,
        var description: String? = null,
        var icon: String? = null
    )
}

