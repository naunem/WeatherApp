package com.example.weatherapp.data.repository

import com.example.weatherapp.data.source.local.dao.LocationDAO
import com.example.weatherapp.data.source.model.Location
import javax.inject.Inject

class LocalRepository @Inject constructor() {
    @Inject lateinit var locationDAO : LocationDAO

    internal fun saveLocation(location: Location) {
        locationDAO.insert(location)
    }

    internal fun removeLocation(location: Location) {
        locationDAO.delete(location)
    }

    internal fun getAllLocation(): List<Location> {
        return locationDAO.getAll()
    }
}
