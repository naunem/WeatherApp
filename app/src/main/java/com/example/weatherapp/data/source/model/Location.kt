package com.example.weatherapp.data.source.model

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("title") val title: String,
    @SerializedName("location_type") val locationType: String,
    @SerializedName("woeid") val woeid: Int,
    @SerializedName("latt_long") val latLong: String
)
