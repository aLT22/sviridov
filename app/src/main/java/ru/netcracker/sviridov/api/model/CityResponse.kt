package ru.netcracker.sviridov.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Turkin A. on 14/11/2018.
 */
data class CityResponse(
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