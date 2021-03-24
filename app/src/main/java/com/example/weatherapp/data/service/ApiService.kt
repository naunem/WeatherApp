package com.example.weatherapp.data.service

import com.example.weatherapp.data.source.model.Location
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/location/search/")
    suspend fun searchByName(@Query("query") query: String): List<Location>
}
