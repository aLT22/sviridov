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

    /*@GET("/api/location/search/")
    fun getCities(@Query("query") searchQuery: String): Call<List<CityResponse>>*/

}