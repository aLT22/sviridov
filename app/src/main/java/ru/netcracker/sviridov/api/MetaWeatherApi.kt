package ru.netcracker.sviridov.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.netcracker.sviridov.api.model.CityResponse
import ru.netcracker.sviridov.api.model.WeatherResponse

/**
 * Created by Turkin A. on 13/11/2018.
 */
const val BASE_URL = "https://www.metaweather.com"

interface MetaWeatherApi {

    @GET("/api/location/search/")
    fun getCities(@Query("query") searchQuery: String): Observable<List<CityResponse>>

    @GET("/api/location/{woeid}/")
    fun getWeather(@Path("woeid") woeID: Long = 44418L): Observable<WeatherResponse>

    companion object {

        const val URL_SNOW = "$BASE_URL/static/img/weather/png/sn.png"
        const val URL_SLEET = "$BASE_URL/static/img/weather/png/sl.png"
        const val URL_HAIL = "$BASE_URL/static/img/weather/png/h.png"
        const val URL_THUNDERSTORM = "$BASE_URL/static/img/weather/png/t.png"
        const val URL_HEAVY_RAIN = "$BASE_URL/static/img/weather/png/hr.png"
        const val URL_LIGHT_RAIN = "$BASE_URL/static/img/weather/png/lr.png"
        const val URL_SHOWERS = "$BASE_URL/static/img/weather/png/s.png"
        const val URL_HEAVY_CLOUD = "$BASE_URL/static/img/weather/png/hc.png"
        const val URL_LIGHT_CLOUD = "$BASE_URL/static/img/weather/png/lc.png"
        const val URL_CLEAR = "$BASE_URL/static/img/weather/png/c.png"

    }

}