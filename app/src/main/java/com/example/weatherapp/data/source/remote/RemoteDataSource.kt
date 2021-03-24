package com.example.weatherapp.data.source.remote

import com.example.weatherapp.data.service.ApiService
import com.example.weatherapp.data.source.ResultWrapper
import com.example.weatherapp.data.source.datasource.IDataSource
import com.example.weatherapp.data.source.model.Location
import com.example.weatherapp.data.source.remote.network.SafeApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) : IDataSource,
    SafeApi() {
    override suspend fun searchLocationByName(query: String): ResultWrapper<List<Location>> =
        safeApiCall {
            apiService.searchByName(query)
        }
}
