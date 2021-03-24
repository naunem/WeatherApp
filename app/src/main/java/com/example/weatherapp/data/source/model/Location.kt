package com.example.weatherapp.data.source.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_location")
data class Location(
    @PrimaryKey
    @SerializedName("woeid") val woeid: Int,
    @SerializedName("title") val title: String,
    @SerializedName("location_type") val locationType: String,
    @SerializedName("latt_long") val latLong: String,
    var isFavorite: Boolean = false
)
