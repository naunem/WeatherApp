package com.example.weatherapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.data.source.local.dao.LocationDAO
import com.example.weatherapp.data.source.model.Location

@Database(entities = [Location::class], version = 1, exportSchema = false)
abstract class LocalDB : RoomDatabase() {
    abstract fun locationDAO(): LocationDAO
}
