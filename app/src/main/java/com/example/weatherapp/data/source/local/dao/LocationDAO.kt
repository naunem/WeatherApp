package com.example.weatherapp.data.source.local.dao

import androidx.room.*
import com.example.weatherapp.data.source.model.Location

@Dao
interface LocationDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(location: Location)

    @Delete
    fun delete(location: Location)

    @Query("SELECT * FROM table_location")
    fun getAll(): List<Location>
}
