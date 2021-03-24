package com.example.weatherapp.data.repository

import com.example.weatherapp.data.source.ResultWrapper
import com.example.weatherapp.data.source.datasource.IDataSource
import com.example.weatherapp.data.source.model.Location
import com.example.weatherapp.data.source.remote.RemoteDataSource
import javax.inject.Inject

class AppRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    IDataSource {
    override suspend fun searchLocationByName(query: String): ResultWrapper<List<Location>> =
        remoteDataSource.searchLocationByName(query)
}
