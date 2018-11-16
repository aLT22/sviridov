package ru.netcracker.sviridov.ui.adapters.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_weather.view.*
import ru.netcracker.sviridov.R
import ru.netcracker.sviridov.api.MetaWeatherApi
import ru.netcracker.sviridov.api.model.ConsolidatedWeather
import ru.netcracker.sviridov.api.model.WeatherResponse

/**
 * Created by Turkin A. on 15/11/2018.
 */
class WeatherAdapter(
    private val context: Context
) : RecyclerView.Adapter<WeatherViewHolder>() {

    var items = ArrayList<ConsolidatedWeather>(5)
    var weatherResponse: WeatherResponse? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): WeatherViewHolder {
        return WeatherViewHolder(LayoutInflater.from(context).inflate(R.layout.item_weather, p0, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.city.text = weatherResponse?.title
        holder.coords.text = weatherResponse?.latLng
        holder.localTime.text = weatherResponse?.time
        holder.timezoneName.text = weatherResponse?.timezoneName

        holder.weatherStateName.text = items[position].weatherStateName
        holder.weatherStateAbbr.text = items[position].weatherStateAbbr
        setWeatherIcon(holder, items[position].weatherStateAbbr ?: "")

        holder.windSpeed.text = items[position].windSpeed?.toInt().toString() + " mph"

        holder.tempMin.text = items[position].minTemp?.toInt().toString() + Html.fromHtml("&#8451;")
        holder.temp.text = items[position].theTemp?.toInt().toString() + Html.fromHtml("&#8451;")
        holder.tempMax.text = items[position].maxTemp?.toInt().toString() + Html.fromHtml("&#8451;")

        holder.airPressure.text = String.format("%.2f", items[position].airPressure)
        holder.humidity.text = items[position].humidity.toString()
        holder.visibility.text = Html.fromHtml("&#128065;").toString() +
                String.format("%.2f", items[position].visibility) + " miles"

        holder.source.text = weatherResponse?.sources!![0].title
    }

    companion object {
        const val TAG = "WeatherAdapter"
    }

    private fun setWeatherIcon(
        holder: WeatherViewHolder,
        weatherAbbreviation: String
    ) {
        when (weatherAbbreviation) {
            "sn" -> Picasso.get().load(MetaWeatherApi.URL_SNOW).into(holder.weatherIcon)
            "sl" -> Picasso.get().load(MetaWeatherApi.URL_SLEET).into(holder.weatherIcon)
            "h" -> Picasso.get().load(MetaWeatherApi.URL_HAIL).into(holder.weatherIcon)
            "t" -> Picasso.get().load(MetaWeatherApi.URL_THUNDERSTORM).into(holder.weatherIcon)
            "hr" -> Picasso.get().load(MetaWeatherApi.URL_HEAVY_RAIN).into(holder.weatherIcon)
            "lr" -> Picasso.get().load(MetaWeatherApi.URL_LIGHT_RAIN).into(holder.weatherIcon)
            "s" -> Picasso.get().load(MetaWeatherApi.URL_SHOWERS).into(holder.weatherIcon)
            "hc" -> Picasso.get().load(MetaWeatherApi.URL_HEAVY_CLOUD).into(holder.weatherIcon)
            "lc" -> Picasso.get().load(MetaWeatherApi.URL_LIGHT_CLOUD).into(holder.weatherIcon)
            "c" -> Picasso.get().load(MetaWeatherApi.URL_CLEAR).into(holder.weatherIcon)
            else -> Log.d(TAG, "Wrong abbreviation")
        }
    }

}

class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val container = itemView.container
    val city = itemView.city
    val coords = itemView.coords
    val localTime = itemView.localTime
    val timezoneName = itemView.timezoneName
    val weatherIcon = itemView.weatherIcon
    val weatherStateName = itemView.weatherStateName
    val weatherStateAbbr = itemView.weatherStateAbbr
    val windIcon = itemView.windIcon
    val windSpeed = itemView.windSpeed
    val tempMin = itemView.tempMin
    val temp = itemView.temp
    val tempMax = itemView.tempMax
    val airPressure = itemView.airPressure
    val humidity = itemView.humidity
    val visibility = itemView.findViewById<TextView>(R.id.visibility)
    val source = itemView.source
}