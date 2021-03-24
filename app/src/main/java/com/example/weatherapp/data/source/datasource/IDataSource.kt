package com.example.weatherapp.data.source.datasource

import com.example.weatherapp.data.source.ResultWrapper
import com.example.weatherapp.data.source.model.Location

interface IDataSource {
    suspend fun searchLocationByName(query: String): ResultWrapper<List<Location>>
}
