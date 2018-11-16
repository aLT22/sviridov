package ru.netcracker.sviridov.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Turkin A. on 15/11/2018.
 */
data class Source(
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("slug")
    @Expose
    val slug: String?,
    @SerializedName("url")
    @Expose
    val url: String?,
    @SerializedName("crawl_rate")
    @Expose
    val crawlRate: Int?
)

data class Parent(
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("location_type")
    @Expose
    val locationType: String?,
    @SerializedName("woeid")
    @Expose
    val woeid: Long?,
    @SerializedName("latt_long")
    @Expose
    val latLng: String?
)

data class ConsolidatedWeather(
    @SerializedName("id")
    @Expose
    val id: Long?,
    @SerializedName("weather_state_name")
    @Expose
    val weatherStateName: String?,
    @SerializedName("weather_state_abbr")
    @Expose
    val weatherStateAbbr: String?,
    @SerializedName("wind_direction_compass")
    @Expose
    val windDirectionCompass: String?,
    @SerializedName("created")
    @Expose
    val created: String?,
    @SerializedName("applicable_date")
    @Expose
    val applicableDate: String?,
    @SerializedName("min_temp")
    @Expose
    val minTemp: Double?,
    @SerializedName("max_temp")
    @Expose
    val maxTemp: Double?,
    @SerializedName("the_temp")
    @Expose
    val theTemp: Double?,
    @SerializedName("wind_speed")
    @Expose
    val windSpeed: Double?,
    @SerializedName("wind_direction")
    @Expose
    val windDirection: Double?,
    @SerializedName("air_pressure")
    @Expose
    val airPressure: Double?,
    @SerializedName("humidity")
    @Expose
    val humidity: Int?,
    @SerializedName("visibility")
    @Expose
    val visibility: Double?,
    @SerializedName("predictability")
    @Expose
    val predictability: Int?
)

data class WeatherResponse(
    @SerializedName("consolidated_weather")
    @Expose
    val weather: List<ConsolidatedWeather>?,
    @SerializedName("time")
    @Expose
    val time: String?,
    @SerializedName("sun_rise")
    @Expose
    val sunRise: String?,
    @SerializedName("sun_set")
    @Expose
    val sunSet: String?,
    @SerializedName("timezone_name")
    @Expose
    val timezoneName: String?,
    @SerializedName("parent")
    @Expose
    val parent: Parent?,
    @SerializedName("sources")
    @Expose
    val sources: List<Source>?,
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("location_type")
    @Expose
    val locationType: String?,
    @SerializedName("woeid")
    @Expose
    val woeid: Long?,
    @SerializedName("latt_long")
    @Expose
    val latLng: String?,
    @SerializedName("timezone")
    @Expose
    val timezone: String?
)